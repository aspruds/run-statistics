package services.statistics.db

import models.statistics.{Club, Person}

trait StatisticsServiceComponent {
  this: PersonRepositoryComponent with ClubRepositoryComponent =>

  val statisticsService: StatisticsService

  import play.api.db.slick.Config.driver.simple._

  class DefaultStatisticsService extends StatisticsService {
    override def insertPerson(person: Person)(implicit session: Session): Person = {
      personRepository.insert(person)
    }

    override def insertClub(club: Club)(implicit session: Session): Club = {
      clubRepository.insert(club)
    }
  }

  trait StatisticsService {
    def insertPerson(person: Person)(implicit session: Session): Person

    def insertClub(club: Club)(implicit session: Session): Club
  }
}
