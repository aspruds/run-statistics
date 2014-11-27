package modules

import services.http.db.UrlRepositoryComponent
import services.http.providers.HttpProviderComponent
import services.skriesim.SkriesimServiceComponent
import services.skriesim.export.SkriesimExporterComponent
import services.skriesim.parsers.SkriesimParserComponent
import services.skriesim.providers.SkriesimProviderComponent
import services.statistics.db.CountryRepositoryComponent

trait SkriesimModule extends SkriesimServiceComponent with SkriesimParserComponent with SkriesimProviderComponent with SkriesimExporterComponent with StatisticsModule {
  override lazy val skriesimService = new DefaultSkriesimService

  override lazy val skriesimParser = new DefaultSkriesimParser

  override val skriesimProvider = new DefaultSkriesimProvider with HttpProviderComponent with UrlRepositoryComponent {
    override val httpProvider = new DefaultHttpProvider
    override val urlRepository = new DefaultUrlRepository
  }

  override val skriesimExporter = new DefaultSkriesimExporter with CountryRepositoryComponent {
    override val countryRepository = new DefaultCountryRepository
  }
}
