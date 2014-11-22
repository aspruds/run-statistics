package services.http.providers

import models.http.Url
import org.joda.time.LocalDateTime
import play.api.Logger
import services.http.db.UrlRepositoryComponent

import scala.io.Source

trait HttpProviderComponent {
  this: UrlRepositoryComponent =>

  val httpProvider: HttpProvider = new DefaultHttpProvider

  class DefaultHttpProvider extends HttpProvider {
    val logger: Logger = Logger("DefaultHttpProvider")

    override def loadURL(url: String): String = {
      urlRepository.getByUrl(url) match {
        case Some(url) => {
          logger.debug(s"returning cached url: ${url.url}")
          url.content
        }
        case None => {
          logger.info(s"fetching url: $url")

          Thread.sleep(2500)

          val content = Source.fromURL(url).mkString
          urlRepository.insert(Url(0, url, content, new LocalDateTime))
          content
        }
      }
    }
  }

  trait HttpProvider {
    def loadURL(url: String): String
  }
}
