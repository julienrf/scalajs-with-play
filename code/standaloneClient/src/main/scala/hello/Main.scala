package hello

import scala.scalajs.js
import org.scalajs.dom
import org.scalajs.dom.html

import JQueryGlobal.jQuery

object Main extends js.JSApp {
  var counter: Int = 0

  lazy val counterHeading = jQuery("#counter")
  lazy val stepInput = jQuery("#step")
  lazy val incButton = jQuery("#inc")
  lazy val resetButton = jQuery("#reset")

  def updateCounter(newCounter: Int): Unit = {
    counter = newCounter
    counterHeading.html(counter.toString())
  }

  def main(): Unit = {
    incButton.click { (e: JQueryEvent) =>
      val step = stepInput.value().toInt
      updateCounter(counter + step)
    }

    resetButton.click { (e: JQueryEvent) =>
      updateCounter(0)
    }
  }
}
