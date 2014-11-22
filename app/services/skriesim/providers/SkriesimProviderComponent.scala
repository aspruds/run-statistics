package services.skriesim.providers

import services.http.providers.HttpProviderComponent

trait SkriesimProviderComponent {

  val skriesimProvider: SkriesimProvider

  class DefaultSkriesimProvider extends SkriesimProvider {
    this: HttpProviderComponent =>

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

    override def getRace(id: Int): String = {
      val url = "http://skriesim.lv/calendar?id=" + id
      loadURL(url)
    }

    override def getAthleteIds: String = {
      val url = "http://skriesim.lv/athletes"
      loadURL(url)
    }

    override def getClubIds: String = {
      val url = "http://skriesim.lv/clubs"
      loadURL(url)
    }

    override def getCoachIds: String = {
      val url = "http://skriesim.lv/coaches"
      loadURL(url)
    }

    override def getStatisticsIds: String = {
      val url = "http://skriesim.lv/statistics"
      loadURL(url)
    }

    override def getStatisticsIdsEnglish: String = {
      val url = "http://skriesim.lv/en/statistics"
      loadURL(url)
    }

    private def loadURL(url: String) = httpProvider.loadURL(url)
  }

  trait SkriesimProvider {
    def getAthlete(id: Int): String
    def getClub(id: Int): String
    def getCoach(id: Int): String
    def getRace(id: Int): String

    def getAthleteIds: String
    def getClubIds: String
    def getCoachIds: String
    def getStatisticsIds: String
    def getStatisticsIdsEnglish: String
  }
}
