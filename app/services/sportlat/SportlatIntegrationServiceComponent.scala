package services.sportlat

import models.sportlat.{Athlete, RaceDistance}
import modules.DAL
import play.api.Logger
import play.api.Play.current
import play.api.db.slick.DB

trait SportlatIntegrationServiceComponent {
  this: SportlatDataServiceComponent with DAL =>

  val sportlatIntegrationService: SportlatIntegrationService

  class DefaultSportlatIntegrationService extends SportlatIntegrationService {

    override def importMainRaceDistances(): Seq[RaceDistance] = {
      Logger.info("Importing sportlat.lv main race results")

      DB.withSession {
        implicit session =>

          for {
            raceId <- sportlatDataService.getRaceIds
            id <- raceId.id
          } yield (sportlatDataService.getRaceMainDistance(id))
      }
    }

    override def importAthletes(): Seq[Option[Athlete]] = {
      for {
        distance <- importMainRaceDistances
        result <- distance.results
        athleteId <- result.athleteId
      } yield (sportlatDataService.getAthlete(athleteId))
    }

    override def importAll() = {
      importMainRaceDistances()
    }
  }

  trait SportlatIntegrationService {
    def importMainRaceDistances(): Seq[RaceDistance]

    def importAthletes(): Seq[Option[Athlete]]

    def importAll()
  }

}
