package models.statistics.db

import models.statistics.Club
import play.api.db.slick.Config.driver.simple._

class Clubs(tag: Tag) extends Table[Club](tag, "clubs") with Metadatas[Club] {

  def name = column[String]("name", O.NotNull)

  def countryId = column[Option[Long]]("country_id")

  def title = column[Option[String]]("title")

  def description = column[Option[String]]("description")

  def fullDescription = column[Option[String]]("full_description")

  def skriesimId = column[Option[Long]]("skriesim_id")

  def skriesimIdIdx = index("clubs_idx_skriesim_id", skriesimId, unique = true)

  def countryIdIdx = index("clubs_idx_country_id", countryId)

  def nameIdx = index("clubs_idx_name", name)

  def * = (
    id,
    name,
    countryId,
    title,
    description,
    fullDescription,
    skriesimId,
    createdAt,
    updatedAt,
    updatedBy) <> (Club.tupled, Club.unapply)
}