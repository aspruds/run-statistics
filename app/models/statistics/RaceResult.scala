package models.statistics

import models.statistics.metadata.{WithMetadata, WithName}
import org.joda.time.LocalDateTime

case class RaceResult(
                   id: Long,
                   raceId: Long,
                   raceDistanceId: Long,
                   time: Option[Long],
                   distance: Option[BigDecimal],
                   points: Option[Short],
                   rank: Option[Short],
                   rankText: Option[String],
                   ageGroupId: Option[Long],
                   classificationTypeId: Option[Long],
                   wind: Option[BigDecimal],
                   updatedAt: Option[LocalDateTime],
                   updatedBy: Option[Long]) extends WithMetadata
