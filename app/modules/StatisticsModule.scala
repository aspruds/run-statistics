package modules

import services.statistics.db._

trait StatisticsModule extends StatisticsServiceComponent with PersonRepositoryComponent with ClubRepositoryComponent
with PersonsClubsRepositoryComponent with PersonsCoachesRepositoryComponent with RaceRepositoryComponent
with DistanceTypeRepositoryComponent with DisciplineTypeRepositoryComponent with VenueTypeRepositoryComponent
with RaceDistanceRepositoryComponent with RaceResultRepositoryComponent
with AgeGroupRepositoryComponent {
  override val statisticsService = new DefaultStatisticsService
  override val personRepository = new DefaultPersonRepository
  override val clubRepository = new DefaultClubRepository
  override val personsClubsRepository = new DefaultPersonsClubsRepository
  override val personsCoachesRepository = new DefaultPersonsCoachesRepository
  override val raceRepository = new DefaultRaceRepository
  override val ageGroupRepository = new DefaultAgeGroupRepository
  override val distanceTypeRepository = new DefaultDistanceTypeRepository
  override val disciplineTypeRepository = new DefaultDisciplineTypeRepository
  override val venueTypeRepository = new DefaultVenueTypeRepository
  override val raceDistanceRepository = new DefaultRaceDistanceRepository
  override val raceResultRepository = new DefaultRaceResultRepository
}
