/*
* Counting Bits
Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n),
ans[i] is the number of 1's in the binary representation of i.

Example 1:
Input: n = 2 | Output: [0,1,1]
Explanation:
0 --> 0
1 --> 1
2 --> 10

Example 2:
Input: n = 5 | Output: [0,1,1,2,1,2]
Explanation:
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101
*/

fun main(args: Array<String>) {
    val result = countBits(5)
    result.forEach { println(it) }
}

fun countBits(n: Int): IntArray {
    val counterArr = IntArray(n+1) {0}
    var index = 0
    var size = 1

    for(i in 1..n) {
        if(index == size) {
            index = 0
            size *= 2
        }
        counterArr[i] = counterArr[index] + 1
        index++
    }

    return counterArr

    /*return IntArray(n+1) {
        Integer.bitCount(it) // Get number of bits in integer value
    }*/
}