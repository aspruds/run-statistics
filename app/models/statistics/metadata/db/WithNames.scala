package models.statistics.metadata.db

import play.api.db.slick.Config.driver.simple._

trait WithNames[T] extends Table[T] {
  def name = column[String]("name", O.NotNull)
}
