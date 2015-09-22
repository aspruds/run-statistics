package models.statistics.db

import models.statistics.PersonCoach
import models.statistics.metadata.db.{WithNamesComponent, WithMetadatasComponent, WithMetadatas}
import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile

trait PersonsCoachesTableComponent extends WithMetadatasComponent with WithNamesComponent {
  self: HasDatabaseConfig[JdbcProfile] =>

  import driver.api._

  class PersonsCoachesTable(tag: Tag) extends Table[PersonCoach](tag, "persons_coaches") with WithMetadatas[PersonCoach] {

    def personId = column[Long]("person_id")

    def coachId = column[Long]("coach_id")

    def * = (
      id,
      personId,
      coachId,
      updatedAt,
      updatedBy) <>(PersonCoach.tupled, PersonCoach.unapply)
  }

}