package models.statistics.db

import models.statistics.AgeGroup
import models.statistics.metadata.db.{WithMetadatas, WithNames}
import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile

trait AgeGroupsTableComponent {
  self: HasDatabaseConfig[JdbcProfile] =>

  import driver.api._

  class AgeGroupsTable(tag: Tag) extends Table[AgeGroup](tag, "age_groups")
  with WithMetadatas[AgeGroup] with WithNames[AgeGroup] {
    def * = (
      id,
      name,
      updatedAt,
      updatedBy) <>(AgeGroup.tupled, AgeGroup.unapply)
  }

}