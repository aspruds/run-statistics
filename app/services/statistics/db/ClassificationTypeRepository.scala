package services.statistics.db

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.statistics.ClassificationType
import models.statistics.db.ClassificationTypesTable
import play.Logger
import play.api.cache.Cache
import play.api.db.slick.DatabaseConfigProvider
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}
import utils.FutureUtils._

@ImplementedBy(classOf[DefaultClassificationTypeRepository])
trait ClassificationTypeRepository extends CRUDRepository[ClassificationType] {
  def findByName(name: String): Option[ClassificationType]
}

class DefaultClassificationTypeRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends DefaultCRUDRepository[ClassificationType, ClassificationTypesTable]
  with ClassificationTypeRepository {

  import driver.api._

  override val tableReference = TableQuery[ClassificationTypesTable]

  override def copyWithId(valueObject: ClassificationType, id: Long) = valueObject.copy(id = id)

  override def findByName(name: String): Option[ClassificationType] = {
    Cache.getOrElse(s"ClassificationType.name.$name") {
      Logger.debug(s"finding ClassificationType by name: $name")
      val action = tableReference.filter(_.name === name).result.headOption
      db.run(action).await()
    }
  }
}