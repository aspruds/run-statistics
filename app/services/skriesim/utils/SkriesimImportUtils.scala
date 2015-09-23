package services.skriesim.utils

import models.skriesim.id.CodeName
import models.skriesim.{Club => SkriesimClub, Race => SkriesimRace, RaceResult => SkriesimRaceResult, NonStandardDistance, Athlete}
import models.statistics._
import models.statistics.metadata.WithName
import org.joda.time.LocalDateTime
import services.skriesim.DefaultSkriesimIntegrationService
import services.skriesim.utils.mappers.CountryMapper

trait SkriesimImportUtils {
  this: DefaultSkriesimIntegrationService =>

  def mapAthleteToPerson(athlete: Athlete): Person = {
    Person(
      id = 0,
      givenName = athlete.givenName,
      familyName = athlete.familyName,
      dateOfBirth = athlete.dateOfBirth,
      yearOfBirth = athlete.yearOfBirth,
      sex = Some(athlete.sex),
      countryId = athlete.country.flatMap(getCountryIdByName(_)),
      skriesimId = athlete.id,
      sportlatId = None,
      noskrienId = None,
      isCoach = athlete.isCoach,
      updatedAt = Some(new LocalDateTime),
      updatedBy = None
    )
  }

  def mapClub(club: SkriesimClub): Club = {
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

  def mapRace(race: SkriesimRace): Race = {
    Race(
      id = 0,
      name = race.name,
      date = race.date,
      countryId = getCountryIdByCode(race.countryCode getOrElse "LV"),
      location = None,
      url = race.url,
      skriesimId = race.id,
      sportlatId = None,
      noskrienId = None,
      updatedAt = Some(new LocalDateTime),
      updatedBy = None
    )
  }

  def mapAgeGroup(ageGroup: CodeName) = {
    AgeGroup(
      id = 0,
      name = ageGroup.name,
      updatedAt = Some(new LocalDateTime),
      updatedBy = None
    )
  }

  def mapDistanceType(distanceType: WithName): DistanceType = {
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

  def mapDistanceType(distanceType: NonStandardDistance): DistanceType = {
    mapDistanceType(distanceType.asInstanceOf[WithName]).
      copy(distance = Some(distanceType.distance)).
      copy(isStandard = Some(false))
  }

  def mapVenueTypeName(venueType: String) = {
    venueType match {
      case "Kross" => "Cross Country"
      case "Telpās" => "Indoor"
      case "Stadionā" => "Stadium"
      case "Šosejā" => "Road"
    }
  }

  def mapRaceResultToRaceDistance(raceResult: SkriesimRaceResult): RaceDistance = {
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

  def mapRaceResult(person: Person, raceResult: SkriesimRaceResult): RaceResult = {
    val mappedRaceDistance = mapRaceResultToRaceDistance(raceResult)
    val raceDistance = raceDistanceRepository.find(mappedRaceDistance).get

    val ageGroup = ageGroupRepository.findByName(raceResult.ageGroup)

    val classificationType = raceResult.classificationType.flatMap(classificationTypeRepository.findByName(_))

    RaceResult(
      id = 0,
      raceId = raceDistance.raceId,
      raceDistanceId = raceDistance.id,
      personId = person.id,
      time = raceResult.time,
      distance = raceResult.distance,
      points = raceResult.points,
      rank = raceResult.rank,
      rankText = raceResult.rankText,
      ageGroupId = ageGroup.map(_.id),
      classificationTypeId = classificationType.map(_.id),
      wind = None,
      updatedAt = Some(new LocalDateTime),
      updatedBy = None
    )
  }

  private def getCountryIdByCode(code: String): Option[Long] = {
    countryRepository.byCode(code).map(_.id)
  }

  private def getCountryIdByName(name: String): Option[Long] = {
    val code = CountryMapper.getCountryCodeFromName(name)
    getCountryIdByCode(code)
  }

}
