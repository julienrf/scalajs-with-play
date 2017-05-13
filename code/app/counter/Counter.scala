package counter

import play.api.mvc.{Action, Controller}

class Counter extends Controller {

  val index = Action { request =>
    Ok("Hello, world!")
  }

}