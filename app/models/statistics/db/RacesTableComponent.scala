package models.statistics.db

import models.statistics.Race
import models.statistics.metadata.db.{WithMetadatas, WithNames, WithRelations}
import org.joda.time.LocalDate
import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile
import utils.db.PortableJodaSupport._

trait RacesTableComponent {
  self: HasDatabaseConfig[JdbcProfile] =>

  import driver.api._

  class RacesTable(tag: Tag) extends Table[Race](tag, "races")
  with WithMetadatas[Race] with WithRelations[Race] with WithNames[Race] {

    def date = column[LocalDate]("race_date", O.NotNull)

    def countryId = column[Option[Long]]("country_id")

    def location = column[Option[String]]("location", O.NotNull)

    def url = column[Option[String]]("url", O.NotNull)

    def * = (
      id,
      name,
      date,
      countryId,
      location,
      url,
      skriesimId,
      sportlatId,
      noskrienId,
      updatedAt,
      updatedBy) <>(Race.tupled, Race.unapply)
  }

}