package models.statistics.db

import models.statistics.Country
import models.statistics.metadata.db.{WithMetadatasComponent, WithNamesComponent}
import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile

trait CountriesTableComponent extends WithMetadatasComponent with WithNamesComponent {
  self: HasDatabaseConfig[JdbcProfile] =>

  import driver.api._
  import utils.db.PortableJodaSupport._

  class CountriesTable(tag: Tag) extends Table[Country](tag, "countries") with WithMetadatas[Country] {

    def code = column[String]("code")

    def name = column[String]("name")

    def codeIdx = index("countries_idx_code", code, unique = true)

    def * = (
      id,
      code,
      name,
      updatedAt,
      updatedBy) <>(Country.tupled, Country.unapply)
  }

}