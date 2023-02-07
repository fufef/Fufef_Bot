package botLogic

import vkAPI.{Keyboard, TextAction, Button}

object MessageHandler {
  def createAnswer(message: String): Message = {
    message match {
      case "заказать коммишку" => Message("ну заказывайте")
      case "оставить гневный отзыв" |
           "меня оставили на линии. что делать?" => Message("ваш запрос обрабатывается. оставайтесь, пожалуйста, на линии...")
      case "оставить радостный отзыв" => Message("спасибо благодарим за вашу обратную связь")
      case "ваш паблик лучший" => Message("спасибо вы тоже ничего")
      case "посмотреть прайс" => Message("прайс в разработке")
      case "задать вопрос" => Message("фурш вас слушает")
      case "хочу анек" => Message("купил фуршет шляпу а она ему - я цилиндр")
      case "отозвать фурша" => Message("фурш отозван. пусть спит дальше")
      case "другие действия" => Message("что вы хотите сделать??",
        Some(Keyboard(one_time = false, inline = false, List(
          List(
            Button("secondary", TextAction(label = "позвать фурша", `type` = "text")),
            Button("secondary", TextAction(label = "ваш паблик лучший", `type` = "text")),
          ),
          List(
            Button("secondary", TextAction(label = "оставить гневный отзыв", `type` = "text")),
            Button("secondary", TextAction(label = "оставить радостный отзыв", `type` = "text"))
          ),
          List(
            Button("secondary", TextAction(label = "меня оставили на линии. что делать?", `type` = "text"))
          ),
          List(
            Button("primary", TextAction(label = "назад", `type` = "text"))
          )
        ))))
      case "позвать фурша" => Message("фурш спит. ожидайте................",
        Some(Keyboard(one_time = true, inline = false, List(
          List(
            Button("secondary", TextAction(label = "отозвать фурша", `type` = "text"))
          )))))
      case "главное меню" | "меню" | "начать" | "старт" | "назад" => Message("что вас интересует???",
        Some(Keyboard(one_time = false, inline = false, List(
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
          ),
          List(
            Button("secondary", TextAction(label = "другие действия", `type` = "text"))
          )))))
      case _ => Message("фурш вас не понимать")
    }
  }
}
