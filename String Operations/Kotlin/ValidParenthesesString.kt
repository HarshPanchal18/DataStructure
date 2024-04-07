import java.util.Stack
import kotlin.math.max

/*
* Valid Parenthesis String
Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
The following rules define a valid string:
Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".

Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "(*)"
Output: true

Example 3:
Input: s = "(*))"
Output: true

Constraints:
1 <= s.length <= 100
s[i] is '(', ')' or '*'.
*/

fun main() {
	println(Solution1().checkValidString("(*))"))
}

fun checkValidString(s: String): Boolean {
	if (s.isEmpty())
		return false

	var min = 0
	var max = 0

	for (ch in s) {
		when (ch) {
			'(' -> {
				max++
				min++
			}

			')' -> {
				if (max < 1)
					return false
				// one previous * need to choose to be "" or ( to make it valid
				min = max(min - 1, 0)
				max--
			}

			// *
			else -> {
				min = max(min - 1, 0)
				max++
			}
		}
	}

	return min == 0
}

class Solution {
	fun checkValidString(s: String): Boolean =
		s.fold(0 to 0) { (cMax, cMin), c ->
			when (c) {
				'(' -> cMax + 1 to cMin + 1
				')' -> cMax - 1 to if (cMin < 1) 0 else cMin - 1
				'*' -> cMax + 1 to if (cMin < 1) 0 else cMin - 1
				else -> cMax to cMin
			}.also { if (it.first < 0) return false }
		}.let { it.second == 0 }
}

class Solution1 {
	fun checkValidString(s: String): Boolean {
		// store index of chars
		val paraOpen = Stack<Int>()
		val wildcard = Stack<Int>()

		// Iterate over index and eliminate the close parenthesis pairs
		for ((index, ch) in s.withIndex()) {
			when (ch) {
				'*' -> wildcard.push(index)

				'(' -> paraOpen.push(index)

				')' -> {
					if (paraOpen.isNotEmpty()) paraOpen.pop()
					else if (wildcard.isNotEmpty()) wildcard.pop()
					else return false
				}
			}
		}

		// Check for leftovers
		while (paraOpen.isNotEmpty()) {
			if (wildcard.isEmpty() || paraOpen.pop() > wildcard.pop())
				return false
		}

		return true
	}
}

class Solution {
	fun checkValidString(s: String): Boolean {
		var leftMax = 0
		var rightMax = 0
		s.forEach { char ->
			when (char) {
				'(' -> {
					leftMax++
					rightMax++
				}

				')' -> {
					leftMax = (leftMax - 1).coerceAtLeast(0)
					rightMax--
				}

				else -> {
					leftMax = (leftMax - 1).coerceAtLeast(0)
					rightMax++
				}
			}
			if (rightMax < 0)
				return false
		}

		return leftMax == 0
	}
}

class Solution {
	fun checkValidString(s: String): Boolean {
		val array = s.toCharArray()
		var i = 0

		var min = 0
		var max = 0
		while (i < array.size) {
			when (array[i]) {
				'*' -> {
					min--; max++
				}

				')' -> {
					min--; max--
				}

				'(' -> {
					min++; max++
				}
			}
			if (max < 0) return false
			if (min < 0) min = 0
			i++
		}

		return min == 0
	}
}

class Solution {
	fun checkValidString(s: String): Boolean {
		val leftBracket = Stack<Int>()
		val wildcard = Stack<Int>()

		for ((index, bracket) in s.withIndex()) {
			if (bracket == '(') {
				leftBracket.push(index)
			} else if (bracket == ')') {
				if (!leftBracket.empty()) {
					leftBracket.pop()
				} else if (!wildcard.empty()) {
					wildcard.pop()
				} else {
					return false
				}
			} else { // *
				wildcard.push(index)
			}
		}

		while (leftBracket.isNotEmpty()) {
			if (wildcard.isEmpty() || leftBracket.peek() > wildcard.peek())
				return false
			leftBracket.pop()
			wildcard.pop()
		}

		return leftBracket.empty()
	}
}

class Solution {
	fun checkValidString(s: String): Boolean {
		var leftParen = 0

		for (i in s.indices) {
			when (s[i]) {
				'(', '*' -> leftParen++
				')' -> {
					if (leftParen == 0)
						return false

					leftParen--
				}
			}
		}

		var rightParen = 0

		for (i in s.lastIndex downTo 0) {
			when (s[i]) {
				')', '*' -> rightParen++
				'(' -> {
					if (rightParen == 0)
						return false
					rightParen--
				}
			}
		}

		return true
	}
}

class Solution {
	fun checkValidString(s: String): Boolean {
		val n = s.length
		if (n == 0) return true

		// let dp[i][j] be true is and only if the interval s[i], s[i +1],...,s[j] can be made valid
		val dp: Array<BooleanArray> = Array(n) { BooleanArray(n) { false } }

		for (i in 0..<n) {
			if (s[i] == '*')
				dp[i][i] = true
			if (i < n - 1 && (s[i] == '(' || s[i] == '*') && (s[i + 1] == ')' || s[i + 1] == '*'))
				dp[i][i + 1] = true
		}

		for (size in 2..<n) {
			for (i in 0..<n - size) {
				// s[i] is * and the interval dp[i + 1][j] can be made valid
				if (s[i] == '*' && dp[i + 1][i + size])
					dp[i][i + size] = true
				else if (s[i] == '*' || s[i] == '(') {
					for (k in i + 1..<i + size + 1) {
						if ((s[k] == '*' || s[k] == ')') && (k == i + 1 || dp[i + 1][k - 1]) && (k == i + size || dp[k + 1][i + size]))
							dp[i][i + size] = true
					}
				}
			}
		}

		return dp[0][n - 1]
	}
}