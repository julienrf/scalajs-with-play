package counter

import scala.scalajs.js.JSApp
import autowire._
import scalajs.concurrent.JSExecutionContext.Implicits.queue

object Main extends JSApp {

  def main(): Unit = {
    Client[ServiceDef].getCounter().call().onComplete(println)
  }

}
