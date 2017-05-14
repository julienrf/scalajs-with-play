package counter

import autowire.Core.Request
import play.api.mvc.{Action, Controller}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class CounterCtl extends Controller {

  val index = Action { request =>
    Ok("<html><head><script src=\"/asset/client-fastopt.js\"></script></head><body></body></html>").as(HTML)
  }

  def communication(path: String) = Action.async(parse.text) { request =>
    val args = upickle.default.read[Seq[(String, String)]](request.body).toMap
    Server.routes(Request(path.split("/"), args)).map(s => Ok(upickle.default.write(s)))
  }

}
