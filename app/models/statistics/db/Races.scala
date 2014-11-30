package models.statistics.db

import models.statistics.Race
import models.statistics.metadata.db.{WithNames, WithRelations, WithMetadatas}
import org.joda.time.LocalDate
import play.api.db.slick.Config.driver.simple._
import utils.db.PortableJodaSupport._

class Races(tag: Tag) extends Table[Race](tag, "races")
with WithMetadatas[Race] with WithRelations[Race] with WithNames[Race]{

  def date = column[LocalDate]("race_date", O.NotNull)

  def countryId = column[Option[Long]]("country_id")

  def url = column[Option[String]]("url", O.NotNull)

  def * = (
    id,
    name,
    date,
    countryId,
    url,
    skriesimId,
    sportlatId,
    noskrienId,
    createdAt,
    updatedAt,
    updatedBy) <> (Race.tupled, Race.unapply)
}