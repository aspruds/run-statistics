package services.skriesim.parsers.lookups

import models.skriesim.id.IdName
import services.skriesim.parsers.utils.ParserUtils

object StandardDisciplineTypesParser {
   def parse(html: String): Seq[IdName] = {
     ParserUtils.parseOptions(html, "Standartas disciplīnas:").map {
       case(id,name) => IdName(id.toInt,name)
     }
   }
 }
