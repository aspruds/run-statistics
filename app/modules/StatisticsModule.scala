package modules

import services.statistics.db.{PersonsClubsRepositoryComponent, ClubsRepositoryComponent, PersonRepositoryComponent, StatisticsServiceComponent}

trait StatisticsModule extends StatisticsServiceComponent with PersonRepositoryComponent with ClubsRepositoryComponent with PersonsClubsRepositoryComponent {
  override val statisticsService = new DefaultStatisticsService
  override val personRepository = new DefaultPersonRepository
  override val clubsRepository = new DefaultClubsRepository
  override val personsClubsRepository = new DefaultPersonsClubsRepository
}
