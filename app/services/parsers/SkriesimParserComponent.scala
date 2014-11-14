package services.parsers

import models._
import services.providers.MockSkriesimProviderComponent

trait SkriesimParserComponent {
  val skriesimParser: SkriesimParser = new DefaultSkriesimParser

  class DefaultSkriesimParser extends SkriesimParser with MockSkriesimProviderComponent {
    def parseAthlete(id: Int): Athlete = {
      AthleteParser.parseAthlete(skriesimProvider.getAthlete(id))
    }
  }

  trait SkriesimParser {
    def parseAthlete(id: Int): Athlete
  }

}
