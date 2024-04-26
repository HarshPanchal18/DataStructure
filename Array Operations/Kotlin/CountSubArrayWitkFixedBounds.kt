/*
* Count Subarrays With Fixed Bounds

You are given an integer array nums and two integers minK and maxK.
A fixed-bound subarray of nums is a subarray that satisfies the following conditions:
* The minimum value in the subarray is equal to minK.
* The maximum value in the subarray is equal to maxK.
Return the number of fixed-bound subarrays.

A subarray is a contiguous part of an array.

Example 1:
Input: nums = [1,3,5,2,7,5], minK = 1, maxK = 5
Output: 2
Explanation: The fixed-bound subarrays are [1,3,5] and [1,3,5,2].

Example 2:
Input: nums = [1,1,1,1], minK = 1, maxK = 1
Output: 10
Explanation: Every subarray of nums is a fixed-bound subarray. There are 10 possible subarrays.

Constraints:
2 <= nums.length <= 105
1 <= nums[i], minK, maxK <= 106
*/

class Solution {
	fun countSubarrays(nums: IntArray, minK: Int, maxK: Int): Long {
		var result = 0L
		var minKIndex = -1
		var maxKIndex = -1
		var culpritIndex = -1

		for (i in nums.indices) {
			if (nums[i] < minK || nums[i] > maxK)
				culpritIndex = i

			if (nums[i] == minK)
				minKIndex = i

			if (nums[i] == maxK)
				maxKIndex = i

			val smaller = minOf(minKIndex, maxKIndex).toLong()
			val temp = smaller - culpritIndex.toLong()

			result += if (temp > 0) temp else 0
		}
		return result
	}
}

class Solution {
	fun countSubarrays(nums: IntArray, minK: Int, maxK: Int): Long =
		nums.foldIndexed(0L to IntArray(3) { -1 }) { i, (r, p), n ->
			if (n !in minK..maxK) p[0] = i
			if (n == minK) p[1] = i
			if (n == maxK) p[2] = i
			r + (minOf(p[1], p[2]) - p[0]).let { if (it > 0) it.toLong() else 0L } to p
		}.first
}

class Solution {
	fun countSubarrays(nums: IntArray, minK: Int, maxK: Int): Long {
		var recentMin = -1 // most recent index where minK occurred
		var recentMax = -1 // most recent index where maxK occurred
		var recentOut = -1 // most recent index where number is out of bounds
		var ans = 0L

		for (i in nums.indices) {
			if (nums[i] !in minK..maxK)
				recentOut = i

			if (nums[i] == minK)
				recentMin = i

			if (nums[i] == maxK)
				recentMax = i

			val currentArrStart = minOf(recentMin, recentMax)
			ans += maxOf(0, currentArrStart - recentOut)
		}

		return ans
	}
}

class Solution {
	fun countSubarrays(nums: IntArray, minK: Int, maxK: Int): Long {
		var i = 0
		var j = 0
		var count = 0L

		var recentMinK = -1
		var recentMaxK = -1

		while (j < nums.size) {
			val nJ = nums[j]

			if (nJ > maxK || nJ < minK) {
				// if the value is out of the range we cannot have it in a valid subarray
				j++
				i = j
				recentMinK = -1
				recentMaxK = -1
			} else {
				// Keep track of the most recent index with the min and max k
				if (minK == nJ) recentMinK = j
				if (maxK == nJ) recentMaxK = j

				// If we have seen the min and max then we have a valid subarray
				if (recentMinK != -1 && recentMaxK != -1)
					count += (if (recentMinK < recentMaxK) recentMinK else recentMaxK) - i + 1 // Add all the valid subarrays [i,j] to [min(recentMinK,recentMaxK),j]

				j++
			}
		}

		return count
	}
}

class Solution {
	fun countSubarrays(nums: IntArray, minK: Int, maxK: Int): Long {
		val smallerOrLargerIndices = TreeSet<Int>()
		val minKIndices = TreeSet<Int>()
		val maxKIndices = TreeSet<Int>()

		for (i in nums.indices) {
			when (nums[i]) {
				minK -> minKIndices.add(i)
				maxK -> maxKIndices.add(i)
				!in minK..maxK -> smallerOrLargerIndices.add(i)
			}
		}

		var sum = 0L
		for (i in nums.indices) {
			val nextMinK = if (nums[i] == minK) i else minKIndices.higher(i)
			val nextMaxK = if (nums[i] == maxK) i else maxKIndices.higher(i)

			// println("i = $i, nextMaxK = $nextMaxK, nextMinK = $nextMinK")
			if (nextMaxK != null && nextMinK != null) {
				val maxOfNextMinAndMaxK = max(nextMinK, nextMaxK)
				val nextSmallerOrLarger = if (nums[i] !in minK..maxK) {
					i
				} else {
					smallerOrLargerIndices.higher(i)
				}
				// println("i = $i, maxOfNextMinAndMaxK = $maxOfNextMinAndMaxK, nextSmallerOrLarger = $nextSmallerOrLarger")
				if (nextSmallerOrLarger == null) {
					val windowSizeEnd = nums.size - 1 - maxOfNextMinAndMaxK + 1
					// println("i = $i, nextSmallerOrLarger = $nextSmallerOrLarger, maxOfNextMinAndMaxK = $maxOfNextMinAndMaxK, windowSizeEnd = $windowSizeEnd")
					sum += windowSizeEnd
				} else {
					if (nextSmallerOrLarger > maxOfNextMinAndMaxK) {
						val windowSizeEnd = nextSmallerOrLarger - maxOfNextMinAndMaxK
						// println("i = $i, nextSmallerOrLarger = $nextSmallerOrLarger, maxOfNextMinAndMaxK = $maxOfNextMinAndMaxK, windowSizeEnd = $windowSizeEnd")
						sum += windowSizeEnd
					}
				}
			}
		}

		return sum
	}
}

class Solution {
	fun countSubarrays(nums: IntArray, minK: Int, maxK: Int): Long {
		fun count(numsSlice: List<Int>): Long {
			var sc = 0L;
			val size = numsSlice.size

			for (si in 0..<size)
				for (sj in si..<size) {
					val s = numsSlice.slice(si..sj)
					if (s.contains(minK) && s.contains(maxK)) {
						sc += size - sj
						break
					}
				}

			return sc
		}

		var c = 0L
		var startIndex = 0
		var endIndex = 0
		var isInRange = false

		for (i in nums.indices)
			if (nums[i] in minK..maxK) {
				endIndex = i
				if (!isInRange) {
					startIndex = i
					isInRange = true
				}
			} else {
				if (isInRange) c += count(nums.slice(startIndex..endIndex))
				isInRange = false
			}

		if (isInRange)
			c += count(nums.slice(startIndex..endIndex))

		return c
	}
}

fun countSubarrays(nums: IntArray, minK: Int, maxK: Int): Long {
	var sum = 0L
	if (minK == maxK) {
		var count = 0
		for (i in 0..nums.lastIndex) {
			if (nums[i] == minK) count++
			else count = 0
			if (count > 0) sum += count
		}
		return sum
	}

	val range = minK..maxK
	// 0 1 2 3 4 5 6 7 8 91011
	// 3 7 2 2 2 2 2 1 2 3 2 1
	//   b
	//               *...*
	//                   *...*
	var border = -1
	var posMin = -1
	var posMax = -1

	for (i in 0..nums.lastIndex) {
		when (nums[i]) {
			!in range -> border = i
			minK -> posMin = i
			maxK -> posMax = i
		}
		if (posMin > border && posMax > border)
			sum += minOf(posMin, posMax) - border
	}
	return sum
}

class Solution {
	fun countSubarrays(nums: IntArray, minK: Int, maxK: Int): Long {
		var result: Long = 0
		var minStart: Int = 0
		var maxStart: Int = 0
		var minFound: Boolean = false
		var maxFound: Boolean = false
		var start: Int = 0

		for (i in nums.indices) {
			val num = nums[i]

			if (num < minK || num > maxK) {
				minFound = false
				maxFound = false
				start = i + 1
			}

			if (num == minK) {
				minFound = true
				minStart = i
			}

			if (num == maxK) {
				maxFound = true
				maxStart = i
			}

			if (minFound && maxFound)
				result += min(maxStart, minStart) - start + 1

		}

		return result
	}
}