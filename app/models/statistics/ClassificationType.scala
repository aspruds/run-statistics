package models.statistics

import models.statistics.metadata.{WithMetadata, WithName}
import org.joda.time.LocalDateTime

case class ClassificationType(
                   id: Long,
                   name: String,
                   createdAt: LocalDateTime,
                   updateAt: LocalDateTime,
                   updatedBy: Option[Long]) extends WithMetadata with WithName
