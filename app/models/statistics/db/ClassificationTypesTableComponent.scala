package models.statistics.db

import models.statistics.ClassificationType
import models.statistics.metadata.db.{WithNamesComponent, WithMetadatasComponent, WithMetadatas, WithNames}
import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile

trait ClassificationTypesTableComponent extends WithMetadatasComponent with WithNamesComponent{
  self: HasDatabaseConfig[JdbcProfile] =>

  import driver.api._

  class ClassificationTypesTable(tag: Tag) extends Table[ClassificationType](tag, "classification_types")
  with WithMetadatas[ClassificationType] with WithNames[ClassificationType] {
    def * = (
      id,
      name,
      updatedAt,
      updatedBy) <>(ClassificationType.tupled, ClassificationType.unapply)
  }

}