/*
* Number of Wonderful Substrings
A wonderful string is a string where at most one letter appears an odd number of times.
For example, "ccjjc" and "abab" are wonderful, but "ab" is not.
Given a string word that consists of the first ten lowercase English letters ('a' through 'j'), return the number of wonderful non-empty substrings in word.
If the same substring appears multiple times in word, then count each occurrence separately.

A substring is a contiguous sequence of characters in a string.

Example 1:
Input: word = "aba"
Output: 4
Explanation: The four wonderful substrings are underlined below:
- "aba" -> "a"
- "aba" -> "b"
- "aba" -> "a"
- "aba" -> "aba"

Example 2:
Input: word = "aabb"
Output: 9
Explanation: The nine wonderful substrings are underlined below:
- "aabb" -> "a"
- "aabb" -> "aa"
- "aabb" -> "aab"
- "aabb" -> "aabb"
- "aabb" -> "a"
- "aabb" -> "abb"
- "aabb" -> "b"
- "aabb" -> "bb"
- "aabb" -> "b"

Example 3:
Input: word = "he"
Output: 2
Explanation: The two wonderful substrings are underlined below:
- "he" -> "h"
- "he" -> "e"

Constraints:
1 <= word.length <= 105
word consists of lowercase English letters from 'a' to 'j'.
*/

fun main() {
	println(wonderfulSubstrings("aabb"))
}

fun wonderfulSubstrings(word: String): Long {
	// val count = LongArray(1024) - equivalent
	val count = LongArray(1.shl(10))
	count[0] = 1
	var result = 0L
	var mask = 0

	for (ch in word) {
		mask = mask.xor(1.shl(ch - 'a')) // Toggle the bit corresponding to the character’s position in the alphabet.
		result += count[mask]

		for (j in 0..9)
			result += count[mask.xor(1.shl(j))]

		count[mask]++
	}

	return result
}

class Solution {
	fun wonderfulSubstrings(word: String): Long {
		val count = LongArray(1024) // 2^10 to store XOR values
		var result: Long = 0
		var prefixXor = 0
		count[prefixXor] = 1

		for (ch in word.toCharArray()) {
			val charIndex = ch.code - 'a'.code
			prefixXor = prefixXor xor (1 shl charIndex)
			result += count[prefixXor]
			for (i in 0..9) {
				result += count[prefixXor xor (1 shl i)]
			}
			count[prefixXor]++
		}

		return result
	}
}

class Solution {
	fun wonderfulSubstrings(word: String): Long {
		val chars = word.toCharArray()
		val n = chars.size

		var result = 0L
		val dp = LongArray(1 shl 11)
		var bits = 0

		for (i in 0 until n) {
			val ch = chars[i].code - 'a'.code
			bits = bits xor (1 shl ch)

			var ret = dp[bits] + if (bits.countOneBits() <= 1) 1L else 0L

			for (j in 0..9)
				ret += dp[bits xor (1 shl j)]

			result += ret
			dp[bits] += 1L
		}

		return result
	}
}