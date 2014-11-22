package services.skriesim.export

import models.skriesim.{Athlete, Club => SkriesimClub}
import models.statistics.{Club, Person}
import org.joda.time.LocalDateTime
import services.skriesim.export.mappers.CountryMapper
import services.statistics.db.CountryRepositoryComponent

trait SkriesimExporterComponent {

  val skriesimExporter: SkriesimExporter

  class DefaultSkriesimExporter extends SkriesimExporter {
    this: CountryRepositoryComponent =>

    override def exportAthlete(athlete: Athlete): Person = {
      Person(
        id = 0,
        givenName = athlete.givenName,
        familyName = athlete.familyName,
        dateOfBirth = athlete.dateOfBirth,
        yearOfBirth = athlete.yearOfBirth,
        sex = athlete.sex,
        countryId = athlete.country.map(getCountryIdByName(_)),
        skriesimId = athlete.id,
        sportlatId = None,
        noskrienId = None,
        isCoach = athlete.isCoach,
        createdAt = new LocalDateTime,
        updateAt = new LocalDateTime,
        updatedById = None
      )
    }

    override def exportClub(club: SkriesimClub): Club = {
      Club(
        id = 0,
        name = club.name,
        countryId = club.country.map(getCountryIdByName(_)),
        title = club.title,
        description = club.description,
        fullDescription = club.fullDescription,
        skriesimId = club.id,
        createdAt = new LocalDateTime,
        updateAt = new LocalDateTime,
        updatedById = None
      )
    }

    private def getCountryIdByName(name: String): Long = {
      val code = CountryMapper.getCountryCodeFromName(name)
      countryRepository.byCode(code).id
    }
  }

  trait SkriesimExporter {
    def exportAthlete(athlete: Athlete): Person

    def exportClub(club: SkriesimClub): Club
  }
}
