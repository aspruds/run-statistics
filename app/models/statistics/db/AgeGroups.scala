package models.statistics.db

import models.statistics.AgeGroup
import models.statistics.metadata.db.Metadatas
import play.api.db.slick.Config.driver.simple._

class AgeGroups(tag: Tag) extends Table[AgeGroup](tag, "age_groups") with Metadatas[AgeGroup] {

  def name = column[String]("name", O.NotNull)

  def * = (
    id,
    name,
    createdAt,
    updatedAt,
    updatedBy) <> (AgeGroup.tupled, AgeGroup.unapply)
}