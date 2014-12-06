package models.statistics

import models.statistics.metadata.{WithMetadata, WithName}
import org.joda.time.LocalDateTime

case class ClassificationType(
                   id: Long,
                   name: String,
                   updatedAt: Option[LocalDateTime],
                   updatedBy: Option[Long]) extends WithMetadata with WithName
