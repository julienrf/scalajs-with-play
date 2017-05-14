package counter

import upickle.default.{Reader, Writer}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

object Server extends autowire.Server[String, Reader, Writer] {
  def read[Result : Reader](p: String): Result = upickle.default.read(p)
  def write[Result : Writer](r: Result): String = upickle.default.write(r)

  val routes: Server.Router = route[ServiceDef](Service)
}
