package services.sportlat

import models.sportlat.{Athlete, RaceDistance, RaceResult}
import models.sportlat.id.RaceId
import modules.DAL
import org.joda.time.DateTime
import services.sportlat.parsers.SportlatParserComponent
import services.sportlat.providers.SportlatProviderComponent

trait SportlatDataServiceComponent {
  this: SportlatProviderComponent with SportlatParserComponent with DAL =>

  val sportlatDataService: SportlatDataService

  class DefaultSportlatDataService extends SportlatDataService {

    private def getRaceIdsForYear(year: Long) = {
      val html = sportlatProvider.getRacesForYear(year)
      sportlatParser.parseRacesInYear(html)
    }

    override def getRaceIds = {
      val currentYear = new DateTime().year.get
      val years = for(year <- 2002 to currentYear) yield(year)
      years.flatMap {
        year => getRaceIdsForYear(year)
      }
    }

    override def getRaceMainDistance(id: Long) = {
      val html = sportlatProvider.getRaceResults(id)
      sportlatParser.parseRaceDistance(html)
    }

    override def getAthlete(id: Long) = {
      val html = sportlatProvider.getAthlete(id)
      sportlatParser.parseAthlete(html)
    }
  }

  trait SportlatDataService {
    def getRaceIds: Seq[RaceId]

    def getRaceMainDistance(id: Long): RaceDistance

    def getAthlete(id: Long): Option[Athlete]
  }

}
