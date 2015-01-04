package services.statistics.db

import models.statistics.ClassificationType
import models.statistics.db.ClassificationTypes
import play.Logger
import play.api.cache.Cache
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}
import play.api.Play.current

trait ClassificationTypeRepositoryComponent {

  val classificationTypeRepository: ClassificationTypeRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultClassificationTypeRepository extends DefaultCRUDRepository[ClassificationType, ClassificationTypes] with ClassificationTypeRepository {
    override val tableReference = TableQuery[ClassificationTypes]

    override def copyWithId(valueObject: ClassificationType, id: Long) = valueObject.copy(id = id)

    override def findByName(name: String)(implicit session: Session): Option[ClassificationType] = {
      Cache.getOrElse(s"ClassificationType.name.$name") {
        Logger.debug(s"finding ClassificationType by name: $name")
        tableReference.filter(_.name === name).firstOption
      }
    }
  }

  trait ClassificationTypeRepository extends CRUDRepository[ClassificationType] {
    def findByName(name: String)(implicit session: Session): Option[ClassificationType]
  }

}
