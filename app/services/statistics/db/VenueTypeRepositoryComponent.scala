package services.statistics.db

import models.statistics.{Club, VenueType, Race}
import models.statistics.db.{VenueTypes, Races}
import play.Logger
import services.statistics.db.support.CRUDRepository

trait VenueTypeRepositoryComponent {

  val venueTypeRepository: VenueTypeRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultVenueTypeRepository extends VenueTypeRepository {
    val venueTypes = TableQuery[VenueTypes]

    private val venueTypesAutoInc = {
      val insertInvoker = venueTypes returning venueTypes.map(_.id)
      insertInvoker into {
        case (url, id) => url.copy(id = id)
      }
    }

    override def insert(venueType: VenueType)(implicit session: Session): VenueType = {
      venueTypesAutoInc.insert(venueType)
    }
  }

  trait VenueTypeRepository extends CRUDRepository[VenueType]
}
