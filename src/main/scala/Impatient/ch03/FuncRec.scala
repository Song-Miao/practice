package Impatient.ch03

import scala.annotation.tailrec

object FuncRec {

  def factorial(n: BigInt): BigInt = {
    if (n <= 1)
      1
    else
      n * factorial(n - 1)
  }


  //递归会造成堆栈的大量占用，可以使用尾递归进行优化。
  //尾递归函数中所有递归形式的调用都出现在函数的末尾。
  //当编译器检测一个函数调用时尾递归时，它就覆盖当前的活动记录而不是在栈中去创建一个新的。
  @tailrec
  def factorialTail(m:BigInt, n:BigInt) : BigInt = {
    if ( n <= 1 )
      m
    else
      factorialTail( m * n , n - 1)
  }


  def main(args: Array[String]): Unit = {
    println(factorial(5))
//    println(factorial(10000)) //Exception in thread "main" java.lang.StackOverflowError

    println(factorialTail(1, 5))
    println(factorialTail(1, 10000))
  }

}
