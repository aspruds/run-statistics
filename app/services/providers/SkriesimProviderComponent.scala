package services.providers

import scala.io.Source

trait SkriesimProviderComponent {
  val skriesimProvider: SkriesimProvider = new DefaultSkriesimProvider

  class DefaultSkriesimProvider extends SkriesimProvider {

    override def getAthlete(id: Int): String = {
      val url = "http://skriesim.lv/athletes?id=" + id
      Source.fromURL(url).mkString
    }

    override def getClub(id: Int): String = ""
    override def getCoach(id: Int): String = ""
    override def getRace(id: Int): String = ""

    override def getAthletes(): String = ""
    override def getClubs(): String = ""
    override def getCoaches(): String = ""
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
