package models.statistics

import org.joda.time.LocalDateTime

case class PersonCoach(
                   id: Long,
                   personId: Long,
                   coachId: Long,
                   createdAt: LocalDateTime,
                   updateAt: LocalDateTime,
                   updatedById: Option[Long]) extends Metadata
