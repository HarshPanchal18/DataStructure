/*
* String Compression III

Given a string word, compress it using the following algorithm:
Begin with an empty string comp. While word is not empty, use the following operation:
Remove a maximum length prefix of word made of a single character c repeating at most 9 times.
Append the length of the prefix followed by c to comp.
Return the string comp.

Example 1:
Input: word = "abcde"
Output: "1a1b1c1d1e"
Explanation:
Initially, comp = "". Apply the operation 5 times, choosing "a", "b", "c", "d", and "e" as the prefix in each operation.
For each prefix, append "1" followed by the character to comp.

Example 2:
Input: word = "aaaaaaaaaaaaaabb"
Output: "9a5a2b"

Explanation:
Initially, comp = "". Apply the operation 3 times, choosing "aaaaaaaaa", "aaaaa", and "bb" as the prefix in each operation.
For prefix "aaaaaaaaa", append "9" followed by "a" to comp.
For prefix "aaaaa", append "5" followed by "a" to comp.
For prefix "bb", append "2" followed by "b" to comp.

Constraints:
1 <= word.length <= 2 * 105
word consists only of lowercase English letters.
*/

fun main() {
    println(compressedString("abcde"))
    println(compressedString("aaaaaaaaaaaaaabb"))
}

fun compressedString(word: String): String {
    return buildString {
        var i = 0

        while (i < word.length) {
            var j = i
            while (j < minOf(i + 9, word.length) && word[i] == word[j]) {
                j++
            }

            append("${j - i}${word[i]}")
            i = j
        }
    }
}

class Solution {
    fun compressedString(word: String): String {
        val s = StringBuilder()
        var current = '-'
        var count = 0

        for (c in word) {
            if (count == 9) {
                s.append(9).append(current)
                count = 0
            }
            if (c == current) {
                count++
                continue
            }
            if (count > 0) s.append(count).append(current)
            current = c
            count = 1
        }
        if (count > 0) s.append(count).append(current)
        return s.toString()
    }
}

class Solution {
    fun compressedString(word: String): String {
        var comp = ""
        var count = 1
        val maxCount = 9
        var index = 1
        var pivotWord = word[0]

        while (index <= word.length) {
            if (index == word.length || pivotWord != word[index] || count >= maxCount) {
                comp += count.toString() + pivotWord
                count = 1
                pivotWord = if (index < word.length) word[index] else ' '
            } else {
                count++
            }
            index++
        }
        return comp
    }
}

class Solution {
    fun compressedString(word: String): String {
        var lastChar: Char? = null
        var lastCount: Int = 0
        val compress = StringBuilder()
        for (c in word) {
            if (lastChar == null) {
                lastChar = c
                lastCount = 1
                continue
            }

            if (c == lastChar) {
                lastCount++
                continue
            }

            compressChar(lastChar, lastCount, compress)
            lastChar = c
            lastCount = 1
        }

        if (lastChar != null)
            compressChar(lastChar, lastCount, compress)

        return compress.toString()
    }

    private fun compressChar(char: Char, count: Int, builder: StringBuilder) {
        var tempCount = count
        var partCount: Int
        while (tempCount > 0) {
            partCount = minOf(9, tempCount)
            tempCount -= partCount

            builder.append("$partCount$char")
        }
    }
}

class Solution {
    /* Complexity: Time O(N) and Space O(N) where N is the length of word. */
    fun compressedString(word: String): String {
        return buildString {
            var countPrev = 1
            for (i in 1..<word.length) {
                if (word[i] == word[i - 1] && countPrev < 9) {
                    countPrev += 1
                } else {
                    append(countPrev, word[i - 1])
                    countPrev = 1
                }
            }
            append(countPrev, word.last())
        }
    }
}