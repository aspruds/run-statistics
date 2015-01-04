package services.statistics.db

import models.statistics.RaceDistance
import models.statistics.db.RaceDistances
import play.Logger
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}

trait RaceDistanceRepositoryComponent {

  val raceDistanceRepository: RaceDistanceRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultRaceDistanceRepository extends DefaultCRUDRepository[RaceDistance, RaceDistances] with RaceDistanceRepository {
    override val tableReference = TableQuery[RaceDistances]

    override def copyWithId(valueObject: RaceDistance, id: Long) = valueObject.copy(id = id)

    override def find(raceDistance: RaceDistance)(implicit session: Session) = {
      Logger.debug(s"finding RaceDistance by example: $raceDistance")
      getFirst(
        raceDistance.raceId,
        raceDistance.distanceTypeId,
        raceDistance.venueTypeId,
        raceDistance.withQualification
      )
    }

    def exists(raceDistance: RaceDistance)(implicit session: Session) = {
      find(raceDistance).isDefined
    }

    private def getFirst(raceId: Long,
                         distanceTypeId: Option[Long],
                         venueTypeId: Option[Long],
                         withQualification: Option[Boolean])(implicit session: Session) = {

      tableReference.filter(_.raceId === raceId).
        filter(_.distanceTypeId === distanceTypeId).
        filter(_.venueTypeId === venueTypeId).
        filter(row => row.withQualification.isEmpty || row.withQualification === withQualification).
        firstOption
    }
  }

  trait RaceDistanceRepository extends CRUDRepository[RaceDistance] {
    def find(raceDistance: RaceDistance)(implicit session: Session): Option[RaceDistance]

    def exists(raceDistance: RaceDistance)(implicit session: Session): Boolean
  }

}
