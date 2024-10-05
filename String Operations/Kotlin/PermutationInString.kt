/*
* Permutation in String

Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
In other words, return true if one of s1's permutations is the substring of s2.

Example 1:
Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").

Example 2:
Input: s1 = "ab", s2 = "eidboaoo"
Output: false

Constraints:
1 <= s1.length, s2.length <= 104
s1 and s2 consist of lowercase English letters.
*/

fun main() {
    println(checkInclusion("ab", "eidbaooo"))
    println(checkInclusion("abc", "ccccbbbbaaaa"))
}

fun checkInclusion(s1: String, s2: String): Boolean {
    val map = IntArray(26) { 0 }
    var n = s1.length
    var i = 0
    var j = 0

    for (ch in s1)
        map[ch - 'a']++

    while (j < s2.length) {
        if (map[s2[j++] - 'a']-- > 0) n--
        if (n == 0) return true
        if (j - i == s1.length && map[s2[i++] - 'a']++ >= 0) n++
    }

    return false
}

class Solution {
    fun checkInclusion(s1: String, s2: String): Boolean {

        val chars1 = IntArray(26)
        val chars2 = IntArray(26)

        s1.forEach {
            chars1[it - 'a']++
        }

        var left = 0
        for (right in s2.indices) {
            chars2[s2[right] - 'a']++ // Each char of s2 is added to chars2

            val count = right - left + 1
            // if count of the chars in chars2 array is bigger than s1.length we need to remove
            // the first char we added in from chars2 and move our left pointer by 1
            if (count > s1.length) {
                chars2[s2[left] - 'a']--
                left++
            }

            // If content of both arrays is equal we found s1 permutation in s2 string
            if (chars1.contentEquals(chars2)) return true

        }

        return false
    }
}

class Solution {

    fun checkInclusion(s1: String, s2: String): Boolean {
        val sortedS1 = s1.toCharArray().sorted()

        for (i in 0..s2.length - s1.length) {
            val substring = s2.substring(i, i + s1.length).toList().sorted().toCharArray()
            if (sortedS1.toCharArray().contentEquals(substring))
                return true
        }

        return false
    }

}

class Solution {
    fun checkInclusion(s1: String, s2: String): Boolean {
        if (s1.length > s2.length) return false

        val s1Freq = IntArray('z' - 'a' + 1)
        val s2Freq = IntArray('z' - 'a' + 1)

        for (ch in s1) {
            s1Freq['z' - ch]++
            s2Freq['z' - ch]++
        }

        for (i in 0 until s2.length - s1.length) {
            if (s1Freq.contentEquals(s2Freq)) return true

            // decrease current start
            s2Freq['z' - s2[i]]--
            // and increase current end
            s2Freq['z' - s2[i + s1.length]]++
        }

        return s1Freq.contentEquals(s2Freq)
    }
}

class Solution {
    fun checkInclusion(s1: String, s2: String): Boolean {
        if (s2.length < s1.length) return false
        return checkInclusion(s1.toCharArray(), s2.toCharArray())
    }

    private fun checkInclusion(s1: CharArray, s2: CharArray): Boolean {
        val freq = IntArray(26)

        for (element in s1) freq[element - 'a']--

        for (i in s1.indices) freq[s2[i] - 'a']++

        if (freq.isZero()) return true

        for (i in 0 until s2.size - s1.size) {
            freq[s2[i] - 'a']--
            if (++freq[s2[i + s1.size] - 'a'] == 0 && freq.isZero()) return true
        }

        return false
    }

    private fun IntArray.isZero(): Boolean {
        /*for (i in 0..25) if (this[i] != 0) return false
        return true*/
        return this.all { it == 0 }
    }
}

class Solution {
    fun checkInclusion(s1: String, s2: String): Boolean {
        if (s2.length < s1.length) return false
        return checkInclusion(s1.toCharArray(), s2.toCharArray())
    }

    private fun checkInclusion(s1: CharArray, s2: CharArray): Boolean {
        val freq = IntArray(26)

        for (element in s1) freq[element - 'a']--
        for (i in s1.indices) freq[s2[i] - 'a']++
        if (freq.contentEquals(ZERO)) return true

        for (i in 0 until s2.size - s1.size) {
            freq[s2[i] - 'a']--
            if (++freq[s2[i + s1.size] - 'a'] == 0 && freq.contentEquals(ZERO)) return true
        }

        return false
    }

    companion object {
        val ZERO = IntArray(26)
    }
}

class Solution {
    fun checkInclusion(s1: String, s2: String): Boolean {
        var total = s1.length
        val s2Length = s2.length

        if (total > s2Length)
            return false

        val charFreq = IntArray(26)
        for (char in s1) charFreq[char - 'a']++

        fun dfs(index: Int, toLeft: Boolean): Boolean {
            if (total == 0) return true // no char left, found
            if (index < 0 || index >= s2Length) return false // border, fail
            val charCode = s2[index] - 'a'
            var found = false

            if (charFreq[charCode] > 0) {
                charFreq[charCode]--
                total--
                // continue on this direction
                found = dfs(if (toLeft) index - 1 else index + 1, toLeft)
            } else {
                return false
            }
            // backtracking
            charFreq[charCode]++
            total++
            return found
        }

        for (i in s2.indices) {
            val found = dfs(i, true) || dfs(i, false) // from both sides
            if (found) {
                return true
            }
        }

        return false
    }
}

class Solution {
    fun checkInclusion(s1: String, s2: String): Boolean {
        var total = s1.length
        val s2Length = s2.length
        if (total > s2Length) return false

        val charFreq = IntArray(26)
        for (char in s1) charFreq[char - 'a']++

        var start = 0
        for (end in s2.indices) {
            val charCode = s2[end] - 'a'
            charFreq[charCode]--
            total--

            while (charFreq[charCode] < 0) {
                charFreq[s2[start] - 'a']++
                start++
                total++
            }

            if (total == 0) return true
        }

        return false
    }
}