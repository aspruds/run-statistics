package services.sportlat

import models.sportlat.id.{RaceDistanceId, RaceId}
import models.sportlat.{Athlete, RaceDistance}
import modules.DAL
import org.joda.time.DateTime
import play.api.Logger
import services.sportlat.parsers.SportlatParserComponent
import services.sportlat.providers.SportlatProviderComponent

trait SportlatDataServiceComponent {
  this: SportlatProviderComponent with SportlatParserComponent with DAL =>

  val sportlatDataService: SportlatDataService

  class DefaultSportlatDataService extends SportlatDataService {

    private def getRaceIdsForYear(year: Long) = {
      Logger.debug(s"parsing sportlat.lv raceIdsForYear $year")

      val html = sportlatProvider.getRacesForYear(year)
      sportlatParser.parseRacesInYear(html)
    }

    override def getRaceIds: Seq[RaceId] = {
      val currentYear = new DateTime().year.get
      val years = for(year <- 2002 to currentYear) yield(year)
      years.flatMap {
        year => getRaceIdsForYear(year)
      }
    }

    override def getRaceMainDistance(id: Long): RaceDistance = {
      Logger.debug(s"parsing sportlat.lv raceMainDistance #$id")

      val html = sportlatProvider.getRaceMainDistance(id)
      sportlatParser.parseRaceDistance(html)
    }

    override def getRaceDistance(raceDistance: RaceDistanceId): RaceDistance = {
      Logger.debug(s"parsing sportlat.lv raceDistance ${raceDistance.identifier}")

      val html = sportlatProvider.getRaceDistance(raceDistance)
      sportlatParser.parseRaceDistance(html)
    }

    override def getAthlete(id: Long): Option[Athlete] = {
      Logger.debug(s"parsing sportlat.lv athlete #$id")

      val html = sportlatProvider.getAthlete(id)
      val maybeAthlete = sportlatParser.parseAthlete(html)
      maybeAthlete.map(_.copy(id=Some(id)))
    }
  }

  trait SportlatDataService {
    def getRaceIds: Seq[RaceId]

    def getRaceMainDistance(id: Long): RaceDistance

    def getRaceDistance(raceDistance: RaceDistanceId): RaceDistance

    def getAthlete(id: Long): Option[Athlete]
  }

}
