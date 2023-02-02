package httpHelpers

import java.io.{BufferedReader, IOException, InputStreamReader}
import java.net.{HttpURLConnection, URL}
import java.util

object HttpHelper {
  def buildUrl(basePath: String, params: Map[String, String]) : String = {
    val stringParams = params.map { case (key, value) => key + "=" + value }.mkString(sep = "&")
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
