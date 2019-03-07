package foo

object IterableTest {

  def test1(): Unit = {

    val t = Iterable(0 to 40: _*)
    t.grouped(5).foreach(println)
    t.groupBy(_ % 10).foreach(println)

    t.sliding(20, 1).foreach(println)
  }

  def test2(): Unit = {
    val t1 = Iterable("A", "B")
    val t2 = Iterable("A", "B")
    val t3 = Iterable("A", "B", "C")
    println(t1.sameElements(t2))
    println(t1.sameElements(t3))
  }

  def main(args: Array[String]): Unit = {
    test2()
  }

}
