import vkAPI.*
import botLogic.*

import java.net.URLEncoder
import scala.annotation.tailrec


object Main extends App {
  def getSecret(file: String): String = {
    val secretSource = scala.io.Source.fromFile(f"src/main/scala/vkAPI/$file")
    try secretSource.mkString finally secretSource.close()
  }

  val vkToken = getSecret("vkToken.txt")
  val vkID = getSecret("publicID.txt")

  val vkServerRequest = vkAPI.VkServerRequest(vkToken, vkID)
  val response = HttpRequest().request(vkServerRequest.createConnectionRequestUrl())

  val responseObject = vkServerRequest.deserializeConnectionResponse(response)

  @tailrec
  def eventLoop(ts: String): Unit = {
    val latestUpdate = vkServerRequest.deserializeUpdateResponse(
      HttpRequest().request(vkAPI.VkHTTPHandler().createServerRequestUrl(
        responseObject.server,
        responseObject.key,
        ts)))

    @tailrec
    def lookOverUpdates(updates: List[NestedUpdateResponse]): Unit = {
      updates match {
        case update :: tail =>
          println(update.`object`.message.text)
          update.`type` match {
            case "message_new" =>
              val url = vkAPI.VkMessageRequest(vkToken, vkID).createRequest(update)
              HttpRequest().request(url)
              Thread.sleep(1000)
          }
          lookOverUpdates(tail)
        case _ =>
      }
    }
    lookOverUpdates(latestUpdate.updates)
    Thread.sleep(1000)
    eventLoop(latestUpdate.ts)
  }
  eventLoop(responseObject.ts)
}
