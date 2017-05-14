package counter

import scala.concurrent.stm.Ref

object Service extends ServiceDef {

  private val value = Ref(0)

  def getCounter(): Int = value.single.get

  def incrementCounter(): Int = value.single.transformAndGet(_ + 1)

  def resetCounter(): Int = value.single.transformAndGet(_ => 0)

}
