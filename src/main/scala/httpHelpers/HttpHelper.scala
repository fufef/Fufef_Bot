package httpHelpers

import java.io.{BufferedReader, IOException, InputStreamReader}
import java.net.{HttpURLConnection, URL}
import java.net.URLEncoder

object HttpHelper {
  def buildUrl(basePath: String, params: Map[String, Option[String]]) : String = {
    val stringParams = params.map { case (key, value) => key + "=" + URLEncoder.encode(value.getOrElse(""), "UTF-8") }.mkString(sep = "&")
    f"$basePath?$stringParams"
  }

  @throws[IOException]
  def sendRequest(url: String): String = {
    val connection = new URL(url).openConnection.asInstanceOf[HttpURLConnection]
    try {
      val input = new BufferedReader(new InputStreamReader(connection.getInputStream))
      input.readLine
    } finally connection.disconnect()
  }
}
