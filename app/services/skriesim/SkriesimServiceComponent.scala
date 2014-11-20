package services.skriesim

import models.skriesim.Athlete
import models.skriesim.id.IdName
import services.skriesim.parsers.SkriesimParserComponent
import services.skriesim.providers.SkriesimProviderComponent

trait SkriesimServiceComponent {
  this: SkriesimProviderComponent with SkriesimParserComponent =>

  val skriesimService = new DefaultSkriesimService

  class DefaultSkriesimService extends SkriesimService{
    override def getAthleteIds: Seq[IdName] = {
      val html = skriesimProvider.getAthleteIds
      skriesimParser.parseAthleteIds(html)
    }

    override def getAthlete(id: Int): Athlete = {
      val html = skriesimProvider.getCoach(id)
      skriesimParser.parseCoach(html).copy(id = Some(id))
    }

    override def getAthletes = getAthleteIds.map {
      a => getAthlete(a.id)
    }

    override def getCoachIds: Seq[IdName] = {
      val html = skriesimProvider.getCoachIds
      skriesimParser.parseCoachIds(html)
    }

    override def getCoach(id: Int): Athlete = getAthlete(id)

    override def getCoaches: Seq[Athlete] = getCoachIds.map {
      a => getCoach(a.id)
    }
  }

  trait SkriesimService {
    def getAthleteIds: Seq[IdName]

    def getAthlete(id: Int): Athlete

    def getAthletes: Seq[Athlete]

    def getCoachIds: Seq[IdName]

    def getCoach(id: Int): Athlete

    def getCoaches: Seq[Athlete]
  }
}
