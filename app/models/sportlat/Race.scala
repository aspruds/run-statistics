package models.sportlat

import models.statistics.metadata.WithName

case class Race(
                   id: Option[Long],
                   name: String,
                   date: String) extends WithName