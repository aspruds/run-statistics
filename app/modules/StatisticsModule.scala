package modules

import services.statistics.db._

trait StatisticsModule extends StatisticsServiceComponent with PersonRepositoryComponent with ClubsRepositoryComponent with PersonsClubsRepositoryComponent with PersonsCoachesRepositoryComponent {
  override val statisticsService = new DefaultStatisticsService
  override val personRepository = new DefaultPersonRepository
  override val clubsRepository = new DefaultClubsRepository
  override val personsClubsRepository = new DefaultPersonsClubsRepository
  override val personsCoachesRepository = new DefaultPersonsCoachesRepository
}
