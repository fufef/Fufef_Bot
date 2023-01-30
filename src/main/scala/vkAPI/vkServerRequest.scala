package vkAPI

class vkServerRequest {
  case class NestedServerResponse(server: String, key: String, ts: String)
  case class ServerResponse(response: NestedServerResponse)
  
  object NestedServerResponse {
    implicit val rw: upickle.default.ReadWriter[NestedServerResponse] = upickle.default.macroRW
  }

  object ServerResponse {
    implicit val rw: upickle.default.ReadWriter[ServerResponse] = upickle.default.macroRW
  }

  def createConnectionRequest(): String = {
    val sourceToken = scala.io.Source.fromFile("src/main/scala/vkAPI/vkToken.txt")
    val token = try sourceToken.mkString finally sourceToken.close()
    val sourceID = scala.io.Source.fromFile("src/main/scala/vkAPI/publicID.txt")
    val id = try sourceID.mkString finally sourceID.close()

    vkHTTPHandler().createRequest("groups.getLongPollServer",
      Map("access_token" -> token, "group_id" -> id, "v" -> "5.131"))
  }


  def deserializeServerResponse(response: String): NestedServerResponse = 
    upickle.default.read[ServerResponse](response).response
}
