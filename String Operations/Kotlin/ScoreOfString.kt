import kotlin.math.abs

/*
* Score of a String
You are given a string s. The score of a string is defined as the sum of the absolute difference between the ASCII values of adjacent characters.
Return the score of s.

Example 1:
Input: s = "hello"
Output: 13
Explanation:
The ASCII values of the characters in s are: 'h' = 104, 'e' = 101, 'l' = 108, 'o' = 111. So, the score of s would be |104 - 101| + |101 - 108| + |108 - 108| + |108 - 111| = 3 + 7 + 0 + 3 = 13.

Example 2:
Input: s = "zaz"
Output: 50
Explanation:
The ASCII values of the characters in s are: 'z' = 122, 'a' = 97. So, the score of s would be |122 - 97| + |97 - 122| = 25 + 25 = 50.

Constraints:
2 <= s.length <= 100
s consists only of lowercase English letters.
*/

fun main() {
	println(scoreOfString("hello"))
}

fun scoreOfString(s: String): Int {
	var sum = 0

	for (i in 1..s.lastIndex) {
		sum += abs(s[i].code - s[i - 1].code)
	}

	return sum
}

fun scoreOfString(s: String, i: Int = 1): Int {
	return if (i == s.length) 0 else abs(s[i].code - s[i - 1].code) + scoreOfString(s, i + 1)
}

fun scoreOfString(s: String) = s.windowed(2) { abs(it[0] - it[1]) }.sum()

class Solution {
	fun scoreOfString(s: String): Int {
		return s
			.asSequence()
			.windowed(2)
			.sumOf { (c1, c2) -> abs(c1 - c2) }
	}
}

fun scoreOfString(s: String): Int =
	s.windowed(2).sumOf { abs(it[0] - it[1]) }

class Solution {
	fun scoreOfString(s: String): Int {
		val lengthString = s.length
		var sumTotal = 0

		for (i in s.indices) {
			val character = s[i]
			var sumTemp = 0

			if (i != lengthString - 1) {
				val nextCharacter = s[i + 1]
				sumTemp = character.code - nextCharacter.code
			}
			sumTotal += abs(sumTemp)

		}

		return sumTotal
	}
}

class Solution {
	fun scoreOfString(s: String): Int {
		var sub: Int
		val sum: ArrayList<Int> = ArrayList()

		for (i in s.indices) {
			val asciiValueFirst: Int = s[i].code
			val asciiValueSecond: Int = s[i + 1].code
			sub = asciiValueFirst - asciiValueSecond
			sum.add(abs(sub))
		}

		return sum.sum()
	}
}