package controllers

import services.http.db.UrlRepositoryComponent
import services.http.providers.HttpProviderComponent
import services.skriesim.SkriesimServiceComponent
import services.skriesim.parsers.SkriesimParserComponent
import services.skriesim.providers.SkriesimProviderComponent
import play.api.db.slick.DB
import play.api.mvc._
import play.api.db.slick.Session
import play.api.Play.current

object Application extends Controller {

  def index = Action {
    DB.withSession {
      implicit session => {

        val service = getSkriesimService

        service.getCoaches.foreach(println)
      }
    }

    Ok(views.html.index("Your new application is ready."))
  }

  def getSkriesimService(implicit session: Session) = {
    new SkriesimServiceComponent with SkriesimParserComponent with SkriesimProviderComponent {
      override val skriesimProvider = new DefaultSkriesimProvider with HttpProviderComponent with UrlRepositoryComponent {
        override val urlRepository = new DefaultUrlRepository
      }
    }.skriesimService
  }
}