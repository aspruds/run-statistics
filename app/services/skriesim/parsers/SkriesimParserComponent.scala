package services.skriesim.parsers

import models.skriesim.id.{CodeName, IdName}
import models.skriesim.{NonStandardDistance, Athlete, Club, Race}
import services.skriesim.parsers.lookups._

trait SkriesimParserComponent {

  val skriesimParser: SkriesimParser

  class DefaultSkriesimParser extends SkriesimParser {
    override def parseAthlete(html: String) = AthleteParser.parse(html)

    override def parseCoach(html: String) = AthleteParser.parse(html)

    override def parseRace(html: String) = RaceParser.parse(html)

    override def parseClub(html: String) = ClubParser.parse(html)

    override def parseAthleteIds(html: String) = AthletesParser.parse(html)

    override def parseClubIds(html: String) = ClubsParser.parse(html)

    override def parseCoachIds(html: String) = CoachesParser.parse(html)

    override def parseRaceIds(html: String) = RacesParser.parse(html)

    override def parseCountryIds(html: String) = CountryParser.parse(html)

    override def parseCountryIdsEnglish(html: String) = CountryParser.parseEnglish(html)

    override def parseAgeGroupIds(html: String) = AgeGroupParser.parse(html)

    override def parseDisciplineTypeIds(html: String) = DisciplineTypesParser.parse(html)

    override def parseStandardDisciplineTypeIds(html: String) = StandardDisciplineTypesParser.parse(html)

    override def parseNonStandardDisciplineTypeIds(html: String) = NonStandardDisciplineTypesParser.parse(html)
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

    def parseCountryIdsEnglish(html: String): Seq[CodeName]

    def parseAgeGroupIds(html: String): Seq[CodeName]

    def parseDisciplineTypeIds(html: String): Seq[IdName]

    def parseStandardDisciplineTypeIds(html: String): Seq[IdName]

    def parseNonStandardDisciplineTypeIds(html: String): Seq[NonStandardDistance]
  }

}
