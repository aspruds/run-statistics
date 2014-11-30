package models.statistics

import models.statistics.metadata.{WithName, WithMetadata}
import org.joda.time.LocalDateTime

case class DistanceType(
                         id: Long,
                         name: String,
                         skriesimName: Option[String],
                         distance: Option[Long],
                         weight: Option[Short],
                         disciplineTypeId: Option[Long],
                         venueTypeId: Option[Long],
                         isStandard: Boolean,
                         createdAt: LocalDateTime,
                         updateAt: LocalDateTime,
                         updatedBy: Option[Long]) extends WithMetadata with WithName
