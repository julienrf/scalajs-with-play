package counter

import autowire._
import mhtml.{Var, mount}
import org.scalajs.dom

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js.JSApp
import scala.util.{Failure, Success}

object Main extends JSApp {

  def main(): Unit = {

    val client = Client[ServiceDef]

    client.getCounter().call().onComplete {
      case Success(currentValue) =>

        val app = {

          val counter = Var(currentValue)

          def onIncrement(): Unit = {
            client.incrementCounter().call().foreach(x => counter := x)
          }

          def onReset(): Unit = {
            client.resetCounter().call().foreach(x => counter := x)
          }

          <div style="text-align: center">
            <h1>{ counter }</h1>
            <div>
              <button onclick={ onIncrement _ }>Increment</button>
              <button onclick={ onReset _ }>Reset</button>
            </div>
          </div>
        }

        mount(dom.document.body, app)

      case Failure(reason) => println("Unable to retrieve the counter state")
    }

  }

}
