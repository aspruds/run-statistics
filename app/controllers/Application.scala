package controllers

import models.statistics.Person
import org.joda.time.{LocalDateTime, LocalDate}
import play.api.Logger
import services.http.db.UrlRepositoryComponent
import services.http.providers.HttpProviderComponent
import services.skriesim.SkriesimServiceComponent
import services.skriesim.parsers.SkriesimParserComponent
import services.skriesim.providers.SkriesimProviderComponent
import services.skriesim.export.SkriesimExporterComponent
import play.api.db.slick.DB
import play.api.mvc._
import play.api.db.slick.Session
import play.api.Play.current

object Application extends Controller {

  def index = Action {
    DB.withSession {
      implicit session => {

        val skriesimService = ServiceRepository.skriesimService
        val skriesimExporter = ServiceRepository.skriesimExporter

        val persons = skriesimService.getAthletes.take(20).map {
          skriesimExporter.athleteToPerson(_)
        }
        persons.foreach(println)

        /*
        Logger.debug("getting athletes")

        case class Name(id: Option[Int], givenName: String, familyName: String)

        service.getAthletes
          //.filter(_.givenName.count(_ == ' ') > 0)
          //.filter(a => a.familyName.contains("-") || a.givenName.contains("-"))
          .filter(_.familyName == "Sprūds")
          .map(a => Name(a.id, a.givenName, a.familyName)).foreach(println)

        //service.getCoaches.foreach(println)
        */
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

    def skriesimExporter = new SkriesimExporterComponent{}.skriesimExporter

  }
}