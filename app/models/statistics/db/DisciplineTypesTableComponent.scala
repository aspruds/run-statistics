package models.statistics.db

import models.statistics.DisciplineType
import models.statistics.metadata.db.{WithMetadatasComponent, WithNamesComponent}
import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile

trait DisciplineTypesTableComponent extends WithMetadatasComponent with WithNamesComponent {
  self: HasDatabaseConfig[JdbcProfile] =>

  import driver.api._
  import utils.db.PortableJodaSupport._

  class DisciplineTypesTable(tag: Tag) extends Table[DisciplineType](tag, "discipline_types")
  with WithMetadatas[DisciplineType] with WithNames[DisciplineType] {
    def * = (
      id,
      name,
      updatedAt,
      updatedBy) <>(DisciplineType.tupled, DisciplineType.unapply)
  }
}