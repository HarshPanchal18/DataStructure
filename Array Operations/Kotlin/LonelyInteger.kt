/*
* Given an array of integers, where all elements but one occur twice, find the unique element.
Example
a=[1,2,3,4,3,2,1]
The unique element is 4.

Function Description
Complete the lonelyinteger function in the editor below.

lonelyinteger has the following parameter(s):
int a[n]: an array of integers

Returns
int: the element that occurs only once

Input Format
The first line contains a single integer, n, the number of integers in the array.
The second line contains  space-separated integers that describe the values in a.

Constraints
1<=n<100

It is guaranteed that  is an odd number and that there is one unique element.
0<=a[i]<=100, where 0<=i<n.

 * Complete the 'lonelyinteger' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts INTEGER_ARRAY a as parameter.
 */

fun lonelyinteger(a: Array<Int>): Int {
    // Write your code here
    for (num in a) {
        if (a.lastIndexOf(num) == a.indexOf(num)) {
            return num
        }
    }
    return 0
}

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    val a = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()
    val result = lonelyinteger(a)
    println(result)
}
