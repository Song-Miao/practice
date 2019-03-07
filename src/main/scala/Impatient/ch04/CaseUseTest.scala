package Impatient.ch04

object CaseUseTest {

  def main(args: Array[String]): Unit = {
    // 1. for 循环中使用
    for ((language, "Hadoop") <- Set("Scala" -> "Spark", "Java" -> "Hadoop")) {
      println(language)
    }

    // 2. 正则表达式中使用
    val numPattern =
      """([0-9]+)([a-z])+""".r
    val s = "2018 eoi"
    s match {
      case numPattern(num, str) => println("num = ", num, "str = ", str)
      case _ => println("ignore")
    }

    // 3. 异常中使用

    // 4. option中使用
    def matchOption(x: Option[Int]) = x match {
      case Some(s) => s
      case None => "?"
    }
    println(matchOption(Some(1)))
    println(matchOption(None))

  }


}
