import java.util.Stack
import java.util.LinkedList
import kotlin.math.max

/*
* Remove K Digits
Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.

Example 1:
Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.

Example 2:
Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.

Example 3:
Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

Constraints:
1 <= k <= num.length <= 105
num consists of only digits.
num does not have any leading zeros except for the zero itself.
*/

fun main() {
	println(removeKdigits("1432219", 3))
	println(removeKdigits("10200", 1))
	println(removeKdigits("9", 1))
}

fun removeKdigits(num: String, k: Int): String {
	val stack = Stack<Char>()
	var k = k

	num.forEach { n ->
        // Pop out the peek element when the smaller value found
		while (k > 0 && stack.isNotEmpty() && stack.peek() > n) {
			stack.pop(); k--
		}
		//stack += n
		stack.push(n) // Push that smaller value
		//println("$n $stack $k")
	}

	repeat(k) { stack.pop() } // Remaining elements

	return if (stack.isEmpty()) "0" else stack.joinToString("").toBigInteger().toString()
	//return stack.joinToString("").toBigInteger().toString().ifEmpty { "0" } - this won't work as the exception is being thrown while joining and converting string.
}

class Solution {
	fun removeKdigits(num: String, k: Int): String {
		var k = k
		val stk = Stack<Char>()
		val ans = StringBuilder()
		for (c in num.toCharArray()) {
			while (k > 0 && stk.isNotEmpty() && stk.peek() > c) {
				stk.pop()
				k--
			}
			stk.push(c)
		}
		while (k > 0) {
			stk.pop()
			k--
		}
		for (c in stk) {
			if (ans.isEmpty() && c == '0') continue
			ans.append(c)
		}

		return if (ans.isEmpty()) "0" else ans.toString()
	}
}

class Solution {
	fun removeKdigits(num: String, k: Int): String {
		var k = k
		val s = StringBuilder()
		s.append(num[0])
		var i = 1
		while (i < num.length) {
			if (k > 0 && s.isNotEmpty() && num[i] < s[s.lastIndex]) {
				s.deleteCharAt(s.lastIndex)
				k--
				i--
				++i
				continue
			}
			s.append(num[i])
			++i
		}
		while (k > 0 && s.isNotEmpty()) {
			s.deleteCharAt(s.lastIndex)
			k--
		}

		while (s.isNotEmpty() && s[0] == '0') {
			s.deleteCharAt(0)
		}

		if (s.isEmpty()) {
			s.append('0')
		}
		return s.toString()
	}
}

class Solution {
	fun removeKdigits(num: String, limit: Int): String {
		var k = limit

		if (k == 0) return num
		if (k >= num.length) return "0"

		val dq = ArrayDeque<Int>()
		for (c in num) {
			val current = c.digitToInt()

			if (dq.isEmpty() && current == 0) continue

			while (dq.isNotEmpty() && dq.last() > current && k > 0) {
				dq.removeLast()
				k--
			}
			dq.add(current)
		}

		//remove leading zeros
		while (dq.isNotEmpty() && dq.first() == 0)
			dq.removeFirst()

		while (k > 0 && dq.isNotEmpty()) {
			dq.removeLast()
			k--
		}

		val result = dq.toList().joinToString("")

		return result.ifBlank { "0" }
	}
}

class Solution {

	fun removeKdigits(num: String, k: Int): String {

		val res = StringBuilder()
		var currK = k

		for (digit in num) {

			while (res.isNotEmpty() && currK > 0 && res.last() > digit) {
				res.deleteCharAt(res.lastIndex)
				currK--
			}

			res.append(digit)
		}

		while (res.isNotEmpty() && currK > 0) {
			res.deleteCharAt(res.lastIndex)
			currK--
		}

		while (res.isNotEmpty() && res[0] == '0') {
			res.deleteCharAt(0)
		}

		return if (res.isEmpty()) "0" else res.toString()
	}
}

class Solution {
	fun removeKdigits(num: String, k: Int): String {
		if (k >= num.length) return "0"
		val sb = StringBuilder(num)
		var kn = k
		var i = 1
		while (i < sb.length && kn > 0) {
			val cur = sb[i] - '0'
			val prev = sb[i - 1] - '0'
			if (cur < prev) {
				sb.deleteAt(i - 1)
				kn--
				i = max(i - 1, 1)
			} else {
				i++
			}

		}

		while (kn > 0 && sb.isNotEmpty()) {
			sb.deleteAt(sb.lastIndex)
			kn--
		}

		while (sb.isNotEmpty() && sb.first() == '0') {
			sb.deleteAt(0)
		}
		return if (sb.isEmpty()) "0" else sb.toString()
	}
}

class Solution {
	fun removeKdigits(num: String, k: Int): String {
		if (k > num.length) return "0"
		val stack = Stack<Int>()
		var remaining = k

		for (element in num) {
			val digit = element.toDigit()
			while (stack.isNotEmpty() && remaining > 0 && stack.peek() > digit) {
				stack.pop()
				remaining--
			}

			stack.push(digit)
		}

		while (remaining > 0) {
			stack.pop()
			remaining--
		}

		val sb = StringBuilder()
		while (stack.isNotEmpty()) {
			sb.append(stack.pop())
		}
		val result = sb.toString().dropLastWhile { it == '0' }.reversed()
		return result.ifEmpty { "0" }
	}

	fun Char.toDigit() = code - '0'.code
}

class Solution {

	fun removeKdigits(num: String, k: Int): String {

		val stack = LinkedList<Char>()
		var currK = k

		for (digit in num) {

			while (stack.isNotEmpty() && currK > 0 && stack.last() > digit) {
				stack.removeLast()
				currK--
			}

			stack.add(digit)
		}

		val res = StringBuilder()
		var isLeadingZero = true
		for (i in 0..stack.lastIndex - currK) {

			val digit = stack[i]
			if (digit == '0' && isLeadingZero) continue

			res.append(digit)
			isLeadingZero = false
		}

		return if (res.isEmpty()) "0"
		else res.toString()
	}
}