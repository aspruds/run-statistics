package models.statistics

import models.statistics.metadata.{WithMetadata, WithName, WithRelation}
import org.joda.time.{LocalDate, LocalDateTime}

case class Race(
                 id: Long,
                 name: String,
                 date: LocalDate,
                 countryId: Option[Long],
                 location: Option[String],
                 url: Option[String],
                 skriesimId: Option[Long],
                 sportlatId: Option[Long],
                 noskrienId: Option[Long],
                 updatedAt: Option[LocalDateTime],
                 updatedBy: Option[Long]
                 ) extends WithMetadata with WithRelation with WithName
