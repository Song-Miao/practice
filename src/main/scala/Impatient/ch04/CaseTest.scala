package Impatient.ch04


case class Person(firstName:String, lastName:String)

object CaseTest {

  def test(x: Any): String = x match {
    // 常量匹配
    case 0 => "zero"
    case true => "true"
    case "hello" => "world"
    case Nil => "an empty List"

    // 匹配列表
    case List(0, _, _) => "list(0, _, _)"
    case List(1, _*) => "List(1, _*)"
    case Vector(1, _*) => "vector(1, _*)"

    // 匹配元组
    case (a, b) => s"$a and $b"
    case (a, b, c) => s"got $a, $b, and $c"

    //
    case Person(first, last) => first + " " + last

    // 类型模式匹配
    case s: String => s"you gave me this string: $s"
    case i: Int => s"thanks for the int: $i"
    case f: Float => s"thanks for the float: $f"
    case a: Array[Int] => s"an array of int: ${a.mkString(",")}"
    case as: Array[String] => s"an array of strings: ${as.mkString(",")}"
    case d: Person => s"dog: ${d.firstName}"
    case list: List[_] => s"thanks for the List: $list"
    case m: Map[_, _] => m.toString

    case _ => "Unknown"
  }

  def main(args: Array[String]): Unit = {
    println(test(0))
    println(test(true))
    println(test("hello"))
    println(test(Nil))
    println(test(List(0, 1, 2)))
    println(test(List(1, 0)))
    println(test(Vector(1, 0)))
    println(test((1,2)))
    println(test((1, 3, 4)))
    println(test(Person("miao", "song")))
    println(test("string"))
    println(test(110))
    println(test(1.0f))
    println(test(Array(1, 2, 3)))
    println(test(Array("aa", "bb")))
    println(test(Map("a" -> "b")))
  }

}
