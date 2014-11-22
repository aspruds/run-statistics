package services.statistics.db

import models.statistics.Country
import models.statistics.db.Countries

trait CountryRepositoryComponent {

  val countryRepository: CountryRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultCountryRepository(implicit session: Session) extends CountryRepository {
    val countries = TableQuery[Countries]

    override def byCode(code: String) = {
      countries.filter(_.code === code).first
    }
  }

  trait CountryRepository {
    def byCode(code: String): Country
  }
}
