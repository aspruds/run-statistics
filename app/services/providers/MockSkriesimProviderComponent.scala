package services.providers

import scala.io.Source

trait MockSkriesimProviderComponent extends SkriesimProviderComponent {
  override val skriesimProvider: SkriesimProvider = new MockSkriesimProvider

  class MockSkriesimProvider extends SkriesimProvider {
    override def getAthlete(id: Int): String = loadFile("athlete.html")
    override def getClub(id: Int): String = loadFile("club.html")
    override def getCoach(id: Int) = loadFile("coach.html")
    override def getRace(id: Int): String = loadFile("race.html")

    override def getAthletes(): String = loadFile("athletes.html")
    override def getClubs(): String = loadFile("clubs.html")
    override def getCoaches(): String = loadFile("coaches.html")
    override def getRaces(): String = loadFile("races.html")

    def loadFile(name: String) = {
      val location = "test/resources/skriesim/"
      Source.fromFile(location + name).mkString
    }
  }
}
