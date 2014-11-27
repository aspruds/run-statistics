package models.statistics

import org.joda.time.LocalDateTime

case class Race(
                 id: Long,
                 name: String,
                 date: String,
                 countryCode: String,
                 url: Option[String],
                 createdAt: LocalDateTime,
                 updateAt: LocalDateTime,
                 updatedById: Option[Long]
                 ) extends Metadata
