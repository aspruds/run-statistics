package services.skriesim.parsers.athlete

import models.skriesim.RaceResult
import models.skriesim.id.IdName
import org.jsoup.nodes.{Document, Element}

import scala.collection.JavaConversions._

class RaceResultsParser(doc: Document) {
  def parseRaceResults() = {
    val raceContainers = doc.select("tr.line2")
    raceContainers.map(c => parseContainer(c))
  }

  private def parseContainer(container: Element): RaceResult = {
    val columns = container.select("td")
    def ex(id: Int): String = columns(id).text

    def parseRaceId(): Int = {
      container.select("td.club a").attr("href").replace("http://skriesim.lv/calendar?id=", "").toInt
    }

    def parseDiscipline() = {
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
    }

    RaceResult(
      discipline = parseDiscipline().get,
      pk = ex(0),
      time = ex(1),
      rank = ex(2),
      category = ex(3),
      wind = None,
      rankingPoints = ex(4),
      ageGroup = ex(5),
      date = ex(6),
      race = IdName(parseRaceId(), ex(7))
    )
  }
}