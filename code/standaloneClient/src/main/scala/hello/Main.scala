package hello

import scala.scalajs.js
import org.scalajs.dom

object Main extends js.JSApp {
  def main(): Unit = {
    val p = dom.document.createElement("p")
    p.innerHTML = "Hello world"
    dom.document.body.appendChild(p)
  }
}
