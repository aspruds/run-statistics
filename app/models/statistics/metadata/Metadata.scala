package models.statistics.metadata

import org.joda.time.LocalDateTime

trait Metadata {
  def createdAt: LocalDateTime
  def updateAt: LocalDateTime
  def updatedBy: Option[Long]
}
