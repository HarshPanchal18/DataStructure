/*
* Subarrays with K Different Integers
Given an integer array nums and an integer k, return the number of good subarrays of nums.
A good array is an array where the number of different integers in that array is exactly k.
For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
A subarray is a contiguous part of an array.

Example 1:
Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]

Example 2:
Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].

Constraints:
1 <= nums.length <= 2 * 104
1 <= nums[i], k <= nums.length
*/

fun main() {
	println(Solution().subarraysWithKDistinct(intArrayOf(1, 2, 1, 2, 3), 2))
	println(Solution().subarraysWithKDistinct(intArrayOf(1, 2, 1, 3, 4), 3))
}

fun subarraysWithKDistinct(nums: IntArray, k: Int): Int {
	var answer = 0
	var left = 0
	var right = 1
	var window: List<Int>
	var map = hashMapOf<Int, Int>()

	while (left != right) {
		window = nums.slice(left..right)
		if (window.distinct().size == k) {
			//println(window)
			answer++
		}
		println(window)
		if (right == nums.size - 1)
			left++
		else
			right++
	}

	return answer
}

class Solution {
	fun subarraysWithKDistinct(nums: IntArray, k: Int): Int {
		return atMostK(nums, k) - atMostK(nums, k - 1)
	}

	fun atMostK(nums: IntArray, _k: Int): Int {
		var result = 0
		var i = 0
		var k = _k
		val cache = HashMap<Int, Int>()

		for (j in nums.indices) {
			if (cache.getOrDefault(nums[j], 0) == 0)
				k--

			cache[nums[j]] = cache.getOrDefault(nums[j], 0) + 1

			while (k < 0) {
				cache[nums[i]] = cache.getOrDefault(nums[i], 1) - 1
				if (cache.getOrDefault(nums[i], 0) == 0)
					k++
				i++
			}

			result += j - i + 1
		}

		return result
	}
}

class Solution {
	fun subarraysWithKDistinct(nums: IntArray, k: Int): Int {
		val m = IntArray(nums.size + 1)
		var cnt = 0
		var j = 0
		var p = 0
		return nums.sumOf { n ->
			if (m[n]++ == 0) cnt++
			if (cnt > k) {
				m[nums[j++]]--
				cnt--
				p = 0
			}
			while (m[nums[j]] > 1) {
				p++
				m[nums[j++]]--
			}
			if (cnt == k) p + 1 else 0
		}
	}
}

class Solution {
	fun subarraysWithKDistinct(nums: IntArray, k: Int): Int {
		val all = hashMapOf<Int, Int>()
		val small = hashMapOf<Int, Int>()

		all[nums[0]] = 1
		small[nums[0]] = 1

		var r = 1
		var mid = 1
		var ans = 0

		for (l in nums.indices) {
			while (mid < nums.size && small.size != k) {
				small[nums[mid]] = small.getOrDefault(nums[mid], 0) + 1
				mid++
			}

			while (r < nums.size && (all.containsKey(nums[r]) || all.size < k)) {
				all[nums[r]] = all.getOrDefault(nums[r], 0) + 1
				r++
			}

			if (small.size == k)
				ans += (r - mid + 1)

			small[nums[l]] = small.getOrDefault(nums[l], 0) - 1
			if (small[nums[l]] == 0)
				small.remove(nums[l])

			all[nums[l]] = all.getOrDefault(nums[l], 0) - 1
			if (all[nums[l]] == 0)
				all.remove(nums[l])

		}
		return ans

	}
}

class Solution {
	fun subarraysWithKDistinct(nums: IntArray, k: Int): Int {
		val subWithMaxK = subarrayWithAtMostK(nums, k)
		val reducedSubWithMaxK = subarrayWithAtMostK(nums, k - 1)
		return subWithMaxK - reducedSubWithMaxK
	}

	fun subarrayWithAtMostK(nums: IntArray, k: Int): Int {
		val map = HashMap<Int, Int>()
		var left = 0
		var right = 0
		var ans = 0

		while (right < nums.size) {
			map[nums[right]] = map.getOrDefault(nums[right], 0) + 1

			while (map.size > k) {
				map[nums[left]] = map[nums[left]]!! - 1

				if (map[nums[left]] == 0)
					map.remove(nums[left])

				left++
			}

			ans += right - left + 1 // Size of subarray
			right++
		}

		return ans
	}
}

class Solution {
	fun solve(nums: IntArray, k: Int): Int {

		if (k == 0)
			return 0

		var ans = 0
		val n = nums.size
		val cnt = HashMap<Int, Int>()
		var diff = 0
		var l = 0
		var r = 0

		while (r < n) {
			cnt[nums[r]] = cnt.getOrDefault(nums[r], 0) + 1
			if (cnt[nums[r]] == 1)
				diff++

			while (diff > k) {
				cnt[nums[l]] = cnt[nums[l]]!! - 1
				if (cnt[nums[l]] == 0)
					diff--
				l++
			}
			ans += (r - l + 1)
			r++
		}
		return ans
	}

	fun subarraysWithKDistinct(nums: IntArray, k: Int): Int {
		return solve(nums, k) - solve(nums, k - 1)
	}
}

class Solution {
	/*
	Main Function:
	--> The main function subarraysWithKDistinct calculates the number of subarrays with exactly k different integers by subtracting the count of subarrays with at most k−1 distinct integers from the count of subarrays with at most k distinct integers.

	Subroutine Function (subarraysWithAtMostKDistinct):
	-->This function calculates the number of subarrays with at most k distinct integers.
	-->Initialize variables:
	:> `ans` to store the total count of subarrays.
	:> count array to track the frequency of elements in the current window.

	--> Iterate through the array using two pointers land r:
	:> Increment the frequency count of nums[r] by 1.
	:> If the frequency count of nums[r] becomes 1 (i.e., it's a new distinct element), decrement k.
	:> While k becomes -1 (i.e., there are more than k distinct elements), increment l and decrement the frequency count of nums[l]. If the frequency count becomes 0, increment k.
	:> Update `ans` by adding the length of the current subarray (r - l + 1), as all subarrays within the current window contribute to the total count.
	-->Return the final value of ans.
*/

	fun subarraysWithKDistinct(nums: IntArray, k: Int): Int {
		return subarraysWithAtMostKDistinct(nums, k) - subarraysWithAtMostKDistinct(nums, k - 1)
	}

	private fun subarraysWithAtMostKDistinct(nums: IntArray, k: Int): Int {
		var _k = k
		var ans = 0
		val count = IntArray(nums.size + 1) // to keep track of the frequency of each integer
		var left = 0
		var right = 0

		// Move the right pointer to the right until we have at most k distinct integers in the current subarray.
		while (right < nums.size) {
			// If count[nums[r]] is 0 (i.e., the current integer is not yet in the subarray), decrement k.
			// Increment count[nums[r]].
			if (++count[nums[right]] == 1)
				_k--

			// Once we have more than k distinct integers, move the left pointer to the right until we have at most k distinct integers again.
			// If count[nums[left]] becomes 0 (i.e., the leftmost integer is no longer in the subarray), increment k.
			// Decrement count[nums[left]].
			while (k == -1)
				if (--count[nums[left++]] == 0)
					_k++

			ans += right - left + 1 // add the length of the subarray
			right++
		}

		return ans
	}
}