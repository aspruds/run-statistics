package models.statistics

import models.statistics.metadata.{WithName, WithMetadata}
import org.joda.time.LocalDateTime

case class VenueType(
                   id: Long,
                   name: String,
                   createdAt: LocalDateTime,
                   updateAt: LocalDateTime,
                   updatedBy: Option[Long]) extends WithMetadata with WithName
