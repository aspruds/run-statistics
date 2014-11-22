package services.skriesim.parsers.athlete

import org.jsoup.nodes.Document

class NameParser(doc: Document) {
  val name = {
    val name = doc.select("td.name").first.ownText

    val pattern = "\\s*-\\s*".r
    pattern.replaceAllIn(name, "-")
  }

  def parseGivenName(): String = name.split(" ").drop(1).mkString(" ")

  def parseFamilyName(): String = name.split(" ").head
}