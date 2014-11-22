package models.statistics.db

import models.statistics.Club
import org.joda.time.LocalDateTime
import play.api.db.slick.Config.driver.simple._
import utils.db.PortableJodaSupport._

class Clubs(tag: Tag) extends Table[Club](tag, "clubs") {
  def id = column[Long]("id", O.AutoInc, O.PrimaryKey)

  def name = column[String]("name", O.NotNull)

  def countryId = column[Option[Long]]("country_id")

  def title = column[Option[String]]("title")

  def description = column[Option[String]]("description")

  def fullDescription = column[Option[String]]("full_description")

  def skriesimId = column[Option[Long]]("skriesim_id")

  def createdAt = column[LocalDateTime]("created_at", O.NotNull)

  def updatedAt = column[LocalDateTime]("updated_at", O.NotNull)

  def updatedById = column[Option[Long]]("updated_by")

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
    updatedById) <> (Club.tupled, Club.unapply)
}