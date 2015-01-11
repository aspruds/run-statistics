package controllers

import modules.ComponentRegistry
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    val races = ComponentRegistry.sportlatIntegrationService.importAthletes
    println(races)
    Ok(views.html.index("Your new application is ready."))
  }
}