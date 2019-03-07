package foo

import scala.collection.mutable

object SeqTest {

  // 1. indices

  // 2. size length lengthCompare
  def test1(): Unit = {
    val t = Range(1, 2)
    println(t.lengthCompare(10))

  }

  // apply(n)
  // indexOf  lastIndexOf
  // indexWhere lastIndexWhere
  // indexOfSlice lastIndexOfSlice


  def test2(): Unit = {
    val t = Seq(1 to 10: _*)
    println(t.segmentLength(_ < 5, 2))
    println(t.prefixLength(_ < 5))
  }

  // 增加元素到序列中，返回新序列
  // +: :+ padTo
  // 替换序列中元素
  // patch
  // 更新指定位置
  // updated update


  // sorted => java.util.Arrays.sort
  // sortBy
  // sortWith


  // reverse
  // reverseIterator
  // reverseMap => reverse + map


  // startsWith
  // endsWith
  // contains
  // containsSlice

  // corresponds 两个序列对应的元素是否满足断言
  def test3(): Unit ={
    val t1 = Seq('A', 'B', 'C', 'D')
    val t2 = t1.map(_.toInt)
    println(t1)
    println(t2)
    println(t1.corresponds(t2)(_ == _.toChar))
  }

  // intersect
  // diff
  // union

  // distinct ====> HashSet


  // transform
  // 1. 原地转化原始序列
  // 2. 元素类型和原来的相同
  def test4(): Unit = {
    val s = mutable.Seq(1, 2, 3)
    val p = s.transform(_ * 10)
    println(p)
    println(p eq s)

    val m = s.map(_ * 10)
    println(m)
    println(m eq s)
  }

  def main(args: Array[String]): Unit = {
    test4()
  }

}
