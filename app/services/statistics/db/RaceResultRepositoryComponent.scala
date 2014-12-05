package services.statistics.db

import models.statistics.{Race, RaceResult, RaceDistance}
import models.statistics.db.{Races, RaceResults, RaceDistances}
import services.statistics.db.support.{DefaultCRUDRepository, CRUDRepository}

trait RaceResultRepositoryComponent {

  val raceResultRepository: RaceResultRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultRaceResultRepository extends DefaultCRUDRepository[RaceResult, RaceResults] with RaceResultRepository {
    override val tableReference = TableQuery[RaceResults]

    override def copyWithId(valueObject: RaceResult, id: Long) = valueObject.copy(id=id)
  }

  trait RaceResultRepository extends CRUDRepository[RaceResult]
}
