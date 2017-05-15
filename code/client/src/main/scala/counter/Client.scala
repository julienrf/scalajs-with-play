package counter

import org.scalajs.dom
import upickle.default.{Reader, Writer}

import scala.concurrent.Future
import scalajs.concurrent.JSExecutionContext.Implicits.queue

object Client extends autowire.Client[String, Reader, Writer] {

  def doCall(req: Request): Future[String] =
    dom.ext.Ajax.post(
      url = s"/service/${req.path.mkString("/")}",
      data = upickle.default.write(req.args.to[Seq])
    ).map { response =>
      upickle.default.read(response.responseText)
    }

  def read[Result : Reader](p: String): Result = upickle.default.read(p)
  def write[Result : Writer](r: Result): String = upickle.default.write(r)

}
