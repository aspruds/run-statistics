package services.sportlat

import models.sportlat.id.{RaceDistanceId, RaceId, Total}
import models.sportlat.{Athlete, RaceDistance, RaceResult}
import modules.DAL
import play.api.Logger

trait SportlatIntegrationServiceComponent {
  this: SportlatDataServiceComponent with DAL =>

  val sportlatIntegrationService: SportlatIntegrationService

  case class Result(raceId: RaceId, distance: RaceDistance, result: RaceResult, athlete: Athlete)

  case class RaceWithDistance(raceId: RaceId, distance: RaceDistance)

  class DefaultSportlatIntegrationService extends SportlatIntegrationService {

    override def getMainRaceDistances(): Seq[RaceWithDistance] = {
      Logger.info("Loading sportlat.lv main race distances")

      def raceWithDistance(raceId: RaceId, distanceId: Long) = {
        RaceWithDistance(raceId, sportlatDataService.getRaceMainDistance(distanceId))
      }

      for {
        raceId <- sportlatDataService.getRaceIds
        distanceId <- raceId.id
      } yield raceWithDistance(raceId, distanceId)
    }

    override def getRaceDistances(mainRaceDistances: Seq[RaceWithDistance]): Seq[RaceWithDistance] = {
      Logger.info("Loading sportlat.lv race distances")

      def raceWithDistance(raceId: RaceId, distanceId: RaceDistanceId) = {
        RaceWithDistance(raceId, sportlatDataService.getRaceDistance(distanceId))
      }

      for {
        raceDistance <- mainRaceDistances
        id <- raceDistance.distance.otherRaceDistances if (id.evaluationType == Total())
      } yield raceWithDistance(raceDistance.raceId, id)
    }

    override def getResults(raceDistances: Seq[RaceWithDistance]): Seq[Result] = {
      Logger.info("Loading sportlat.lv race results")

      for {
        raceDistance <- raceDistances
        raceResult <- raceDistance.distance.results
        athleteId <- raceResult.athleteId
        athlete <- sportlatDataService.getAthlete(athleteId)
      } yield Result(raceDistance.raceId, raceDistance.distance, raceResult, athlete)
    }

    override def getResults(): Seq[Result]  = {
      val mainRaceDistances = getMainRaceDistances()
      val raceDistances = getRaceDistances(mainRaceDistances)
      getResults(raceDistances)
    }
  }

  trait SportlatIntegrationService {
    def getMainRaceDistances(): Seq[RaceWithDistance]

    def getRaceDistances(mainRaceDistances: Seq[RaceWithDistance]): Seq[RaceWithDistance]

    def getResults(distances: Seq[RaceWithDistance]): Seq[Result]

    def getResults(): Seq[Result]
  }

}
