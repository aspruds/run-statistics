package services.sportlat.providers

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.sportlat.id.RaceDistanceId
import services.http.providers.HttpProvider

@ImplementedBy(classOf[DefaultSportlatProvider])
trait SportlatProvider {
  def getRacesForYear(year: Long): String

  def getRaceMainDistance(id: Long): String

  def getRaceDistance(raceDistance: RaceDistanceId): String

  def getAthlete(id: Long): String
}

class DefaultSportlatProvider @Inject()(httpProvider: HttpProvider) extends SportlatProvider {
  override def getRacesForYear(year: Long): String = {
    val url = "http://www.sportlat.lv/index.php?calendar=all&year=" + year
    loadURL(url)
  }

  override def getRaceMainDistance(id: Long): String = {
    val url = "http://www.sportlat.lv/results.php?id=" + id
    loadURL(url)
  }

  override def getAthlete(id: Long): String = {
    val url = "http://www.sportlat.lv/index.php?id=user&uid=" + id
    loadURL(url)
  }

  override def getRaceDistance(raceDistance: RaceDistanceId): String = {
    val url = s"http://www.sportlat.lv/results.php${raceDistance.href}"
    loadURL(url)
  }

  private def loadURL(url: String) = httpProvider.loadURL(url)
}
