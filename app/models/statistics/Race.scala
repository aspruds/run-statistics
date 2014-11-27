package models.statistics

import models.statistics.metadata.{Relation, Metadata}
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
                 createdAt: LocalDateTime,
                 updateAt: LocalDateTime,
                 updatedBy: Option[Long]
                 ) extends Metadata with Relation
