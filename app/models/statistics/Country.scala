package models.statistics

import models.statistics.metadata.WithMetadata
import org.joda.time.LocalDateTime

case class Country(
                   id: Long,
                   code: String,
                   name: String,
                   createdAt: LocalDateTime,
                   updateAt: LocalDateTime,
                   updatedBy: Option[Long]) extends WithMetadata
