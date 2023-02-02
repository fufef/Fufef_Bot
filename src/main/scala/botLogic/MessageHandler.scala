package botLogic

import vkAPI.{Keyboard, TextAction, Button}

class MessageHandler {
  def createAnswer(message: String): Message = {
    message match {
      case "заказать коммишку" => Message("ну заказывайте")
      case "оставить гневный отзыв" => Message("ваш запрос обрабатывается")
      case "ваш паблик лучший" => Message("спасибо вы тоже ничего")
      case "главное меню" => MessageWithMenu("что вас интересует???",
        Keyboard(false, false, List(
          Button("primary", TextAction(label = "заказать коммишку", `type` = "text"))
        )))
      case _ => Message("фурш вас не понимать")
    }
  }
}
