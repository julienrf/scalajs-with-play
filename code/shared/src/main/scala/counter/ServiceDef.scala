package counter

trait ServiceDef {

  def getCounter(): Int

  def incrementCounter(): Int

  def resetCounter(): Int

}
