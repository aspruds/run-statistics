package services.statistics.db

import models.statistics.DisciplineType
import models.statistics.db.DisciplineTypes
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}

trait DisciplineTypeRepositoryComponent {

  val disciplineTypeRepository: DisciplineTypeRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultDisciplineTypeRepository extends DefaultCRUDRepository[DisciplineType, DisciplineTypes] with DisciplineTypeRepository {
    override val tableReference = TableQuery[DisciplineTypes]

    override def copyWithId(valueObject: DisciplineType, id: Long) = valueObject.copy(id=id)
  }

  trait DisciplineTypeRepository extends CRUDRepository[DisciplineType]
}
