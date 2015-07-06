package services.statistics.db

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.statistics.DisciplineType
import models.statistics.db.DisciplineTypesTable
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}
import slick.driver.JdbcProfile

@ImplementedBy[DefaultDisciplineTypeRepository]
trait DisciplineTypeRepository extends CRUDRepository[DisciplineType]

class DefaultDisciplineTypeRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends DefaultCRUDRepository[DisciplineType, DisciplineTypesTable]
  with HasDatabaseConfigProvider[JdbcProfile] with DisciplineTypeRepository {

  import driver.api._

  override val tableReference = TableQuery[DisciplineTypesTable]

  override def copyWithId (valueObject: DisciplineType, id: Long) = valueObject.copy (id = id)
}

