import vkAPI._


object Main extends App {
  val response = httpRequest().request(vkAPI.vkServerRequest().createConnectionRequest())

  val responseObject = vkAPI.vkServerRequest().deserializeServerResponse(response)
  val ts = responseObject.ts

  while (true) {
    val a = httpRequest().request(vkAPI.vkHTTPHandler().createServerRequest(
      responseObject.server,
      responseObject.key,
      ts))
    println(a)
    Thread.sleep(15000)
  }
}
