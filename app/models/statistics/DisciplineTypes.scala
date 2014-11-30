package models.statistics

import models.statistics.metadata.db.{WithMetadatas, WithNames}
import play.api.db.slick.Config.driver.simple._

class DisciplineTypes(tag: Tag) extends Table[DisciplineType](tag, "discipline_types")
with WithMetadatas[DisciplineType] with WithNames[DisciplineType] {
  def * = (
    id,
    name,
    createdAt,
    updatedAt,
    updatedBy) <> (DisciplineType.tupled, DisciplineType.unapply)
}