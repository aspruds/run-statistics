package services.skriesim.parsers.athlete

import models.skriesim.RaceResult
import models.skriesim.id.IdName
import org.jsoup.nodes.{Document, Element}
import play.Logger
import utils.text.TextUtils
import models.skriesim.results.{Distance, Time}

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

    val distanceType = TextUtils.removeNbsp(distanceTypeWithVenueParts._1).replace(" ar kvkl.", "")

    val venue = distanceTypeWithVenueParts._2.replaceAll("\\(|\\)", "")

    val withQualification = {
      if (distanceTypeWithVenue.contains("ar kvkl."))
        Some(true)
      else
        None
    }

    val rankingPoints = TextUtils.toOption(ex(4)).map(_.toShort)

    def isNumeric(str: String) = str.forall(Character.isDigit)

    val rank = TextUtils.toOption(ex(2)).flatMap {
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
        TextUtils.toOption(ex(2))
    }

    val result = TextUtils.toOption(ex(1))

    RaceResult(
      athleteId = None,
      distanceType = distanceType,
      venue = venue,
      distanceTypeWithVenue = distanceTypeWithVenue,
      withQualification = withQualification,
      pk = ex(0),
      time = result.flatMap(Time.parse(_)),
      distance = result.flatMap(Distance.parse(_)),
      rank = rank,
      rankText = rankText,
      classificationType = TextUtils.toOption(ex(3)),
      wind = None,
      points = rankingPoints,
      ageGroup = ex(5),
      date = ex(6),
      race = IdName(parseRaceId(), ex(7))
    )
  }
}