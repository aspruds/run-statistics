package controllers

import play.api.Play.current
import play.api.db.slick.DB
import play.api.mvc._
import services.ComponentRegistry

object Application extends Controller {

  def index = Action {
    DB.withSession {
      implicit sess => {
        val skriesimService = ComponentRegistry.skriesimService
        skriesimService.getClubIds.foreach(println)
      }
    }

    Ok(views.html.index("Your new application is ready."))
  }
}