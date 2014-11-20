package services.http.db

import models.http.Url
import models.http.db.Urls

trait UrlRepositoryComponent {
  val urlRepository: UrlRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultUrlRepository(implicit session: Session) extends UrlRepository {

    val urls = TableQuery[Urls]

    private val urlsAutoInc = {
      val insertInvoker = urls returning urls.map(_.id)
      insertInvoker into {
        case (url, id) => url.copy(id = id)
      }
    }

    override def insert(url: Url): Url = {
      urlsAutoInc.insert(url)
    }

    override def getByUrl(url: String) = {
      urls.filter(_.url === url).firstOption
    }
  }

  trait UrlRepository {
    def insert(url: Url): Url

    def getByUrl(url: String): Option[Url]

    def urls: TableQuery[Urls]
  }

}
