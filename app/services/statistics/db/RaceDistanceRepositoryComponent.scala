package services.statistics.db

import models.statistics.RaceDistance
import models.statistics.db.RaceDistances

trait RaceDistanceRepositoryComponent {

  val raceDistanceRepository: RaceDistanceRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultRaceDistanceRepository extends RaceDistanceRepository {
    val raceDistances = TableQuery[RaceDistances]

    private val raceDistancesAutoInc = {
      val insertInvoker = raceDistances returning raceDistances.map(_.id)
      insertInvoker into {
        case (url, id) => url.copy(id = id)
      }
    }

    override def insert(raceDistance: RaceDistance)(implicit session: Session): RaceDistance = {
      raceDistancesAutoInc.insert(raceDistance)
    }
  }

  trait RaceDistanceRepository {
    def insert(raceDistance: RaceDistance)(implicit session: Session): RaceDistance
  }
}
