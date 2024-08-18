import java.util.PriorityQueue
import kotlin.collections.HashSet

/*
* Ugly Number II
An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
Given an integer n, return the nth ugly number.

Example 1:
Input: n = 10
Output: 12
Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.

Example 2:
Input: n = 1
Output: 1
Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.

Constraints:
1 <= n <= 1690
*/

fun main() {
    println(nthUglyNumber(10))
}

fun Int.isUgly(): Boolean {
    var n = this

    for (d in intArrayOf(2, 3, 5)) {
        while (n.mod(d) == 0)
            n /= d
    }

    return n == 1
}

fun nthUglyNumber(n: Int): Int {
    var answer = 1
    var count = 1

    while (count < n) {
        answer++
        if (answer.isUgly())
            count++
    }

    return answer
}

class Solution {
    fun nthUglyNumber(n: Int): Int {
        val ugly = IntArray(n)
        ugly[0] = 1
        var i2 = 0
        var i3 = 0
        var i5 = 0
        var next2 = 2
        var next3 = 3
        var next5 = 5

        for (i in 1 until n) {
            val nextUgly = minOf(next2, next3, next5)
            ugly[i] = nextUgly

            if (nextUgly == next2) {
                i2++
                next2 = ugly[i2] * 2
            }
            if (nextUgly == next3) {
                i3++
                next3 = ugly[i3] * 3
            }
            if (nextUgly == next5) {
                i5++
                next5 = ugly[i5] * 5
            }
        }

        return ugly[n - 1]
    }
}

fun nthUglyNumber(n: Int): Int {
    val set = HashSet<Long>()
    val pq = PriorityQueue<Long>()
    var nth = 0L

    pq.offer(1L)

    for (i in 1..n) {
        nth = pq.poll()

        val u2 = nth * 2L
        val u3 = nth * 3L
        val u5 = nth * 5L

        if (!set.contains(u2)) {
            set.add(u2)
            pq.offer(u2)
        }
        if (!set.contains(u3)) {
            set.add(u3)
            pq.offer(u3)
        }
        if (!set.contains(u5)) {
            set.add(u5)
            pq.offer(u5)
        }
    }

    return nth.toInt()
}

class Solution {

    fun nthUglyNumber(n: Int): Int {
        var arr = IntArray(n)

        arr[0] = 1

        var i = 1
        var i2 = 0
        var i3 = 0
        var i5 = 0
        while (i < n) {
            val a = arr[i2] * 2
            val b = arr[i3] * 3
            val c = arr[i5] * 5
            arr[i] = min(min(a, b), c)

            if (arr[i] == a) i2++
            if (arr[i] == b) i3++
            if (arr[i] == c) i5++
            i++
        }

        return arr[n - 1]
    }
}

class Solution {
    fun nthUglyNumber(n: Int): Long {
        val primes = listOf(2, 3, 5)
        val seen = mutableSetOf<Long>()
        val minHeap = PriorityQueue<Long>()

        seen += 1L
        minHeap.offer(1L)

        var number = 1L
        for (i in 1..n) {
            number = minHeap.poll()

            for (prime in primes) {
                val nextUgly = prime * number
                if (nextUgly !in seen) {
                    seen += nextUgly
                    minHeap.offer(nextUgly)
                }
            }
        }
        return number
    }
}

class Solution {
    fun nthUglyNumber(n: Int): Int {
        // every ugly number is product of 2, 3, 5,
        // l2, l3, l5 keep reference of head of 3 lists, then merge the list until reach n
        if (n == 1) return 1

        val ugly = IntArray(n + 1) { 1 } //0, 1, 2, 3
        var (p2, p3, p5) = arrayOf(1, 1, 1)
        var (prod2, prod3, prod5) = arrayOf(1, 1, 1)
        var p = 1 //stands for the head of the merged list

        while (p <= n) { // 5
            // count 2 3 4
            val min = minOf(prod2, prod3, prod5) // 1 2
            ugly[p] = min
            p++

            if (min == prod2) {
                prod2 = ugly[p2] * 2 // 1*2 2*2
                p2++
            }
            if (min == prod3) {
                prod3 = ugly[p3] * 3
                p3++
            }
            if (min == prod5) {
                prod5 = ugly[p5] * 5
                p5++
            }

        }
        return ugly[n]

    }

}