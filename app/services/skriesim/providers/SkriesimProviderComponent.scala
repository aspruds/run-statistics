package services.skriesim.providers

import services.http.db.UrlRepositoryComponent
import services.http.providers.HttpProviderComponent

trait SkriesimProviderComponent {
  val skriesimProvider: SkriesimProvider = new DefaultSkriesimProvider

  import play.api.db.slick.Config.driver.simple._

  class DefaultSkriesimProvider extends SkriesimProvider with HttpProviderComponent with UrlRepositoryComponent {

    override def getAthlete(id: Int)(implicit session: Session): String = {
      val url = "http://skriesim.lv/athletes?id=" + id
      loadURL(url)
    }

    override def getClub(id: Int)(implicit session: Session): String = {
      val url = "http://skriesim.lv/clubs?id=" + id
      loadURL(url)
    }

    override def getCoach(id: Int)(implicit session: Session): String = {
      getAthlete(id)
    }

    override def getRace(id: Int)(implicit session: Session): String = {
      val url = "http://skriesim.lv/calendar?id=" + id
      loadURL(url)
    }

    override def getAthletes()(implicit session: Session): String = {
      val url = "http://skriesim.lv/athletes"
      loadURL(url)
    }

    override def getClubs()(implicit session: Session): String = {
      val url = "http://skriesim.lv/clubs"
      loadURL(url)
    }

    override def getCoaches()(implicit session: Session): String = {
      val url = "http://skriesim.lv/coaches"
      loadURL(url)
    }

    override def getStatistics()(implicit session: Session): String = {
      val url = "http://skriesim.lv/statistics"
      loadURL(url)
    }

    private def loadURL(url: String)(implicit session: Session) = httpProvider.loadURL(url)
  }

  trait SkriesimProvider {
    def getAthlete(id: Int)(implicit session: Session): String
    def getClub(id: Int)(implicit session: Session): String
    def getCoach(id: Int)(implicit session: Session): String
    def getRace(id: Int)(implicit session: Session): String

    def getAthletes()(implicit session: Session): String
    def getClubs()(implicit session: Session): String
    def getCoaches()(implicit session: Session): String
    def getStatistics()(implicit session: Session): String
  }
}
