package Impatient.ch03

object Quick {

  def quickSort(list: List[Int]) : List[Int] = {
    list match {
      case Nil => Nil
      case x :: xs => {
        val (left, right) = xs.partition(_ < x)
        quickSort(left) ++ (x :: quickSort(right))
      }
    }
  }


  def main(args: Array[String]): Unit = {
    println(quickSort(List(12, 55, 33, 33, 22, 3, 56, 372, 34)))
  }

}
