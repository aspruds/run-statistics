package models.statistics.db

import models.statistics.DisciplineType
import models.statistics.metadata.db.{WithMetadatas, WithNames}
import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile

trait DisciplineTypesTableComponent {
  self: HasDatabaseConfig[JdbcProfile] =>

  import driver.api._

  class DisciplineTypesTable(tag: Tag) extends Table[DisciplineType](tag, "discipline_types")
  with WithMetadatas[DisciplineType] with WithNames[DisciplineType] {
    def * = (
      id,
      name,
      updatedAt,
      updatedBy) <>(DisciplineType.tupled, DisciplineType.unapply)
  }
}