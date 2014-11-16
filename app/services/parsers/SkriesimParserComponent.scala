package services.parsers

import models._
import models.id.{IdName, CodeName}
import services.parsers.lookups._
import services.providers.MockSkriesimProviderComponent

trait SkriesimParserComponent {
  val skriesimParser: SkriesimParser = new DefaultSkriesimParser

  class DefaultSkriesimParser extends SkriesimParser with MockSkriesimProviderComponent {
    override def parseAthlete(id: Int): Athlete = {
      val html = skriesimProvider.getAthlete(id)
      AthleteParser.parse(html).copy(id = Some(id))
    }

    override def parseCoach(id: Int): Athlete = {
      val html = skriesimProvider.getCoach(id)
      AthleteParser.parse(html).copy(id = Some(id))
    }

    override def parseRace(id: Int): Race = {
      val html = skriesimProvider.getRace(id)
      RaceParser.parse(html).copy(id = Some(id))
    }

    override def parseClub(id: Int): Club = {
      val html = skriesimProvider.getClub(id)
      ClubParser.parse(html).copy(id = Some(id))
    }

    override def parseAthletes(): Seq[IdName] = {
      AthletesParser.parse(skriesimProvider.getAthletes())
    }

    override def parseClubs(): Seq[IdName] = {
      ClubsParser.parse(skriesimProvider.getClubs())
    }

    override def parseCoaches(): Seq[IdName] = {
      CoachesParser.parse(skriesimProvider.getCoaches())
    }

    override def parseRaces(): Seq[IdName] = {
      RacesParser.parse(skriesimProvider.getStatistics())
    }

    override def parseCountries(): Seq[CodeName] = {
      CountryParser.parse(skriesimProvider.getStatistics())
    }

    override def parseAgeGroups(): Seq[CodeName] = {
      AgeGroupParser.parse(skriesimProvider.getStatistics())
    }

    override def parseDisciplineTypes(): Seq[IdName] = {
      DisciplineTypesParser.parse(skriesimProvider.getStatistics())
    }

    override def parseStandardDisciplineTypes(): Seq[IdName] = {
      StandardDisciplineTypesParser.parse(skriesimProvider.getStatistics())
    }

    override def parseNonStandardDisciplineTypes(): Seq[CodeName] = {
      NonStandardDisciplineTypesParser.parse(skriesimProvider.getStatistics())
    }
  }

  trait SkriesimParser {
    def parseAthlete(id: Int): Athlete

    def parseCoach(id: Int): Athlete

    def parseRace(id: Int): Race

    def parseClub(id: Int): Club

    def parseAthletes(): Seq[IdName]

    def parseClubs(): Seq[IdName]

    def parseCoaches(): Seq[IdName]

    def parseRaces(): Seq[IdName]

    def parseCountries(): Seq[CodeName]

    def parseAgeGroups(): Seq[CodeName]

    def parseDisciplineTypes(): Seq[IdName]

    def parseStandardDisciplineTypes(): Seq[IdName]

    def parseNonStandardDisciplineTypes(): Seq[CodeName]
  }

}
