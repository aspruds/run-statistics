package modules

import services.http.db.UrlRepositoryComponent
import services.http.providers.HttpProviderComponent
import services.sportlat.{SportlatIntegrationServiceComponent, SportlatDataServiceComponent}
import services.sportlat.parsers.SportlatParserComponent
import services.sportlat.providers.SportlatProviderComponent

trait SportlatModule extends SportlatProviderComponent with SportlatParserComponent with SportlatDataServiceComponent
with SportlatIntegrationServiceComponent with  DAL{

  override lazy val sportlatDataService = new DefaultSportlatDataService

  override lazy val sportlatIntegrationService = new DefaultSportlatIntegrationService

  override lazy val sportlatParser = new DefaultSportlatParser

  override val sportlatProvider = new DefaultSportlatProvider with HttpProviderComponent with UrlRepositoryComponent {
    override val httpProvider = new DefaultHttpProvider
    override val urlRepository = new DefaultUrlRepository
  }
}
