package Impatient.ch02

class Creature {

  val range:Int = 10
  val env: Array[Int] = new Array[Int](range)
}

class Ant extends Creature {
  // range 在子类重写并且在超类构造器中使用
  override val range: Int = 2
}

/**
  * 解决方案：
  * 1 将val声明为final，安全不灵活的方案
  * 2 在超类中将range声明为lazy 安全不高效
  * 3 提前定义语法
  */
class Ant2 (override val range: Int = 2) extends Creature {}


object A05Test {
  def main(args: Array[String]): Unit = {
    val egg = new Ant

    /**
      * 1 ant 构造器在构建之前先调用createure的构造器
      * 2 createure先初始化range为10
      * 3 初始化env，调用rangge() 取值器
      * 4 该方法被ant重写，返回ant类的rangge字段值
      * 5 range方法返回0 env长度设置为0
      * 6 ant构造器再继续执行，将其值设为2
      */
    println(egg.env.length)

    val egg2 = new Ant2
    println(egg2.env.length)

  }
}
