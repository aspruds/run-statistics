package models

case class AthleteId(
                      id: Int,
                      name: String)

case class Athlete(
                    name: String,
                    dateOfBirth: String,
                    ageGroup: String,
                    sex: String,
                    country: String,
                    clubs: Seq[ClubId],
                    coaches: Seq[CoachId],
                    raceResults: Seq[RaceResult])