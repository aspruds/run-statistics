package models.statistics.db

import models.statistics.Country
import models.statistics.metadata.db.WithMetadatas
import play.api.db.slick.Config.driver.simple._

class Countries(tag: Tag) extends Table[Country](tag, "countries") with WithMetadatas[Country] {

  def code = column[String]("code", O.NotNull)

  def name = column[String]("name", O.NotNull)

  def codeIdx = index("countries_idx_code", code, unique = true)

  def * = (
    id,
    code,
    name,
    createdAt,
    updatedAt,
    updatedBy) <> (Country.tupled, Country.unapply)
}