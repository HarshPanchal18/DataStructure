/*
* N-th Tribonacci Number
The Tribonacci sequence Tn is defined as follows:
T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
Given n, return the value of Tn.

Example 1:
Input: n = 4
Output: 4
Explanation:
T_3 = 0 + 1 + 1 = 2
T_4 = 1 + 1 + 2 = 4

Example 2:
Input: n = 25
Output: 1389537

Constraints:
0 <= n <= 37
The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
*/

fun main() {
	println(tribonacci(4))
	println(tribonacci(25))
}

fun tribonacci(n: Int): Int {
	return when (n) {
		0 -> 0
		1, 2 -> 1
		else -> tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3)
	}
}

class Solution {
	fun tribonacci(n: Int): Int {
		if (n == 0)
			return 0

		var cache = Triple(0, 1, 1)
		for (i in 3..n) {
			cache = Triple(cache.second, cache.third, cache.first + cache.second + cache.third)
		}
		return cache.third
	}
}

class Solution {

	init {
		dp[0] = 0
		dp[1] = 1
		dp[2] = 1
		for (i in 3 until 38)
			dp[i] = (1 until 4).map { dp[i - it] }.sum()
	}

	fun tribonacci(n: Int) = dp[n]

	companion object {
		val dp = IntArray(38)
	}
}

class Solution {
	fun tribonacci(n: Int): Int {
		if (n < 2)
			return n

		val numQueue = intArrayOf(0, 0, 1)
		var lastN = 1
		var pointer = 0

		repeat(n - 2) {
			val pointedValue = numQueue[pointer]
			numQueue[pointer] = lastN
			lastN = lastN * 2 - pointedValue
			pointer = if (pointer + 1 in numQueue.indices) pointer + 1 else 0
		}

		return lastN
	}
}

class Solution {
	fun tribonacci(n: Int): Int {
		if (n == 0)
			return 0
		else if (n <= 2)
			return 1

		var one = 0
		var two = 1
		var three = 1
		var temp3: Int
		var temp2: Int

		for (i in 3..n) {
			temp3 = three
			temp2 = two
			three += two + one
			two = temp3
			one = temp2
		}
		return three
	}
}

// Memoization
class Solution {
	fun tribonacci(n: Int): Int {
		val memo: MutableMap<Int, Int> = HashMap()
		return helper(n, memo)
	}

	private fun helper(n: Int, memo: MutableMap<Int, Int>): Int {
		if (memo.containsKey(n))
			return memo[n]!!

		val result = when (n) {
			0 -> 0
			1, 2 -> 1
			else -> helper(n - 1, memo) + helper(n - 2, memo) + helper(n - 3, memo)
		}

		memo[n] = result
		return result
	}
}

// Iterative DP
class Solution {
	fun tribonacci(n: Int): Int {
		if (n == 0)
			return 0
		else if (n == 1 || n == 2)
			return 1

		val dp = IntArray(n + 1)
		dp[1] = 1
		dp[2] = 1

		for (i in 3..n)
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]

		return dp[n]
	}
}

// Space optimized iterative
class Solution {
	fun tribonacci(n: Int): Int {
		if (n == 0)
			return 0
		else if (n == 1 || n == 2)
			return 1

		var a = 0
		var b = 1
		var c = 1
		var nextTrib: Int

		for (i in 3..n) {
			nextTrib = a + b + c
			a = b
			b = c
			c = nextTrib
		}

		return c
	}
}

class Solution {
	fun tribonacci(n: Int): Int {
		if (n <= 1)
			return n

		val sumList = ArrayList<Int>()
		sumList.add(0)
		sumList.add(1)

		var next = 0
		for (i in 1 until n) {
			next = if (i - 3 < 0)
				sumList[i]
			else
				sumList[i] - sumList[i - 3]

			sumList.add(next + sumList[i])
		}

		return next
	}
}