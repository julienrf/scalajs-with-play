package hello

import scala.scalajs.js
import scala.scalajs.js.annotation._

@js.native
@JSGlobalScope
object JQueryGlobal extends js.Object {
  def jQuery(selector: String): JQuery = js.native
}

@ScalaJSDefined
trait JQuery extends js.Object {
  def click(handler: js.Function1[JQueryEvent, Any]): Unit

  def html(): String
  def html(v: String): this.type

  @JSName("val") def value(): String
  @JSName("val") def value(v: String): this.type
}

@js.native
@JSGlobal("jQuery.Event")
class JQueryEvent(name: String) extends js.Object {
}
