package services.parsers

import models._
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

    override def parseAthletes(): Seq[AthleteId] = {
      AthletesParser.parse(skriesimProvider.getAthletes())
    }

    override def parseClubs(): Seq[ClubId] = {
      ClubsParser.parse(skriesimProvider.getClubs())
    }

    def parseCoaches(): Seq[CoachId] = {
      CoachesParser.parse(skriesimProvider.getCoaches())
    }

    def parseRaces(): Seq[RaceId] = {
      RacesParser.parse(skriesimProvider.getRaces())
    }
  }

  trait SkriesimParser {
    def parseAthlete(id: Int): Athlete

    def parseCoach(id: Int): Athlete

    def parseRace(id: Int): Race

    def parseClub(id: Int): Club

    def parseAthletes(): Seq[AthleteId]

    def parseClubs(): Seq[ClubId]

    def parseCoaches(): Seq[CoachId]

    def parseRaces(): Seq[RaceId]
  }

}
