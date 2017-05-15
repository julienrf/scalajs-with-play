package hello

import scala.scalajs.js
import org.scalajs.dom
import org.scalajs.dom.html

import mhtml._

object Main extends js.JSApp {
  val counter = Var(0)
  var step: Int = 1

  def main(): Unit = {
    val content =
      <div>
        <h1>{ counter } (twice is { counter.map(c => c * 2) })</h1>
        <p><input type="number" value="1" onchange={ (e: dom.Event) =>
          step = e.target.asInstanceOf[html.Input].value.toInt
        } /></p>
        <div>
          <button onclick={ () =>
            counter.update(prev => prev + step)
          }>Increment</button>
          <button onclick={ () =>
            counter := 0
          }>Reset</button>
        </div>
      </div>

    mount(dom.document.getElementById("main"), content)
  }
}
