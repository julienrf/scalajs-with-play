package hello

import scala.scalajs.js
import org.scalajs.dom
import org.scalajs.dom.html

import JQueryGlobal.jQuery

object Main extends js.JSApp {
  var counter: Int = 0

  lazy val incButton = jQuery("#inc")

  def main(): Unit = {
    incButton.click { (e: JQueryEvent) =>
      dom.window.alert("The 'increment' button was clicked")
    }
  }
}
