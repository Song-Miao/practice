package Impatient.ch03

object FunCurry {

  def main(args: Array[String]): Unit = {

    // 柯里化
    // 将原来接受两个参数的函数变成新的接受一个参数的函数的过程.新的函数返回一个以原有第二个参数作为参数的函数
    def sumCurry(x:Int) = (y:Int) => x + y
    println("sumCurry = " + sumCurry(1)(2) )

    def sumCurry2(x:Int)(y:Int) = x + y
    println("sumCurry2 = " + sumCurry(1)(2) )

  }

}
