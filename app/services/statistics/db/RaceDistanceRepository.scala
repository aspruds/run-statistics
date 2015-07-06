package services.statistics.db

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.statistics.RaceDistance
import models.statistics.db.RaceDistances
import play.Logger
import play.api.db.slick.DatabaseConfigProvider
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}
import utils.FutureUtils._

@ImplementedBy[DefaultRaceDistanceRepository]
trait RaceDistanceRepository extends CRUDRepository[RaceDistance] {
  def find(raceDistance: RaceDistance): Option[RaceDistance]

  def exists(raceDistance: RaceDistance): Boolean
}

class DefaultRaceDistanceRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends DefaultCRUDRepository[RaceDistance, RaceDistances] with RaceDistanceRepository {

  import driver.api._

  override val tableReference = TableQuery[RaceDistances]

  override def copyWithId(valueObject: RaceDistance, id: Long) = valueObject.copy(id = id)

  override def find(raceDistance: RaceDistance) = {
    Logger.debug(s"finding RaceDistance by example: $raceDistance")
    getFirst(
      raceDistance.raceId,
      raceDistance.distanceTypeId,
      raceDistance.venueTypeId,
      raceDistance.withQualification
    )
  }

  def exists(raceDistance: RaceDistance) = {
    find(raceDistance).isDefined
  }

  private def getFirst(raceId: Long,
                       distanceTypeId: Option[Long],
                       venueTypeId: Option[Long],
                       withQualification: Option[Boolean]) = {

    val action = tableReference.filter(_.raceId === raceId).
      filter(_.distanceTypeId === distanceTypeId).
      filter(_.venueTypeId === venueTypeId).
      filter(row => row.withQualification.isEmpty || row.withQualification === withQualification).
      result.headOption
    db.run(action).await()
  }
}
