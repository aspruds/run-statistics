package models.statistics.db

import models.statistics.PersonCoach
import org.joda.time.LocalDateTime
import play.api.db.slick.Config.driver.simple._
import utils.db.PortableJodaSupport._

class PersonsCoaches(tag: Tag) extends Table[PersonCoach](tag, "persons_coaches") {
  def id = column[Long]("id", O.AutoInc, O.PrimaryKey)

  def personId = column[Long]("person_id")

  def coachId = column[Long]("coach_id")

  def createdAt = column[LocalDateTime]("created_at", O.NotNull)

  def updatedAt = column[LocalDateTime]("updated_at", O.NotNull)

  def updatedById = column[Option[Long]]("updated_by")

  def * = (
    id,
    personId,
    coachId,
    createdAt,
    updatedAt,
    updatedById) <> (PersonCoach.tupled, PersonCoach.unapply)
}