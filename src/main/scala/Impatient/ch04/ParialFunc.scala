package Impatient.ch04

object ParialFunc {

  //1. 函数只处理传入来的部分参数
  val pf: PartialFunction[Int, String]= {
    case 1 => "One"
    case 2 => "Two"
    case 3 => "Three"
    case _ => "Other"
  }

  def main(args: Array[String]): Unit = {

    // 1. isDefinedAt : 判断传入来的参数是否在这个偏函数所处理的范围内
    println(pf.isDefinedAt(1))

    // 2. orElse : 将多个偏函数组合起来使用
    val one : PartialFunction[Int, String] = {
      case 1 => "one"
    }
    val two : PartialFunction[Int, String] = {
      case 2 => "two"
    }
    val newPF = one orElse two
    println(newPF(2))

    // 3. andThen: 方法的连续调用
    val atPF : PartialFunction[String, String] = {
      case "one" => "in atpf partial"
    }
    println((one andThen atPF) (1))

    //4. applyOrElse：它接收2个参数，第一个是调用的参数，第二个是个回调函数。
    // 如果第一个调用的参数匹配，返回匹配的值，否则调用回调函数
    val str = atPF.applyOrElse("two", {
      s:String => "get " + s
    })
    println(str)

  }
}
