package models.statistics.db

import models.statistics.PersonClub
import models.statistics.metadata.db.{WithMetadatasComponent, WithNamesComponent}
import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile

trait PersonsClubsTableComponent extends WithMetadatasComponent with WithNamesComponent {
  self: HasDatabaseConfig[JdbcProfile] =>

  import driver.api._
  import utils.db.PortableJodaSupport._

  class PersonsClubsTable(tag: Tag) extends Table[PersonClub](tag, "persons_clubs") with WithMetadatas[PersonClub] {

    def personId = column[Long]("person_id")

    def clubId = column[Long]("club_id")

    def * = (
      id,
      personId,
      clubId,
      updatedAt,
      updatedBy) <>(PersonClub.tupled, PersonClub.unapply)
  }

}