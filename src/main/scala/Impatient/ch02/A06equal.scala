package Impatient.ch02

/**
  * 判等的约束
  * 1 如果两个对象相等，它们的散列值应该也相等
  * 2 一个对象的散列值在对象的生命周期不应该变化
  * 3 在把对象发送到另一个jvm时，应该用两个jvm里面都有的属性来判等
  */



// simple
class Item(val desc:String, val price:Double) {

  override def hashCode(): Int = (desc, price).##

  override def equals(obj: scala.Any): Boolean = obj match {
    case item:Item => desc == item.desc && price == item.price
    case _ => false
  }
}


// 多态下的判等
trait InstantaneousTime {
  val repr : Int
  override def hashCode(): Int = repr.##
  override def equals(obj: scala.Any): Boolean = obj match {
    case that:InstantaneousTime =>
      if ( that eq this) true // 引用判等
      else {
        that.## == this.## && // hashcode
          repr == that.repr
      }
    case _ => false
  }
}

trait Event extends InstantaneousTime {
  val name : String
  override def equals(obj: Any): Boolean = obj match {
    case that:Event =>
      if (that == this) true
      else {
        repr == that.repr && name == that.name
      }
    case  _ => false
  }

}



trait InstantaneousTime2 extends Equals{
  val repr : Int
  override def hashCode(): Int = repr.##
  // 使子类可以跳出其父类的判等实现
  override def canEqual(that: Any): Boolean = that.isInstanceOf[InstantaneousTime2]

  override def equals(obj: scala.Any): Boolean = obj match {
    case that:InstantaneousTime2 =>
      if ( that eq this) true // 引用判等
      else {
        that.## == this.## && // hashcode
          (that canEqual this) && //
          repr == that.repr
      }
    case _ => false
  }
}

trait Event2 extends InstantaneousTime2 {
  val name : String

  override def canEqual(that: Any): Boolean = that.isInstanceOf[Event2]

  override def equals(obj: Any): Boolean = obj match {
    case that:Event2 =>
      if (that == this) true
      else {
        (that canEqual this) && repr == that.repr && name == that.name
      }
    case  _ => false
  }

}



object A06equal {

  def main(args: Array[String]): Unit = {
    val x = new InstantaneousTime {
      override val repr: Int = 1
    }
    val y = new Event {
      override val name: String = "ss"
      override val repr: Int = 1
    }
    println("x == y : ", x == y) // true
    println("y == x : ", y == x) // false

    val x2 = new InstantaneousTime2 {
      override val repr: Int = 1
    }
    val y2 = new Event2 {
      override val name: String = "ss"
      override val repr: Int = 1
    }
    println("x2 == y2 : ", x2 == y2) // false
    println("y2 == x2 : ", y2 == x2) // false
  }


}
