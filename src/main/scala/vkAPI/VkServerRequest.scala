package vkAPI

class VkServerRequest {
  val token: String = getToken
  val id: String = getID

  case class NestedConnectionResponse(server: String, key: String, ts: String)

  case class ConnectionResponse(response: NestedConnectionResponse)

  object NestedConnectionResponse {
    implicit val rw: upickle.default.ReadWriter[NestedConnectionResponse] = upickle.default.macroRW
  }

  object ConnectionResponse {
    implicit val rw: upickle.default.ReadWriter[ConnectionResponse] = upickle.default.macroRW
  }

  def getToken: String = {
    val sourceToken = scala.io.Source.fromFile("src/main/scala/vkAPI/vkToken.txt")
    try sourceToken.mkString finally sourceToken.close()
  }

  def getID: String = {
    val sourceID = scala.io.Source.fromFile("src/main/scala/vkAPI/publicID.txt")
    try sourceID.mkString finally sourceID.close()
  }

  def createConnectionRequest(): String = {
    VkHTTPHandler().createRequest("groups.getLongPollServer",
      Map("access_token" -> token, "group_id" -> id, "v" -> "5.131"))
  }

  def deserializeConnectionResponse(response: String): NestedConnectionResponse =
    upickle.default.read[ConnectionResponse](response).response

  def deserializeUpdateResponse(response: String): UpdateResponse =
    upickle.default.read[UpdateResponse](response)

  case class MessageResponse(date: Int, from_id: Int, id: Int, text: String, conversation_message_id: Int, 
                             important: Boolean, random_id: Int)

  case class ObjectResponse(message: MessageResponse)

  case class NestedUpdateResponse(`type`: String, event_id: String, `object`: ObjectResponse)

  case class UpdateResponse(ts: String, updates: Array[NestedUpdateResponse])


  object MessageResponse {
    implicit val rw: upickle.default.ReadWriter[MessageResponse] = upickle.default.macroRW
  }

  object ObjectResponse {
    implicit val rw: upickle.default.ReadWriter[ObjectResponse] = upickle.default.macroRW
  }

  object NestedUpdateResponse {
    implicit val rw: upickle.default.ReadWriter[NestedUpdateResponse] = upickle.default.macroRW
  }

  object UpdateResponse {
    implicit val rw: upickle.default.ReadWriter[UpdateResponse] = upickle.default.macroRW
  }
}
