package services.parsers.lookups

import models.id.IdName
import services.parsers.utils.ParserUtils

object StandardDisciplineTypesParser {
   def parse(html: String): Seq[IdName] = {
     ParserUtils.parseOptions(html, "Standartas disciplīnas:").map {
       case(id,name) => IdName(id.toInt,name)
     }
   }
 }
