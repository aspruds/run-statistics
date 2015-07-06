package services.statistics.db

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.statistics.RaceResult
import models.statistics.db.RaceResultsTable
import play.api.db.slick.DatabaseConfigProvider
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}
import utils.FutureUtils._

@ImplementedBy(classOf[DefaultRaceResultRepository])
trait RaceResultRepository extends CRUDRepository[RaceResult] {
  def exists(raceResult: RaceResult): Boolean
}

class DefaultRaceResultRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends DefaultCRUDRepository[RaceResult, RaceResultsTable] with RaceResultRepository {

  import driver.api._

  override val tableReference = TableQuery[RaceResultsTable]

  override def copyWithId(valueObject: RaceResult, id: Long) = valueObject.copy(id = id)

  override def exists(raceResult: RaceResult) = {
    val action = tableReference.filter(_.raceId === raceResult.raceId).
      filter(_.raceDistanceId === raceResult.raceDistanceId).
      filter(_.personId === raceResult.personId).
      result.headOption
    db.run(action).await().isDefined
  }
}
