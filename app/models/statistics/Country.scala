package models.statistics

import models.statistics.metadata.Metadata
import org.joda.time.LocalDateTime

case class Country(
                   id: Long,
                   code: String,
                   name: String,
                   createdAt: LocalDateTime,
                   updateAt: LocalDateTime,
                   updatedBy: Option[Long]) extends Metadata
