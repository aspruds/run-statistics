package services.statistics.db

import models.statistics.ClassificationType
import models.statistics.db.ClassificationTypes
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}

trait ClassificationTypeRepositoryComponent {

  val classificationTypeRepository: ClassificationTypeRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultClassificationTypeRepository extends DefaultCRUDRepository[ClassificationType, ClassificationTypes] with ClassificationTypeRepository {
    override val tableReference = TableQuery[ClassificationTypes]

    override def copyWithId(valueObject: ClassificationType, id: Long) = valueObject.copy(id=id)
  }

  trait ClassificationTypeRepository extends CRUDRepository[ClassificationType]
}
