package services.http.db

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.http.UrlCache
import play.api.db.slick._
import slick.driver.JdbcProfile

@ImplementedBy(classOf[DefaultUrlRepository])
trait UrlRepository {
  def insert(url: UrlCache): UrlCache

  def getByUrl(url: String): Option[UrlCache]

  def urls: TableQuery[Urls]
}

class DefaultUrlRepository @Inject() (
protected val dbConfigProvider: DatabaseConfigProvider) extends UrlRepository with HasDatabaseConfigProvider[JdbcProfile] {


  import driver.api._

  val urls = TableQuery[Urls]

  private val urlsAutoInc = {
    val insertInvoker = urls returning urls.map(_.id)
    insertInvoker into {
      case (url, id) => url.copy(id = id)
    }
  }

  override def insert(url: UrlCache): UrlCache = {
      urlsAutoInc.insert(url)
  }

  override def getByUrl(url: String) = {
      urls.filter(_.url === url).firstOption
  }
}