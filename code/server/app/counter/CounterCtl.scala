package counter

import autowire.Core.Request
import play.api.mvc.{Action, Controller}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.twirl.api.{Html, HtmlFormat}

class CounterCtl extends Controller {

  val greet = Action { request =>
    Ok("Hello, world!")
  }

  val index = Action { request =>
    val clientScript = {
      Seq("client-opt.js", "client-fastopt.js")
        .find(name => getClass.getResource(s"/public/$name") != null)
        .map(name => controllers.routes.Assets.versioned(name).url)
        .get
    }
    val html =
      HtmlFormat.fill(List(
        Html("<html><head><script src=\""),
        HtmlFormat.escape(clientScript),
        Html("\"></script></head><body></body></html>")
      ))
    Ok(html)
  }

  val increment = Action(parse.json) { request =>
    request.body.validate[Int]
      .fold(
        _ => BadRequest,
        step => Ok(Json.toJson(Service.increment(step)))
      )
  }

  def service(path: String) = Action.async(parse.text) { request =>
    val args = upickle.default.read[Seq[(String, String)]](request.body).toMap
    Server.routes(Request(path.split("/"), args)).map(s => Ok(upickle.default.write(s)))
  }

}
