package models.statistics

import models.statistics.metadata.WithMetadata
import org.joda.time.LocalDateTime

case class VenueType(
                   id: Long,
                   name: Long,
                   createdAt: LocalDateTime,
                   updateAt: LocalDateTime,
                   updatedBy: Option[Long]) extends WithMetadata
