/*
Considering MutableList as an single integer. Perform arithmetic operation on it and returning as MutableList
arr=[1,8,7]
numberToAdd = 4
arr=[1,9,1]
*/

import kotlin.math.pow

fun main(args:Array<String>) {
    val arr = mutableListOf(1, 8, 7)
    val res = addNtoArray(arr, 4)
    printList(res)
}

fun addNtoArray(a: MutableList<Int>, number: Int): MutableList<Int> {
    val size = a.size - 1
    var sum = 0
    var power: Int

    for (i in size downTo 0) {
        power = 10.0.pow(i).toInt() // 1, 10, 100, 1000
        sum += (a[size - i] * power)
    }
    sum += number
    var temp = sum
    val result = ArrayList<Int>(size)

    while (temp != 0) {
        //result.add(temp.mod(10))
        result.add(temp.rem(10))
        temp /= 10
    }

    return result.asReversed()
}

fun printList(a: MutableList<Int>) {
    a.forEach { element -> print("$element,") }
}