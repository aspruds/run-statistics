package services.sportlat.parsers

import com.google.inject.ImplementedBy
import models.sportlat.id.RaceId
import models.sportlat.{Athlete, RaceDistance}

@ImplementedBy(classOf[DefaultSportlatParser])
trait SportlatParser {
  def parseRacesInYear(html: String): Seq[RaceId]

  def parseRaceDistance(html: String): RaceDistance

  def parseAthlete(html: String): Option[Athlete]
}


class DefaultSportlatParser extends SportlatParser {
  override def parseRacesInYear(html: String): Seq[RaceId] = RacesInYearParser.parse(html)

  override def parseRaceDistance(html: String): RaceDistance = RaceDistanceParser.parse(html)

  override def parseAthlete(html: String): Option[Athlete] = AthleteParser.parse(html)
}