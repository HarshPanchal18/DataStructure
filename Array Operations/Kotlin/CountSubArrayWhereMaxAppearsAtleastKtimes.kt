/*
* Count Subarrays Where Max Element Appears at Least K Times

You are given an integer array nums and a positive integer k.
Return the number of subarrays where the maximum element of nums appears at least k times in that subarray.
A subarray is a contiguous sequence of elements within an array.

Example 1:
Input: nums = [1,3,2,3,3], k = 2
Output: 6
Explanation: The subarrays that contain the element 3 at least 2 times are: [1,3,2,3], [1,3,2,3,3], [3,2,3], [3,2,3,3], [2,3,3] and [3,3].

Example 2:
Input: nums = [1,4,2,1], k = 3
Output: 0
Explanation: No subarray contains the element 4 at least 3 times.

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 106
1 <= k <= 105
*/

fun main() {
	print(countSubArrays(nums = intArrayOf(1, 3, 2, 3, 3), k = 2)) // 6
}

// The sub-arrays that contain the element 3 at least 2 times are:
// [1,3,2,3], [1,3,2,3,3], [3,2,3], [3,2,3,3], [2,3,3] and [3,3].

fun countSubarrays(nums: IntArray, k: Int): Long {
	val max = nums.max()
	var counter = 0
	var result = 0L
	var left = 0
	var right = 0

	while (right < nums.size) {
		if (nums[right] == max) {
			counter++

			while (left <= right && counter >= k) {
				result += (nums.size - right)
				if (nums[left] == max)
					counter--
				left++
			}
		}

		right++
	}

	return result
}

class Solution {
	fun countSubarrays(nums: IntArray, k: Int): Long {
		var i = 0;
		var j = 0
		var count = 0L

		val maxNum = nums.max()
		var maxNumCount = 0

		while (j < nums.size) {
			if (nums[j] == maxNum) maxNumCount++

			while (maxNumCount >= k) {
				if (nums[i] == maxNum) maxNumCount--
				count += nums.size - j
				i++
			}
			j++
		}
		return count
	}
}

class Solution {
	fun countSubarrays(nums: IntArray, k: Int): Long {

		var result = 0L
		var max = nums.max() ?: 0
		var left = 0
		var right = 0
		var count = 0
		while (right < nums.size) {
			if (nums[right] == max)
				count++
			while (count >= k) {
				if (nums[left++] == max)
					count--
				result += (nums.size - right)
			}
			right++
		}
		return result
	}
}

class Solution {
	fun countSubarrays(nums: IntArray, k: Int): Long {
		val n = nums.size
		val totalCnt = (n.toLong() * (n.toLong() + 1)) / 2
		var lessCnt: Long = 0
		val maxE = nums.maxOrNull() ?: 0
		var j = 0
		var cnt = 0

		for (i in nums.indices) {
			if (nums[i] == maxE)
				cnt++

			while (j < n && cnt >= k) {
				if (nums[j] == maxE)
					cnt--
				j++
			}

			lessCnt += (i - j + 1).toLong()
		}

		return totalCnt - lessCnt

	}
}


fun countSubarrays(nums: IntArray, k: Int): Long {
	val n = nums.size.toLong()
	val m = nums.max()
	var ck = 0
	var j = 0

	return n * (n + 1) / 2 +
			nums.indices.sumOf { i ->
				if (nums[i] == m)
					ck++
				while (ck >= k)
					if (nums[j++] == m)
						ck--
				-(i - j + 1).toLong()
			}
}


class Solution {
	fun countSubarrays(nums: IntArray, k: Int): Long {
		val maxElement = nums.max()
		var solutionCount = 0L
		var maxElementCount = 0
		var left = 0

		for (right in nums.indices) {
			if (nums[right] == maxElement)
				maxElementCount++

			while (maxElementCount >= k) {
				maxElementCount -= if (nums[left++] == maxElement) 1 else 0
			}

			solutionCount += left
		}

		return solutionCount
	}
}