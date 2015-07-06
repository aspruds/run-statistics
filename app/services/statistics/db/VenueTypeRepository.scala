package services.statistics.db

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.statistics.VenueType
import models.statistics.db.VenueTypesTable
import play.Logger
import play.api.cache.Cache
import play.api.db.slick.DatabaseConfigProvider
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}
import utils.FutureUtils._

@ImplementedBy(classOf[DefaultVenueTypeRepository])
trait VenueTypeRepository extends CRUDRepository[VenueType] {
  def findByName(name: String): Option[VenueType]
}

class DefaultVenueTypeRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends DefaultCRUDRepository[VenueType, VenueTypesTable] with VenueTypeRepository {

  import driver.api._

  override val tableReference = TableQuery[VenueTypesTable]

  override def copyWithId(valueObject: VenueType, id: Long) = valueObject.copy(id = id)

  override def findByName(name: String): Option[VenueType] = {
    Cache.getOrElse(s"VenueType.name.$name") {
      Logger.debug(s"finding VenueType by name: $name")
      val action = tableReference.filter(_.name === name).result.headOption
      db.run(action).await()
    }
  }
}
