package models.statistics

import models.statistics.metadata.WithMetadata
import org.joda.time.LocalDateTime

case class Country(
                   id: Long,
                   code: String,
                   name: String,
                   updatedAt: Option[LocalDateTime],
                   updatedBy: Option[Long]) extends WithMetadata
