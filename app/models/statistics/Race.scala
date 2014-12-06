package models.statistics

import models.statistics.metadata.{WithName, WithRelation, WithMetadata}
import org.joda.time.{LocalDate, LocalDateTime}

case class Race(
                 id: Long,
                 name: String,
                 date: LocalDate,
                 countryId: Option[Long],
                 url: Option[String],
                 skriesimId: Option[Long],
                 sportlatId: Option[Long],
                 noskrienId: Option[Long],
                 updatedAt: Option[LocalDateTime],
                 updatedBy: Option[Long]
                 ) extends WithMetadata with WithRelation with WithName
