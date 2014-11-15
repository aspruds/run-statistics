package models

case class ClubId(id: Int, name: String)

case class Club(id: Int, name: String, country: String, description: Option[String])