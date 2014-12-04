package services.statistics.db

import models.statistics.DistanceType
import models.statistics.db.DistanceTypes
import services.statistics.db.support.CRUDRepository

trait DistanceTypeRepositoryComponent {

  val distanceTypeRepository: DistanceTypeRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultDistanceTypeRepository extends DistanceTypeRepository {
    val distanceTypes = TableQuery[DistanceTypes]

    private val distanceTypesAutoInc = {
      val insertInvoker = distanceTypes returning distanceTypes.map(_.id)
      insertInvoker into {
        case (url, id) => url.copy(id = id)
      }
    }

    override def insert(distanceType: DistanceType)(implicit session: Session): DistanceType = {
      distanceTypesAutoInc.insert(distanceType)
    }

    override def findBySkriesimName(skriesimName: String)(implicit session: Session) = {
      distanceTypes.filter(_.skriesimName === skriesimName).firstOption
    }
  }

  trait DistanceTypeRepository extends CRUDRepository[DistanceType] {
    def findBySkriesimName(skriesimName: String)(implicit session: Session): Option[DistanceType]
  }
}
