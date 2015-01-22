package services.sportlat

import models.sportlat.id.Total
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
          var mainDistances =
          for {
            raceId <- sportlatDataService.getRaceIds
            id <- raceId.id
          } yield (sportlatDataService.getRaceMainDistance(id))
          mainDistances.toStream
      }
    }

    override def importRaceDistances(): Seq[RaceDistance] = {
      val otherDistances = importMainRaceDistances.flatMap {
        raceDistance => raceDistance.otherRaceDistances
      }
      val otherTotalDistances = otherDistances.filter(_.evaluationType == Total())
      otherTotalDistances.map {
        case distance => sportlatDataService.getRaceDistance(distance)
      }.toStream
    }

    override def importAthletes(): Seq[Option[Athlete]] = {
      val athletes = for {
        distance <- importRaceDistances
        result <- distance.results
        athleteId <- result.athleteId
      } yield (sportlatDataService.getAthlete(athleteId))
      athletes.toStream
    }

    override def importAll() = {
      importMainRaceDistances()
    }
  }

  trait SportlatIntegrationService {
    def importMainRaceDistances(): Seq[RaceDistance]

    def importRaceDistances(): Seq[RaceDistance]

    def importAthletes(): Seq[Option[Athlete]]

    def importAll()
  }

}
