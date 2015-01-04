package models.skriesim

import models.skriesim.id.IdName

case class RaceResult(
                       athleteId: Option[Long],
                       distanceType: String,
                       venue: String,
                       distanceTypeWithVenue: String,
                       withQualification: Option[Boolean],
                       pk: String,
                       time: Option[Long],
                       distance: Option[BigDecimal],
                       rank: Option[Int],
                       rankText: Option[String],
                       classificationType: Option[String],
                       wind: Option[String],
                       points: Option[Short],
                       ageGroup: String,
                       date: String,
                       race: IdName)
