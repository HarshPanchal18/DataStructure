/*
* Sequential Digits
An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.

Example 1:
Input: low = 100, high = 300
Output: [123,234]

Example 2:
Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]

Constraints:
10 <= low <= high <= 10^9
*/

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow

fun main() {
    val res = sequentialDigits(100, 400)
    res.forEach { println(it) }
}

fun sequentialDigits(low: Int, high: Int): List<Int> {
    val result = mutableListOf<Int>()

    (1..9).forEach { digit ->
        var current = digit
        var next = digit + 1

        while (next <= 9) {
            current = current * 10 + next
            //println("$current")
            if (current in low..high)
                result.add(current)
            next++
        }
    }
    return result.sorted()
}

/**
 * There are some variable we need to find
 * - first sequential digit with length n: 123...n
 * - step of each sequential with length n: 1111...1 (n digit 1)
 *
 * Example: low = 1300, high = 13000
 * - result may have length 4 or 5
 *
 * - length = 4
 *     * step = 1111
 *     * First sequential digit is 1234
 *     * 1234 is less than low -> skip
 *     * the next is 2345, 3456, ..., 6789 -> accept
 * - length = 5
 *     * step = 11111
 *     * First sequential is 12345 -> accept
 *     * 23456 is more than 13000 -> stop
 */
class Solution {
    fun sequentialDigits(low: Int, high: Int): List<Int> {
        val result = mutableListOf<Int>()
        for (i in low.length()..high.length()) {
            var sequential = firstSequential(i)
            val step = step(i)
            while (sequential % 10 != 0) {
                if (sequential in low..high) {
                    result.add(sequential)
                    sequential += step
                }
                if (sequential < low) {
                    sequential += step
                }
                if (sequential > high) {
                    break
                }
            }
        }
        return result
    }

    private fun Int.length(): Int {
        var temp = this
        var result = 0
        while (temp > 0) {
            temp /= 10
            result++
        }
        return result
    }


    // E.g: firstSequential(3) = 123
    private fun firstSequential(length: Int): Int {
        var result = 0
        for (i in 1..length)
            result += 10.pow(length - i) * i

        return result
    }

    // E.g: step(3) = 111
    private fun step(length: Int): Int {
        var result = 0
        for (i in 1..length) {
            result += 10.pow(length - i)
        }
        return result
    }

    private fun Int.pow(n: Int): Int {
        var result = 1
        repeat(n) { result *= this }
        return result
    }
}

class Solution {
    fun sequentialDigits(low: Int, high: Int): List<Int> {
        val candidates = intArrayOf(
            1, 2, 3, 4, 5, 6, 7, 8, 9,
            12, 23, 34, 45, 56, 67, 78, 89,
            123, 234, 345, 456, 567, 678, 789,
            1234, 2345, 3456, 4567, 5678, 6789,
            12345, 23456, 34567, 45678, 56789,
            123456, 234567, 345678, 456789,
            1234567, 2345678, 3456789,
            12345678, 23456789,
            123456789
        )

        val result = ArrayList<Int>()
        for (c in candidates) {
            if (c > high)
                break
            if (c >= low)
                result.add(c)
        }

        return result
    }
}

class Solution {
    fun sequentialDigits(low: Int, high: Int): List<Int> {
        return (2..9).flatMap { digitNum ->
            (1..9)
                .windowed(digitNum)
                .map {
                    it.reversed()
                        .withIndex()
                        .sumOf { (index, value) ->
                            value * 10.0.pow(index).toInt()
                        }
                }
        }.filter { it in (low..high) }
    }
}

class Solution {
    fun sequentialDigits(low: Int, high: Int): List<Int> {
        val seq = "123456789"

        val result = mutableListOf<Int>()

        for (i in 2..9) {
            for (j in 0..<(9 - i + 1)) {
                // println("$i .. $j")
                val curr = seq.substring(j..<j + i).toInt()
                if (curr in low..high)
                    result.add(curr)
            }
        }

        return result
    }
}

class Solution {
    fun sequentialDigits(low: Int, high: Int): List<Int> {
        val result = mutableListOf<Int>()
        val digits = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        var temp = ""
        for (i in 0..digits.lastIndex) {
            temp += digits[i]
            for (j in i + 1..digits.lastIndex) {
                temp += digits[j]
                if (temp.toInt() in low..high) result.add(temp.toInt())
            }
            temp = ""
        }
        result.sort()
        return result
    }

    fun sequentialDigits(low: Int, high: Int): List<Int> {
        val all = listOf(
            12, 23, 34, 45, 56, 67, 78, 89,
            123, 234, 345, 456, 567, 678, 789,
            1234, 2345, 3456, 4567, 5678, 6789,
            12345, 23456, 34567, 45678, 56789,
            123456, 234567, 345678, 456789,
            1234567, 2345678, 3456789,
            12345678, 23456789,
            123456789
        )
        val result = mutableListOf<Int>()
        all.forEach { if (it in low..high) result.add(it) }
        return result
    }
}

class Solution {
    fun sequentialDigits(low: Int, high: Int): List<Int> {
        val res = mutableListOf<Int>()
        val q = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        while (q.isNotEmpty()) {
            val d = q.removeFirst()

            if (d in low..high)
                res.add(d)
            if (d > high)
                break

            (d % 10).also { if (it < 9) q.add(d * 10 + it + 1) }
        }
        return res
    }
}

class Solution {

    fun sequentialDigits(low: Int, high: Int): List<Int> {
        val sequentialNumbers = mutableListOf<Int>()

        /*
        generate sequential numbers:
        12,123,1234,12345,
        23,234,2345,23456
		34,345,3456,34567
        */

        for (i in 1..8) {
            var nextDigit = i
            var k = nextDigit + 1
            while (k in i..9) {
                nextDigit = (nextDigit * 10) + k++
                if (nextDigit <= high)
                    sequentialNumbers.add(nextDigit)
                else
                    break
            }
        }
        return sequentialNumbers
            .filter { it in low..high }
            .sorted()
    }
}

class Solution {
    fun sequentialDigits(low: Int, high: Int): List<Int> {
        val result = ArrayList<Int>()
        var lowDigitCount = 0
        var lowNum = low
        // Find the min length of the target number.
        // e.g. if low is 123, the lowDigitCount will be 3.
        while (lowNum > 0) {
            lowNum /= 10
            lowDigitCount++
        }

        var highDigitCount = 0
        var highNum = high
        // Find the max length of the target number.
        // e.g. if high is 1234, the highDigitCount will be 4.
        while (highNum > 0) {
            highNum /= 10
            highDigitCount++
        }

        // Iterate all possible number with specific length,
        // and check if the generated number is valid.
        for (length in lowDigitCount..highDigitCount) {
            for (startDigit in 1..9) {
                var digit = startDigit
                var num = 0
                var count = 0

                for (i in 0..<length) {
                    num = num * 10 + digit
                    digit++
                    count++

                    if (digit >= 10)
                        break
                }

                // Since we have already know the length range of
                // the valid number, we can check the num's length
                // is valid or not to skip some redundant num.
                // e.g. if low is 1000, high is 13000,
                // when the current length is 4, num is 6789, we can add
                // 6789 to the list.
                // But when the length is 5, we still generate 6789,
                // since count(4) is not equal to length(5),
                // we don't add 6789 again.
                if (count == length && num >= low && num <= high) {
                    result.add(num)
                }
            }
        }

        return result
    }
}

class Solution {
    fun sequentialDigits(low: Int, high: Int): List<Int> {
        val result = LinkedList<Int>()

        for (length in low.toString().length..high.toString().length) {

            for (start in 1..(10 - length)) {
                var currNumber = 0
                for (i in start..<start + length)
                    currNumber = (currNumber * 10) + i

                if (currNumber in low..high)
                    result.add(currNumber)
            }
        }

        return result
    }
}