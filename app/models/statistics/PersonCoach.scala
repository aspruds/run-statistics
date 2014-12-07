package models.statistics

import models.statistics.metadata.WithMetadata
import org.joda.time.LocalDateTime

case class PersonCoach(
                        id: Long,
                        personId: Long,
                        coachId: Long,
                        updatedAt: Option[LocalDateTime],
                        updatedBy: Option[Long]) extends WithMetadata
