package services.parsers.lookups

import models.id.CodeName
import services.parsers.utils.ParserUtils

object NonStandardDisciplineTypesParser {
   def parse(html: String): Seq[CodeName] = {
     ParserUtils.parseOptions(html, "Nestandartas disciplīnas:").map {
       case(code,name) => CodeName(code,name)
     }
   }
 }
