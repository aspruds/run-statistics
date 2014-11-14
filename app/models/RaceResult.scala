package models


case class RaceInRaceResult(
                             id: Int,
                             name: String
                             )

case class RaceResult(
                       pk: String,
                       time: String,
                       rank: String,
                       category: String,
                       rankingPoints: String,
                       ageGroup: String,
                       date: String,
                       race: RaceInRaceResult)