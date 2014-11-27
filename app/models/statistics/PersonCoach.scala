package models.statistics

import models.statistics.metadata.Metadata
import org.joda.time.LocalDateTime

case class PersonCoach(
                   id: Long,
                   personId: Long,
                   coachId: Long,
                   createdAt: LocalDateTime,
                   updateAt: LocalDateTime,
                   updatedBy: Option[Long]) extends Metadata
