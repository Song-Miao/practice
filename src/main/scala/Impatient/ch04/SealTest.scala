package Impatient.ch04

// seal 修饰的class trait 只能在当前的文件里面被继承
// 使用 sealed 修饰某个 class 的目的是让 Scala 知道所有 case 的情况，否则会编译报错
sealed abstract class Human

case class Man(name: String) extends Human

case class Woman(name: String) extends Human


object SealTest {
  def main(args: Array[String]): Unit = {
    val human = Man("song")

    def mm(h: Human) = {
      h match {
        case Man(name) => println(name)
        case Woman(name) => println(name)
      }
    }
    mm(human)
  }
}
