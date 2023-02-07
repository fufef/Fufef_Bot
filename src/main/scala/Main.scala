import vkAPI.{ConnectionResponse, NestedUpdateResponse, UpdateResponse, VkRequestUrlBuilder}
import httpHelpers.HttpHelper

import scala.annotation.tailrec
import upickle.default.read


object Main extends App {
  def getSecret(file: String): String = {
    val secretSource = scala.io.Source.fromFile(f"src/main/scala/vkAPI/$file")
    try secretSource.mkString finally secretSource.close()
  }

  val db = slick.jdbc.JdbcBackend.Database.forConfig("postgres")
  val session = db.createSession()
  val stm = session.conn.createStatement()

  val res = stm.executeQuery("""
      select
         id, name
      from
         test
      limit 10""")
  while (res.next()) {
    print(res.getString(2))
  }

  stm.close()
  session.close()

  val vkToken = getSecret("vkToken.txt")
  val vkID = getSecret("publicID.txt")

  val vkRequestUrlBuilder = vkAPI.VkRequestUrlBuilder(vkToken, vkID)
  val response = HttpHelper.sendRequest(vkRequestUrlBuilder.createConnectionRequestUrl())

  val responseObject = read[ConnectionResponse](response).response

  @tailrec
  def eventLoop(ts: String): Unit = {
    val latestUpdate = read[UpdateResponse](
      HttpHelper.sendRequest(VkRequestUrlBuilder.createLongPollRequestUrl(
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
              val url = vkRequestUrlBuilder.createMessage(update)
              HttpHelper.sendRequest(url)
              Thread.sleep(250)
            case _ =>
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
