package Impatient.ch02

import scala.beans.BeanProperty

// 1. scala 中，类并不声明为public
// 一个文件可以有多个类，这些类都有可见性
class Counter {

  private var value = 0

  def increment() = { value += 1} // 方法默认是public

  def current = value

}


class Person {
  var age = 0
  // 3. scala 生成Person.class
  // javap -p Person.class 包含一个私有的age字段和setter getter方法 public void age_$eq(int x$1)
  // private var age // setter getter也是私有的
}

// 4. 自定义 setter getter
class Person2 {
  private var priage = 0
  def age = priage
  def age_= (newValue:Int) = if (newValue > priage) priage = newValue
}

// 6. 只读属性, 使用val修饰。对象构建完成后就不在改变
// var foo : 会有getter 和 setter
// val foo : 只有getter
class Message {
  val timeStamp = java.time.Instant.now
}

// 7. 更加严格的访问限制
class Counter2 {
  private var value = 0
  def isLess (other : Counter2)  = value < other.value
}
class Counter3 {
  private[this] var value = 0
//  def isLess (other : Counter2)  = value < other.value
}


// 8. bean 生成java规范的setter 和 getter
class Person3 {
  @BeanProperty var name : String = _
}

object Test1 extends App {

  val c = new Counter
  // 2. 调用无参方法，可以不写
  // style : 对于改变值的方法使用(),对于取值的方法去掉()
  c.current
  c.increment()

  // 4.
  val p = new Person2
  p.age = 100
  println(p.age)

  // 6
  val m = new Message
  println(m.timeStamp)
//  m.timeStamp = java.time.Instant.now




}