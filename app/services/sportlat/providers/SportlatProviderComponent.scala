package services.sportlat.providers

import services.http.providers.HttpProviderComponent

trait SportlatProviderComponent {

  val sportlatProvider: SportlatProvider

  class DefaultSportlatProvider extends SportlatProvider {
    this: HttpProviderComponent =>

    override def getRacesForYear(year: Long): String = {
      val url = "http://www.sportlat.lv/index.php?calendar=all&year=" + year
      loadURL(url)
    }

    override def getRaceResults(id: Long): String = {
      val url = "http://www.sportlat.lv/results.php?id=" + id
      loadURL(url)
    }

    override def getAthlete(id: Long): String = {
      val url = "http://www.sportlat.lv/index.php?id=user&uid=" + id
      loadURL(url)
    }

    private def loadURL(url: String) = httpProvider.loadURL(url)
  }

  trait SportlatProvider {
    def getRacesForYear(year: Long): String

    def getRaceResults(id: Long): String

    def getAthlete(id: Long): String
  }

}
