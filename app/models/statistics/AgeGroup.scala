package models.statistics

import models.statistics.metadata.{WithName, WithMetadata}
import org.joda.time.LocalDateTime

case class AgeGroup(
                   id: Long,
                   name: String,
                   updatedAt: Option[LocalDateTime],
                   updatedBy: Option[Long]) extends WithMetadata with WithName
