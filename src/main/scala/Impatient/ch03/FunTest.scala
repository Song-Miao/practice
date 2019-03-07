package Impatient.ch03

object FunTest {

  def main(args: Array[String]): Unit = {
    // 1. 在Scala当中，函数是一等公民，像变量一样，既可以作为函数的参数使用，也可以将函数赋值给一个变量.
    // 2. Scala中的匿名函数也叫做函数字面量
    val add = (s1:String, s2:String) => s1 + " add " + s2
    println(add("hello", "world"))

    def addF(f: (String, String) => String, arg1 : String, arg2 : String): String = {
       f(arg1, arg2)
    }
    println(addF(add, "hello1", "hello2"))


    // 高阶函数:
    // 将一个函数作为参数的函数
    // 返回值是函数的函数.
    val ints = (0 to 10).filter(x => x % 2 == 0)
    println(ints)
    val high:String => String = s => "append " + s
    println(high("high"))


    //闭包
    //当函数的变量超出它的有效作用域的时候，还能对函数内部的变量进行访问, 变量在内部外部发生变化都能被捕获
    var sum = 0
    val array = 0 to 100
    array.foreach(x => sum += x)
    println("sum = " + sum)


    // 部分应用函数
    // 提供部分参数
    val fsum = (a : Int, b:Int, c:Int) => a + b + c
    val partsum = fsum(1, 2, _:Int)
    println("partsum = " + partsum(100))



    // 可变参数
    def printStr(args : String*): Unit = {
      for (s <- args) {
        println("可变参数 ：" + s)
      }
    }
    printStr("aaaa", "bbb", "cccc", "ddddd")


  }

}
