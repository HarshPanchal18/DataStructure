/*
* Minimum Changes To Make Alternating Binary String
You are given a string s consisting only of the characters '0' and '1'.
In one operation, you can change any '0' to '1' or vice versa.
The string is called alternating if no two adjacent characters are equal.
For example, the string "010" is alternating, while the string "0100" is not.
Return the minimum number of operations needed to make s alternating.

Example 1:
Input: s = "0100"
Output: 1
Explanation: If you change the last character to '1', s will be "0101", which is alternating.

Example 2:
Input: s = "10"
Output: 0
Explanation: s is already alternating.

Example 3:
Input: s = "1111"
Output: 2
Explanation: You need two operations to reach "0101" or "1010".

Constraints:
1 <= s.length <= 104
s[i] is either '0' or '1'.
*/

fun main() {
    println(minOperations("1111"))
}

fun minOperations(s: String): Int {
    var count = 0
    val binaries = arrayOf('0','1')
    s.forEachIndexed{ index, ch -> if(ch != binaries[index % 2]) count++ }
    return min(count, s.length - count)
}

fun minOperations(s: String): Int = s.foldIndexed(0){ i, a, v -> a+abs(v-'0'-i%2)}.let{ minOf(it, s.length-it)}

fun minOperations(s: String): Int {
    var opsForZeroStart = 0
    var opsForOneStart = 0

    for ((i, c) in s.withIndex()) {
        if (i % 2 == 0) {
            when (c) {
                '1' -> opsForZeroStart++
                '0' -> opsForOneStart++
            }
        } else {
            when (c) {
                '0' -> opsForZeroStart++
                '1' -> opsForOneStart++
            }
        }
    }

    return minOf(opsForZeroStart, opsForOneStart)
}

fun minOperations(s: String): Int {
    var zero = false
    var counter = 0
    var i = 0

    while (i < s.length) {
        when (s[i]) {
            '0' -> {
                zero = !zero
                if (zero) counter++
            }
            '1' -> {
                zero = !zero
                if (!zero) counter++
            }
        }
        i++
    }
    return Math.min(counter, s.length - counter)
}

fun minOperations(s: String): Int {
    var len1 = 0
    var len2 = 0
    var prevBoundary = 0
    for (i in 1..<s.length) {
        if (s[i - 1] == s[i]) {
            len1 += i - prevBoundary
            len1 = len2.also { len2 = len1 }
            prevBoundary = i
        }
    }
    len1 += s.length - prevBoundary
    return Math.min(len1, len2)
}


fun minOperations(s: String): Int {
    var oddOnesEvenZeros = 0
    var oddZerosEvenOnes = 0

    for (i in s.indices) when {
        s[i] == '0' && i % 2 == 0 -> oddZerosEvenOnes++
        s[i] == '0' && i % 2 != 0 -> oddOnesEvenZeros++
        s[i] == '1' && i % 2 == 0 -> oddOnesEvenZeros++
        s[i] == '1' && i % 2 != 0 -> oddZerosEvenOnes++
    }

    return min(oddOnesEvenZeros, oddZerosEvenOnes)
}

fun minOperations(s: String): Int {

    fun sumByChange(text: String): Int {
        var sum = 0
        var lastC = s[0]
        for (i in 1 until text.length) {
            if (lastC == text[i]) {
                sum++
                lastC = if (lastC == '0') '1' else '0'
            } else {
                lastC = text[i]
            }
        }
        return sum
    }

    return minOf(sumByChange("1$s"), sumByChange(s))
}