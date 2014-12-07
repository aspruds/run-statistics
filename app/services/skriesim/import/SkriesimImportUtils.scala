package services.skriesim.`import`

import models.skriesim.id.CodeName
import models.skriesim.{Athlete, Club => SkriesimClub, Race => SkriesimRace, RaceResult => SkriesimRaceResult}
import models.statistics._
import models.statistics.metadata.WithName
import modules.DAL
import org.joda.time.LocalDateTime
import services.skriesim.`import`.mappers.CountryMapper

trait SkriesimImportUtils {
  this: DAL =>

  import play.api.db.slick.Config.driver.simple._

  def importAthlete(athlete: Athlete)(implicit session: Session): Person = {
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
      updatedAt = Some(new LocalDateTime),
      updatedBy = None
    )
  }

  def importClub(club: SkriesimClub)(implicit session: Session): Club = {
    Club(
      id = 0,
      name = club.name,
      countryId = club.country.flatMap(getCountryIdByName(_)),
      title = club.title,
      description = club.description,
      fullDescription = club.fullDescription,
      skriesimId = club.id,
      updatedAt = Some(new LocalDateTime),
      updatedBy = None
    )
  }

  def importRace(race: SkriesimRace)(implicit session: Session): Race = {
    Race(
      id = 0,
      name = race.name,
      date = race.date,
      countryId = getCountryIdByCode(race.countryCode getOrElse "LV"),
      url = race.url,
      skriesimId = race.id,
      sportlatId = None,
      noskrienId = None,
      updatedAt = Some(new LocalDateTime),
      updatedBy = None
    )
  }

  def importAgeGroup(ageGroup: CodeName) = {
    AgeGroup(
      id = 0,
      name = ageGroup.name,
      updatedAt = Some(new LocalDateTime),
      updatedBy = None
    )
  }

  def importDistanceType(distanceType: WithName) = {
    DistanceType(
      id = 0,
      name = distanceType.name,
      skriesimName = Some(distanceType.name),
      distance = None,
      weight = None,
      height = None,
      disciplineTypeId = None,
      isStandard = Some(true),
      updatedAt = Some(new LocalDateTime),
      updatedBy = None
    )
  }

  def importRaceDistance(raceResult: SkriesimRaceResult)(implicit session: Session): RaceDistance = {

    def mapVenueTypeName(venueType: String) = {
      venueType match {
        case "Kross" => "Cross Country"
        case "Telpās" => "Indoor"
        case "Stadionā" => "Stadium"
        case "Šosejā" => "Road"
      }
    }

    val distanceType = distanceTypeRepository.findBySkriesimName(raceResult.distanceType)
    assert(distanceType.isDefined)

    val venueTypeName = mapVenueTypeName(raceResult.venue)
    val venueType = venueTypeRepository.findByName(venueTypeName)
    assert(venueType.isDefined)

    val race = raceRepository.findBySkriesimId(Some(raceResult.race.id))
    assert(race.isDefined)

    RaceDistance(
      id = 0,
      raceId = race.get.id,
      distanceTypeId = distanceType.map(_.id),
      withQualification = raceResult.withQualification,
      venueTypeId = venueType.map(_.id),
      isCertified = None,
      isElectronicTime = None,
      updatedAt = Some(new LocalDateTime),
      updatedBy = None
    )
  }

  private def getCountryIdByCode(code: String)(implicit session: Session): Option[Long] = {
    countryRepository.byCode(code).map(_.id)
  }

  private def getCountryIdByName(name: String)(implicit session: Session): Option[Long] = {
    val code = CountryMapper.getCountryCodeFromName(name)
    getCountryIdByCode(code)
  }

}
