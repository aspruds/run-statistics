package services.skriesim.providers

import services.http.db.UrlRepositoryComponent
import services.http.providers.HttpProviderComponent

trait SkriesimProviderModule extends SkriesimProviderComponent {
  override def skriesimProvider = new DefaultSkriesimProvider with HttpProviderComponent with UrlRepositoryComponent {
    override lazy val httpProvider = new DefaultHttpProvider
    override lazy val urlRepository = new DefaultUrlRepository
  }
}
