package models.statistics.db

import models.statistics.AgeGroup
import models.statistics.metadata.db.{WithMetadatasComponent, WithNamesComponent}
import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile

trait AgeGroupsTableComponent extends WithNamesComponent with WithMetadatasComponent{
  self: HasDatabaseConfig[JdbcProfile] =>

  import driver.api._
  import utils.db.PortableJodaSupport._

  class AgeGroupsTable(tag: Tag) extends Table[AgeGroup](tag, "age_groups") with WithMetadatas[AgeGroup] with WithNames[AgeGroup] {
    def * = (
      id,
      name,
      updatedAt,
      updatedBy) <>(AgeGroup.tupled, AgeGroup.unapply)
  }

}