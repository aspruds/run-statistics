package services.statistics.db

import models.statistics.{Club, Person}

trait StatisticsServiceComponent {
  this: PersonRepositoryComponent with ClubsRepositoryComponent =>

  val statisticsService = new DefaultStatisticsService

  class DefaultStatisticsService extends StatisticsService {
    override def insertPerson(person: Person): Person = {
      personRepository.insert(person)
    }

    override def insertClub(club: Club): Club = {
      clubsRepository.insert(club)
    }
  }

  trait StatisticsService {
    def insertPerson(person: Person): Person

    def insertClub(club: Club): Club
  }
}
