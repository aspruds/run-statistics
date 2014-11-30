package models.statistics.db

import models.statistics.AgeGroup
import models.statistics.metadata.db.{WithMetadatas, WithNames}
import play.api.db.slick.Config.driver.simple._

class AgeGroups(tag: Tag) extends Table[AgeGroup](tag, "age_groups")
with WithMetadatas[AgeGroup] with WithNames[AgeGroup] {
  def * = (
    id,
    name,
    createdAt,
    updatedAt,
    updatedBy) <> (AgeGroup.tupled, AgeGroup.unapply)
}