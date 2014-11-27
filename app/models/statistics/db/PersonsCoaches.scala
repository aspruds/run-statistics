package models.statistics.db

import models.statistics.PersonCoach
import models.statistics.metadata.db.Metadatas
import play.api.db.slick.Config.driver.simple._

class PersonsCoaches(tag: Tag) extends Table[PersonCoach](tag, "persons_coaches") with Metadatas[PersonCoach] {

  def personId = column[Long]("person_id")

  def coachId = column[Long]("coach_id")

  def * = (
    id,
    personId,
    coachId,
    createdAt,
    updatedAt,
    updatedBy) <> (PersonCoach.tupled, PersonCoach.unapply)
}