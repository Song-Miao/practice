package Impatient.ch03

object FunGener {

  // 常见的高阶函数
  // map 将一个函数应用于列表的每一个元素并且将其作为一个新的列表返回
  // flatMap 应用于每个序列元素会返回包含原始列表所有序列内的元素的列表
  // zip 合并两个列表
  // reduce
  def main(args: Array[String]): Unit = {

    //map
    val list = 0 to 10 toList
    val mapList = list.map(x => x * x)
    println("mapList = " + mapList)

    // flatMap
    val tmpList = List(1, 2, 3)
    println(tmpList.flatMap(x => x to 5))
    val flatMapList = List(list, mapList).flatMap(x => x.map( y => y - 1))
    println("flatMapList = " + flatMapList)


    //zip
    val tuples = list.zip(mapList)
    println("zip result = " + tuples)
    // 如果列表长度不一致，到末尾就停止
    val tuples2 = list.zip(flatMapList)
    println("zip result = " + tuples2)

    val tuples3 = list.zipAll(flatMapList, "a", "B")
    println(tuples3)


    //reduce
    val testList = (0 to 5).toList
    testList.reduce((x, y) => {
      println(x, y)
      x + y
    })
    println("~~~~~~~reduceLeft~~~~~~~")
    testList.reduceLeft((x, y) => {
      println(x, y)
      x + y
    })
    println("~~~~~~~reduceRight~~~~~")
    testList.reduceRight((x, y) => {
      println(x, y)
      x + y
    })

  }
}
