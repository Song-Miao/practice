package Impatient.ch01

object C02for {

  def main(args: Array[String]): Unit = {

    val r = if (true) 1 else 2
    print(r)

    val rr = for ( x <- 0 to 10 ) yield x
    print(rr)

  }

}
