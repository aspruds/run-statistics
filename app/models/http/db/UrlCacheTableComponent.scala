package models.http.db

import models.http.UrlCache
import org.joda.time.LocalDateTime
import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile
import utils.db.PortableJodaSupport._

trait UrlCacheTableComponent {
  self: HasDatabaseConfig[JdbcProfile] =>

  import driver.api._

  class UrlCacheTable(tag: Tag) extends Table[UrlCache](tag, "url_cache") {
    def id = column[Long]("id", O.AutoInc, O.PrimaryKey)

    def url = column[String]("url")

    def content = column[String]("content")

    def dateVisited = column[LocalDateTime]("date_visited")

    def * = (
      id,
      url,
      content,
      dateVisited) <>(UrlCache.tupled, UrlCache.unapply)
  }

}
