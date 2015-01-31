package models.sportlat

case class RaceResult(
                       athleteId: Option[Long],
                       bibNumber: Option[Int],
                       club: Option[String],
                       city: Option[String],
                       ageGroup: Option[String],
                       rank: Option[Int],
                       points: Option[Int],
                       isDNF: Option[Boolean],
                       isDNS: Option[Boolean],
                       time: Option[Long]
                       )