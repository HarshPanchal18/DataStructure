/*
* Bitwise AND of Numbers Range

Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.

Example 1:
Input: left = 5, right = 7
Output: 4

Example 2:
Input: left = 0, right = 0
Output: 0

Example 3:
Input: left = 1, right = 2147483647
Output: 0

Constraints: 0 <= left <= right <= 231 - 1
*/

fun main() {
    println(rangeBitwiseAnd(5, 7))
    println(rangeBitwiseAnd(0, 0))
    println(rangeBitwiseAnd(1, 2147483647))
}

fun rangeBitwiseAnd(left: Int, right: Int): Int {
    var _left = left
    var _right = right
    var count = 0

    while (_left != _right) {
        _left = left.shr(1)
        count++
        _right = right.shr(1)
    }
    return _left.shl(count)
}

// Time limit exceeded
fun rangeBitwiseAnd(left: Int, right: Int): Int {
    var and = -1
    (left..right).forEach {
        and = and.and(it)
    }
    return and
}

fun rangeBitwiseAnd(left: Int, right: Int): Int {
    val t = left and right
    val r = right - left
    if (r > 1) {
        val d = kotlin.math.log2(r.toDouble()).toInt()
        val m = -1 shl (d + 1)
        return m and t
    } else
        return t

}

class Solution {
    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun rangeBitwiseAnd(left: Int, right: Int): Int {
        return longestCommonPrefixBits(left, right)
    }

    private fun longestCommonPrefixBits(a: Int, b: Int): Int {
        require(a >= 0 && b >= 0)
        var result = 0
        var probe = 1 shl 30

        while (probe > 0) {
            val aBit = a and probe
            val bBit = b and probe

            if (aBit != bBit)
                break

            result += aBit
            probe = probe shr 1
        }
        return result
    }
}

class Solution {

    fun rangeBitwiseAnd(left: Int, right: Int): Int {
        var res = left

        if ((right - left > 10000)) {
            if (left < 10000)
                for (i in right - left..right)
                    res = res and i
            else
                for (i in left..right - left)
                    res = res and i
        } else {
            if (right - left > 0)
                for (i in left + 1..right)
                    res = res and i
        }
        return res
    }
}