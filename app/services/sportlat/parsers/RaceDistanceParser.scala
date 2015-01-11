package services.sportlat.parsers

import models.sportlat.id.{Total, InGroup, EvaluationType, RaceDistanceId}
import models.sportlat.{RaceDistance, RaceResult, Race}
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import utils.text.TextUtils
import scala.collection.JavaConversions._

object RaceDistanceParser {

  def parse(html: String): RaceDistance = {
    val doc = Jsoup.parse(html)

    val currentDistanceLink = doc.select("center a[style=font-weight:bold;]").first

    def parseDistanceId: Option[Long] = {
      val href = currentDistanceLink.attr("href")

      val pattern = "dist=(\\d+)".r("distanceId")
      pattern.findFirstMatchIn(href) match {
        case Some(m) => {
          val id = m.group("distanceId").toInt
          Some(id)
        }
        case None => None
      }
    }

    def parseName = currentDistanceLink.ownText

    def parseResults: Seq[RaceResult] = {
      val trs = doc.select("table tr").toList.drop(1)
      trs.map {
        case tr => {

          def parseAthleteId: Option[Long] = {
            val nameTd = tr.child(2)

            val maybeAthleteId = nameTd.select("a").attr("href").replace("http://www.sportlat.lv/index.php?id=user&uid=", "")
            TextUtils.toOption(maybeAthleteId).map(_.toLong)
          }
          RaceResult(athleteId = parseAthleteId)
        }
      }
    }

    def parseOtherRaceDistances: Seq[RaceDistanceId] = {
      def parseName(link: Element) = link.ownText().replaceAll(" (kopvērtējums)", "").replace(" (pa grupām)", "")

      def parseEvaluationType(group: String): EvaluationType = group match {
        case g if g == "group" => InGroup()
        case g if g == "total" => Total()
        case _ => throw new Exception(s"unable to parse evaluationt type: $group")
      }

      def parseDistanceLink(link: Element): RaceDistanceId = {
        val pattern = "id=(\\d+)&(?:dist=(\\d+)&)?type=(total|group)".r("raceId", "distanceId", "evaluationType")
        val href = link.attr("href")

        pattern.findFirstMatchIn(href) match {
          case Some(m) => {
            RaceDistanceId(
              distanceId = Option(m.group("distanceId")).map(_.toLong),
              raceId = m.group("raceId").toLong,
              evaluationType = parseEvaluationType(m.group("evaluationType")),
              name = parseName(link)
            )
          }
          case None => throw new Exception(s"unable to parse race distance: $href")
        }
      }

      val distanceLinks = doc.select("center > a")
      val distanceLinksWithoutExcel = distanceLinks.toList.filterNot(_.ownText.contains("formātā"))

      distanceLinksWithoutExcel.map(parseDistanceLink(_))
    }

    RaceDistance(
      distanceId = parseDistanceId,
      name = parseName,
      otherRaceDistances = parseOtherRaceDistances,
      results = parseResults
    )
  }
}
