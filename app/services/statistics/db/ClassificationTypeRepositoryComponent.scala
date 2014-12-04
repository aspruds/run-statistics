package services.statistics.db

import models.statistics.ClassificationType
import models.statistics.db.ClassificationTypes
import services.statistics.db.support.CRUDRepository

trait ClassificationTypeRepositoryComponent {

  val classificationTypeRepository: ClassificationTypeRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultVenueTypeRepository extends ClassificationTypeRepository {
    val classificationTypes = TableQuery[ClassificationTypes]

    private val classificationTypesAutoInc = {
      val insertInvoker = classificationTypes returning classificationTypes.map(_.id)
      insertInvoker into {
        case (url, id) => url.copy(id = id)
      }
    }

    override def insert(classificationType: ClassificationType)(implicit session: Session): ClassificationType = {
      classificationTypesAutoInc.insert(classificationType)
    }
  }

  trait ClassificationTypeRepository extends CRUDRepository[ClassificationType]
}
