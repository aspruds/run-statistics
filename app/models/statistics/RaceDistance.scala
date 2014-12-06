package models.statistics

import models.statistics.metadata.{WithName, WithMetadata}
import org.joda.time.LocalDateTime

case class RaceDistance(
                   id: Long,
                   raceId: Long,
                   distanceTypeId: Option[Long],
                   name: String,
                   isCertified: Option[Boolean],
                   isElectronicTime: Option[Boolean],
                   updatedAt: Option[LocalDateTime],
                   updatedBy: Option[Long]) extends WithMetadata with WithName
