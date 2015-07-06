package services.statistics.db

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.statistics.DistanceType
import models.statistics.db.DistanceTypesTable
import play.Logger
import play.api.cache.Cache
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}
import slick.driver.JdbcProfile
import utils.FutureUtils._

@ImplementedBy[DefaultDistanceTypeRepository]
trait DistanceTypeRepository extends CRUDRepository[DistanceType] {
  def findBySkriesimName(skriesimName: String): Option[DistanceType]
}

class DefaultDistanceTypeRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends DefaultCRUDRepository[DistanceType, DistanceTypesTable] with DistanceTypeRepository
  with HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  override val tableReference = TableQuery[DistanceTypesTable]

  override def copyWithId(valueObject: DistanceType, id: Long) = valueObject.copy(id = id)

  override def findBySkriesimName(skriesimName: String) = {
    Cache.getOrElse(s"DistanceType.skriesimName.$skriesimName") {
      Logger.debug(s"finding DistanceType by skriesimName: $skriesimName")
      val action = tableReference.filter(_.skriesimName === skriesimName.toLowerCase).result.headOption
      db.run(action).await()
    }
  }
}
