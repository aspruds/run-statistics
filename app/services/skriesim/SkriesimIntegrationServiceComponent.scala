package services.skriesim

import models.statistics.{PersonClub, PersonCoach}
import modules.DAL
import org.joda.time.LocalDateTime
import play.api.Logger
import play.api.Play.current
import play.api.db.slick.DB
import services.skriesim.`import`.SkriesimImportUtils

trait SkriesimIntegrationServiceComponent extends SkriesimImportUtils {
  this: SkriesimDataServiceComponent with DAL =>

  val skriesimIntegrationService: SkriesimIntegrationService

  class DefaultSkriesimIntegrationService extends SkriesimIntegrationService{
    override def importAgeGroups() = {
      Logger.info("Importing skriesim.lv age groups")

      DB.withSession {
        implicit session =>

          skriesimDataService.getAgeGroupsIds.map {
            ageGroup => importAgeGroup(ageGroup)
          }.filter {
            ageGroup => ageGroupRepository.findByName(ageGroup.name).isEmpty
          }.foreach {
            ageGroup => ageGroupRepository.insert(ageGroup)
          }
      }
    }

    override def importRaces() = {
      Logger.info("Importing skriesim.lv races")

      DB.withSession {
        implicit session =>
          skriesimDataService.getRaces.filter(
            race => raceRepository.findBySkriesimId(race.id).isEmpty
          ).map {
            race => importRace(race)
          }.foreach {
            race => raceRepository.insert(race)
          }
      }
    }

    override def importAthletes() = {
      Logger.info("Importing skriesim.lv athletes")

      DB.withSession {
        implicit session =>
          skriesimDataService.getAthletes.map {
            athlete => importAthlete(athlete)
          }.filter {
            person => personRepository.findBySkriesimId(person.skriesimId).isEmpty
          }.foreach {
            person => personRepository.insert(person)
          }
      }
    }

    override def importClubs() = {

      Logger.info("Importing skriesim.lv clubs")
      DB.withSession {
        implicit session =>
          skriesimDataService.getClubs.map {
            club => importClub(club)
          }.filter {
            club => clubRepository.findBySkriesimId(club.skriesimId).isEmpty
          }.foreach {
            club => clubRepository.insert(club)
          }
      }
    }

    override def importAthletesClubs() = {
      Logger.info("Importing skriesim.lv athletes-clubs")

      DB.withSession {
        implicit session =>
          val personsClubs = for {
            athlete <- skriesimDataService.getAthletes
            athleteClub <- athlete.clubs
            person <- personRepository.findBySkriesimId(athlete.id)
            club <- clubRepository.findBySkriesimId(Some(athleteClub.id))
          } yield (person, club)

          personsClubs.foreach {
            case (person, club) => {
              val personClub = PersonClub(0, person.id, club.id, new LocalDateTime(), new LocalDateTime(), None)
              personsClubsRepository.insert(personClub)
            }
          }
      }
    }

    override def importAthletesCoaches() = {
      Logger.info("Importing skriesim.lv athletes-coaches")
      DB.withSession {
        implicit session =>
          val personsCoaches = for {
            athlete <- skriesimDataService.getAthletes
            athleteCoach <- athlete.coaches
            person <- personRepository.findBySkriesimId(athlete.id)
            coach <- personRepository.findBySkriesimId(Some(athleteCoach.id))
          } yield (person, coach)

          personsCoaches.foreach {
            case (person, coach) => {
              val personCoach = PersonCoach(0, person.id, coach.id, new LocalDateTime(), new LocalDateTime(), None)
              personsCoachesRepository.insert(personCoach)
            }
          }
      }
    }


    override def importAll() = {
      importAgeGroups()
      importAthletes()
      importClubs()
      importRaces()
      importAthletesClubs()
      importAthletesCoaches()
    }
  }

  trait SkriesimIntegrationService {
    def importAgeGroups()

    def importRaces()

    def importAthletes()

    def importClubs()

    def importAthletesClubs()

    def importAthletesCoaches()

    def importAll()
  }
}
