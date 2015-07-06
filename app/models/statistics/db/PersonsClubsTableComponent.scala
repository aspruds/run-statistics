package models.statistics.db

import models.statistics.PersonClub
import models.statistics.metadata.db.WithMetadatas
import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile

trait PersonsClubsTableComponent {
  self: HasDatabaseConfig[JdbcProfile] =>

  import driver.api._

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