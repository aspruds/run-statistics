package services.skriesim.providers

import scala.io.Source

trait MockSkriesimProviderComponent extends SkriesimProviderComponent {
  override val skriesimProvider: SkriesimProvider = new MockSkriesimProvider

  import play.api.db.slick.Config.driver.simple._

  class MockSkriesimProvider extends SkriesimProvider {
    override def getAthlete(id: Int)(implicit session: Session): String = loadFile("athlete.html")
    override def getClub(id: Int)(implicit session: Session): String = loadFile("club.html")
    override def getCoach(id: Int)(implicit session: Session) = loadFile("coach.html")
    override def getRace(id: Int)(implicit session: Session): String = loadFile("race.html")

    override def getAthletes()(implicit session: Session): String = loadFile("athletes.html")
    override def getClubs()(implicit session: Session): String = loadFile("clubs.html")
    override def getCoaches()(implicit session: Session): String = loadFile("coaches.html")
    override def getStatistics()(implicit session: Session): String = loadFile("statistics.html")

    def loadFile(name: String) = {
      val location = "test/resources/skriesim/"
      Source.fromFile(location + name).mkString
    }
  }
}
