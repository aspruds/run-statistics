package models.statistics

import models.statistics.metadata.Metadata
import org.joda.time.LocalDateTime

case class AgeGroup(
                   id: Long,
                   name: String,
                   createdAt: LocalDateTime,
                   updateAt: LocalDateTime,
                   updatedBy: Option[Long]) extends Metadata
