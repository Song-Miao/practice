package Impatient.ch03

object FunCallByName {

  def main(args: Array[String]): Unit = {
    // call by name ：传给函数/方法M的参数是另外一个参数函数，该参数函数在函数体内调用时执行
    // call by value ： 传给函数的参数是个值，如果是个表达式或者是另外一个参数函数，
    // 则要先计算出表达式的值或者是要先得到参数函数执行后的返回值

    def something() = {
      println("calling something")
      1 // return value
    }

    def callByValue(x:Int) = {
      println( " x1 = " + x)
      println( " x2 = " + x)
    }

    def callByName(x : => Int) = {
      println( " x1 = " + x)
      println( " x2 = " + x)
    }

    callByValue(something())

    println("~~~~~~~~~~~~~~")

    callByName(something())

  }

}
