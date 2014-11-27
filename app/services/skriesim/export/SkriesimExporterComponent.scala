package services.skriesim.export

import models.skriesim.{Athlete, Club => SkriesimClub, Race => SkriesimRace}
import models.statistics.{Club, Person, Race}
import org.joda.time.LocalDateTime
import play.api.Logger
import play.api.Play.current
import play.api.db.slick.DB
import services.skriesim.export.mappers.CountryMapper
import services.statistics.db.CountryRepositoryComponent
import play.api.db.slick.Config.driver.simple._
import Database.dynamicSession

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
        countryId = athlete.country.map(getCountryIdByName(_)).get,
        skriesimId = athlete.id,
        sportlatId = None,
        noskrienId = None,
        isCoach = athlete.isCoach,
        createdAt = new LocalDateTime,
        updateAt = new LocalDateTime,
        updatedBy = None
      )
    }

    override def exportClub(club: SkriesimClub): Club = {
      Club(
        id = 0,
        name = club.name,
        countryId = club.country.map(getCountryIdByName(_)).get,
        title = club.title,
        description = club.description,
        fullDescription = club.fullDescription,
        skriesimId = club.id,
        createdAt = new LocalDateTime,
        updateAt = new LocalDateTime,
        updatedBy = None
      )
    }

    override def exportRace(race: SkriesimRace): Race = {
      Race(
        id = 0,
        name = race.name,
        date = race.date,
        countryId = getCountryIdByCode(race.countryCode getOrElse "LV"),
        url = race.url,
        skriesimId = race.id,
        sportlatId = None,
        noskrienId = None,
        createdAt = new LocalDateTime,
        updateAt = new LocalDateTime,
        updatedBy = None
      )
    }

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

  trait SkriesimExporter {
    def exportAthlete(athlete: Athlete): Person

    def exportClub(club: SkriesimClub): Club

    def exportRace(club: SkriesimRace): Race
  }
}
