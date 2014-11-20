package services.http.providers

import models.http.Url
import org.joda.time.LocalDateTime
import services.http.db.UrlRepositoryComponent

import scala.io.Source

trait HttpProviderComponent {
  this: UrlRepositoryComponent =>

  val httpProvider: HttpProvider = new DefaultHttpProvider

  class DefaultHttpProvider extends HttpProvider {
    override def loadURL(url: String): String = {
      urlRepository.getByUrl(url) match {
        case Some(url) => {
          println(s"returning cached url: ${url.url}")
          url.content
        }
        case None => {
          println(s"fetching url: $url")

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
