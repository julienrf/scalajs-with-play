package hello

import scala.scalajs.js
import org.scalajs.dom
import org.scalajs.dom.html

import mhtml._

sealed abstract class Operation
final case class IncrementStep(step: Int) extends Operation
case object Reset extends Operation

object Main extends js.JSApp {
  val step = Var(1)
  val incrementClicks = Var(())
  val resetClicks = Var(())

  val incrementOps = zipLeft(incrementClicks, step).map {
    case (click, step) => IncrementStep(step)
  }
  val resetOps = resetClicks.map(_ => Reset)
  val allOperations: Rx[Operation] = incrementOps.merge(resetOps)

  def zipLeft[A, B](x: Rx[A], y: Rx[B]): Rx[(A, B)] =
    x.product(y).sampleOn(x)

  val counter = allOperations.foldp(0) { (prev, op) =>
    op match {
      case IncrementStep(step) => prev + step
      case Reset               => 0
    }
  }

  def main(): Unit = {
    val content =
      <div>
        <h1>{ counter }</h1>
        <p><input type="number" value="1" onchange={ (e: dom.Event) =>
          step := e.target.asInstanceOf[html.Input].value.toInt
        } /></p>
        <div>
          <button onclick={ () => incrementClicks := () }>Increment</button>
          <button onclick={ () => resetClicks := () }>Reset</button>
        </div>
      </div>

    mount(dom.document.getElementById("main"), content)
  }
}
