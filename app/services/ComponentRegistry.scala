package services

import services.skriesim.SkriesimServiceComponent
import services.skriesim.parsers.SkriesimParserComponent
import services.skriesim.providers.SkriesimProviderModule

object ComponentRegistry extends SkriesimServiceComponent with SkriesimParserComponent with SkriesimProviderModule {
  override lazy val skriesimService = new DefaultSkriesimService
  override lazy val skriesimParser = new DefaultSkriesimParser
}
