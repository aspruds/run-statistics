package models.statistics.db

import models.statistics.VenueType
import models.statistics.metadata.db.{WithMetadatas, WithNames}
import play.api.db.slick.Config.driver.simple._

class VenueTypes(tag: Tag) extends Table[VenueType](tag, "venue_types")
with WithMetadatas[VenueType] with WithNames[VenueType] {
  def * = (
    id,
    name,
    updatedAt,
    updatedBy) <> (VenueType.tupled, VenueType.unapply)
}