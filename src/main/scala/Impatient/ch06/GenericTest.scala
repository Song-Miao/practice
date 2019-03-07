package Impatient.ch06

import scala.reflect.ClassTag

object GenericTest {

  // 1. 类和特质可以带类型参数。在Scala中，我们用方括号来定义类型参数
  //    Scala会从构造参数中推断出实际类型, 可以自己指定类型
  class Pair[T, S](val first: T, val second: S)

  val p = new Pair(18, "xxx")
  var p2 = new Pair[Int, String](18, "xxx")

  // 2. 函数和方法也可以带有类型参数
  // Scala会从调用该方法使用的实际类型来推断出类型
  def getMidlle[T](a: Array[T]) = a(a.length / 2)

  var p3 = getMidlle(Array(1, 3, 5, 6, 7))


  //3. 类型界定 限制上界：T必须是Comparable[T]的子类型
  class Pair2[T <: Comparable[T]](val first: T, val second: T) {
    def smaller = if (first.compareTo(second) < 0) first else second
  }

  class Pair3[T](val first: T, val second: T) {
    // 限定下界
    def replaceFirst[R >: T](r: R) = new Pair3[R](r, second)
  }


  // 4. 隐式转换 视图界定
  //  class Pair4[T <% Comparable[T]](val first : T, val second: T) {
  //    def smaller = if (first.compareTo(second) < 0) first else second
  //  }
  class Pair4[T](val first: T, val second: T)(implicit t: T => Comparable[T]) {
    def smaller = if (first.compareTo(second) < 0) first else second
  }


  // 5. 上下文界定的形式为T:M，其中M是另一个泛型类。它要求必须存在一个类型为M[T]的“隐式值”
  class Pair5[T: Ordering](val first: T, val second: T) {
    def smaller(implicit ord: Ordering[T]) =
      if (ord.compare(first, second) < 0) first else second
  }



  // 6. 在虚拟机中，泛型相关的类型信息是被抹掉的,
  //    要实例化一个泛型的Array[T]，我们需要一个Manifest[T]对象
  def makeArr6[T : ClassTag](ele : T*) = Array[T](ele : _*)



  def main(args: Array[String]): Unit = {
    println(p)
    println(p2)
    println(p3)


    class Person()
    class Student(name: String) extends Person
    new Pair3[Student](new Student("aa"), new Student("bb")).replaceFirst(new Person())

    // Error:(37, 17) type arguments [Int] do not conform to class Pair2's type parameter bounds [T <: Comparable[T]]
    //    println(new Pair2[Int](1, 2).smaller)
    println(new Pair4[Int](1, 2).smaller)


    println(new Pair5[Long](12, 34).smaller)

    makeArr6[Int](1, 3, 5, 4).foreach(print)


  }


}
