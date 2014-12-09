package models.skriesim

import models.statistics.metadata.WithName

case class NonStandardDistance(code: String, name: String, distance: BigDecimal) extends WithName
