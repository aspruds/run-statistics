package models.statistics

import models.statistics.metadata.Metadata
import org.joda.time.LocalDateTime

case class PersonClub(
                   id: Long,
                   personId: Long,
                   clubId: Long,
                   createdAt: LocalDateTime,
                   updateAt: LocalDateTime,
                   updatedBy: Option[Long]) extends Metadata
