package controllers

import modules.ComponentRegistry
import play.api.Logger
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    val races = ComponentRegistry.sportlatPersistenceService.persistResults()
    Ok(views.html.index("Your new application is ready."))
  }
}