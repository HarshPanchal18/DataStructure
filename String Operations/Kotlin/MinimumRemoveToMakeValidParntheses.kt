import java.util.Stack

/*
* Minimum Remove to Make Valid Parentheses
Given a string s of '(' , ')' and lowercase English characters.
Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
Formally, a parentheses string is valid if and only if:
It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.

Example 1:
Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

Example 2:
Input: s = "a)b(c)d"
Output: "ab(c)d"

Example 3:
Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.

Constraints:
1 <= s.length <= 105
s[i] is either'(' , ')', or lowercase English letter.
*/

fun main() {
	print(minRemoveToMakeValid("lee(t(c)o)de)"))
}

/*fun minRemoveToMakeValid(s: String): String {
	var result = ""
	val left = s.count { it == '(' }
	val right = s.count { it == ')' }
	val more = maxOf(left, right)

	if (left != right) {
		when (more) {
			left -> {
				result = s.removeRange(s.indexOf("("), s.indexOf("(") + 1)
			}

			right -> {
				result = s.removeRange(s.indexOf(")"), s.indexOf(")") + 1)
			}
		}
	}

	return result
}*/

internal class Solution {
	fun minRemoveToMakeValid(s: String): String {
		// Initialize pointers for the start and end of the string
		val startPointer = 0
		val endPointer = s.length - 1

		// Initialize variables for storing start and end parts of the string, and the final result
		val startPart = ""
		val endPart = ""
		val result: String

		// Convert input string to character array for easier manipulation
		val arr = s.toCharArray()

		// Counter for open parentheses
		var openParenthesesCount = 0

		// First pass: mark excess closing parentheses with '*'
		for (i in arr.indices) {
			if (arr[i] == '(') openParenthesesCount++
			else if (arr[i] == ')') {
				if (openParenthesesCount == 0) arr[i] = '*' // Mark excess closing parentheses
				else openParenthesesCount--
			}
		}

		// Second pass: mark excess opening parentheses from the end
		for (i in arr.indices.reversed()) {
			if (openParenthesesCount > 0 && arr[i] == '(') {
				arr[i] = '*' // Mark excess opening parentheses
				openParenthesesCount--
			}
		}

		// Filter out marked characters and store the result in the character array
		var p = 0 // Pointer for updating the character array
		for (i in arr.indices)
			if (arr[i] != '*')
				arr[p++] = arr[i]

		// Construct the result string from the filtered character array
		result = String(arr).substring(0, p)

		return result
	}
}

internal class Solution {
	fun minRemoveToMakeValid(s: String): String {
		// Initialize counts for left and right parentheses
		var leftCount = 0
		var rightCount = 0

		// Use a stack to keep track of valid parentheses
		val stack = Stack<Char>()

		// Pass 1: Iterate through the string and process parentheses
		for (element in s) {

			// Increment count for left parentheses
			if (element == '(')
				leftCount++

			// Increment count for right parentheses
			if (element == ')')
				rightCount++

			// If there are more right parentheses than left, skip the current right parenthesis
			if (rightCount > leftCount) {
				rightCount-- // Decrease right count
				continue  // Skip processing this right parenthesis
			} else {
				stack.push(element) // Add valid parentheses to the stack
			}
		}

		// Pass 2: Reconstruct the string using the valid parentheses in the stack
		val result = StringBuilder()
		while (!stack.isEmpty()) {
			val currentChar = stack.pop()
			// If there are more left parentheses than right, skip the current left parenthesis
			if (leftCount > rightCount && currentChar == '(') {
				leftCount-- // Decrease left count. Do nothing, skip this left parenthesis
			} else {
				result.append(currentChar) // Add valid parentheses to the result
			}
		}

		// Reverse the result string and return
		return result.reverse().toString()
	}
}

class Solution {
	fun minRemoveToMakeValid(s: String) = buildString {
		val indexes = mutableListOf<Int>()
		s.forEach {
			when (it) {
				'(' -> indexes.add(length)
				')' -> if (indexes.isNotEmpty()) indexes.removeLast()
				else return@forEach
			}
			append(it)
		}
		indexes.reversed().forEach {
			deleteAt(it)
		}
	}
}

class Solution {
	fun minRemoveToMakeValid(s: String): String {
		val positionsStack = Stack<Int>()
		val result = StringBuffer(s)

		val chars = s.toCharArray()
		chars.forEachIndexed { index, c ->
			if (c == '(') {
				positionsStack.push(index)
			} else if (c == ')' && positionsStack.isNotEmpty()) {
				positionsStack.pop()
			} else if (c == ')') {
				result.replace(index, index + 1, " ")
			}
		}

		while (positionsStack.size > 0) {
			val positionToRemove = positionsStack.pop()
			// problem: after using delete the positionToRemove is not accurate anymore
			result.replace(positionToRemove, positionToRemove + 1, " ")
		}

		val res = result.toString()
		return res.replace(" ", "")

	}
}

class Solution {
	fun minRemoveToMakeValid(s: String): String {
		val removeIndices = mutableSetOf<Int>()
		val stack = mutableListOf<Int>()

		for ((i, c) in s.withIndex()) {
			when (c) {
				'(' -> stack.add(i)
				')' -> {
					if (stack.isEmpty()) {
						removeIndices.add(i)
					} else {
						stack.removeAt(stack.lastIndex)
					}
				}
			}
		}

		removeIndices.addAll(stack)

		var res = String()
		for ((i, c) in s.withIndex())
			if (i !in removeIndices)
				res += c

		return res
	}
}

class Solution {
	fun minRemoveToMakeValid(s: String): String {
		return buildString {
			var openedCount = 0
			for (index in s.indices) {
				when (val char = s[index]) {
					'(' -> {
						openedCount++
						append(char)
					}

					')' -> {
						if (openedCount > 0) {
							openedCount--
							append(char)
						}
					}

					else -> {
						append(char)
					}
				}
			}
			var index = length - 1
			while (openedCount > 0) {
				if (get(index) == '(') {
					deleteAt(index)
					openedCount--
				}
				index--
			}
		}
	}
}

class Solution {

	fun minRemoveToMakeValid(s: String): String {
		if (s.isEmpty())
			return s

		val sb = StringBuilder()
		var balance = 0
		var openPar = 0

		for (char in s) {
			if (char == '(') {
				balance++
				openPar++
			} else if (char == ')') {
				if (balance == 0) continue
				balance--
			}
			sb.append(char)
		}

		var appendOpen = openPar - balance
		val answer = StringBuilder()
		for (char in sb) {
			if (char == '(' && appendOpen == 0) continue
			if (char == '(') appendOpen--
			answer.append(char)
		}

		return answer.toString()
	}
}

class Solution {
	fun minRemoveToMakeValid(s: String): String {
		return minRemoveToMakeValidSecond(s)
	}

	fun minRemoveToMakeValidSecond(s: String): String {
		val stack = ArrayDeque<Int>()
		val toRemove = mutableListOf<Int>()
		for (index in s.indices) {
			val char = s[index]
			if (char == ')') {
				if (stack.isEmpty()) {
					toRemove.add(index)
				} else {
					stack.removeLast()
				}
			}
			if (char == '(') {
				stack.addLast(index)
			}
		}

		while (stack.isNotEmpty()) {
			toRemove.add(stack.removeLast())
		}

		val sb = StringBuilder()
		for (index in s.indices) {
			if (!toRemove.contains(index)) {
				sb.append(s[index])
			}
		}
		return sb.toString()
	}

	fun minRemoveToMakeValidInitial(s: String): String {
		val stack = ArrayDeque<Char>()
		var res = s
		val toRemove = mutableListOf<Int>()

		res.forEachIndexed { index, char ->
			if (char == '(' || char == ')') {
				if (stack.isEmpty() && char == ')') {
					toRemove.add(index)
				} else {
					if (stack.isNotEmpty() && stack.last() != char) {
						stack.removeLast()
					} else {
						stack.addLast(char)
					}
				}
			}
		}

		var count = 0
		toRemove.forEach { i ->
			res = res.removeRange(i - count, i + 1 - count)
			count++
		}

		stack.forEach { char ->
			val i = res.lastIndexOf(char)
			res = res.removeRange(i, i + 1)
		}

		return res
	}
}