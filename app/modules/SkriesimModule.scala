package modules

import services.http.db.UrlRepositoryComponent
import services.http.providers.HttpProviderComponent
import services.skriesim.parsers.SkriesimParserComponent
import services.skriesim.providers.SkriesimProviderComponent
import services.skriesim.{SkriesimDataServiceComponent, SkriesimIntegrationServiceComponent}

trait SkriesimModule extends SkriesimDataServiceComponent with SkriesimParserComponent with SkriesimProviderComponent
 with DAL with SkriesimIntegrationServiceComponent {
  override lazy val skriesimDataService = new DefaultSkriesimDataService

  override lazy val skriesimIntegrationService = new DefaultSkriesimIntegrationService

  override lazy val skriesimParser = new DefaultSkriesimParser

  override val skriesimProvider = new DefaultSkriesimProvider with HttpProviderComponent with UrlRepositoryComponent {
    override val httpProvider = new DefaultHttpProvider
    override val urlRepository = new DefaultUrlRepository
  }
}
