package Impatient.ch01

object C03try {

  def main(args: Array[String]): Unit = {

    val r = try {
      val x = 1 / 0
    } catch {
      case _ => 0
    } finally {
      println("finally")
    }
    println(r == 0)

    val m = "a"
    val rr = m match {
      case "a" => 200
      case "b" => 400
      case _ => 500
    }
    print(rr == 200)
  }



}
