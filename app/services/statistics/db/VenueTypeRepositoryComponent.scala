package services.statistics.db

import models.statistics.{VenueType, Race}
import models.statistics.db.{VenueTypes, Races}
import play.Logger

trait VenueTypeRepositoryComponent {

  val venueTypeRepository: VenueTypeRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultRaceRepository extends VenueTypeRepository {
    val venueTypes = TableQuery[VenueTypes]

    private val racesAutoInc = {
      val insertInvoker = venueTypes returning venueTypes.map(_.id)
      insertInvoker into {
        case (url, id) => url.copy(id = id)
      }
    }

    override def insert(venueType: VenueType)(implicit session: Session): Race = {
      racesAutoInc.insert(venueType)
    }
  }

  trait VenueTypeRepository {
    def insert(venueType: VenueType)(implicit session: Session): VenueType
  }
}
