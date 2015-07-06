package models.statistics.metadata.db

import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile
import slick.lifted.AbstractTable

trait WithNames[T] extends AbstractTable[T] {
  self: HasDatabaseConfig[JdbcProfile] =>

  import driver.api._

  def name = column[String]("name")
}
