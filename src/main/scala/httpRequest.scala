import java.io.{BufferedReader, IOException, InputStreamReader}
import java.net.{HttpURLConnection, URL}
import java.util


class httpRequest {
  private var headers = null

  @throws[IOException]
  def request(url: String): String = {
    val connection = new URL(url).openConnection.asInstanceOf[HttpURLConnection]
    try {
      val input = new BufferedReader(new InputStreamReader(connection.getInputStream))
      input.readLine
    } finally connection.disconnect()
  }
}
