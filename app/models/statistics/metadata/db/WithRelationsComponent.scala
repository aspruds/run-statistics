package models.statistics.metadata.db

import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile

trait WithRelationsComponent {
  self: HasDatabaseConfig[JdbcProfile] =>

  import driver.api._

  trait WithRelations[T] {
    self: Table[T] =>

    def skriesimId = column[Option[Long]]("skriesim_id")

    def sportlatId = column[Option[Long]]("sportlat_id")

    def noskrienId = column[Option[Long]]("noskrien_id")
  }

}