package models.sportlat

import models.sportlat.id.RaceDistanceId

case class RaceDistance(
                         distanceId: Option[Long],
                         name: String,
                         otherRaceDistances: Seq[RaceDistanceId],
                         results: Seq[RaceResult])