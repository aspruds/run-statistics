package services.skriesim

import models.skriesim.{Club, Athlete}
import models.skriesim.id.{CodeName, IdName}
import services.skriesim.parsers.SkriesimParserComponent
import services.skriesim.providers.SkriesimProviderComponent

trait SkriesimServiceComponent {
  this: SkriesimProviderComponent with SkriesimParserComponent =>

  val skriesimService = new DefaultSkriesimService

  class DefaultSkriesimService extends SkriesimService{
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

    override def getClubs = {
      getClubIds
        .map(c => getClub(c.id))
        .filterNot(_.id == Some(1494)) // Bebrene
        .filterNot(_.id == Some(682)) // Smiltene
        .filterNot(_.id == Some(652)) // Tīturga
    }
  }

  trait SkriesimService {
    // athletes
    def getAthleteIds: Seq[IdName]

    def getAthlete(id: Long): Athlete

    def getAthletes: Seq[Athlete]

    // coaches
    def getCoachIds: Seq[IdName]

    def getCoach(id: Long): Athlete

    def getCoaches: Seq[Athlete]

    // countries
    def getCountryIds: Seq[CodeName]

    def getCountryIdsLatvian: Seq[CodeName]

    // clubs
    def getClubIds: Seq[IdName]

    def getClub(id: Long): Club

    def getClubs: Seq[Club]
  }
}
