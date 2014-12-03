package models.statistics

import models.statistics.metadata.{WithName, WithMetadata}
import org.joda.time.LocalDateTime

case class Club(
                   id: Long,
                   name: String,
                   countryId: Option[Long],
                   title: Option[String],
                   description: Option[String],
                   fullDescription: Option[String],
                   skriesimId: Option[Long],
                   createdAt: LocalDateTime,
                   updateAt: LocalDateTime,
                   updatedBy: Option[Long]) extends WithMetadata with WithName
