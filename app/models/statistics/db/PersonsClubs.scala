package models.statistics.db

import models.statistics.PersonClub
import org.joda.time.LocalDateTime
import play.api.db.slick.Config.driver.simple._
import utils.db.PortableJodaSupport._

class PersonsClubs(tag: Tag) extends Table[PersonClub](tag, "persons_clubs") {
  def id = column[Long]("id", O.AutoInc, O.PrimaryKey)

  def personId = column[Long]("person_id")

  def clubId = column[Long]("club_id")

  def createdAt = column[LocalDateTime]("created_at", O.NotNull)

  def updatedAt = column[LocalDateTime]("updated_at", O.NotNull)

  def updatedById = column[Option[Long]]("updated_by")

  def * = (
    id,
    personId,
    clubId,
    createdAt,
    updatedAt,
    updatedById) <> (PersonClub.tupled, PersonClub.unapply)
}