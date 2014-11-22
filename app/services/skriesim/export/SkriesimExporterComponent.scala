package services.skriesim.export

import models.skriesim.Athlete
import models.statistics.{Country, Person}
import org.joda.time.LocalDateTime

trait SkriesimExporterComponent {

  val skriesimExporter: SkriesimExporter = new DefaultSkriesimExporter

  class DefaultSkriesimExporter extends SkriesimExporter {
    override def athleteToPerson(athlete: Athlete): Person = {
      def getCountryIdByName(name: String): Long = {
        Country(0,"LV","Latvia").id
      }

      Person(
        id = 0,
        givenName = athlete.givenName,
        familyName = athlete.familyName,
        dateOfBirth = athlete.dateOfBirth,
        yearOfBirth = athlete.yearOfBirth,
        sex = athlete.sex,
        countryId = getCountryIdByName(athlete.country),
        skriesimId = athlete.id,
        sportlatId = None,
        noskrienId = None,
        isCoach = athlete.isCoach,
        createdAt = new LocalDateTime,
        updateAt = new LocalDateTime,
        updatedById = None
      )
    }
  }

  trait SkriesimExporter {
    def athleteToPerson(athlete: Athlete): Person
  }
}
