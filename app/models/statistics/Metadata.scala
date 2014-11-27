package models.statistics

import org.joda.time.LocalDateTime

trait Metadata {
  def createdAt: LocalDateTime
  def updateAt: LocalDateTime
  def updatedById: Option[Long]
}
