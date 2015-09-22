package models.statistics.metadata.db

import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile

trait WithNamesComponent {
  self: HasDatabaseConfig[JdbcProfile] =>

  import driver.api._

  trait WithNames[T] {
    self: Table[T] =>

    def name = column[String]("name")
  }

}
