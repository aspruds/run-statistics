package services.sportlat

import models.sportlat.Athlete
import models.sportlat.id.RaceId
import models.statistics.{Race, Person}
import modules.DAL
import org.joda.time.LocalDateTime
import play.api.Logger
import play.api.Play.current
import play.api.db.slick.DB

trait SportlatPersistenceServiceComponent {
  this: SportlatIntegrationServiceComponent with DAL =>

  type PersistedResults = Seq[Person]

  val sportlatPersistenceService: SportlatPersistenceService

  import play.api.db.slick.Config.driver.simple._

  class DefaultSportlatPersistenceService extends SportlatPersistenceService {

    private def persistAthlete(athlete: Athlete)(implicit session: Session): Person = {
      val countryId = athlete.country.flatMap {
        case name => countryRepository.byName(name).map(_.id)
      }

      val person = Person(
        id = 0,
        givenName = athlete.givenName,
        familyName = athlete.familyName,
        dateOfBirth = athlete.dateOfBirth,
        yearOfBirth = athlete.dateOfBirth.map(_.getYear),
        sex = None,
        countryId = countryId,
        skriesimId = None,
        sportlatId = athlete.id,
        noskrienId = None,
        isCoach = false,
        updatedAt = Some(new LocalDateTime),
        updatedBy = None
      )

      val persistedPerson = person.sportlatId.flatMap(personRepository.findBySportlatId(_))

      persistedPerson getOrElse {
        personRepository.insert(person)
      }
    }

    private def persistRace(raceId: RaceId)(implicit session: Session): Race = {
      val race = Race(
        id = 0,
        name = raceId.name,
        date = raceId.date,
        countryId = None,
        location = Some(raceId.location),
        url = None,
        skriesimId = None,
        sportlatId = raceId.id,
        noskrienId = None,
        updatedAt = Some(new LocalDateTime),
        updatedBy = None
      )

      val persistedRace = raceRepository.findBySportlatId(race.sportlatId)

      persistedRace getOrElse {
        raceRepository.insert(race)
      }
    }

    private def persistResult(result: Result)(implicit session: Session): Person = {
      Logger.debug("Persisting sportlat.lv result")
      persistRace(result.raceId)
      persistAthlete(result.athlete)
    }

    override def persistResults(): PersistedResults = {
      Logger.debug("Persisting sportlat.lv results")

      DB withSession {
        implicit session =>
          sportlatIntegrationService.getResults().map {
            persistResult
          }
      }
    }
  }

  trait SportlatPersistenceService {
    def persistResults(): PersistedResults
  }

}
