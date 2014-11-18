package services.http.db

import models.http.Url
import models.http.db.Urls

trait UrlRepositoryComponent {
  def urlRepository: UrlRepository = new DefaultUrlRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultUrlRepository extends UrlRepository {

    val urls = TableQuery[Urls]

    private val urlsAutoInc = {
      val insertInvoker = urls returning urls.map(_.id)
      insertInvoker into {
        case (url, id) => url.copy(id = id)
      }
    }

    override def insert(url: Url)(implicit session: Session): Url = {
      urlsAutoInc.insert(url)
    }

    override def getByUrl(url: String)(implicit session: Session) = {
      urls.filter(_.url === url).firstOption
    }
  }

  trait UrlRepository {
    def insert(url: Url)(implicit session: Session): Url

    def getByUrl(url: String)(implicit session: Session): Option[Url]

    def urls: TableQuery[Urls]
  }

}
