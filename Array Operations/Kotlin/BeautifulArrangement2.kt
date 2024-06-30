/*
* Beautiful Arrangement II
Given two integers n and k, construct a list answer that contains n different positive integers ranging from 1 to n and obeys the following requirement:
Suppose this list is answer = [a1, a2, a3, ... , an], then the list [ |a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an| ] has exactly k distinct integers.
Return the list answer.
If there multiple valid answers, return any of them.

Example 1:
Input: n = 3, k = 1
Output: [1,2,3]
Explanation: The [1,2,3] has three different positive integers ranging from 1 to 3, and the [1,1] has exactly 1 distinct integer: 1

Example 2:
Input: n = 3, k = 2
Output: [1,3,2]
Explanation: The [1,3,2] has three different positive integers ranging from 1 to 3, and the [2,1] has exactly 2 distinct integers: 1 and 2.

Constraints:
1 <= k < n <= 104
*/

fun main() {
	val result = constructArray(5, 2)
	result.forEach { print("$it ") }
}

fun constructArray(n: Int, k: Int): IntArray {
	var count = 1
	val array = IntArray(n)

	for (i in 0 until n)
		array[i] = i + 1

	while (count < k)
		reverseArray(count++, array)

	return array
}

fun reverseArray(index: Int, array: IntArray) {
	var start = index
	var end = array.lastIndex

	while (start < end) {
		array[start] = array[end].also { array[end--] = array[start++] }
	}
}

class Solution {
	fun constructArray(n: Int, k: Int): IntArray {
		val nums = IntArray(n) { i -> i + 1 }
		val temp = IntArray(k + 1) { i -> i + 1 }
		var low = 0
		var high = k

		for (i in 0..k) {
			var value: Int

			if (i % 2 == 0) {
				value = temp[low]
				low += 1
			} else {
				value = temp[high]
				high -= 1
			}

			nums[i] = value
		}

		return nums
	}
}

internal class Solution {
	fun constructArray(n: Int, k: Int): IntArray {
		var num = -1
		val res = IntArray(n)
		res[0] = n

		for (i in 1 until n) {
			if (i < k) {
				if (num > 0) {
					res[i] = res[i - 1] + (n - i)
					num *= -1
				} else {
					res[i] = res[i - 1] - (n - i)
					num *= -1
				}
			} else {
				res[i] = res[i - 1] + num
			}
		}

		return res
	}
}

class Solution {
	fun constructArray(n: Int, k: Int): IntArray {
		var k = k
		val answer = IntArray(n)
		var left = 1
		var right = n
		var i = 0

		while (i < n) {
			if (k > 1) {
				answer[i++] = if (k % 2 == 0) right-- else left++
				k--
			} else {
				answer[i++] = left++
			}
		}
		return answer
	}
}

class Solution {
	fun constructArray(n: Int, k: Int): IntArray {
		// k = 4, n = anything
		// idea is to interleave the array [1, 5, 2, 4, 3, 6, 7, 8, ....]

		var k = k
		val result = IntArray(n)
		result[0] = 1
		var idx = 1 // index of result array
		var i = 1 // multiplier to alternate prefix sum

		// we know the rest of the array will look like [k + 2, k + 3, ...]
		var start = k + 2 // initiaize here before we change k, use later
		while (k > 0) { // alternating prefix sum w decrements!!
			result[idx] = result[idx - 1] + k * (i)
			k--
			idx++
			i *= -1
		}

		// filling the rest of array
		while (idx < n)
			result[idx++] = start++

		return result
	}
}

class Solution {
	fun constructArray(n: Int, k: Int): IntArray {
		val ans = IntArray(n)
		var c = 0

		for (v in 1 until n - k)
			ans[c++] = v

		for (i in 0..k)
			ans[c++] = if ((i % 2 == 0)) (n - k + i / 2) else (n - i / 2)

		return ans
	}
}