package models.statistics.metadata.db

import models.statistics.metadata.WithRelation
import play.api.db.slick.Config.driver.simple._

trait WithRelations[T <: WithRelation] extends Table[T] {
  def skriesimId = column[Option[Long]]("skriesim_id", O.NotNull)

  def sportlatId = column[Option[Long]]("sportlat_id", O.NotNull)

  def noskrienId = column[Option[Long]]("noskrien_id", O.NotNull)
 }
