package services.http.db

import models.http.Url
import models.http.db.Urls
import play.api.Play.current
import play.api.db.slick._

trait UrlRepository {
  def insert(url: Url): Url

  def getByUrl(url: String): Option[Url]

  def urls: TableQuery[Urls]
}

class DefaultUrlRepository extends UrlRepository {

  val urls = TableQuery[Urls]

  private val urlsAutoInc = {
    val insertInvoker = urls returning urls.map(_.id)
    insertInvoker into {
      case (url, id) => url.copy(id = id)
    }
  }

  override def insert(url: Url): Url = {
    DB.withDynSession {
      urlsAutoInc.insert(url)
    }
  }

  override def getByUrl(url: String) = {
    DB.withDynSession {
      urls.filter(_.url === url).firstOption
    }
  }
}