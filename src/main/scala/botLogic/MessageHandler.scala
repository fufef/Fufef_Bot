package botLogic

import vkAPI.{Keyboard, TextAction, Button}

class MessageHandler {
  def createAnswer(message: String): Message = {
    message match {
      case "заказать коммишку" => Message("ну заказывайте")
      case "оставить гневный отзыв" => Message("ваш запрос обрабатывается")
      case "ваш паблик лучший" => Message("спасибо вы тоже ничего")
      case "посмотреть прайс" => Message("прайс в разработке")
      case "задать вопрос" => Message("фурш вас слушает")
      case "хочу анек" => Message("купил фуршет шляпу а она ему - я цилиндр")
      case "позвать фурша" => Message("фурш спит. ожидайте................")
      case "главное меню"|"меню"|"начать"|"старт" => Message("что вас интересует???",
        Some(Keyboard(false, false, List(
          List(
          Button("primary", TextAction(label = "заказать коммишку", `type` = "text"))
        ),
          List(
            Button("secondary", TextAction(label = "посмотреть прайс", `type` = "text"))
          ),
          List(
            Button("secondary", TextAction(label = "задать вопрос", `type` = "text"))
          ),
          List(
            Button("secondary", TextAction(label = "хочу анек", `type` = "text"))
          )))))
      case _ => Message("фурш вас не понимать")
    }
  }
}
