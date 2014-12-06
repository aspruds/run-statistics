package models.statistics.db

import models.statistics.Person
import models.statistics.metadata.db.{WithRelations, WithMetadatas}
import org.joda.time.LocalDate
import play.api.db.slick.Config.driver.simple._
import utils.db.PortableJodaSupport._

class Persons(tag: Tag) extends Table[Person](tag, "persons")
with WithMetadatas[Person] with WithRelations[Person] {

  def givenName = column[String]("given_name", O.NotNull)

  def familyName = column[String]("family_name", O.NotNull)

  def dateOfBirth = column[Option[LocalDate]]("date_of_birth")

  def yearOfBirth = column[Option[Int]]("year_of_birth")

  def sex = column[String]("sex", O.NotNull)

  def countryId = column[Option[Long]]("country_id", O.NotNull)

  def isCoach = column[Boolean]("is_coach", O.NotNull)

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
    updatedAt,
    updatedBy) <>(Person.tupled, Person.unapply)
}