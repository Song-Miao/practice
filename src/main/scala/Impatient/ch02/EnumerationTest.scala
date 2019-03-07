package Impatient.ch02

//  枚举工具类
object EnumerationTest extends Enumeration {
  type TrafficLightColor = Value
  val RED, Yellow, Green = Value
}

object ETest extends App {

  import Impatient.ch02.EnumerationTest.TrafficLightColor
  def doWhat (color : TrafficLightColor): Unit = {
    println(color.id)
  }

  doWhat(EnumerationTest.Green)
}
