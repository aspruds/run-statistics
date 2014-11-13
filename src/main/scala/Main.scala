import org.jsoup.Jsoup;
import scala.collection.JavaConversions._
import org.jsoup.nodes.Element

object Main extends App {
	case class Result(
						pk: String,
						time: String,
						date: String,
						event: String)

	def parseSkriesim(id: Int) = {
		val url = s"http://skriesim.lv/athletes?id=$id"
		val doc = Jsoup.connect(url).get()
		val raceContainers = doc.select("tr.line2")

		//println(raceContainers)
		raceContainers.map {
			rc => {
				val tds = rc.select("td")
				def ex(id: Int) = tds(id).text

				Result(
					pk = ex(0),
					time = ex(1),
					date = ex(6),
					event = ex(7)
				)	
			}
		}
	}
	val profile = parseSkriesim(24060)
	println(profile)
}