import org.jsoup.Jsoup;
import scala.collection.JavaConversions._
import org.jsoup.nodes.Element

object Main extends App {
	case class Result(
						pk: String,
						time: String,
						rank: String,
						category: String,
						rankingPoints: String,
						ageGroup: String,
						date: String,
						event: String)

	def parseSkriesim(id: Int) = {
		val url = s"http://skriesim.lv/athletes?id=$id"
		val doc = Jsoup.connect(url).get()
		val raceContainers = doc.select("tr.line2")
		raceContainers.map {
			container => {
				val columns = container.select("td")
				def ex(id: Int) = columns(id).text

				Result(
					pk = ex(0),
					time = ex(1),
					rank = ex(2),
					category = ex(3), 
					rankingPoints = ex(4),
					ageGroup = ex(5),
					date = ex(6),
					event = ex(7)
				)	
			}
		}
	}
	val profile = parseSkriesim(24060)
	println(profile)
}