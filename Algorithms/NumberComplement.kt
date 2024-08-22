import java.util.TreeMap
import kotlin.math.pow

/*
* Number Complement
The complement of an integer is the integer you get when you flip all the 0's to 1's and all the 1's to 0's in its binary representation.
For example, The integer 5 is "101" in binary and its complement is "010" which is the integer 2.
Given an integer num, return its complement.

Example 1:
Input: num = 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.

Example 2:
Input: num = 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.

Constraints:
1 <= num < 231
*/

fun main() {
    println(findComplement(5))
}

fun findComplement(num: Int): Int {
    if (num == 0) return 1

    val bitLength = Integer.toBinaryString(num).length
    val mask = (1 shl bitLength) - 1

    return num xor mask
}

class Solution {
    fun findComplement(num: Int): Int {
        var n = num
        var mask = 0

        while (n != 0) {
            n = n shr 1
            mask = mask shl 1 or 1
        }

        return num xor mask
    }
}

class Solution {
    fun findComplement(num: Int): Int {
        return num xor (masks[num.takeHighestOneBit()] ?: 0)
    }

    companion object {
        val masks = TreeMap<Int, Int>()

        init {
            for (i in 0..32) {
                val bit = 1 shl i
                masks[bit] = (bit shl 1) - 1
            }
        }
    }
}

class Solution {
    fun findComplement(num: Int): Int {
        var n = num
        var result = 0
        var offset = 0

        for (i in 0 until 31) {
            if (n == 0) break
            val bit = (n and 1) xor 1

            result = result or (bit shl offset)
            offset++
            n = n shr 1
        }

        return result
    }
}

class Solution {
    fun findComplement(num: Int): Int {
        var n = num
        var result = 0

        for (i in 0 until 31) {
            if (n == 0) break
            result = result or ((1 shl i xor num) and (1 shl i))
            n = n shr 1
        }

        return result
    }
}

class Solution {

    fun findComplement(num: Int): Int {
        val binary = Integer.toBinaryString(num)
        println("Binary : $binary")
        val reversed = reverseBinary(binary)
        println("Reversed: $reversed")

        return binaryToInteger(reversed)
    }

    fun reverseBinary(string: String): String {
        var newString = ""
        for (i in string.indices) {
            newString += if (string[i] == '0') "1" else "0"
        }

        return newString
    }

    fun binaryToInteger(binary: String): Int {
        val numbers = binary.toCharArray()
        var result = 0

        for (i in numbers.indices.reversed())
            if (numbers[i] == '1')
                result += 2.0.pow((numbers.size - i - 1).toDouble()).toInt()

        return result
    }
}