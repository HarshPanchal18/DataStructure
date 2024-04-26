import kotlin.math.max

/*
* Length of Longest Subarray With at Most K Frequency

You are given an integer array nums and an integer k.
The frequency of an element x is the number of times it occurs in an array.
An array is called good if the frequency of each element in this array is less than or equal to k.
Return the length of the longest good subarray of nums.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,2,3,1,2,3,1,2], k = 2
Output: 6
Explanation: The longest possible good subarray is [1,2,3,1,2,3] since the values 1, 2, and 3 occur at most twice in this subarray. Note that the subarrays [2,3,1,2,3,1] and [3,1,2,3,1,2] are also good.
It can be shown that there are no good subarrays with length more than 6.

Example 2:
Input: nums = [1,2,1,2,1,2,1,2], k = 1
Output: 2
Explanation: The longest possible good subarray is [1,2] since the values 1 and 2 occur at most once in this subarray. Note that the subarray [2,1] is also good.
It can be shown that there are no good subarrays with length more than 2.

Example 3:
Input: nums = [5,5,5,5,5,5,5], k = 4
Output: 4
Explanation: The longest possible good subarray is [5,5,5,5] since the value 5 occurs 4 times in this subarray.
It can be shown that there are no good subarrays with length more than 4.

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 109
1 <= k <= nums.length
*/

fun main() {
	print(maxSubarrayLength(intArrayOf(1, 2, 3, 1, 2, 3, 1, 2), 2))
}

fun maxSubarrayLength(nums: IntArray, k: Int): Int {
	val freq = HashMap<Int, Int>() // Frequency hashmap
	var ans = 0
	// Left boundaries
	var i = 0
	// right boundaries
	var j = 0

	while (j < nums.size) {
		freq[nums[j]] = freq.getOrDefault(nums[j], 0) + 1 // Update the frequency of nums[j]

		// Adjusting the left boundary of the subarray when the frequency of an element exceeds the specified limit k.
		while (freq[nums[j]]!! > k) {
			freq[nums[i]] = freq[nums[i]]!! - 1 // decrease the frequency of nums[i]
			i++ // move the left pointer to the right
		}

		ans = max(ans, j - i + 1)
		j++
	}

	return ans
}

class Solution {
	// hint: at most freq k
	// seems like a variant of sliding-fixed-window tho we may need a hashmap.
	fun maxSubarrayLength(nums: IntArray, k: Int): Int {
		var left = 0
		var result = 0 // max-len
		val map = hashMapOf<Int, Int>() // int || freq

		// we can iterate with sliding-window and fill the map. then just update the window depending on when we exceed k freqs.
		for (right in nums.indices) {
			val num = nums[right]

			val stored = map[num]
			if (stored == null)
				map[num] = 1
			else
				map[num] = stored + 1

			var curLen = map[num] ?: error("unexpected")
			while (curLen > k) {
				val leftNum = nums[left]
				val storedLeft = map[leftNum] ?: error("null storedLeft")
				// It's ok to skip cur left ptr if there are no more elements to remove from this int.
				map[leftNum] = storedLeft - 1
				curLen = map[num] ?: error("unexpected")
				left++
			}

			result = maxOf(result, right - left + 1)
		}

		return result
	}
}

/*
 nums = [1,2,3,1,2,3,1,2], k = 2
 ans = 6
 1->2
 2->2
 3->2
 */
class Solution {
	fun maxSubarrayLength(nums: IntArray, k: Int): Int {
		val frequencyMap = hashMapOf<Int, Int>()

		var left = 0
		var ans = 0

		for (right in nums.indices) {
			val curr = nums[right]
			frequencyMap[curr] = (frequencyMap[curr] ?: 0) + 1

			while (frequencyMap[curr]!! > k) {
				val leftValue = nums[left]
				frequencyMap[leftValue] = frequencyMap[leftValue]!! - 1
				if (frequencyMap[leftValue] == 0) {
					frequencyMap.remove(frequencyMap[leftValue])
				}
				left++
			}

			ans = ans.coerceAtLeast(right - left + 1)
		}

		return ans
	}
}

class Solution {
	fun maxSubarrayLength(nums: IntArray, k: Int): Int {
		val frequencies = hashMapOf<Int, Int>()
		var left = 0
		var maxLength = 0
		for (right in nums.indices) {
			val num = nums[right]
			val newFrequency = frequencies.getOrDefault(num, 0) + 1
			if (newFrequency > k) {
				while (nums[left] != num) {
					frequencies[nums[left]] = frequencies.getOrDefault(nums[left], 0) - 1
					left++
				}
				left++
			} else {
				frequencies[num] = newFrequency
			}
			maxLength = maxOf(maxLength, right - left + 1)
		}
		return maxLength
	}
}

class Solution {
	fun maxSubarrayLength(nums: IntArray, k: Int): Int {
		val counter = mutableMapOf<Int, Int>()
		var moreK = 0
		var res = 0

		var l = 0
		var r = 0

		while (r < nums.size) {
			val n = nums[r]
			counter[n] = counter.getOrDefault(n, 0) + 1
			if (counter[n]!! > k) moreK++

			while (l < nums.size && moreK > 0) {
				val d = nums[l++]
				counter[d] = counter.getOrDefault(d, 0) - 1
				if (counter[d] == k) moreK--
			}

			res = res.coerceAtLeast(r - l + 1)
			r++
		}

		return res
	}
}