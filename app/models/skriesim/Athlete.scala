package models.skriesim

import models.skriesim.id.IdName

case class Athlete(
                    id: Option[Int],
                    name: String,
                    dateOfBirth: String,
                    ageGroup: String,
                    sex: String,
                    country: String,
                    clubs: Seq[IdName],
                    coaches: Seq[IdName],
                    raceResults: Seq[RaceResult],
                    isCoach: Boolean)
