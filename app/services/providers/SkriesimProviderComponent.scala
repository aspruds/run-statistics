package services.providers

import scala.io.Source

trait SkriesimProviderComponent {
  val skriesimProvider: SkriesimProvider = new DefaultSkriesimProvider

  class DefaultSkriesimProvider extends SkriesimProvider with HttpProviderComponent {

    override def getAthlete(id: Int): String = {
      val url = "http://skriesim.lv/athletes?id=" + id
      loadURL(url)
    }

    override def getClub(id: Int): String = {
      val url = "http://skriesim.lv/clubs?id=" + id
      loadURL(url)
    }

    override def getCoach(id: Int): String = {
      getAthlete(id)
    }

    override def getRace(id: Int): String = ""

    override def getAthletes(): String = {
      val url = "http://skriesim.lv/athletes"
      loadURL(url)
    }

    override def getClubs(): String = {
      val url = "http://skriesim.lv/clubs"
      loadURL(url)
    }

    override def getCoaches(): String = {
      val url = "http://skriesim.lv/coaches"
      loadURL(url)
    }

    override def getRaces(): String = {
      val url = "http://skriesim.lv/statistics"
      loadURL(url)
    }

    private def loadURL(url: String) = httpProvider.loadURL(url)
  }

  trait SkriesimProvider {
    def getAthlete(id: Int): String
    def getClub(id: Int): String
    def getCoach(id: Int): String
    def getRace(id: Int): String

    def getAthletes(): String
    def getClubs(): String
    def getCoaches(): String
    def getRaces(): String
  }
}
