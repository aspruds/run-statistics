package models.statistics.db

import models.statistics.VenueType
import models.statistics.metadata.db.{WithMetadatasComponent, WithNamesComponent}
import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile

trait VenueTypesTableComponent extends WithMetadatasComponent with WithNamesComponent {
  self: HasDatabaseConfig[JdbcProfile] =>

  import driver.api._
  import utils.db.PortableJodaSupport._

  class VenueTypesTable(tag: Tag) extends Table[VenueType](tag, "venue_types")
  with WithMetadatas[VenueType] with WithNames[VenueType] {
    def * = (
      id,
      name,
      updatedAt,
      updatedBy) <>(VenueType.tupled, VenueType.unapply)
  }

}