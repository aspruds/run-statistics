package services.writers.sql

import models.id.{CodeName, IdName}

object IdWriter {
  def toSql(ids: Seq[IdName], tableName: String) = {
    ids.map {
      id => {
        s"INSERT INTO $tableName (ID,VALUE) VALUES(${id.id}, '${id.name}');"
      }
    }.mkString("\n")
  }

  def toSql2(ids: Seq[CodeName], tableName: String) = {
    ids.map {
      id => {
        s"INSERT INTO $tableName (ID,VALUE) VALUES('${id.code}', '${id.name}');"
      }
    }.mkString("\n")
  }
}
