package models.statistics

import models.statistics.metadata.WithMetadata
import org.joda.time.LocalDateTime

case class RaceResult(
                       id: Long,
                       raceId: Long,
                       raceDistanceId: Long,
                       personId: Long,
                       time: Option[Long],
                       distance: Option[BigDecimal],
                       points: Option[Short],
                       rank: Option[Int],
                       rankText: Option[String],
                       ageGroupId: Option[Long],
                       classificationTypeId: Option[Long],
                       wind: Option[BigDecimal],
                       updatedAt: Option[LocalDateTime],
                       updatedBy: Option[Long]) extends WithMetadata
