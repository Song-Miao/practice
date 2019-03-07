package Impatient.ch04

// 当一个类被定义成为case类后，Scala会自动帮你创建一个伴生对象并帮你实现了一系列方法
// 1. 实现了apply方法，意味着你不需要使用new关键字就能创建该类对象
// 2. 实现了unapply方法，可以通过模式匹配来获取类属性，是Scala中抽取器的实现和模式匹配的关键方法
// 3. 实现了类构造参数的getter方法（构造参数默认被声明为val），但是当你构造参数是声明为var类型的，它将帮你实现setter和getter方法
// 4. toString, equals，copy和hashCode等
case class People(name:String, age:Int)

object CaseUsage {

  def main(args: Array[String]): Unit = {
    val p = People("eoi", 101) // 1

    p match {
      case People(name, age) => println("unapply", name, age) // 2
    }

    println(p.name, p.age) // 3

    val p2 = People("eoi", 101) // 4
    println(p == p2)
  }

}
