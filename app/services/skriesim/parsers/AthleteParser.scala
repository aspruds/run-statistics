package services.skriesim.parsers

import models._
import models.skriesim.{RaceResult, Athlete}
import models.skriesim.id.IdName
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

import scala.collection.JavaConversions._

object AthleteParser {
  def parse(html: String): Athlete = {
    val doc = Jsoup.parse(html)

    def parseName(): String = doc.select("td.name").first.ownText

    def parseAttribute(value: String) = doc.select(s":containsOwn($value) + td").first.ownText

    def parseIsCoach() = {
      val selector = "table.personView td.name span.disciplineDetails"
      val text = doc.select(selector).first.ownText

      if(text == "(Treneris)")
        true
      else
        false
    }

    def parseClubs() = {
      doc.select(":containsOwn(Klubs) + td a").map {
        a => {
          val id = a.attr("href").replace("http://skriesim.lv/clubs?id=", "").toInt
          val name = a.ownText
          IdName(id, name)
        }
      }
    }

    def parseCoaches() = {
      doc.select(":containsOwn(Treners) + td a").map {
        a => {
          val id = a.attr("href").replace("http://skriesim.lv/coaches?id=", "").toInt
          val name = a.ownText
          IdName(id, name)
        }
      }
    }

    def parseRaceResults() = {
      if(parseIsCoach())
        Seq.empty
      else
        _parseRaceResults()
    }

    def _parseRaceResults() = {
      def parseContainer(container: Element): RaceResult = {
        val columns = container.select("td")
        def ex(id: Int) = columns(id).text

        def parseRaceId(): Int = {
          container.select("td.club a").attr("href").replace("http://skriesim.lv/calendar?id=", "").toInt
        }

        def parseDiscipline() = {
          def findDiscipline(el: Element): Option[String] = {
            if(el.hasClass("discipline")) {
              Some(el.select("td").text)
            }
            else {
              val prev = el.previousElementSibling
              if(prev == null) None
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

      val raceContainers = doc.select("tr.line2")
      raceContainers.map(c => parseContainer(c))
    }

    Athlete(
      None,
      name = parseName(),
      dateOfBirth = parseAttribute("Dz.datums"),
      ageGroup = parseAttribute("Vec.gr."),
      sex = parseAttribute("Dzimums"),
      country = parseAttribute("Valsts"),
      clubs = parseClubs(),
      coaches = parseCoaches(),
      raceResults = parseRaceResults(),
      isCoach = parseIsCoach()
    )
  }
}
