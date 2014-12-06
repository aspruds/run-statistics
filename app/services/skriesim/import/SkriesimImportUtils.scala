package services.skriesim.`import`

import models.skriesim.id.CodeName
import models.skriesim.{Athlete, Club => SkriesimClub, Race => SkriesimRace, RaceResult => SkriesimRaceResult}
import models.statistics._
import modules.DAL
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import Database.dynamicSession
import play.api.db.slick.DB
import services.skriesim.`import`.mappers.CountryMapper

trait SkriesimImportUtils {
  this: DAL =>

     def importAthlete(athlete: Athlete): Person = {
      Person(
        id = 0,
        givenName = athlete.givenName,
        familyName = athlete.familyName,
        dateOfBirth = athlete.dateOfBirth,
        yearOfBirth = athlete.yearOfBirth,
        sex = athlete.sex,
        countryId = athlete.country.flatMap(getCountryIdByName(_)),
        skriesimId = athlete.id,
        sportlatId = None,
        noskrienId = None,
        isCoach = athlete.isCoach,
        updatedAt = None,
        updatedBy = None
      )
    }

     def importClub(club: SkriesimClub): Club = {
      Club(
        id = 0,
        name = club.name,
        countryId = club.country.flatMap(getCountryIdByName(_)),
        title = club.title,
        description = club.description,
        fullDescription = club.fullDescription,
        skriesimId = club.id,
        updatedAt = None,
        updatedBy = None
      )
    }

     def importRace(race: SkriesimRace): Race = {
      Race(
        id = 0,
        name = race.name,
        date = race.date,
        countryId = getCountryIdByCode(race.countryCode getOrElse "LV"),
        url = race.url,
        skriesimId = race.id,
        sportlatId = None,
        noskrienId = None,
        updatedAt = None,
        updatedBy = None
      )
    }

     def importAgeGroup(ageGroup: CodeName) = {
      AgeGroup(
        id = 0,
        name = ageGroup.name,
        updatedAt = None,
        updatedBy = None
      )
    }

  /*
    def exportRaceResult(raceResult: SkriesimRaceResult) = {
      val race = getRace(raceResult);


      RaceResult(
        id = 0,
        raceId = raceResult.race.id,
        raceDistanceId = getRaceDistance(raceResult),
      )
      throw new RuntimeException("not implemented yet")
    }
    */

    private def getRace(raceResult: SkriesimRaceResult): Race = {
      val maybeRace = raceRepository.findBySkriesimId(Some(raceResult.race.id))
      maybeRace match {
        case Some(race) => race
        case None => throw new RuntimeException("unknown race")
      }
    }

  /*
    private def getRaceDistance(raceResult: SkriesimRaceResult): Long = {
      val distanceTypeId = getDistanceTypeId(raceResult);
      val maybeDistance = raceDistanceRepository.findByRaceIdAndDistanceTypeId()
    }

    private def getDistanceTypeId(raceResult: SkriesimRaceResult): Long = {

    }
    */

    private def getCountryIdByCode(code: String): Option[Long] = {
      DB.withDynSession {
        countryRepository.byCode(code).map(_.id)
      }
    }

    private def getCountryIdByName(name: String): Option[Long] = {
      val code = CountryMapper.getCountryCodeFromName(name)
      getCountryIdByCode(code)
    }

}
