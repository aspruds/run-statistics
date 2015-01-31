package models.sportlat.id

import models.statistics.metadata.WithName
import org.joda.time.LocalDate

case class RaceId(
                   id: Option[Long],
                   name: String,
                   date: LocalDate,
                   location: String) extends WithName