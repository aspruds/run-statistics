package controllers

import play.api.Play.current
import play.api.db.slick.{DB, Session}
import play.api.mvc._
import services.http.db.UrlRepositoryComponent
import services.http.providers.HttpProviderComponent
import services.skriesim.SkriesimServiceComponent
import services.skriesim.export.SkriesimExporterComponent
import services.skriesim.parsers.SkriesimParserComponent
import services.skriesim.providers.SkriesimProviderComponent
import services.statistics.db.{ClubsRepositoryComponent, CountryRepositoryComponent, PersonRepositoryComponent, StatisticsServiceComponent}

object Application extends Controller {

  def index = Action {
    DB.withSession {
      implicit session => {

        val skriesimService = ServiceRepository.skriesimService
        val skriesimExporter = ServiceRepository.skriesimExporter
        val statisticsService = ServiceRepository.statisticsService

      }
    }

    Ok(views.html.index("Your new application is ready."))
  }

  object ServiceRepository {

    def skriesimService(implicit session: Session) = {
      new SkriesimServiceComponent with SkriesimParserComponent with SkriesimProviderComponent {
        override val skriesimProvider = new DefaultSkriesimProvider with HttpProviderComponent with UrlRepositoryComponent {
          override val urlRepository = new DefaultUrlRepository
        }
      }.skriesimService
    }

    def skriesimExporter(implicit session: Session) = new SkriesimExporterComponent {
      override val skriesimExporter = new DefaultSkriesimExporter with CountryRepositoryComponent {
        override val countryRepository = new DefaultCountryRepository
      }
    }.skriesimExporter

    def statisticsService(implicit session: Session) = {
      new StatisticsServiceComponent with PersonRepositoryComponent with ClubsRepositoryComponent {
        override val personRepository = new DefaultPersonRepository
        override val clubsRepository = new DefaultClubsRepository
      }.statisticsService
    }
  }

}