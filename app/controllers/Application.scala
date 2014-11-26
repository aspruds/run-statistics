package controllers

import modules.ComponentRegistry
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    ComponentRegistry.skriesimService.exportClubsAthletes()
    Ok(views.html.index("Your new application is ready."))
  }
}