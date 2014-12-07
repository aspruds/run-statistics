package models.statistics.db

import models.statistics.PersonClub
import models.statistics.metadata.db.WithMetadatas
import play.api.db.slick.Config.driver.simple._

class PersonsClubs(tag: Tag) extends Table[PersonClub](tag, "persons_clubs") with WithMetadatas[PersonClub] {

  def personId = column[Long]("person_id")

  def clubId = column[Long]("club_id")

  def * = (
    id,
    personId,
    clubId,
    updatedAt,
    updatedBy) <>(PersonClub.tupled, PersonClub.unapply)
}