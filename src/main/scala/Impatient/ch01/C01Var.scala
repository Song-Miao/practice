package Impatient.ch01

object C01Var {

  def main(args: Array[String]): Unit = {

    val x = 10;
//    x = 11; 不可变

    var  y = 10;
    y  = 20; // var 修饰可变

    lazy val z = x + y;
    println(z)
  }

}
