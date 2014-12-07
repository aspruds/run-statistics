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

    override def findByRaceIdAndDistanceTypeId(raceId: Long, distanceTypeId: Long)(implicit session: Session) = {
      tableReference.filter(rd => rd.raceId === raceId && rd.distanceTypeId === distanceTypeId).firstOption
    }

    def exists(raceDistance: RaceDistance)(implicit session: Session) = {
      tableReference.filter(_.raceId === raceDistance.raceId).
      filter(_.distanceTypeId === raceDistance.distanceTypeId).
      filter(_.venueTypeId === raceDistance.venueTypeId).
      filter(_.withQualification === raceDistance.withQualification).
      firstOption.isDefined
    }
  }

  trait RaceDistanceRepository extends CRUDRepository[RaceDistance] {
    def findByRaceIdAndDistanceTypeId(raceId: Long, distanceTypeId: Long)(implicit session: Session): Option[RaceDistance]

    def exists(raceDistance: RaceDistance)(implicit session: Session): Boolean
  }
}
