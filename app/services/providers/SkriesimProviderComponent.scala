package services.providers

import scala.io.Source

trait SkriesimProviderComponent {
  val skriesimProvider: SkriesimProvider = new DefaultSkriesimProvider

  class DefaultSkriesimProvider extends SkriesimProvider with HttpProviderComponent {

    override def getAthlete(id: Int): String = {
      val url = " http://skriesim.lv/athletes?id=" + id
      httpProvider.loadURL(url)
    }

    override def getClub(id: Int): String = ""

    override def getCoach(id: Int): String = getAthlete(id)

    override def getRace(id: Int): String = ""

    override def getAthletes(): String = {
      val url = "http://skriesim.lv/athletes"
      httpProvider.loadURL(url)
    }

    override def getClubs(): String = {
      val url = "http://skriesim.lv/clubs"
      httpProvider.loadURL(url)
    }

    override def getCoaches(): String = {
      val url = "http://skriesim.lv/coaches"
      httpProvider.loadURL(url)
    }

    override def getRaces(): String = ""
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
