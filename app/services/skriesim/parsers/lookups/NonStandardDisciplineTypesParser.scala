package services.skriesim.parsers.lookups

import models.skriesim.id.CodeName
import services.skriesim.parsers.utils.ParserUtils

object NonStandardDisciplineTypesParser {
   def parse(html: String): Seq[CodeName] = {
     ParserUtils.parseOptions(html, "Nestandartas disciplīnas:").map {
       case(code,name) => CodeName(code,name)
     }
   }
 }
