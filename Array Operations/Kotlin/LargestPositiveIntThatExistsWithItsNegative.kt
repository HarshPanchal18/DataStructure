import kotlin.math.abs

/*
* Largest Positive Integer That Exists With Its Negative
Given an integer array nums that does not contain any zeros, find the largest positive integer k such that -k also exists in the array.
Return the positive integer k. If there is no such integer, return -1.

Example 1:
Input: nums = [-1,2,-3,3]
Output: 3
Explanation: 3 is the only valid k we can find in the array.

Example 2:
Input: nums = [-1,10,6,7,-7,1]
Output: 7
Explanation: Both 1 and 7 have their corresponding negative values in the array. 7 has a larger value.

Example 3:
Input: nums = [-10,8,6,7,-2,-3]
Output: -1
Explanation: There is no a single valid k, we return -1.

Constraints:
1 <= nums.length <= 1000
-1000 <= nums[i] <= 1000
nums[i] != 0
*/

fun main() {
	print(findMaxK(intArrayOf(-1, 2, -3, 3)))
}

fun findMaxK(nums: IntArray): Int {
	val pairs = mutableListOf<Int>()
	nums.forEach { n ->
		if (nums.contains(n) && nums.contains(-n))
			pairs.add(n)
	}
	if (pairs.isNotEmpty()) return pairs.max()
	return -1
}

class Solution {
	fun findMaxK(nums: IntArray): Int {
		nums.sort()
		var l = 0
		var r = nums.lastIndex

		while (l < r) {
			when {
				nums[l] + nums[r] > 0 -> r--
				nums[l] + nums[r] < 0 -> l++
				else -> return nums[r]
			}
		}

		return -1
	}
}

class Solution {
	fun findMaxK(nums: IntArray): Int {
		var ans = -1
		val set = mutableSetOf<Int>()
		nums.forEach {
			set.add(it)
			if (set.contains(-it) && abs(it) > ans)
				ans = abs(it)
		}
		return ans
	}
}

class Solution {
	fun findMaxK(nums: IntArray): Int {
		nums.sort()
		var l = 0
		var r = nums.lastIndex

		while (r > l) {
			val dif = nums[l] + nums[r]
			if (dif == 0) return nums[r]
			if (dif > 0) r-- else l++
		}

		return -1
	}
}

class Solution {
	fun findMaxK(nums: IntArray): Int {
		nums.sort()
		var l = 0
		var r = nums.lastIndex

		while (l < r) {
			val dif = nums[l] + nums[r]

			when {
				dif > 0 -> r--
				dif < 0 -> l++
				else -> return nums[r]
			}
		}

		return -1
	}
}

class Solution {
	fun findMaxK(nums: IntArray): Int {
		val set = mutableSetOf<Int>()
		var res = 0
		for (i in nums.indices) {
			set.add(nums[i])
			if ((nums[i] * -1 in set))
				res = maxOf(res, abs(nums[i]))
		}
		return if (res != 0) res else -1
	}
}

class Solution {
	fun findMaxK(nums: IntArray): Int {
		var max = Int.MIN_VALUE
		for (i in 0..nums.lastIndex) {
			val j = nums[i]
			for (k in i + 1..nums.lastIndex) {
				if ((j != nums[k]) && (abs(j) == nums[k] || abs(nums[k]) == j) && max < abs(j)) {
					max = abs(j)
					break
				}
			}
		}
		return if (max == Int.MIN_VALUE) -1 else max
	}
}