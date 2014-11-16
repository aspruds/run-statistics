package controllers

import play.api.mvc._
import services.parsers.SkriesimParserComponent

object Application extends Controller with SkriesimParserComponent {

  def index = Action {
    /*
    //val ids = List(24060,14617,10495)
    val ids = List(24060)
    for(id <- ids) {
      val athlete = skriesimParser.parseAthlete(id)
      println(athlete)
      println("")
    }

    val athletes = skriesimParser.parseAthletes()
    println(athletes)

    val clubs = skriesimParser.parseClubs()
    println(clubs.size)

    val coaches = skriesimParser.parseCoaches()
    println(coaches)

    val coaches = skriesimParser.parseCoaches()
    val coach = skriesimParser.parseCoach(coaches(0).id)
    println(coach)

    val clubId = 13
    val club = skriesimParser.parseClub(clubId)
    println(club)

    val raceId = 14825
    val race = skriesimParser.parseRace(raceId)
    println(race)

    val races = skriesimParser.parseRaces()
    println(races)

    val countries = skriesimParser.parseCountries()
    println(countries)

    val ageGroups = skriesimParser.parseAgeGroups()
    println(ageGroups)

    val disciplineTypes = skriesimParser.parseDisciplineTypes()
    println(disciplineTypes)

    val standardDisciplineTypes = skriesimParser.parseStandardDisciplineTypes()
    println(standardDisciplineTypes)

    val nonStandardDisciplineTypes = skriesimParser.parseNonStandardDisciplineTypes()
    println(nonStandardDisciplineTypes)
    */

    val sql = lookupSql()
    println(sql)

    Ok(views.html.index("Your new application is ready."))
  }

  def lookupSql(): String = {
    import services.writers.sql.SQLWriter._

    val clubs = skriesimParser.parseClubs().take(2).map {
      club => skriesimParser.parseClub(club.id)
    }

    val parts = List(
      skriesimParser.parseStandardDisciplineTypes().toSql("STANDARD_DISCIPLINE_TYPES"),
      skriesimParser.parseNonStandardDisciplineTypes().toSql("NON_STANDARD_DISCIPLINE_TYPES"),
      skriesimParser.parseCountries().toSql("COUNTRIES"),
      skriesimParser.parseAgeGroups().toSql("AGE_GROUPS"),
      skriesimParser.parseDisciplineTypes().toSql("DISCIPLINE_TYPES"),
      clubs.toSql("CLUBS")
    )

    parts
      .map(_.mkString("\n"))
      .mkString("\n")
  }

}