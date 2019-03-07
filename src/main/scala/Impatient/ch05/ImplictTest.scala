package Impatient.ch05

object ImplictTest {

  // 通过隐式转换，程序员可以在编写Scala程序时故意漏掉一些信息，
  // 让编译器去尝试在编译期间自动推导出这些信息来，这种特性可以极大的减少代码量，
  // 忽略那些冗长，过于细节的代码
  def main(args: Array[String]): Unit = {
    // 1. 隐式值
    def test(implicit name : String): Unit = {
      println( " in test : = " + name )
    }

    implicit val p = "AKKA"
    test


    // 2. 隐式类型转化 能把一种类型自动转换到另一种类型
    // 方法接受string类型
    def foo(msg : String): Unit = {
      println(msg)
    }
    implicit def int2String(int: Int): String = {
      int.toString
    }
    foo(100)


    // 3. 隐式转换调用类中本不存在的方法 类型中不存在的方法，尝试查找试图转化
    val silenceType = new SilenceType
    import Impatient.ch05.PlayType._
    silenceType.play()



    // 4. 隐式类
    // 在scala2.10后提供了隐式类，可以使用implicit声明类，但是需要注意以下几点：
    //1.其所带的构造参数有且只能有一个
    //2.隐式类必须被定义在类，伴生对象和包对象里
    //3.隐式类不能是case class
    //4.作用域内不能有与之相同名称的标示符
    import Impatient.ch05.Helpers._
    5 times println("ECHO")

    // 5. 查找规则
    // 1).首先会在当前代码作用域下查找隐式实体（隐式方法  隐式类 隐式对象）
    // 2).如果第一条规则查找隐式实体失败，会继续在隐式参数的类型的作用域里查找
    //    （1）如果T被定义为T with A with B with C,那么A,B,C都是T的部分，在T的隐式解析过程中，它们的伴生对象都会被搜索
    //    （2）如果T是参数化类型，那么类型参数和与类型参数相关联的部分都算作T的部分，比如List[String]的隐式搜索会搜索List的 伴生对象和String的伴生对象
    //    （3） 如果T是一个单例类型p.T，即T是属于某个p对象内，那么这个p对象也会被搜索
    //    （4） 如果T是个类型注入S#T，那么S和T都会被搜索






  }














}
