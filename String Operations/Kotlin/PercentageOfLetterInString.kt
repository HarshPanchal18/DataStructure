import kotlin.math.floor

/*
* Percentage of Letter in String

Given a string s and a character letter, return the percentage of characters in s that equal letter rounded down to the nearest whole percent.

Example 1:
Input: s = "foobar", letter = "o"
Output: 33
Explanation:
The percentage of characters in s that equal the letter 'o' is 2 / 6 * 100% = 33% when rounded down, so we return 33.

Example 2:
Input: s = "jjjj", letter = "k"
Output: 0
Explanation:
The percentage of characters in s that equal the letter 'k' is 0%, so we return 0.

Constraints:
1 <= s.length <= 100
s consists of lowercase English letters.
letter is a lowercase English letter.
*/

fun main() {
    println(percentageLetter("foobar", 'o'))
}

fun percentageLetter(s: String, letter: Char): Int {
    return (s.count { it == letter } * 100) / s.length
}

class Solution {
    fun percentageLetter(s: String, letter: Char): Int {
        var (result, total) = Pair(0, 0)

        for (char in s)
            if (++total > 0 && char == letter)
                result++

        return result * 100 / total
    }
}

class Solution {
    fun percentageLetter(s: String, letter: Char): Int {
        return floor(s.count { it == letter }.div(s.length).times(100).toDouble()).toInt()
    }
}

class Solution {
    fun percentageLetter(s: String, letter: Char): Int {
        var total: Double = 0.0
        var letterCount: Double = 0.0

        s.forEach { i ->
            if (i == letter) letterCount++
            total++
        }

        return ((letterCount / total) * 100).toInt()
    }
}