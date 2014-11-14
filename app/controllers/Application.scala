package controllers

import play.api.mvc._
import services.parsers.SkriesimParserComponent

object Application extends Controller with SkriesimParserComponent {

  def index = Action {
    //val ids = List(24060,14617,10495)
    val ids = List(24060)
    for(id <- ids) {
      val athlete = skriesimParser.parseAthlete(id)
      println(athlete)
      println("")
    }

    Ok(views.html.index("Your new application is ready."))
  }

}