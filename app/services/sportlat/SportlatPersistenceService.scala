package services.sportlat

import javax.inject.Inject

import models.sportlat.Athlete
import models.sportlat.id.RaceId
import models.statistics.{Person, Race}
import org.joda.time.LocalDateTime
import play.api.Logger
import services.statistics.db.{CountryRepository, PersonRepository, RaceRepository}

trait SportlatPersistenceService {
  type PersistedResults = Seq[Person]

  def persistResults(): PersistedResults
}

class DefaultSportlatPersistenceService @Inject()(countryRepository: CountryRepository,
                                                  personRepository: PersonRepository,
                                                  raceRepository: RaceRepository,
                                                  sportlatIntegrationService: SportlatIntegrationService) extends SportlatPersistenceService {

  private def persistAthlete(athlete: Athlete): Person = {
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

  private def persistRace(raceId: RaceId): Race = {
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

  private def persistResult(result: Result): Person = {
    Logger.debug("Persisting sportlat.lv result")
    persistRace(result.raceId)
    persistAthlete(result.athlete)
  }

  override def persistResults(): PersistedResults = {
    Logger.debug("Persisting sportlat.lv results")
    sportlatIntegrationService.getResults().map {
      persistResult
    }
  }
}