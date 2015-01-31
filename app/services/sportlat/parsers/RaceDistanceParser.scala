package services.sportlat.parsers

import models.sportlat.id.{EvaluationType, InGroup, RaceDistanceId, Total}
import models.sportlat.{RaceDistance, RaceResult}
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import services.sportlat.parsers.results.TimeParser
import utils.text.TextUtils._

import scala.collection.JavaConversions._

object RaceDistanceParser {

  def parse(html: String): RaceDistance = {
    val doc = Jsoup.parse(html)

    val currentDistanceLink = doc.select("center a[style=font-weight:bold;]").first

    val trs = doc.select("table tr").toList

    val columnMap: Map[String,Int] = {
      trs.head.children().map(_.ownText()).zipWithIndex.toMap
    }

    def parseDistanceId(): Option[Long] = {
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

    def parseName() = currentDistanceLink.ownText

    def parseOtherRaceDistances(): Seq[RaceDistanceId] = {
      def parseName(link: Element) = link.ownText().replaceAll(" (kopvērtējums)", "").replace(" (pa grupām)", "")

      def parseEvaluationType(group: String): EvaluationType = group match {
        case g if g == "group" => InGroup()
        case g if g == "total" => Total()
        case _ => throw new Exception(s"unable to parse evaluation type: $group")
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
              name = parseName(link),
              href = href
            )
          }
          case None => throw new Exception(s"unable to parse race distance: $href")
        }
      }

      val distanceLinks = doc.select("center > a")

      val distanceLinksWithoutExcel = distanceLinks.toList.filterNot(_.ownText.contains("formātā"))

      distanceLinksWithoutExcel.map(parseDistanceLink(_))
    }

    def parseResults(): Seq[RaceResult] = {
      trs.drop(1).map {
        case(tr) =>

          def tdByName(name: String): Option[Element] = {
            val idx = columnMap.get(name)
            idx.map(tr.child(_))
          }

          def textOption(name: String): Option[String] = tdByName(name).flatMap {
            _.ownText.toOption
          }

          def longOption(name: String): Option[Long] = tdByName(name).flatMap {
            _.ownText.toLongOption
          }

          def intOption(name: String): Option[Int] = longOption(name).map(_.toInt)

          def parseAthleteId(): Option[Long] = {
            val nameTd = tdByName("Vārds").get
            val maybeAthleteId = nameTd.select("a").attr("href").replace("http://www.sportlat.lv/index.php?id=user&uid=", "")
            maybeAthleteId.toLongOption
          }

          def parseBibNumber() = intOption("Nr.").flatMap {
            value =>
              if(value > 0) Some(value)
              else None
          }

          def parseClub() = textOption("Komanda")

          def parseCity() = textOption("Pilsēta")

          def parseAgeGroup() = textOption("Grupa")

          def parseRank() = textOption("Vieta").map {
            text => text.replace(".", "").toInt
          }

          def parsePoints() = intOption("Punkti")

          val timeText = {
            for {
              td <- tdByName("Finišs")
              b <- Option(td.select("b").first())
              text <- b.ownText.toOption
            } yield text
          }

          def parseIsDNS() = timeText.map(TimeParser.isDNS(_))

          def parseIsDNF() = timeText.map(TimeParser.isDNF(_))

          def parseTime() = timeText.flatMap(TimeParser.parse(_))

          RaceResult(
            athleteId = parseAthleteId(),
            bibNumber = parseBibNumber(),
            club = parseClub(),
            city = parseCity(),
            ageGroup = parseAgeGroup(),
            rank = parseRank(),
            points = parsePoints(),
            isDNF = parseIsDNF(),
            isDNS = parseIsDNS(),
            time = parseTime()
          )
      }
    }

    RaceDistance(
      distanceId = parseDistanceId(),
      name = parseName(),
      otherRaceDistances = parseOtherRaceDistances(),
      results = parseResults()
    )
  }
}
