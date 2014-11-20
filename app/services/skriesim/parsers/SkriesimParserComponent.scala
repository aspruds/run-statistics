package services.skriesim.parsers

import models.skriesim.id.{CodeName, IdName}
import models.skriesim.{Athlete, Club, Race}
import services.skriesim.parsers.lookups._

trait SkriesimParserComponent {

  val skriesimParser: SkriesimParser = new DefaultSkriesimParser

  class DefaultSkriesimParser extends SkriesimParser {
    override def parseAthlete(html: String): Athlete = AthleteParser.parse(html)

    override def parseCoach(html: String): Athlete = AthleteParser.parse(html)

    override def parseRace(html: String): Race = RaceParser.parse(html)

    override def parseClub(html: String): Club = ClubParser.parse(html)

    override def parseAthleteIds(html: String): Seq[IdName] = AthletesParser.parse(html)

    override def parseClubIds(html: String): Seq[IdName] = ClubsParser.parse(html)

    override def parseCoachIds(html: String): Seq[IdName] = CoachesParser.parse(html)

    override def parseRaceIds(html: String): Seq[IdName] = RacesParser.parse(html)

    override def parseCountryIds(html: String): Seq[CodeName] = CountryParser.parse(html)

    override def parseAgeGroupIds(html: String): Seq[CodeName] = AgeGroupParser.parse(html)

    override def parseDisciplineTypeIds(html: String): Seq[IdName] = DisciplineTypesParser.parse(html)

    override def parseStandardDisciplineTypeIds(html: String): Seq[IdName] = StandardDisciplineTypesParser.parse(html)

    override def parseNonStandardDisciplineTypeIds(html: String): Seq[CodeName] = NonStandardDisciplineTypesParser.parse(html)
  }

  trait SkriesimParser {
    def parseAthlete(html: String): Athlete

    def parseCoach(html: String): Athlete

    def parseRace(html: String): Race

    def parseClub(html: String): Club

    def parseAthleteIds(html: String): Seq[IdName]

    def parseClubIds(html: String): Seq[IdName]

    def parseCoachIds(html: String): Seq[IdName]

    def parseRaceIds(html: String): Seq[IdName]

    def parseCountryIds(html: String): Seq[CodeName]

    def parseAgeGroupIds(html: String): Seq[CodeName]

    def parseDisciplineTypeIds(html: String): Seq[IdName]

    def parseStandardDisciplineTypeIds(html: String): Seq[IdName]

    def parseNonStandardDisciplineTypeIds(html: String): Seq[CodeName]
  }

}
