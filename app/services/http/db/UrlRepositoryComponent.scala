package services.http.db

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.http.UrlCache
import play.api.db.slick._
import slick.driver.JdbcProfile
import utils.FutureUtils._

@ImplementedBy(classOf[DefaultUrlRepository])
trait UrlRepository {
  def insert(url: UrlCache): UrlCache

  def getByUrl(url: String): Option[UrlCache]
}

class DefaultUrlRepository @Inject() (
protected val dbConfigProvider: DatabaseConfigProvider) extends UrlRepository with HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  val urls = TableQuery[UrlCache]

  private val urlsAutoInc = {
    val insertInvoker = urls returning urls.map(_.id)
    insertInvoker into {
      case (url, id) => url.copy(id = id)
    }
  }

  override def insert(url: UrlCache): UrlCache = {
    val action = urlsAutoInc ++= url
    db.run(action).await()
  }

  override def getByUrl(url: String) = {
      urls.filter(_.url === url).firstOption
  }
}