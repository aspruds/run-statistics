package services.sportlat.parsers

import models.sportlat.Athlete
import org.jsoup.Jsoup
import services.skriesim.parsers.athlete.{DateParser, NameParser, RaceResultsParser}

import scala.collection.JavaConversions._

object AthleteParser {

  def parse(html: String): Option[Athlete] = {
    val doc = Jsoup.parse(html)

    val name = Option(doc.select("h2.vards").first).map(_.ownText)

    if(name.isDefined) {
      Some(Athlete(
        id = None,
        givenName = name.get,
        familyName = name.get,
        dateOfBirth = None
      ))
    }
    else {
      None
    }
  }
}
