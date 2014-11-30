package models.statistics.metadata

import org.joda.time.LocalDateTime

trait WithMetadata {
  def id: Long
  def createdAt: LocalDateTime
  def updateAt: LocalDateTime
  def updatedBy: Option[Long]
}
