package models

case class RaceResult(
                       discipline: String,
                       pk: String,
                       time: String,
                       rank: String,
                       category: String,
                       wind: Option[String],
                       rankingPoints: String,
                       ageGroup: String,
                       date: String,
                       race: RaceId)