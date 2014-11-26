package services.skriesim

import models.skriesim.{Club, Athlete}
import models.skriesim.id.{CodeName, IdName}
import models.statistics.{PersonCoach, PersonClub, Person}
import modules.ComponentRegistry
import org.joda.time.{LocalDateTime, LocalDate}
import services.skriesim.export.SkriesimExporterComponent
import services.skriesim.parsers.SkriesimParserComponent
import services.skriesim.providers.SkriesimProviderComponent
import play.api.Play.current
import play.api.db.slick.DB

trait SkriesimServiceComponent {
  this: SkriesimProviderComponent with SkriesimParserComponent with SkriesimExporterComponent =>

  val skriesimService: SkriesimService

  class DefaultSkriesimService extends SkriesimService{
    override def getAthleteIds = {
      val html = skriesimProvider.getAthleteIds
      skriesimParser.parseAthleteIds(html)
    }

    override def getAthlete(id: Long) = {
      val html = skriesimProvider.getCoach(id)
      skriesimParser.parseCoach(html).copy(id = Some(id))
    }

    override def getAthletes = getAthleteIds.map {
      a => getAthlete(a.id)
    }

    override def getCoachIds = {
      val html = skriesimProvider.getCoachIds
      skriesimParser.parseCoachIds(html)
    }

    override def getCoach(id: Long) = getAthlete(id)

    override def getCoaches = getCoachIds.map {
      a => getCoach(a.id)
    }

    override def getCountryIds = {
      val html = skriesimProvider.getStatisticsIdsEnglish
      skriesimParser.parseCountryIdsEnglish(html)
    }

    override def getCountryIdsLatvian = {
      val html = skriesimProvider.getStatisticsIds
      skriesimParser.parseCountryIds(html)
    }

    override def getClubIds = {
      val html = skriesimProvider.getClubIds
      skriesimParser.parseClubIds(html)
    }

    override def getClub(id: Long) = {
      val html = skriesimProvider.getClub(id)
      skriesimParser.parseClub(html).copy(id = Some(id))
    }

    override def getClubs = {
      getClubIds
        .map(c => getClub(c.id))
        .filterNot(_.id == Some(1494)) // Bebrene
        .filterNot(_.id == Some(682)) // Smiltene
        .filterNot(_.id == Some(652)) // Tīturga
    }

    override def exportAthletes() = {
      DB.withSession {
        implicit session =>
          getAthletes.map {
            athlete => skriesimExporter.exportAthlete(athlete)
          }.filterNot {
            person => ComponentRegistry.personRepository.findBySkriesimId(person.skriesimId).isDefined
          }.foreach {
            person => ComponentRegistry.personRepository.insert(person)
          }
      }
    }

    override def exportClubs() = {
      DB.withSession {
        implicit session =>
          getClubs.map {
            club => skriesimExporter.exportClub(club)
          }.filterNot {
            club => ComponentRegistry.clubsRepository.findBySkriesimId(club.skriesimId).isDefined
          }.foreach {
            club => ComponentRegistry.clubsRepository.insert(club)
          }
      }
    }

    def exportClubsAthletes() = {
      DB.withSession {
        implicit session =>
          val personsClubs = for {
            athlete <- ComponentRegistry.skriesimService.getAthletes
            athleteClub <- athlete.clubs
            person <- ComponentRegistry.personRepository.findBySkriesimId(athlete.id)
            club <- ComponentRegistry.clubsRepository.findBySkriesimId(Some(athleteClub.id))
          } yield (person, club)

          personsClubs.foreach {
            case(person, club) => {
              val personClub = PersonClub(0, person.id, club.id, new LocalDateTime(), new LocalDateTime(), None)
              ComponentRegistry.personsClubsRepository.insert(personClub)
            }
          }
      }
    }

    def exportAthletesCoaches() = {
      DB.withSession {
        implicit session =>
          val personsCoaches = for {
            athlete <- ComponentRegistry.skriesimService.getAthletes
            athleteCoach <- athlete.coaches
            person <- ComponentRegistry.personRepository.findBySkriesimId(athlete.id)
            coach <- ComponentRegistry.personRepository.findBySkriesimId(Some(athleteCoach.id))
          } yield (person, coach)

          personsCoaches.foreach {
            case(person, coach) => {
              val personCoach = PersonCoach(0, person.id, coach.id, new LocalDateTime(), new LocalDateTime(), None)
              ComponentRegistry.personsCoachesRepository.insert(personCoach)
            }
          }
      }
    }
  }

  trait SkriesimService {
    // athletes
    def getAthleteIds: Seq[IdName]

    def getAthlete(id: Long): Athlete

    def getAthletes: Seq[Athlete]

    // coaches
    def getCoachIds: Seq[IdName]

    def getCoach(id: Long): Athlete

    def getCoaches: Seq[Athlete]

    // countries
    def getCountryIds: Seq[CodeName]

    def getCountryIdsLatvian: Seq[CodeName]

    // clubs
    def getClubIds: Seq[IdName]

    def getClub(id: Long): Club

    def getClubs: Seq[Club]

    def exportAthletes()

    def exportClubs()

    def exportClubsAthletes()
  }
}
