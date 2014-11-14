package models

case class RaceResult(
                       pk: String,
                       time: String,
                       rank: String,
                       category: String,
                       rankingPoints: String,
                       ageGroup: String,
                       date: String,
                       race: RaceId)