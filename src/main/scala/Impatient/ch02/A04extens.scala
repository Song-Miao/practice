package Impatient.ch02


// 1. 和java统一采用extends关键字
// 2. 重写非抽象方法必须使用override修饰符
// 3. 调用超类的方法和java一样，使用super关键字
// 4. 类型检查isInstanceOf 转换asInstanceOf
// 5. scala.Predef.classOf 判断类型
class Parent(name:String) {

}

// 6. 主构造器和类的定义交织在一起的
class Child(name:String, age:Int) extends Parent(name:String) {

}

//7. 重写字段
// def 只能重写另一个def
// val 只能重写另一个val或着不带参数的def
// var 只能重写另一个抽象的var
abstract class Human {
  def id: Int
}
class Student(override val id: Int) extends Human



abstract class Human2 {
  def getId: Int
  // 10.抽象字段 没有初始值的字段
  val id:Int  // 带有抽象getter方法
  var name:String // 带有抽象的getter 和 setter方法
}
class Student2 extends Human2 {
  // 9. 重写抽象方法可以不使用override关键字
  def getId: Int = 1

  override val id: Int = 1
  override var name: String = ""
}










object A04Test extends App {
  val c = new Child("songm", 18)
  // 4
  println(c.isInstanceOf[Parent])
  val p = c.asInstanceOf[Parent]
  println(p)
  // 5
  println(p.getClass == classOf[Parent])


  // 8. 匿名子类
  val alien = new Human {
    override def id: Int = 100
  }
}