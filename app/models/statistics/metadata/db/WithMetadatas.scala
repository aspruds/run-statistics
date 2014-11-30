package models.statistics.metadata.db

import models.statistics.metadata.WithMetadata
import org.joda.time.LocalDateTime
import play.api.db.slick.Config.driver.simple._
import utils.db.PortableJodaSupport._

trait WithMetadatas[T <: WithMetadata] extends Table[T] {
  def id = column[Long]("id", O.AutoInc, O.PrimaryKey)

  def createdAt = column[LocalDateTime]("created_at", O.NotNull)

  def updatedAt = column[LocalDateTime]("updated_at", O.NotNull)

  def updatedBy = column[Option[Long]]("updated_by")
}
