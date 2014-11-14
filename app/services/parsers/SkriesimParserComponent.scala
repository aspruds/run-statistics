package services.parsers

import models._
import services.providers.MockSkriesimProviderComponent

trait SkriesimParserComponent {
  val skriesimParser: SkriesimParser = new DefaultSkriesimParser

  class DefaultSkriesimParser extends SkriesimParser with MockSkriesimProviderComponent {
    override def parseAthlete(id: Int): Athlete = {
      AthleteParser.parseAthlete(skriesimProvider.getAthlete(id))
    }

    override def parseAthletes(): Seq[AthleteId] = {
      AthletesParser.parse(skriesimProvider.getAthletes())
    }

    override def parseClubs(): Seq[ClubId] = {
      ClubsParser.parse(skriesimProvider.getClubs())
    }

    def parseCoaches(): Seq[CoachId] = {
      CoachesParser.parse(skriesimProvider.getCoaches())
    }
  }

  trait SkriesimParser {
    def parseAthlete(id: Int): Athlete

    def parseAthletes(): Seq[AthleteId]

    def parseClubs(): Seq[ClubId]

    def parseCoaches(): Seq[CoachId]
  }

}
