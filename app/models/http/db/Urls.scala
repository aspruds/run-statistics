package models.http.db

import models.http.Url
import utils.db.PortableJodaSupport._
import org.joda.time.LocalDateTime
import play.api.db.slick.Config.driver.simple._

class Urls(tag: Tag) extends Table[Url](tag, "url_cache") {
  def id = column[Long]("id", O.AutoInc, O.PrimaryKey)

  def url = column[String]("url", O.NotNull)

  def content = column[String]("content", O.NotNull)

  def dateVisited = column[LocalDateTime]("date_visited", O.NotNull)

  def * = (
    id,
    url,
    content,
    dateVisited) <> (Url.tupled, Url.unapply)
}
