package models.statistics.metadata.db

import org.joda.time.LocalDateTime
import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile

trait WithMetadatasComponent {
  self: HasDatabaseConfig[JdbcProfile] =>

  import driver.api._

  import utils.db.PortableJodaSupport._

  trait WithMetadatas[T] {
    self: Table[T] =>

    def id = column[Long]("id", O.AutoInc, O.PrimaryKey)

    def updatedAt = column[Option[LocalDateTime]]("updated_at")

    def updatedBy = column[Option[Long]]("updated_by")
  }

}