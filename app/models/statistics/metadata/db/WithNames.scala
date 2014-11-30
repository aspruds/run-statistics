package models.statistics.metadata.db

import models.statistics.metadata.WithName
import play.api.db.slick.Config.driver.simple._

trait WithNames[T <: WithName] extends Table[T] {
  def name = column[String]("name", O.NotNull)
 }
