package services.skriesim

import models.statistics.{DistanceType, PersonClub, PersonCoach}
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
            person => personRepository.findBySkriesimId(person.skriesimId.getOrElse(0)).isEmpty
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
            person <- personRepository.findBySkriesimId(athlete.id.getOrElse(0))
            club <- clubRepository.findBySkriesimId(Some(athleteClub.id))
          } yield (person, club)

          val newPersonsClubs = personsClubs.filter {
            case (person, club) => personsClubsRepository.find(person.id, club.id).isEmpty
          }

          newPersonsClubs.foreach {
            case (person, club) => personsClubsRepository.insert(person, club)
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
            person <- personRepository.findBySkriesimId(athlete.id.getOrElse(0))
            coach <- personRepository.findBySkriesimId(athleteCoach.id)
          } yield (person, coach)

          val newPersonsCoaches = personsCoaches.filter {
            case (person, coach) => personsCoachesRepository.find(person.id, coach.id).isEmpty
          }

          newPersonsCoaches.foreach {
            case (person, coach) => personsCoachesRepository.insert(person, coach)
          }
      }
    }

    override def importStandardDistanceTypes() = {
      Logger.info("Importing skriesim.lv standard distance types")

      DB.withSession {
        implicit session =>
          val distanceTypes = skriesimDataService.getStandardDisciplineTypeIds
          assert(!distanceTypes.isEmpty)

          val newDistanceTypes = distanceTypes.filter {
            dt => distanceTypeRepository.findBySkriesimName(dt.name).isEmpty
          }

          newDistanceTypes.foreach {
            dt =>
              val distanceType = importDistanceType(dt)
              distanceTypeRepository.insert(distanceType)
          }
      }
    }

    override def importNonStandardDistanceTypes() = {
      Logger.info("Importing skriesim.lv non standard distance types")

      DB.withSession {
        implicit session =>
          val distanceTypes = skriesimDataService.getNonStandardDisciplineTypeIds
          assert(!distanceTypes.isEmpty)

          val newDistanceTypes = distanceTypes.filter {
            dt => distanceTypeRepository.findBySkriesimName(dt.name).isEmpty
          }

          newDistanceTypes.foreach {
            dt =>

              val distance = {
                if(dt.name.endsWith("km")) {
                  val decimal: String = dt.name.replace("km","")
                  Some(BigDecimal(decimal))
                }
                else
                  None
              }

              val distanceType = importDistanceType(dt)
                .copy(isStandard = Some(false))
                .copy(distance = distance)
              distanceTypeRepository.insert(distanceType)
          }
      }
    }

    override def importRaceDistances() = {
      Logger.info("Importing skriesim.lv race distances")

      DB.withSession {
        implicit session =>

          val uniqueDistances = skriesimDataService.getAthletes.flatMap(_.raceResults).groupBy {
            result => (result.distanceType, result.venue, result.withQualification, result.race.id)
          }.map(_._2.head)

          uniqueDistances.map {
            importRaceDistance(_)
          }.filter {
            !raceDistanceRepository.exists(_)
          }.foreach {
            raceDistanceRepository.insert(_)
          }
      }
    }

    override def importAll() = {
      importAgeGroups()
      importNonStandardDistanceTypes()
      importClubs()
      importAthletes()
      importRaces()
      importAthletesClubs()
      importAthletesCoaches()
      importRaceDistances()
    }
  }

  trait SkriesimIntegrationService {
    def importAgeGroups()

    def importStandardDistanceTypes()

    def importNonStandardDistanceTypes()

    def importRaces()

    def importAthletes()

    def importClubs()

    def importAthletesClubs()

    def importAthletesCoaches()

    def importRaceDistances()

    def importAll()
  }
}
