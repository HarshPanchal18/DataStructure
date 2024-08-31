/*
* Multiply Strings
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.

Example 1:
Input: num1 = "2", num2 = "3"
Output: "6"

Example 2:
Input: num1 = "123", num2 = "456"
Output: "56088"

Constraints:
1 <= num1.length, num2.length <= 200
num1 and num2 consist of digits only.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
*/

fun main() {
    println(multiply("123456789", "987654321"))
}

fun multiply(num1: String, num2: String): String {
    return (
            num1.map { c -> c.digitToInt() }.joinToString("").toBigInteger() *
                    num2.map { c -> c.digitToInt() }.joinToString("").toBigInteger()
            ).toString()

}

class Solution {
    fun multiply(num1: String, num2: String): String {
        val m = num1.length
        val n = num2.length
        val pos = IntArray(m + n)

        for (i in num1.indices.reversed()) {
            for (j in num2.indices.reversed()) {
                val mul = (num1[i].code - '0'.code) * (num2[j].code - '0'.code)
                val p1 = i + j
                val p2 = i + j + 1
                val sum = mul + pos[p2]

                pos[p1] += sum / 10
                pos[p2] = (sum) % 10
            }
        }

        val sb = StringBuilder()
        for (p in pos)
            if (!(sb.isEmpty() && p == 0))
                sb.append(p)

        return if (sb.isEmpty()) "0" else sb.toString()
    }

    fun multiply(num1: String, num2: String): String {
        if (num1 == "0" || num2 == "0") return "0"

        val num1 = StringBuilder(num1).reversed().toString()
        val num2 = StringBuilder(num2).reversed().toString()
        val len1 = num1.length;
        val len2 = num2.length

        val sums = IntArray(len1 + len2)

        for (i in 0..len1 - 1) {
            val a = num1[i] - '0'
            for (j in 0..len2 - 1) {
                val b = num2[j] - '0'
                sums[i + j] += a * b
            }
        }

        val res = StringBuilder()
        var carry = 0

        for (sum in sums) {
            val num = (sum + carry) % 10
            carry = (sum + carry) / 10
            res.append(num)
        }

        if (res.last() == '0') res.deleteCharAt(res.lastIndex)
        return res.reversed().toString()
    }
}

class Solution {
    fun multiply(num1: String, num2: String): String {
        if (num1 == "0" || num2 == "0") return "0"
        val num = num1.reversed()
        var curr = "0"

        num.forEachIndexed { i, it ->
            curr = addStrings(helper(num2, it - '0', i), curr)
        }

        return curr
    }

    fun helper(str: String, digit: Int, tens: Int): String {
        var carry = 0
        val curr = str.reversed()
        val sb = StringBuilder()

        curr.forEach {
            val num = it - '0'
            val temp = num * digit + carry
            carry = temp / 10
            sb.append(temp % 10)
        }

        if (carry != 0) sb.append(carry)
        sb.reverse()

        for (i in 0 until tens)
            sb.append("0")

        return sb.toString()
    }

    fun addStrings(num1: String, num2: String): String {
        val x = num1.reversed()
        val y = num2.reversed()
        val sb = StringBuilder()
        var i = 0
        var carry = 0
        while (i < num1.length || i < num2.length || carry != 0) {
            val x1 = if (i > num1.lastIndex) 0 else x[i] - '0'
            val y1 = if (i > num2.lastIndex) 0 else y[i] - '0'

            val curr = x1 + y1 + carry

            carry = curr / 10
            sb.append(curr % 10)
            i++

        }
        return sb.reverse().toString()
    }
}