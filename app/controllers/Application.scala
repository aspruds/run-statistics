package controllers

import modules.ComponentRegistry
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    val races = ComponentRegistry.sportlatIntegrationService.importAthletes
      .filter {
          maybeAthlete =>
            val club = for {
              athlete <- maybeAthlete
              club <- athlete.club
            } yield club
            club.isDefined
       }.foreach {
      athlete => println(athlete.get.club.get)
    }
    Ok(views.html.index("Your new application is ready."))
  }
}