package models.statistics.db

import models.statistics.Country
import models.statistics.metadata.db.WithMetadatas
import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile

trait CountriesTableComponent {
  self: HasDatabaseConfig[JdbcProfile] =>

  import driver.api._

  class CountriesTable(tag: Tag) extends Table[Country](tag, "countries") with WithMetadatas[Country] {

    def code = column[String]("code", O.NotNull)

    def name = column[String]("name", O.NotNull)

    def codeIdx = index("countries_idx_code", code, unique = true)

    def * = (
      id,
      code,
      name,
      updatedAt,
      updatedBy) <>(Country.tupled, Country.unapply)
  }

}