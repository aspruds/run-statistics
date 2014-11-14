package models

case class ClubInAthlete(id: Int, name: String)

case class CoachInAthlete(id: Int, name: String)

case class Athlete(
                    name: String,
                    dateOfBirth: String,
                    ageGroup: String,
                    sex: String,
                    country: String,
                    clubs: Seq[ClubInAthlete],
                    coaches: Seq[CoachInAthlete],
                    raceResults: Seq[RaceResult])