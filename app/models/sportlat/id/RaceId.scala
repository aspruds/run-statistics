package models.sportlat.id

import models.statistics.metadata.WithName

case class RaceId(
                   id: Option[Long],
                   name: String,
                   date: String,
                   location: String) extends WithName