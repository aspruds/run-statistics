package models.statistics.db

import models.statistics.Person
import models.statistics.metadata.db._
import org.joda.time.LocalDate
import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile
import utils.db.PortableJodaSupport._

trait PersonsTableComponent extends WithMetadatasComponent with WithNamesComponent with WithRelationsComponent {
  self: HasDatabaseConfig[JdbcProfile] =>

  import driver.api._

  class PersonsTable(tag: Tag) extends Table[Person](tag, "persons")
  with WithMetadatas[Person] with WithRelations[Person] {

    def givenName = column[String]("given_name")

    def familyName = column[String]("family_name")

    def dateOfBirth = column[Option[LocalDate]]("date_of_birth")

    def yearOfBirth = column[Option[Int]]("year_of_birth")

    def sex = column[Option[String]]("sex")

    def countryId = column[Option[Long]]("country_id")

    def isCoach = column[Boolean]("is_coach")

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

}