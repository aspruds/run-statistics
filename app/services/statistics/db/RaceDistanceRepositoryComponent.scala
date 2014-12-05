package services.statistics.db

import models.statistics.RaceDistance
import models.statistics.db.RaceDistances
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}

trait RaceDistanceRepositoryComponent {

  val raceDistanceRepository: RaceDistanceRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultRaceDistanceRepository extends DefaultCRUDRepository[RaceDistance, RaceDistances] with RaceDistanceRepository {
    override val tableReference = TableQuery[RaceDistances]

    override def copyWithId(valueObject: RaceDistance, id: Long) = valueObject.copy(id=id)
  }

  trait RaceDistanceRepository extends CRUDRepository[RaceDistance]
}
