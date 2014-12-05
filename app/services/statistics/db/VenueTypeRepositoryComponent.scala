package services.statistics.db

import models.statistics.VenueType
import models.statistics.db.VenueTypes
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}

trait VenueTypeRepositoryComponent {

  val venueTypeRepository: VenueTypeRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultVenueTypeRepository extends DefaultCRUDRepository[VenueType, VenueTypes] with VenueTypeRepository {
    override val tableReference = TableQuery[VenueTypes]

    override def copyWithId(valueObject: VenueType, id: Long) = valueObject.copy(id=id)
  }

  trait VenueTypeRepository extends CRUDRepository[VenueType]
}
