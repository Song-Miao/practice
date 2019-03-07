package Impatient.ch02

import scala.collection.mutable.ArrayBuffer

class Person4 {

  private var name = ""
  private var age = 0

  // 1. 辅助构造器this
  def this(name: String) = {
    this() // 每一个辅助构造器都必须以主构造器调用开始
    this.name = name
  }

}

// 2. 主
// 主构造器参数直接放到类名之后
// 会在主构造器执行类定义的所有语句
// 构造参数 不带var 和 val 被方法使用，将被升为字段，否则该不保存该字段
class Person5(name: String = "", val age: Int = 0) {

  println("hello " + name)
  println("hello " + name)

  def description = s"$name is $age"
}

// 3. 嵌套类
class Network {

  class Member(val name: String) {
    val contacts = new ArrayBuffer[Member]()
  }

  private val members = new ArrayBuffer[Member]()

  def join(name: String) = {
    val m = new Member(name)
    members += m
    m
  }

}


// 4.
class Network2 {

  class Member(val name: String) {
    val contacts = new ArrayBuffer[Network2#Member]()
  }

  private val members = new ArrayBuffer[Member]()

  def join(name: String) = {
    val m = new Member(name)
    members += m
    m
  }

}


object Test extends App {
  val chatter = new Network
  val myFace = new Network

  // 3. 两个不同的member类
  // 建议将Member放入伴生对象或者使用类型投影
  // val contacts = new ArrayBuffer[Network#Member]()
  private val fred: chatter.Member = chatter.join("fred")
  private val wilma: myFace.Member = myFace.join("wilma")
  private val barney: myFace.Member = myFace.join("barney")
//    fred.contacts += barney
  wilma.contacts += barney

  val chatter2 = new Network2
  val myFace2 = new Network2
  private val fred2: chatter2.Member = chatter2.join("fred")
  private val wilma2: myFace2.Member = myFace2.join("wilma")
  private val barney2: myFace2.Member = myFace2.join("barney")
  fred2.contacts += barney2
  wilma2.contacts += barney2
}
