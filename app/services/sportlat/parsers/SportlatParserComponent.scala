package services.sportlat.parsers

import models.sportlat.{Athlete, RaceDistance, RaceResult}
import models.sportlat.id.RaceId

trait SportlatParserComponent {

  val sportlatParser: SportlatParser

  class DefaultSportlatParser extends SportlatParser {
    override def parseRacesInYear(html: String): Seq[RaceId] = RacesInYearParser.parse(html)

    override def parseRaceDistance(html: String): RaceDistance = RaceDistanceParser.parse(html)

    override def parseAthlete(html: String): Option[Athlete] = AthleteParser.parse(html)
  }

  trait SportlatParser {
    def parseRacesInYear(html: String): Seq[RaceId]

    def parseRaceDistance(html: String): RaceDistance

    def parseAthlete(html: String): Option[Athlete]
  }

}
