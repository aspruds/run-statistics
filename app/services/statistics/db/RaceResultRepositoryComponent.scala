package services.statistics.db

import models.statistics.RaceResult
import models.statistics.db.RaceResults
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}

trait RaceResultRepositoryComponent {

  val raceResultRepository: RaceResultRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultRaceResultRepository extends DefaultCRUDRepository[RaceResult, RaceResults] with RaceResultRepository {
    override val tableReference = TableQuery[RaceResults]

    override def copyWithId(valueObject: RaceResult, id: Long) = valueObject.copy(id = id)
  }

  trait RaceResultRepository extends CRUDRepository[RaceResult]

}
