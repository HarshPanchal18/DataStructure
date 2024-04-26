import kotlin.math.pow

/*
* Valid Perfect Square
Given a positive integer num, return true if num is a perfect square or false otherwise.
A perfect square is an integer that is the square of an integer. In other words, it is the product of some integer with itself.
You must not use any built-in library function, such as sqrt.

Example 1:
Input: num = 16
Output: true
Explanation: We return true because 4 * 4 = 16 and 4 is an integer.

Example 2:
Input: num = 14
Output: false
Explanation: We return false because 3.742 * 3.742 = 14 and 3.742 is not an integer.

Constraints:
1 <= num <= 231 - 1
 */

fun main() {
	println(isPerfectSquare(16))
	println(isPerfectSquare(35))
	//println(isPerfectSquare(25))
}

fun isPerfectSquare(num: Int): Boolean {
	val half = 1.coerceAtLeast(num / 2)
	for (n1 in 1..half) {
		for (n2 in half downTo n1) {
			println("$n1 $n2")
			if ((n1 * n2 == num) && (n1 == n2))
				return true
		}
	}
	return false
}

fun isPerfectSquare(num: Int): Boolean {
	val n = num.toDouble().pow(0.5).toInt()
	return n * n == num
}

class Solution {
	fun isPerfectSquare(num: Int): Boolean {
		var remain = num;
		var n = 1
		while (remain > 0) {
			remain -= n
			n += 2
		}
		return remain == 0

		var l = 1;
		var r = num
		while (l <= r) {
			val mid = (r - l) / 2 + l
			if (mid * mid == num)
				return true
			else if (mid * mid < num)
				l = mid + 1
			else
				r = mid - 1
		}
		return l * l == num

		var n = 1
		while (n * n < num) {
			n++
		}
		return n * n == num
	}
}

class Solution {
	fun isPerfectSquare(num: Int): Boolean {
		var l = 1
		var r = num

		while (l <= r) {
			val m = l + (r - l) / 2
			val div = num / m
			if (div == m && div * m == num) {
				return true
			}
			if (div < m) {
				r = m - 1
			} else {
				l = m + 1
			}
		}
		return false
	}
}


class Solution {
	fun isPerfectSquare(num: Int): Boolean {
		val n = num.toLong()
		var i: Long = 1
		while (i * i <= n) {
			if (i * i == n) {
				return true
			}
			i++
		}
		return false
	}
}

class Solution {
	fun isPerfectSquare(num: Int): Boolean {
		if (num == 1)
			return true

		for (i in 0..num / 2) {
			if (i * i == num)
				return true
		}

		return false
	}
}

class Solution {
	fun isPerfectSquare(num: Int): Boolean {
		if (num < 1) return false

		var left = 1
		var right = num

		while (left <= right) {
			val mid = left + (right - left) / 2
			val square = mid.toLong() * mid.toLong()

			if (square == num.toLong()) return true

			if (square < num) {
				left = mid + 1
			} else {
				right = mid - 1
			}
		}

		return false
	}
}