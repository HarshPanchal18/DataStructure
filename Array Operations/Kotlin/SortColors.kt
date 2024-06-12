/*
* Sort Colors
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
You must solve this problem without using the library's sort function.

Example 1:
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Example 2:
Input: nums = [2,0,1]
Output: [0,1,2]

Constraints:
n == nums.length
1 <= n <= 300
nums[i] is either 0, 1, or 2.

Follow up: Could you come up with a one-pass algorithm using only constant extra space?
*/

fun main() {
	sortColors(intArrayOf(2, 0, 2, 1, 1, 0))
}

fun sortColors(nums: IntArray) {
	val zeros = nums.count { it == 0 }
	val ones = nums.count { it == 1 }

	for (i in 0 until zeros)
		nums[i] = 0

	for (i in zeros until zeros + ones)
		nums[i] = 1

	for (i in zeros + ones until nums.size)
		nums[i] = 2
}

class Solution {
	fun sortColors(nums: IntArray) {
		val counts = nums.asSequence().groupingBy { it }.eachCount()
		(0..2).map { it to (counts[it] ?: 0) }
			.fold(0) { i, (color, j) ->
				nums.fill(color, i, i + j)
				i + j
			}
	}
}

class Solution {
	fun sortColors(nums: IntArray) {
		var k1 = -1
		var k2 = 0
		val n = nums.size
		for (i in nums.indices) {
			if (nums[i] == 0) {
				if (k1 != -1 && k1 < n) {
					val temp = nums[k1]
					nums[k1] = nums[i]
					nums[i] = temp
					k1++
				}
				if (k2 < n) {
					val temp = nums[k2]
					nums[k2] = nums[i]
					nums[i] = temp
					k2++
				}
			} else if (nums[i] == 1) {
				if (k1 == -1) k1 = k2
				if (k2 < n) {
					val temp = nums[k2]
					nums[k2] = nums[i]
					nums[i] = temp
					k2++
				}
			}
		}
	}
}

class Solution {
	fun sortColors(nums: IntArray): Unit {
		val count = intArrayOf(0, 0, 0)

		for (num in nums)
			count[num] += 1

		var position = 0
		for (i in count.indices) {
			for (j in 0 until count[i])
				nums[position++] = i
		}

	}
}

class Solution {
	fun sortColors(nums: IntArray) {
		var left = 0
		var iter = 0
		var right = nums.lastIndex

		while (iter <= right) {
			when (nums[iter]) {
				0 -> nums.swap(left++, iter++)
				1 -> iter++
				2 -> nums.swap(iter, right--)
			}
		}
	}

	fun IntArray.swap(i: Int, j: Int) {
		this[i] = this[j].also { this[j] = this[i] }
	}
}

class Solution {

	fun sortColors(nums: IntArray): Unit {
		for (n in nums)
			counters[n].count++

		var offset = 0
		for (c in counters) {
			System.arraycopy(c.color, 0, nums, offset, c.count)
			offset += c.count
			c.count = 0
		}
	}

	class Counter(val index: Int) {
		val color = IntArray(300) { index }
		var count = 0
	}

	companion object {
		val counters = Array(3) { Counter(it) }
	}

}

class Solution {

	fun sortColors(nums: IntArray): Unit {
		for (n in nums)
			counters[n].count++

		var ti = -1
		for (i in 0..2) {
			repeat(counters[i].count) { nums[++ti] = i }
			counters[i].count = 0
		}

	}

	class Counter {
		var count = 0
	}

	companion object {
		val counters = Array(3) { Counter() }
	}
}

class Solution {
	fun sortColors(nums: IntArray) {
		for (i in nums.indices) {
			for (k in i + 1 until nums.size) {
				if (nums[k] < nums[i]) {
					nums[i] = nums[i] xor nums[k]
					nums[k] = nums[i] xor nums[k]
					nums[i] = nums[i] xor nums[k]
				}
			}
		}
	}
}