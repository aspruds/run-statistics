package services.skriesim.parsers

import _root_.utils.text.TextUtils._
import models.skriesim.Athlete
import models.skriesim.id.IdName
import org.jsoup.Jsoup
import services.skriesim.parsers.athlete.{DateParser, NameParser}
import services.skriesim.parsers.results.RaceResultsParser

import scala.collection.JavaConversions._

object AthleteParser {

  def parse(html: String): Athlete = {
    val doc = Jsoup.parse(html)

    def parseAttribute(value: String) = doc.select(s":containsOwn($value) + td").first.ownText

    val nameParser = new NameParser(doc)

    val dateParser = new DateParser(parseAttribute("Dz.datums"))

    def parseIsCoach() = {
      val selector = "table.personView td.name span.disciplineDetails"
      val text = doc.select(selector).first.ownText

      if (text == "(Treneris)")
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

    def parseSex(): String = if (parseAttribute("Dzimums") == "Vīrietis") "M" else "F"

    def parseRaceResults() = {
      if (parseIsCoach())
        Seq.empty
      else
        new RaceResultsParser(doc).parseRaceResults()
    }

    Athlete(
      None,
      givenName = nameParser.parseGivenName(),
      familyName = nameParser.parseFamilyName(),
      dateOfBirth = dateParser.parseDateOfBirth(),
      yearOfBirth = dateParser.parseYearOfBirth(),
      ageGroup = parseAttribute("Vec.gr."),
      sex = parseSex(),
      country = parseAttribute("Valsts").toOption,
      clubs = parseClubs(),
      coaches = parseCoaches(),
      raceResults = parseRaceResults(),
      isCoach = parseIsCoach()
    )
  }
}
