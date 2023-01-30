import vkAPI.*
import botLogic.*

import java.net.URLEncoder


object Main extends App {
  val response = HttpRequest().request(vkAPI.VkServerRequest().createConnectionRequest())

  val responseObject = vkAPI.VkServerRequest().deserializeConnectionResponse(response)
  var ts = responseObject.ts

  while (true) {
    val latestUpdate = vkAPI.VkServerRequest().deserializeUpdateResponse(
      HttpRequest().request(vkAPI.VkHTTPHandler().createServerRequest(
        responseObject.server,
        responseObject.key,
        ts)))
    ts = latestUpdate.ts

    for (i <- latestUpdate.updates) {
      println(i.`object`.message.text)
      i.`type` match {
        case "message_new" =>
          val url = vkAPI.vkMessageRequest().createRequest(i)
          HttpRequest().request(url)
          Thread.sleep(1000)
      }
    }
    Thread.sleep(1000)
  }
}
