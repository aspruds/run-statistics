package models.statistics

import models.statistics.metadata.WithMetadata
import org.joda.time.LocalDateTime

case class RaceDistance(
                         id: Long,
                         raceId: Long,
                         distanceTypeId: Option[Long],
                         withQualification: Option[Boolean],
                         venueTypeId: Option[Long],
                         isCertified: Option[Boolean],
                         isElectronicTime: Option[Boolean],
                         updatedAt: Option[LocalDateTime],
                         updatedBy: Option[Long]) extends WithMetadata
