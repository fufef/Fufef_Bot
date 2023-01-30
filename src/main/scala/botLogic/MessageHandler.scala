package botLogic

class MessageHandler {
  def createAnswer(message: String): String = {
    message match {
      case "заказать коммишку" => "ну заказывайте"
      case "оставить гневный отзыв" => "ваш запрос обрабатывается"
      case "ваш паблик лучший" => "спасибо вы тоже ничего"
      case _ => "фурш вас не понимать"
    }
  }
}
