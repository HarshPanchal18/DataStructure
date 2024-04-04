import java.util.Stack
import kotlin.math.max

/*
* Maximum Nesting Depth of the Parentheses
A string is a valid parentheses string (denoted VPS) if it meets one of the following:
It is an empty string "", or a single character not equal to "(" or ")",
It can be written as AB (A concatenated with B), where A and B are VPS's, or
It can be written as (A), where A is a VPS.

We can similarly define the nesting depth depth(S) of any VPS S as follows:
depth("") = 0
depth(C) = 0, where C is a string with a single character not equal to "(" or ")".
depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's.
depth("(" + A + ")") = 1 + depth(A), where A is a VPS.
For example, "", "()()", and "()(()())" are VPS's (with nesting depths 0, 1, and 2), and ")(" and "(()" are not VPS's.

Given a VPS represented as string s, return the nesting depth of s.

Example 1:
Input: s = "(1+(2*3)+((8)/4))+1"
Output: 3
Explanation: Digit 8 is inside of 3 nested parentheses in the string.

Example 2:
Input: s = "(1)+((2))+(((3)))"
Output: 3

Constraints:
1 <= s.length <= 100
s consists of digits 0-9 and characters '+', '-', '*', '/', '(', and ')'.
It is guaranteed that parentheses expression s is a VPS.
*/

fun main() {
	print(maxDepth("(1+(2*3)+((8)/4))+1"))
}

fun maxDepth(s: String): Int {
	var depth = 0
	var maxCounter = 0

	for (ch in s) {
		when (ch) {
			'(' -> depth++
			')' -> depth--
		}
		maxCounter = max(depth, maxCounter)
	}

	return maxCounter
}

class Solution {
	fun maxDepth(s: String): Int {
		var output = 0
		var count = 0

		s.forEach { c ->
			if (c == '(') {
				count++
			} else if (c == ')') {
				output = max(output, count)
				count--
			}
		}

		return output
	}
}

class Solution {
	fun maxDepth(s: String): Int {
		var max = 0;
		var counter = 0;
		for (c in s) {
			when (c) {
				'(' -> counter++
				')' -> {
					max = max(max, counter)
					counter--
				}
			}
		}
		return max
	}
}

class Solution {
	fun maxDepth(s: String): Int {
		if (s.isEmpty() || (s.length == 1 && s != "(" && s != ")")) return 0
		var resultDepth = 0

		val stack = Stack<Char>()
		var tmpCount = 0
		s.forEach {
			when (it) {
				'(' -> {
					if (stack.isEmpty()) {
						tmpCount = 0
					}
					tmpCount++
					stack.push(it)
				}

				')' -> {
					if (stack.isEmpty()) return 0
					stack.pop()
					resultDepth = max(resultDepth, tmpCount)
					if (!stack.isEmpty()) {
						tmpCount = stack.size
					}
				}
			}
		}
		return resultDepth
	}
}

class Solution {
	fun maxDepth(s: String): Int {
		var res = 0
		val stack = Stack<Char>()

		for (c in s) {
			if (c != '(' && c != ')')
				continue
			if (c == '(') {
				stack.push(c)
				res = maxOf(res, stack.size)
			} else if (stack.isNotEmpty()) {
				stack.pop()
			}
		}

		return res
	}
}