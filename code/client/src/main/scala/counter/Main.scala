package counter

import autowire._
import mhtml.{Rx, Var, mount}
import org.scalajs.dom
import org.scalajs.dom.Event
import org.scalajs.dom.html.Input

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js.JSApp
import scala.util.{Failure, Success}

object Main extends JSApp {

  def main(): Unit = {

    val client = Client[ServiceDef]

    client.get().call().onComplete {
      case Success(currentValue) =>

        val app = {

          val counter = Var(currentValue)
          var step = 1

          def onIncrement(): Unit = {
            client.increment(step).call().foreach(x => counter := x)
          }

          def onReset(): Unit = {
            client.reset().call().foreach(x => counter := x)
          }

          def onChangeStep(event: Event): Unit = {
            step = event.target.asInstanceOf[Input].value.toInt
          }

          <div style="text-align: center">
            <h1 id="counter">{ counter }</h1>
            <h1><input type="number" value={ step.toString } onchange={ onChangeStep _ } /></h1>
            <div>
              <button id="inc" onclick={ onIncrement _ }>Increment</button>
              <button id="reset" onclick={ onReset _ }>Reset</button>
            </div>
          </div>
        }

        mount(dom.document.body, app)

      case Failure(reason) => println("Unable to retrieve the counter state")
    }

  }

}
