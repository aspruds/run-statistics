package services.statistics.db

import models.statistics.DistanceType
import models.statistics.db.DistanceTypes
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}

trait DistanceTypeRepositoryComponent {

  val distanceTypeRepository: DistanceTypeRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultDistanceTypeRepository extends DefaultCRUDRepository[DistanceType, DistanceTypes] with DistanceTypeRepository {
    override val tableReference = TableQuery[DistanceTypes]

    override def copyWithId(valueObject: DistanceType, id: Long) = valueObject.copy(id=id)

    override def findBySkriesimName(skriesimName: String)(implicit session: Session) = {
      tableReference.filter(_.skriesimName === skriesimName).firstOption
    }
  }

  trait DistanceTypeRepository extends CRUDRepository[DistanceType] {
    def findBySkriesimName(skriesimName: String)(implicit session: Session): Option[DistanceType]
  }
}
