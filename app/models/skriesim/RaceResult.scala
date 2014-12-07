package models.skriesim

import models.skriesim.id.IdName

case class RaceResult(
                       distanceType: String,
                       venue: String,
                       distanceTypeWithVenue: String,
                       withQualification: Option[Boolean],
                       pk: String,
                       time: String,
                       rank: String,
                       category: String,
                       wind: Option[String],
                       rankingPoints: String,
                       ageGroup: String,
                       date: String,
                       race: IdName)
