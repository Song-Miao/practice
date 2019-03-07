package Function.ch02

object C21 {

  def main(args: Array[String]): Unit = {
    // c21
    def fib(n: Int) : Int = {

      def fibInner(n: Int, first: Int, sencond: Int): Int = {
        if (n < 2) 1
        if (n == 3) first + sencond
        else fibInner(n - 1, sencond, first + sencond)
      }
      fibInner(n, 0, 1)
    }


    println(fib(5))
    println(fib(6))
  }

}
