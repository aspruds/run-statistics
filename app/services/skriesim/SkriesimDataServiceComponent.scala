package services.skriesim

import models.skriesim.id.{CodeName, IdName}
import models.skriesim.{Athlete, Club, Race}
import modules.DAL
import play.api.Logger
import services.skriesim.`import`.SkriesimImportUtils
import services.skriesim.parsers.SkriesimParserComponent
import services.skriesim.providers.SkriesimProviderComponent

trait SkriesimDataServiceComponent {
  this: SkriesimProviderComponent with SkriesimParserComponent with SkriesimImportUtils with DAL =>

  val skriesimDataService: SkriesimDataService

  class DefaultSkriesimDataService extends SkriesimDataService {
    override def getAthleteIds = {
      val html = skriesimProvider.getAthleteIds
      skriesimParser.parseAthleteIds(html)
    }

    override def getAthlete(id: Long) = {
      val html = skriesimProvider.getCoach(id)
      skriesimParser.parseCoach(html).copy(id = Some(id))
    }

    override def getAthletes = getAthleteIds.map {
      a => getAthlete(a.id)
    }

    override def getCoachIds = {
      val html = skriesimProvider.getCoachIds
      skriesimParser.parseCoachIds(html)
    }

    override def getCoach(id: Long) = getAthlete(id)

    override def getCoaches = getCoachIds.map {
      a => getCoach(a.id)
    }

    override def getCountryIds = {
      val html = skriesimProvider.getStatisticsIdsEnglish
      skriesimParser.parseCountryIdsEnglish(html)
    }

    override def getCountryIdsLatvian = {
      val html = skriesimProvider.getStatisticsIds
      skriesimParser.parseCountryIds(html)
    }

    override def getClubIds = {
      val html = skriesimProvider.getClubIds
      skriesimParser.parseClubIds(html)
    }

    override def getClub(id: Long) = {
      val html = skriesimProvider.getClub(id)
      skriesimParser.parseClub(html).copy(id = Some(id))
    }

    override def getRaceIds = {
      val html = skriesimProvider.getRaceIds
      skriesimParser.parseRaceIds(html)
    }

    override def getRace(id: Long) = {
      val html = skriesimProvider.getRace(id)
      skriesimParser.parseRace(html).copy(id = Some(id))
    }

    override def getRaces = getRaceIds.map {
      r =>
        Logger.debug(s"parsing Race ${r.id}")
        getRace(r.id)
    }

    override def getClubs = {
      getClubIds
        .map(c => getClub(c.id))
        .filterNot(_.id == Some(1494)) // Bebrene
        .filterNot(_.id == Some(682)) // Smiltene
        .filterNot(_.id == Some(652)) // Tīturga
    }

    override def getAgeGroupsIds = {
      val html = skriesimProvider.getStatisticsIds
      skriesimParser.parseAgeGroupIds(html)
    }
  }

  trait SkriesimDataService {
    def getAthleteIds: Seq[IdName]

    def getAthlete(id: Long): Athlete

    def getAthletes: Seq[Athlete]

    def getCoachIds: Seq[IdName]

    def getCoach(id: Long): Athlete

    def getCoaches: Seq[Athlete]

    def getCountryIds: Seq[CodeName]

    def getCountryIdsLatvian: Seq[CodeName]

    def getClubIds: Seq[IdName]

    def getClub(id: Long): Club

    def getClubs: Seq[Club]

    def getRaceIds: Seq[IdName]

    def getRace(id: Long): Race

    def getRaces: Seq[Race]

    def getAgeGroupsIds: Seq[CodeName]
  }

}
