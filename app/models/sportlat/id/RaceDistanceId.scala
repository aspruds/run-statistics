package models.sportlat.id

import models.statistics.metadata.WithName

abstract sealed class EvaluationType

case class Total() extends EvaluationType

case class InGroup() extends EvaluationType

case class RaceDistanceId(
                           distanceId: Option[Long],
                           raceId: Long,
                           evaluationType: EvaluationType,
                           name: String,
                           href: String) extends WithName