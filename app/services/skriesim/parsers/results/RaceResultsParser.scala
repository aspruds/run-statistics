package services.skriesim.parsers.results

import models.skriesim.RaceResult
import models.skriesim.id.IdName
import org.jsoup.nodes.{Document, Element}
import utils.text.TextUtils._

import scala.collection.JavaConversions._

class RaceResultsParser(doc: Document) {
  def parseRaceResults() = {
    val raceContainers = doc.select("tr[class~=^line(1|2)$]")
    raceContainers.map(c => parseContainer(c))
  }

  private def parseContainer(container: Element): RaceResult = {
    val columns = container.select("td")
    def ex(id: Int): String = columns(id).text

    def parseRaceId(): Int = {
      container.select("td.club a").attr("href").replace("http://skriesim.lv/calendar?id=", "").toInt
    }

    val distanceTypeWithVenue = {
      def findDiscipline(el: Element): Option[String] = {
        if (el.hasClass("discipline")) {
          Some(el.select("td").text)
        }
        else {
          val prev = el.previousElementSibling
          if (prev == null) None
          else findDiscipline(prev)
        }
      }

      findDiscipline(container.previousElementSibling)
    }.get

    val distanceTypeWithVenueParts = {
      val lastIndex = distanceTypeWithVenue.lastIndexOf("(")
      distanceTypeWithVenue.splitAt(lastIndex)
    }

    def parseDistanceType() = {
      distanceTypeWithVenueParts._1.replace(" ar kvkl.", "")
    }

    def parseVenue() = distanceTypeWithVenueParts._2.replaceAll("\\(|\\)", "")

    val withQualification = {
      if (distanceTypeWithVenue.contains("ar kvkl."))
        Some(true)
      else
        None
    }

    def parseRankingPoints() = {
      ex(4).toOption.map(_.toShort)
    }

    def isNumeric(str: String) = str.forall(Character.isDigit)

    val rank = ex(2).toOption.flatMap {
      str =>
        if (isNumeric(str))
          Some(str.toInt)
        else
          None
    }

    val rankText = {
      if (rank.isDefined)
        None
      else
        ex(2).toOption
    }

    val result = ex(1).toOption

    def parseClassificationType() = ex(3).toOption

    RaceResult(
      athleteId = None,
      distanceType = parseDistanceType(),
      venue = parseVenue(),
      distanceTypeWithVenue = distanceTypeWithVenue,
      withQualification = withQualification,
      pk = ex(0),
      time = result.flatMap(TimeParser.parse(_)),
      distance = result.flatMap(DistanceParser.parse(_)),
      rank = rank,
      rankText = rankText,
      classificationType = parseClassificationType(),
      wind = None,
      points = parseRankingPoints(),
      ageGroup = ex(5),
      date = ex(6),
      race = IdName(parseRaceId(), ex(7))
    )
  }
}