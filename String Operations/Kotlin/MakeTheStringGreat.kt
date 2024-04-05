import java.util.*
import kotlin.math.abs

/*
* Make The String Great
Given a string s of lower and upper case English letters.
A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where: 0 <= i <= s.length - 2
s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
To make the string good, you can choose two adjacent characters that make the string bad and remove them. You can keep doing this until the string becomes good.
Return the string after making it good. The answer is guaranteed to be unique under the given constraints.
Notice that an empty string is also good.

Example 1:
Input: s = "leEeetcode"
Output: "leetcode"
Explanation: In the first step, either you choose i = 1 or i = 2, both will result "leEeetcode" to be reduced to "leetcode".

Example 2:
Input: s = "abBAcC"
Output: ""
Explanation: We have many possible scenarios, and all lead to the same answer. For example:
"abBAcC" --> "aAcC" --> "cC" --> ""
"abBAcC" --> "abBA" --> "aA" --> ""

Example 3:
Input: s = "s"
Output: "s"

Constraints:
1 <= s.length <= 100
s contains only lower and upper case English letters.
*/

fun main() {
	print(makeGood("leeEtcode"))
}

fun makeGood(s: String): String {
	val stack = Stack<Char>()

	for (c in s.toCharArray()) {
		if (stack.isNotEmpty() && abs((c.code - stack.peek().code)) == 32) {
			stack.pop()
		} else {
			stack.push(c)
		}
	}

	val result = StringBuilder()

	while (stack.isNotEmpty())
		result.insert(0, stack.pop())

	return result.toString()
}

class Solution {
	fun makeGood(s: String): String {
		val result = StringBuilder()
		if (s.length <= 1) {
			return s
		}

		for (c in s) {
			if (result.isEmpty()) {
				result.append(c)
				continue
			}

			val prev = result[result.lastIndex]
			if (c.uppercaseChar() != prev.uppercaseChar()) {
				result.append(c)
				continue
			}

			if ((prev.isLowerCase() && c.isUpperCase()) || (c.isLowerCase() && prev.isUpperCase())) {
				result.deleteCharAt(result.lastIndex)
			} else {
				result.append(c)
			}
		}

		return result.toString()
	}
}

class Solution {
	fun makeGood(s: String): String {
		var word = s
		var isGreat = false
		var indexToRemove: Int?

		while (!isGreat) {
			isGreat = true
			indexToRemove = null

			val chars = word.toCharArray()
			for (i in 0..chars.size - 2) {
				val char = chars[i]
				val nextChar = chars[i + 1]

				if (areNotGood(char, nextChar)) {
					isGreat = false
					indexToRemove = i
					break
				}
			}

			indexToRemove?.let { word = word.removeRange(it, it + 2) }
		}

		return word
	}
}

private fun areNotGood(char1: Char, char2: Char): Boolean =
	char1 != char2 && char1.lowercaseChar() == char2.lowercaseChar()

class Solution {
	fun makeGood(s: String): String {
		val stack = Stack<Char>()

		for (c in s) {
			if (stack.isNotEmpty() && abs(c - stack.peek()) == 32) {
				stack.pop()
			} else {
				stack.push(c)
			}
		}

		return stack.joinToString("")
	}
}

class Solution {
	fun makeGood(s: String): String {
		val sb = StringBuilder()
		for (c in s) {
			if (sb.isNotEmpty() && isBad(c, sb.last())) {
				sb.deleteCharAt(sb.lastIndex)
			} else {
				sb.append(c)
			}
		}
		return sb.toString()
	}

	private fun isBad(a: Char, b: Char): Boolean {
		return a != b && a.lowercaseChar() == b.lowercaseChar()
	}
}