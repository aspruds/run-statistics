package services.http.providers

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.http.UrlCache
import org.joda.time.LocalDateTime
import play.api.Logger
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import services.http.db.UrlRepository
import slick.driver.JdbcProfile

import scala.io.Source
import scala.util.Random

@ImplementedBy[DefaultHttpProvider]
trait HttpProvider {
  def loadURL(url: String): String
}

class DefaultHttpProvider @Inject() (urlRepository: UrlRepository) extends HttpProvider {

  val logger: Logger = Logger("DefaultHttpProvider")

  override def loadURL(url: String): String = {
    urlRepository.getByUrl(url) match {
      case Some(url) => {
        logger.debug(s"returning cached url: ${url.url}")
        url.content
      }
      case None => {
        logger.info(s"fetching url: $url")

        Thread.sleep(Random.nextInt(3000))

        val content = Source.fromURL(url).mkString
        urlRepository.insert(UrlCache(0, url, content, new LocalDateTime))
        content
      }
    }
  }
}