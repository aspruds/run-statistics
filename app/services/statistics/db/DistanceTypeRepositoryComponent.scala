package services.statistics.db

import models.statistics.DistanceType
import models.statistics.db.DistanceTypes
import play.Logger
import play.api.cache.Cache
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}
import play.api.Play.current

trait DistanceTypeRepositoryComponent {

  val distanceTypeRepository: DistanceTypeRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultDistanceTypeRepository extends DefaultCRUDRepository[DistanceType, DistanceTypes] with DistanceTypeRepository {
    override val tableReference = TableQuery[DistanceTypes]

    override def copyWithId(valueObject: DistanceType, id: Long) = valueObject.copy(id = id)

    override def findBySkriesimName(skriesimName: String)(implicit session: Session) = {
      Cache.getOrElse(s"DistanceType.skriesimName.$skriesimName") {
        Logger.debug(s"finding DistanceType by skriesimName: $skriesimName")
        tableReference.filter(_.skriesimName === skriesimName.toLowerCase).firstOption
      }
    }
  }

  trait DistanceTypeRepository extends CRUDRepository[DistanceType] {
    def findBySkriesimName(skriesimName: String)(implicit session: Session): Option[DistanceType]
  }

}
