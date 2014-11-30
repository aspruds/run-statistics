package models.statistics

import models.statistics.metadata.WithMetadata
import org.joda.time.LocalDateTime

case class RaceDistance(
                   id: Long,
                   raceId: Long,
                   distanceTypeId: Option[Long],
                   name: String,
                   isCertified: Option[Boolean],
                   isElectronicTime: Option[Boolean],
                   createdAt: LocalDateTime,
                   updateAt: LocalDateTime,
                   updatedBy: Option[Long]) extends WithMetadata
