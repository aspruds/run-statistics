package services.skriesim.providers

import scala.io.Source

trait MockSkriesimProviderComponent extends SkriesimProviderComponent {
  override val skriesimProvider: SkriesimProvider = new MockSkriesimProvider

  class MockSkriesimProvider extends SkriesimProvider {
    override def getAthlete(id: Long) = loadFile("athlete.html")
    override def getClub(id: Long) = loadFile("club.html")
    override def getCoach(id: Long) = loadFile("coach.html")
    override def getRace(id: Long) = loadFile("race.html")

    override def getAthleteIds = loadFile("athletes.html")
    override def getClubIds = loadFile("clubs.html")
    override def getCoachIds = loadFile("coaches.html")
    override def getRaceIds = getStatisticsIds
    override def getStatisticsIds = loadFile("statistics.html")
    override def getStatisticsIdsEnglish = loadFile("statistics_en.html")

    def loadFile(name: String) = {
      val location = "test/resources/skriesim/"
      Source.fromFile(location + name).mkString
    }
  }
}
