package Function.ch02

object C22 {

  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {

    def loop(n:Int): Boolean = {
      if (n > as.length - 2) true
      else if (ordered(as(n + 1), as(n))) false
      else loop(n + 1)
    }
    loop(0)
  }

  def main(args: Array[String]): Unit = {
    println(isSorted(Array(1, 2, 3, 4, 5), (x:Int, y:Int) => x < y))
    println(isSorted(Array(1, 2, 6, 4, 5), (x:Int, y:Int) => x < y))
  }

}
