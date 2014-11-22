package models.statistics.db

import models.statistics.Person
import org.joda.time.{LocalDate, LocalDateTime}
import play.api.db.slick.Config.driver.simple._
import utils.db.PortableJodaSupport._

class Persons(tag: Tag) extends Table[Person](tag, "persons") {
  def id = column[Long]("id", O.AutoInc, O.PrimaryKey)

  def givenName = column[String]("given_name", O.NotNull)

  def familyName = column[String]("family_name", O.NotNull)

  def dateOfBirth = column[Option[LocalDate]]("date_of_birth")

  def yearOfBirth = column[Option[Int]]("year_of_birth")

  def sex = column[String]("sex", O.NotNull)

  def countryId = column[Option[Long]]("country_id", O.NotNull)

  def skriesimId = column[Option[Long]]("skriesim_id", O.NotNull)

  def sportlatId = column[Option[Long]]("sportlat_id", O.NotNull)

  def noskrienId = column[Option[Long]]("noskrien_id", O.NotNull)

  def isCoach = column[Boolean]("is_coach", O.NotNull)

  def createdAt = column[LocalDateTime]("created_at", O.NotNull)

  def updatedAt = column[LocalDateTime]("updated_at", O.NotNull)

  def updatedBy = column[Option[Long]]("updated_by")

  def skriesimIdIdx = index("persons_idx_skriesim_id", skriesimId, unique = true)

  def sportlatIdIdx = index("persons_idx_sportlat_id", sportlatId, unique = true)

  def noskrienIdIdx = index("persons_idx_noskrien_id", noskrienId, unique = true)

  def countryIdIdx = index("persons_idx_country_id", countryId)

  def sexIdx = index("persons_idx_sex", sex)

  def isCoachIdx = index("persons_idx_is_coach", isCoach)

  def givenNameIdx = index("persons_idx_given_name", givenName)

  def familyNameIdx = index("persons_idx_family_name", familyName)

  def * = (
    id,
    givenName,
    familyName,
    dateOfBirth,
    yearOfBirth,
    sex,
    countryId,
    skriesimId,
    sportlatId,
    noskrienId,
    isCoach,
    createdAt,
    updatedAt,
    updatedBy) <> (Person.tupled, Person.unapply)
}