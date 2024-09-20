/*
* Shortest Palindrome
You are given a string s. You can convert s to a palindrome by adding characters in front of it.

Return the shortest palindrome you can find by performing this transformation.

Example 1:
Input: s = "aacecaaa"
Output: "aaacecaaa"

Example 2:
Input: s = "abcd"
Output: "dcbabcd"

Constraints:
0 <= s.length <= 5 * 104
s consists of lowercase English letters only.
*/

fun main() {
    println(shortestPalindrome("aacecaaa"))
}

fun shortestPalindrome(s: String): String {
    val resultBuilder = StringBuilder()
    val prefixBuilder = StringBuilder()
    var result = ""

    if (isPalindrome(s))
        return s

    for (i in s.indices.reversed()) {
        prefixBuilder.append(s[i])

        resultBuilder.clear()
        resultBuilder.append(prefixBuilder)
        resultBuilder.append(s)

        val string = resultBuilder.toString()

        if (isPalindrome(string)) {
            result = string
            break
        }
    }

    return result
}

fun isPalindrome(s: String): Boolean {
    var i = 0
    var j = s.lastIndex

    while (i <= j) {
        if (s[i] == s[j]) {
            i++; j--
        } else {
            return false
        }
    }

    return true
}

class Solution {
    fun shortestPalindrome(s: String): String {
        val rev = s.reversed()
        val combined = "$s#$rev"
        val lpp = computeLPP(combined)

        return rev.substring(0, s.length - lpp[combined.length - 1]) + s
    }

    private fun computeLPP(s: String): IntArray {
        val n = s.length
        val lpp = IntArray(n) { 0 }
        var len = 0
        var i = 1

        while (i < n) {
            if (s[i] == s[len]) {
                len++
                lpp[i] = len
                i++
            } else {
                if (len != 0) {
                    len = lpp[len - 1]
                } else {
                    lpp[i] = 0
                    i++
                }
            }
        }

        return lpp
    }
}

class Solution {
    fun shortestPalindrome(s: String): String {
        return if (s.length < 2) s
        else {
            buildString {
                var i = 0
                append(s)

                while (true) {
                    val isOdd = length % 2 == 1
                    val pivot = length / 2
                    var left = pivot - 1
                    var right = pivot + if (isOdd) 1 else 0

                    while (true) {
                        if (get(left) != get(right)) {
                            insert(i, s[s.lastIndex - i])
                            i++
                            break
                        }

                        left--
                        right++

                        if (left <= 0)
                            return@buildString

                    }
                }
            }
        }
    }
}

class Solution {
    fun shortestPalindrome(s: String): String {
        val kmp = kmp(s + "#" + s.reversed())
        val front = s.substring(kmp[kmp.lastIndex]).reversed()
        return front + s
    }

    fun kmp(s: String): IntArray {
        val n: Int = s.length
        val buffArray = IntArray(n)

        for (index in 1 until n) {
            var count = buffArray[index - 1]
            while (count >= 0) {
                if (s[count] == s[index]) {
                    buffArray[index] = count + 1
                    count = -1
                } else {
                    count = if (count - 1 < 0) -1 else buffArray[count - 1]
                }
            }
        }

        return buffArray
    }
}

class Solution {
    fun shortestPalindrome(s: String): String {
        val sb = StringBuilder()
        var palindromeEndIndex = s.lastIndex

        while (palindromeEndIndex > 0) {
            var left = 0
            var right = palindromeEndIndex // 6

            while (s[left] == s[right] && left < right) {
                left++
                right--
            }

            if (left >= right) { // Found the end index
                break
            }

            palindromeEndIndex--
        }

        for (i in s.lastIndex downTo (palindromeEndIndex + 1)) {
            sb.append(s[i])
        }

        sb.append(s)
        return sb.toString()
    }
}

class Solution {
    fun shortestPalindrome(s: String): String {
        val str = s + "#" + s.reversed()

        // aacecaaa # aacecaaa
        // LongestPrefix which is also a suffix

        val lps = IntArray(str.length)
        var sIndex = 0 // len = 0
        var index = 1

        while (index < str.length) {
            if (str[index] == s[sIndex]) {
                sIndex++
                lps[index] = sIndex
                index++

                if (sIndex == s.length)
                    return s

            } else if (sIndex == 0) {
                lps[index] = sIndex
                index++
            } else {
                sIndex = lps[sIndex - 1]
            }
        }

        return s.substring(
            lps[str.lastIndex],
            s.length
        ).reversed() + s
    }
}