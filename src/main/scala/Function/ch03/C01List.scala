package Function.ch03

sealed trait List[+A]

// empty
case object Nil extends List[Nothing]

case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] = {
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
  }

  // 3.1
  val x = List(1, 2, 3, 4, 5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101
  }
  println("3.1 x = " + x)


  // 3.2
  def tail(list: List[Int]): List[Int] = list match {
    case Nil => Nil
    case Cons(x, xs) => xs
  }

  // 3.3
  def setHead(list: List[Int], head: Int): List[Int] = list match {
    case Nil => Nil
    case Cons(x, xs) => Cons(head, xs)
  }

  // 3.4
  def drop[A](l: List[A], n: Int): List[A] = {
    if (n == 0) l
    else {
      l match {
        case Nil => Nil
        case Cons(x, xs) => drop(xs, n - 1)
      }

    }
  }


  // 3.5
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case Nil => l
    case Cons(h, t) =>
      if ( f(h) ) dropWhile(t, f) else Cons(h, dropWhile(t, f))
  }


  //泛化
  def foldRight[A, B](as:List[A], z: B)(f: (A, B) => B): B = {
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }
  }



}

object C01List {

  def main(args: Array[String]): Unit = {
    List


    // 3.2
    println(List.tail(List(1, 2, 3)))

    // 3.3
    println(List.setHead(List(1, 2, 3), 100))

    //3.4
    println("3.4 = ",  List.drop(List(1, 2, 3), 2))


    println("3.5 = ", List.dropWhile(List(1, 2, 3, 4), (x:Int) => x % 2 == 0))


  }

}
