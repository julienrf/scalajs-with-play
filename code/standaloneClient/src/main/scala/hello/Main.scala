package hello

import scala.scalajs.js
import org.scalajs.dom
import org.scalajs.dom.html

object Main extends js.JSApp {
  var counter: Int = 0

  lazy val counterHeading =
    dom.document.getElementById("counter").asInstanceOf[html.Heading]
  lazy val stepInput =
    dom.document.getElementById("step").asInstanceOf[html.Input]
  lazy val incButton =
    dom.document.getElementById("inc").asInstanceOf[html.Button]
  lazy val resetButton =
    dom.document.getElementById("reset").asInstanceOf[html.Button]

  def updateCounter(newCounter: Int): Unit = {
    counter = newCounter
    counterHeading.textContent = counter.toString()
  }

  def main(): Unit = {
    incButton.addEventListener("click", { (e: dom.MouseEvent) =>
      val step = stepInput.value.toInt // value is a String
      updateCounter(counter + step)
    })

    resetButton.addEventListener("click", { (e: dom.MouseEvent) =>
      updateCounter(0)
    })
  }
}
