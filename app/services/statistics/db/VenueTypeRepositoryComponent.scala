package services.statistics.db

import models.statistics.VenueType
import models.statistics.db.VenueTypes
import play.Logger
import play.api.cache.Cache
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}
import play.api.Play.current

trait VenueTypeRepositoryComponent {

  val venueTypeRepository: VenueTypeRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultVenueTypeRepository extends DefaultCRUDRepository[VenueType, VenueTypes] with VenueTypeRepository {
    override val tableReference = TableQuery[VenueTypes]

    override def copyWithId(valueObject: VenueType, id: Long) = valueObject.copy(id = id)

    override def findByName(name: String)(implicit session: Session): Option[VenueType] = {
      Cache.getOrElse(s"VenueType.name.$name") {
        Logger.debug(s"finding VenueType by name: $name")
        tableReference.filter(_.name === name).firstOption
      }
    }
  }

  trait VenueTypeRepository extends CRUDRepository[VenueType] {
    def findByName(name: String)(implicit session: Session): Option[VenueType]
  }

}
