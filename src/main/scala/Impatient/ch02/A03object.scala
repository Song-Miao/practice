package Impatient.ch02

// 2. 既有实例方法 又有静态方法的类，使用伴生对象
// 类和伴生对象可以相互访问私有特性，它们必须存在于同一个源文件中
class Accounts {
  private val id = Accounts.newUniqueNumber()
  var balance = 0
  def desposit(amount: Int): Unit =  balance += amount
}

// 1. scala 中没有静态方法和静态字段。但是可以用object语法达到同样的的目的
object Accounts {
  private var lastNumbers = 0
  def newUniqueNumber() = { lastNumbers += 1; lastNumbers}

  // 3. apply 方法：通常apply方法返回的是伴生类的对象
  def apply() = new Accounts
}



object Test2 extends App {

  // 1.
  println(Accounts.newUniqueNumber())

}

