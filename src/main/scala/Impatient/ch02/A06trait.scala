package Impatient.ch02

// 1. 在Scala中，类继承Trait后，必须实现其中的抽象方法，
// 实现时不需要使用override关键字，
// 同时Scala同Java一样，不支持类多继承，但支持多重继承Trait，使用with关键字即可
trait Atrait {
  def funA(text:String)
}
trait Btrait {
  def funB(text:String)
}
class Ccls extends Atrait with Btrait {
  override def funA(text: String): Unit = println("in funA : " + text)
  override def funB(text: String): Unit = println("int funB : " + text)
}


// 2. Trait不只定义抽象方法，还可以定义具体方法
trait Logger {
  def log(msg:String) = println(msg)
}


//3. Trait可以定义具体字段
trait Dtrait {
  val name:String = "default"
}
class Ecls extends Dtrait {
  def funD = println("in funD " + name)
}

//4. Trait中可以定义抽象字段，而Trait中的具体方法可以基于抽象字段来编写，
// 继承Trait的类，则必须覆盖抽象的field，提供具体的值
trait Ftrait {
  val name:String
}
class Fcls extends Ftrait {
  def funF = println("in funF " + name)
  override val name: String = "hehe"
}


//5. 为实例混入Trait：在创建某个类的对象时
// 可以指定该对象混入某个Trait
trait ExtraLogger extends Logger {
  override def log(msg: String): Unit = println("in G log ...." + msg)
}
class Gcls extends Logger {
  def funG(text : String) = {
    log(text)
  }
}


//6. 类继承多个Trait 依次调用多个Trait中的同一个方法，只要让多个Trait中的同一个方法中，在最后都执行super方法。
// 首先会从最右边的Trait的方法开始执行，
// 然后依次向左执行，形成一个调用条。这个相当于设计模式中的责任链模式的一种具体实现依赖
trait Handler {
  def handler = println("root handler")
}
trait AHandler extends Handler{
  override def handler = {
    println("A handler")
    super.handler
  }
}
trait BHandler extends Handler{
  override def handler = {
    println("B handler")
    super.handler
  }
}
class Hcls extends Handler with AHandler with BHandler {
  def funH = {
    println("in H fun....")
    handler
  }
}

//7. 可以覆盖父Trait的抽象方法，如果使用super调用，需要加上abstract override修饰。
trait Logger2 {
  def log(message: String)
}
trait MyLogger extends Logger2 {
  abstract override def log(message: String) {
    super.log(message)
  }
}


// 8. Trait是有构造代码的
//1）父类的构造函数
//2）Trait的构造代码执行，多个Trait从左向右依次执行
//3）构造Trait时会先构造父Trait，如果多个Trait继承同一个父Trait，则父Trait只会构造一次
//4）所有trait构造结束之后，子类的构造函数执行



//9. 在Scala中，Trait可以继承class，这个class就会成为所有继承该trait的类的父类


object A06trait {
  def main(args: Array[String]): Unit = {
    val c = new Ccls

    val e = new Fcls
    e.funF

    // 5
    val g = new Gcls with ExtraLogger
    g.funG("GGGGGG")

    val h = new Hcls
    h.funH
  }
}
