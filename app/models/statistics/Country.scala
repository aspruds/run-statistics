package models.statistics

import org.joda.time.LocalDateTime

case class Country(
                   id: Long,
                   code: String,
                   name: String,
                   createdAt: LocalDateTime,
                   updateAt: LocalDateTime,
                   updatedById: Option[Long]) extends Metadata
