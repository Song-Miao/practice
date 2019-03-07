package foo

import scala.collection.immutable.HashSet

object TraverableTest {

  def test1(): Unit = {
    /// 1. foreach
    // 2.flattern
    val list = List(List(1), List(2), List(3), List(Some(1), None, Some(4)))
    println(list.flatten)
    println(List(Some(1), None, Some(4)).flatten)
    /*
List(1, 2, 3, Some(1), None, Some(4))
List(1, 4)
     */
  }

  def test2(): Unit = {
    val list = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
    println(list)
    println(list.transpose)
    /*
    List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
List(List(1, 4, 7), List(2, 5, 8), List(3, 6, 9))
     */
  }

  def test3(): Unit = {
    val list = Traversable("a" -> 1, "b" -> 2, "c" -> 3)
    val (a, b) = list.unzip
    println(a)
    println(b)

    val list2 = Traversable("a_1", "b_2", "c_3")
    val (a2, b2) = list2.unzip(s => (s(0), s.substring(2)))
    println(a2)
    println(b2)
/*
List(a, b, c)
List(1, 2, 3)
List(a, b, c)
List(1, 2, 3)
 */
  }

  def test4(): Unit = {
    import scala.reflect.NameTransformer._
    val ops = "~=<>!#%^&|*/+-:\\?@"
    ops map { o => o -> encode(o.toString) } foreach println

    val names = Traversable("A", "b", "C")
    val ids = HashSet(1, 2, 3)
    println(names ++ ids)
    println(ids ++ names)
    // ++ 大量中间集合
    println(Traversable.concat(names, ids))
/*
(~,$tilde)
(=,$eq)
(<,$less)
(>,$greater)
(!,$bang)
(#,$hash)
(%,$percent)
(^,$up)
(&,$amp)
(|,$bar)
(*,$times)
(/,$div)
(+,$plus)
(-,$minus)
(:,$colon)
(\,$bslash)
(?,$qmark)
(@,$at)
List(A, b, C, 1, 2, 3)
Set(1, A, 2, b, 3, C)
List(A, b, C, 1, 2, 3)
 */
  }

  def test5(): Unit = {
    val t = Traversable(1, 2, 3, 4, 5, 6, 7, 8, 9)
    println(t.filter(x => x % 2 == 0))
    // collect == filter + map
    // type may different
    val result = t collect {
      case x if x % 2 == 0 => x + "YES"
    }
    println(result)
/*
List(2, 4, 6, 8)
List(2YES, 4YES, 6YES, 8YES)
 */
  }

  def test6(): Unit = {
    val t = Traversable(1 to 5: _*)
    val res = t.scan(1)(_ * _)
    println(res)
    println(t.scanLeft(1)(_ * _))
    // scanLeft 保留中间结果
    //List(1, 1, 2, 6, 24, 120)
    //List(1, 1, 2, 6, 24, 120)
    //120
    // foldLeft 只返回最后的结果值
    println(t.foldLeft(1)(_ + _))
  }

  def test7(): Unit ={
    val t = Traversable(1 to 5: _*)
    println(t.take(3))
    println(t.takeWhile(_ < 4))
    println(t.drop(3))
    println(t.dropWhile(_ < 4))
  }

  def test8(): Unit = {
    // 过滤
    val t = Traversable(1 to 10: _*)
    t.filter(_ < 6).filter(_ >= 4).filter(_ % 2 == 0).foreach(println)
    // monad 直到foreach 才进行计算，filter 每一步都进行计算
    t.withFilter(_ < 6) withFilter(_ >= 4) withFilter(_ % 2 == 0) foreach println
  }

  def test9(): Unit = {
    // 分组
    val t = Traversable(1 to 10: _*)
    println(t.splitAt(5))
    println(t.span(_ % 2 != 0))
    println(t.partition(_ % 2 == 0))
    println(t.groupBy(_ % 3))
/*
(List(1, 2, 3, 4, 5),List(6, 7, 8, 9, 10))
(List(1),List(2, 3, 4, 5, 6, 7, 8, 9, 10))
(List(2, 4, 6, 8, 10),List(1, 3, 5, 7, 9))
Map(2 -> List(2, 5, 8), 1 -> List(1, 4, 7, 10), 0 -> List(3, 6, 9))
 */
  }

  def test10(): Unit = {
    // 元素是否满足条件
    val t = Traversable(1 to 10: _*)
    println(t.forall(_ <= 10))
    println(t.exists(_ == 5))

    println(t.count(_ % 2 == 0))

  }

  def test11(): Unit = {
    val t = Traversable("a good sun day")
    val i = t.aggregate(0)(_ + _.length, _ + _)
    println(i)
    println(t.par.aggregate(0)(_ + _.length, _ + _))
    println(t.par.aggregate(0)(_ + _.length, (_, _) => 0))
  }

  def test12(): Unit = {
    //  集合转换
    println(Traversable("a" -> 1, "b" -> 2).toMap)
    val t = Traversable(1 to 5: _*)
    println(t.toArray.mkString(t.toArray.stringPrefix + "(", ", ", ")"))
    println(t.toParArray)
    println(t.toBuffer)
    println(t.toIndexedSeq)
    println(t.toIterable)
    println(t.toIterator)
    println(t.toList)
    println(t.toSeq)
    println(t.toSet)
    println(t.toStream)
    println(t.toVector)
    println(t.to[scala.collection.immutable.Queue])
  }

  def test13(): Unit = {
    // 赋值元素到一个数组
    // copyToArray
    // copyToBuffer
    val t = Traversable(1 to 5: _*)
    val arr = new Array[Int](t.size)
    t.copyToArray(arr)
    println(arr.mkString(","))
    val arr2 = new Array[Int](t.size * 2)
    t.copyToArray(arr2, 0)
    println(arr2.mkString(","))
/*
1,2,3,4,5
1,2,3,4,5,0,0,0,0,0
 */

  }

  def test14(): Unit = {
    // 生成间隔队列
    val t = Traversable.range(1, 20, 5)
    println(t)
  }

  def test15(): Unit = {
    // 填充
    val t = Traversable.fill(5, 2, 2)("a")
    println(t)
    val t2 = Traversable.iterate(1, 5)(_ * 10)
    println(t2)
    val t3 = Traversable.tabulate(5)( _ * 10 )
    println(t3)
    val t4 = Traversable.tabulate(5, 2)(_ * 10 + _ )
    println(t4)
  }

  def test16(): Unit = {
    val t = Traversable(0 to 1000000: _*)
    val parT = t.par
    val seqT = t.seq

    System.gc()
    var time = System.currentTimeMillis()
    parT.sum
    println(System.currentTimeMillis() - time)

    System.gc()
    time = System.currentTimeMillis()
    seqT.sum
    println(System.currentTimeMillis() - time)
  }

  def main(args: Array[String]): Unit = {
    test16()
  }

}
