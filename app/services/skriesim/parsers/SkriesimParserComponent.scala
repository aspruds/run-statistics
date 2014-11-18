package services.skriesim.parsers

import models._
import models.skriesim.{Club, Athlete, Race}
import models.skriesim.id.{IdName, CodeName}
import services.skriesim.parsers.lookups._
import services.skriesim.providers.{SkriesimProviderComponent, MockSkriesimProviderComponent}

trait SkriesimParserComponent {
  val skriesimParser: SkriesimParser = new DefaultSkriesimParser

  import play.api.db.slick.Config.driver.simple._

  class DefaultSkriesimParser extends SkriesimParser with SkriesimProviderComponent {
    override def parseAthlete(id: Int)(implicit session: Session): Athlete = {
      val html = skriesimProvider.getAthlete(id)
      AthleteParser.parse(html).copy(id = Some(id))
    }

    override def parseCoach(id: Int)(implicit session: Session): Athlete = {
      val html = skriesimProvider.getCoach(id)
      AthleteParser.parse(html).copy(id = Some(id))
    }

    override def parseRace(id: Int)(implicit session: Session): Race = {
      val html = skriesimProvider.getRace(id)
      RaceParser.parse(html).copy(id = Some(id))
    }

    override def parseClub(id: Int)(implicit session: Session): Club = {
      val html = skriesimProvider.getClub(id)
      ClubParser.parse(html).copy(id = Some(id))
    }

    override def parseAthletes()(implicit session: Session): Seq[IdName] = {
      AthletesParser.parse(skriesimProvider.getAthletes())
    }

    override def parseClubs()(implicit session: Session): Seq[IdName] = {
      ClubsParser.parse(skriesimProvider.getClubs()).sortBy(_.id)
    }

    override def parseCoaches()(implicit session: Session): Seq[IdName] = {
      CoachesParser.parse(skriesimProvider.getCoaches())
    }

    override def parseRaces()(implicit session: Session): Seq[IdName] = {
      RacesParser.parse(skriesimProvider.getStatistics())
    }

    override def parseCountries()(implicit session: Session): Seq[CodeName] = {
      CountryParser.parse(skriesimProvider.getStatistics())
    }

    override def parseAgeGroups()(implicit session: Session): Seq[CodeName] = {
      AgeGroupParser.parse(skriesimProvider.getStatistics())
    }

    override def parseDisciplineTypes()(implicit session: Session): Seq[IdName] = {
      DisciplineTypesParser.parse(skriesimProvider.getStatistics()).sortBy(_.id)
    }

    override def parseStandardDisciplineTypes()(implicit session: Session): Seq[IdName] = {
      StandardDisciplineTypesParser.parse(skriesimProvider.getStatistics()).sortBy(_.id)
    }

    override def parseNonStandardDisciplineTypes()(implicit session: Session): Seq[CodeName] = {
      NonStandardDisciplineTypesParser.parse(skriesimProvider.getStatistics())
    }
  }

  trait SkriesimParser {
    def parseAthlete(id: Int)(implicit session: Session): Athlete

    def parseCoach(id: Int)(implicit session: Session): Athlete

    def parseRace(id: Int)(implicit session: Session): Race

    def parseClub(id: Int)(implicit session: Session): Club

    def parseAthletes()(implicit session: Session): Seq[IdName]

    def parseClubs()(implicit session: Session): Seq[IdName]

    def parseCoaches()(implicit session: Session): Seq[IdName]

    def parseRaces()(implicit session: Session): Seq[IdName]

    def parseCountries()(implicit session: Session): Seq[CodeName]

    def parseAgeGroups()(implicit session: Session): Seq[CodeName]

    def parseDisciplineTypes()(implicit session: Session): Seq[IdName]

    def parseStandardDisciplineTypes()(implicit session: Session): Seq[IdName]

    def parseNonStandardDisciplineTypes()(implicit session: Session): Seq[CodeName]
  }

}
