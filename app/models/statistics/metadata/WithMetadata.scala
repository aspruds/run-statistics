package models.statistics.metadata

import org.joda.time.LocalDateTime

trait WithMetadata {
  def id: Long
  def updatedAt: Option[LocalDateTime]
  def updatedBy: Option[Long]
}
