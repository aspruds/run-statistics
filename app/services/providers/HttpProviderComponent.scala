package services.providers

import scala.io.Source

trait HttpProviderComponent {
  val httpProvider: HttpProvider = new DefaultHttpProvider

  class DefaultHttpProvider extends HttpProvider {
    override def loadURL(url: String): String = {
      Source.fromURL(url).mkString
    }
  }

  trait HttpProvider {
    def loadURL(url: String): String
  }
}
