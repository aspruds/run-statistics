package controllers

import models.statistics.{Club, Person}
import modules.ComponentRegistry
import play.Logger
import play.api.Play.current
import play.api.db.slick.DB
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    DB.withSession {
      implicit session => {

        def getPersons(): Seq[Person] = {
          ComponentRegistry.skriesimService.getAthletes.map {
            athlete => ComponentRegistry.skriesimExporter.exportAthlete(athlete)
          }
        }

        def getClubs(): Seq[Club] = {
          ComponentRegistry.skriesimService.getClubs.map {
            club => ComponentRegistry.skriesimExporter.exportClub(club)
          }
        }

        def importClubs() = {
          Logger.info("fetching clubs")
          val persons = getClubs

          Logger.info("filtering new clubs")
          val newClubs = persons.filterNot {
            club => ComponentRegistry.clubsRepository.findBySkriesimId(club.skriesimId).isDefined
          }
          Logger.info(s"found ${newClubs.size} new clubs")
        }

        def importPersons() = {
          Logger.info("fetching persons")
          val persons = getPersons

          Logger.info("filtering new persons")
          val newPersons = persons.filterNot {
            person => ComponentRegistry.personRepository.findBySkriesimId(person.skriesimId).isDefined
          }
          Logger.info(s"found ${newPersons.size} new persons")
        }

        def importAthleteClubs() = {
          val personClubs = for {
            athlete <- ComponentRegistry.skriesimService.getAthletes
            person <- ComponentRegistry.personRepository.findBySkriesimId(athlete.id)
            athleteClub <- athlete.clubs
            club <- ComponentRegistry.clubsRepository.findBySkriesimId(Some(athleteClub.id))

          } yield (person, club)

          personClubs.foreach {
            case(person, club) => {

            }
          }
        }


        /*
        importClubs()
        importPersons()
      */
      }
    }

    Ok(views.html.index("Your new application is ready."))
  }
}