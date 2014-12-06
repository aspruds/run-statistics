package models.statistics.db

import models.statistics.ClassificationType
import models.statistics.metadata.db.{WithMetadatas, WithNames}
import play.api.db.slick.Config.driver.simple._

class ClassificationTypes(tag: Tag) extends Table[ClassificationType](tag, "classification_types")
with WithMetadatas[ClassificationType] with WithNames[ClassificationType] {
  def * = (
    id,
    name,
    updatedAt,
    updatedBy) <> (ClassificationType.tupled, ClassificationType.unapply)
}