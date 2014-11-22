package services.skriesim.writers.sql

import models.skriesim.Club
import models.skriesim.id.{CodeName, IdName}

object SQLWriter {

  implicit class IdNameWriter(ids: Seq[IdName]) {
    def toSql(tableName: String) = ids.map {
      case IdName(id, name) => {
        s"INSERT INTO $tableName (ID,VALUE) VALUES($id, '$name');"
      }
    }
  }

  implicit class CodeNameWriter(ids: Seq[CodeName]) {
    def toSql(tableName: String) = ids.map {
      case CodeName(code, name) => {
        s"INSERT INTO $tableName (CODE,VALUE) VALUES('$code', '$name');"
      }
    }
  }

  implicit class ClubWriter(ids: Seq[Club]) {
    def toSql(tableName: String) = ids.map {
      case Club(id, name, country, title, description, fullDescription) => {
        s"INSERT INTO $tableName (ID,NAME,COUNTRY,TITLE,DESCRIPTION) VALUES($id, '$name','$country','$title','$description');"
      }
    }
  }
}
