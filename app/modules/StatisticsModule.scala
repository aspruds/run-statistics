package modules

import services.statistics.db.{ClubsRepositoryComponent, PersonRepositoryComponent, StatisticsServiceComponent}

trait StatisticsModule extends StatisticsServiceComponent with PersonRepositoryComponent with ClubsRepositoryComponent {
  override val statisticsService = new DefaultStatisticsService
  override val personRepository = new DefaultPersonRepository
  override val clubsRepository = new DefaultClubsRepository
}
