package foo

import scala.reflect.runtime.{universe => ru}

case class Person(name: String)
case class Purchase(name:String, orderNum: Int, var shipped: Boolean) {
  def build(a: String) = println(s"$a build purchase " + name + orderNum + shipped)
}
object TypeOfTest {

  def testTypeOf(): Unit = {
    println(classOf[List[Int]] == classOf[List[String]]) // true
    println(ru.typeOf[List[Int]] == ru.typeOf[List[String]]) // false

    case class A[T]()
    println(A[String].isInstanceOf[A[String]])
    println(A[String].isInstanceOf[A[Int]]) // true

    val tt = ru.typeTag[List[List[String]]]
    println(tt)
  }


  def testReflect(): Unit = {
    // 运行时启动类型
    // mirror m，它使得当前类加载器加载的所有类和类型都可用
    // 使用 reflectClass 方法获取 Person 类的 ClassMirror
    val mirror = ru.runtimeMirror(getClass.getClassLoader)
    val clazz = ru.typeOf[Person].typeSymbol.asClass
    val classMirror = mirror.reflectClass(clazz)

    val method = ru.typeOf[Person].decl(ru.termNames.CONSTRUCTOR).asMethod
    val methodMirror = classMirror.reflectConstructor(method)
    val p = methodMirror("Hello")
    println(p)
  }


  def testMemberField(): Unit = {
    // 访问调用类型成员

    val pu = Purchase("jeff", 23, shipped = true)
    val mirror = ru.runtimeMirror(getClass.getClassLoader)
    val objm = mirror.reflect(pu)
    val fieldMirror = objm.reflectField(ru.typeOf[Purchase].decl(ru.TermName("shipped")).asTerm)
    println(fieldMirror.get)
  }

  def testMethod(): Unit = {
    val pu = Purchase("jeff", 23, shipped = true)
    val rm = ru.runtimeMirror(getClass.getClassLoader)
    val objm = rm.reflect(pu)
    val method = objm.reflectMethod(ru.typeOf[Purchase].decl(ru.TermName("build")).asMethod)
    method("abcdefg")
  }


  def main(args: Array[String]): Unit = {

//    testTypeOf()


//    testMemberField()

//    testReflect()

//    testMemberField()

    testMethod()

  }

}


