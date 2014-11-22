package services.skriesim.providers

import scala.io.Source

trait MockSkriesimProviderComponent extends SkriesimProviderComponent {
  override val skriesimProvider: SkriesimProvider = new MockSkriesimProvider

  class MockSkriesimProvider extends SkriesimProvider {
    override def getAthlete(id: Int): String = loadFile("athlete.html")
    override def getClub(id: Int): String = loadFile("club.html")
    override def getCoach(id: Int) = loadFile("coach.html")
    override def getRace(id: Int): String = loadFile("race.html")

    override def getAthleteIds: String = loadFile("athletes.html")
    override def getClubIds: String = loadFile("clubs.html")
    override def getCoachIds: String = loadFile("coaches.html")
    override def getStatisticsIds: String = loadFile("statistics.html")
    override def getStatisticsIdsEnglish: String = loadFile("statistics_en.html")

    def loadFile(name: String) = {
      val location = "test/resources/skriesim/"
      Source.fromFile(location + name).mkString
    }
  }
}
