/*
* Separate the Digits in an Array
Given an array of positive integers nums, return an array answer that consists of the digits of each integer in nums after separating them in the same order they appear in nums.
To separate the digits of an integer is to get all the digits it has in the same order.
For example, for the integer 10921, the separation of its digits is [1,0,9,2,1].

Example 1:
Input: nums = [13,25,83,77]
Output: [1,3,2,5,8,3,7,7]
Explanation:
- The separation of 13 is [1,3].
- The separation of 25 is [2,5].
- The separation of 83 is [8,3].
- The separation of 77 is [7,7].
answer = [1,3,2,5,8,3,7,7]. Note that answer contains the separations in the same order.

Example 2:
Input: nums = [7,1,3,9]
Output: [7,1,3,9]
Explanation: The separation of each integer in nums is itself.
answer = [7,1,3,9].

Constraints:
1 <= nums.length <= 1000
1 <= nums[i] <= 105
*/

fun main() {
	val answer = separateDigits(intArrayOf(13, 25, 83, 77, 100))
	answer.forEach { println("$it ") }
}

fun separateDigits(nums: IntArray): IntArray {
	val result = mutableListOf<Int>()

	for (i in nums.indices) {//nums.size - 1 downTo 0) {
		var temp = nums[nums.size - i - 1]
		while (temp > 0) {
			result.add(temp.mod(10))
			temp /= 10
		}
	}

	return result.toIntArray().reversedArray()
}

class Solution {
	fun separateDigits(nums: IntArray): IntArray {
		return nums.reversed().asSequence().flatMap { divide(it) }.toList().reversed().toIntArray()
	}

	private fun divide(num: Int): Sequence<Int> {
		return generateSequence(num) { it / 10 }.takeWhile { it > 0 }.map { it % 10 }
	}
}

class Solution {
	fun separateDigits(nums: IntArray): IntArray {
		var res: IntArray = intArrayOf()

		for (num in nums) {
			val numStr = num.toString()

			for (i in numStr.indices)
				res += numStr[i].toString().toInt()
		}

		return res
	}
}

class Solution {
	fun separateDigits(nums: IntArray): IntArray {
		val res = mutableListOf<Int>()

		for (n in nums) {
			var n = n
			val tmp = mutableListOf<Int>()

			while (n > 0) {
				tmp.add(0, n % 10)
				n /= 10
			}
			res.addAll(tmp)
		}

		return res.toIntArray()
	}
}

class Solution {
	fun separateDigits(nums: IntArray): IntArray {
		val res = mutableListOf<Int>()
		nums.forEach {
			res.addAll(it.parse())
		}
		return res.toIntArray()
	}

	private fun Int.parse(): List<Int> {
		val digits = mutableListOf<Int>()
		var that = this
		while (that > 0) {
			digits.add(that % 10)
			that /= 10
		}
		return digits.reversed()
	}
}

class Solution {
	fun separateDigits(nums: IntArray): IntArray {
		val list = ArrayList<Char>()

		for (n in nums) {
			val str = n.toString()
			for (s in str)
				list.add(s)
		}

		val arr = IntArray(list.size)

		for (k in 0..<list.size)
			arr[k] = Integer.parseInt(list[k].toString())

		return arr

	}
}

class Solution {
	fun separateDigits(nums: IntArray): IntArray {
		val results = mutableListOf<Int>()

		for (num in nums) {
			val digits = num.toString().map { it.toString().toInt() }
			results.addAll(digits)
		}

		return results.toIntArray()
	}
}