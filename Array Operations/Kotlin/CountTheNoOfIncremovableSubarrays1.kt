/*
* Count the Number of Incremovable Subarrays I
You are given a 0-indexed array of positive integers nums.
A subarray of nums is called incremovable if nums becomes strictly increasing on removing the subarray. For example, the subarray [3, 4] is an incremovable subarray of [5, 3, 4, 6, 7] because removing this subarray changes the array [5, 3, 4, 6, 7] to [5, 6, 7] which is strictly increasing.
Return the total number of incremovable subarrays of nums.
Note that an empty array is considered strictly increasing.
A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,2,3,4]
Output: 10
Explanation: The 10 incremovable subarrays are: [1], [2], [3], [4], [1,2], [2,3], [3,4], [1,2,3], [2,3,4], and [1,2,3,4], because on removing any one of these subarrays nums becomes strictly increasing. Note that you cannot select an empty subarray.

Example 2:
Input: nums = [6,5,7,8]
Output: 7
Explanation: The 7 incremovable subarrays are: [5], [6], [5,7], [6,5], [5,7,8], [6,5,7] and [6,5,7,8].
It can be shown that there are only 7 incremovable subarrays in nums.

Example 3:
Input: nums = [8,7,6,6]
Output: 3
Explanation: The 3 incremovable subarrays are: [8,7,6], [7,6,6], and [8,7,6,6]. Note that [8,7] is not an incremovable subarray because after removing [8,7] nums becomes [6,6], which is sorted in ascending order but not strictly increasing.

Constraints:
1 <= nums.length <= 50
1 <= nums[i] <= 50
*/

fun incremovableSubarrayCount(nums: IntArray): Int {
	var answer = 0 // [1,2,3,4]

	return answer
}

internal class Solution {
	fun incremovableSubarrayCount(nums: IntArray): Int {
		var answer = 0

		for (i in nums.indices)
			for (j in i..nums.lastIndex)
				if (check(nums, i, j))
					answer++

		return answer
	}

	private fun check(nums: IntArray, low: Int, high: Int): Boolean {
		for (i in 0 until low - 1) {
			if (nums[i] >= nums[i + 1])
				return false
		}

		if (low > 0 && high < nums.lastIndex && nums[low - 1] >= nums[high + 1])
			return false

		for (i in high + 1..<nums.lastIndex) {
			if (nums[i] >= nums[i + 1])
				return false
		}

		return true
	}
}

class Solution {
	fun incremovableSubarrayCount(A: IntArray): Int {
		var answer = 0
		val ok = { A: List<Int> -> (1 until A.size).all { A[it - 1] < A[it] } }

		for (i in 0..A.lastIndex)
			for (k in 1..A.lastIndex - i + 1)
				if (
					ok(
						A.slice(0 until i)
								+ A.slice(i + k until A.size)
					)
				) answer++
		//else 0
		return answer
	}
}

class Solution {
	fun incremovableSubarrayCount(nums: IntArray): Int {
		val n = nums.size

		fun good(arr: MutableList<Int>): Boolean {
			//val y = arr.size
			for ((x, y) in arr.zip(arr.drop(1))) {
				if (x >= y)
					return false
			}
			return true
		}

		var counter = 0
		for (i in 0 until n) {
			for (j in i until n) {
				val x = mutableListOf<Int>()
				for (k in 0 until n) {
					if (k !in i..j) {
						x.add(nums[k])
					}
				}

				if (good(x))
					counter++
			}
		}

		return counter
	}
}

class Solution {
	fun incremovableSubarrayCount(nums: IntArray): Int {
		var count = 0
		for (index in nums.indices) {
			for (i in index..nums.lastIndex) {
				if (isIncreasing(nums, index, i))
					count++
			}
		}
		return count
	}

	private fun isIncreasing(nums: IntArray, skipStart: Int, skipEnd: Int): Boolean {
		if (nums.size - ((skipEnd - skipStart) + 1) <= 1)
			return true

		var prev = -1
		for (i in nums.indices) {
			if (i < skipStart || i > skipEnd) {
				if (nums[i] <= prev)
					return false
				prev = nums[i]
			}
		}
		return true
	}

}

class Solution {
	fun incremovableSubarrayCount(nums: IntArray): Int {
		var ans = 0

		for (l in nums.indices) {
			if (l >= 2 && nums[l - 2] >= nums[l - 1])
				break

			for (r in nums.size - 1 downTo l) {
				if (r < nums.size - 2 && nums[r + 1] >= nums[r + 2])
					break
				if (l >= 1 && r < nums.size - 1 && nums[l - 1] >= nums[r + 1])
					break
				ans++
			}
		}
		return ans
	}
}

class Solution {
	fun isIncresingArrayBeforeIndex(nums: IntArray, index: Int): Boolean {
		if (index == 0)
			return true

		for (i in 1 until index) {
			if (nums[i - 1] >= nums[i])
				return false
		}

		return true
	}

	fun isIncresingArrayAfterIndex(nums: IntArray, index: Int): Boolean {
		if (index == nums.size - 1)
			return true

		for (i in index + 2 until nums.size) {
			if (nums[i - 1] >= nums[i])
				return false
		}

		return true
	}

	fun check(nums: IntArray, i: Int, j: Int): Boolean {
		var start = Int.MIN_VALUE
		var end = Int.MAX_VALUE

		if (i > 0)
			start = nums[i - 1]
		if (j < nums.size - 1)
			end = nums[j + 1]

		return start < end
	}

	fun incremovableSubarrayCount(nums: IntArray): Int {
		var subArrayCount = 0

		for (i in nums.indices) {
			for (j in i until nums.size) {
				val canTakeIt =
					check(nums, i, j) && isIncresingArrayBeforeIndex(nums, i) && isIncresingArrayAfterIndex(nums, j)
				if (canTakeIt)
					subArrayCount += 1
			}
		}

		return subArrayCount
	}
}