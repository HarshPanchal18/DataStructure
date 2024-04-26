/*
* First Missing Positive - Hard

Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.
You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.

Example 1:
Input: nums = [1,2,0]
Output: 3
Explanation: The numbers in the range [1,2] are all in the array.

Example 2:
Input: nums = [3,4,-1,1]
Output: 2
Explanation: 1 is in the array but 2 is missing.

Example 3:
Input: nums = [7,8,9,11,12]
Output: 1
Explanation: The smallest positive integer 1 is missing.

Constraints:
1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
*/

fun main() {
    print(firstMissingPositive(intArrayOf(1, 2, 0)))
}

fun firstMissingPositive(nums: IntArray): Int {
	val map = HashMap<Int, Int>()
	var maximum = Int.MIN_VALUE

	for (n in nums) {
		map[n] = 1
		maximum = maxOf(maximum, n)
	}

	for (i in 1..maximum)
		if (!map.containsKey(i))
			return i

	return if (maximum < 0) 1 else maximum + 1
}

class Solution {
	fun firstMissingPositive(nums: IntArray): Int {
		val range = nums.indices
		nums.forEachIndexed { i, it ->
			if (it < 0) {
				nums[i] = 0
			}
		}
		nums.forEachIndexed { i, it ->
			val markI = Math.abs(it) - 1
			if (markI in range && nums[markI] >= 0) {
				nums[markI] = if (nums[markI] > 0) -nums[markI] else Int.MIN_VALUE
			}
		}
		var i = 0
		while (i in range) {
			val cur = nums[i++]
			if (cur >= 0) {
				return i
			}
		}
		return i + 1
	} //tc: O(n), sc: O(1)
}

class Solution {
	fun firstMissingPositive(nums: IntArray): Int {
		nums.sort()
		var ans = 1
		for (i in nums)
			if (i == ans)
				ans++

		return ans
	}
}

class Solution {
	fun firstMissingPositive(nums: IntArray): Int {
		val slots = IntArray(nums.size)

		for (n in nums)
			if (n <= slots.size && n > 0)
				slots[n - 1] = n

		for (i in slots.indices)
			if (slots[i] == 0)
				return i + 1

		return slots.size + 1

	}
}

class Solution {
	fun firstMissingPositive(nums: IntArray): Int {
		val n = nums.size

		for (i in 0..<n)
			if (nums[i] <= 0 || nums[i] > n)
				nums[i] = n + 1

		for (i in 0..<n) {
			val abs = abs(nums[i])

			if (abs > n) continue

			val index = abs - 1
			if (nums[index] > 0)
				nums[index] *= -1
		}

		for (i in 0..<n)
			if (nums[i] >= 0)
				return i + 1

		return n + 1
	}
}

class Solution {
	fun firstMissingPositive(nums: IntArray): Int {
		val sorted = nums.toSet().filter { it > 0 }.sorted()

		if (sorted.isEmpty())
			return 1

		for ((index, i) in sorted.withIndex())
			if ((index + 1) != i)
				return index + 1

		return sorted.last() + 1
	}
}

class Solution {
	fun firstMissingPositive(nums: IntArray): Int {
		val posSorted = nums.sorted().filter { it > 0 }
		var smallest = 1
		posSorted.forEach {
			if (it <= smallest)
				smallest = it + 1
			else
				return smallest
		}

		return smallest
	}
}

class Solution {
	fun firstMissingPositive(nums: IntArray): Int {
		var isSizeElementPresent = false

		for (i in 0..nums.lastIndex) {
			if (nums[i] == nums.size)
				isSizeElementPresent = true

			if (nums[i] <= 0 || nums[i] >= nums.size)
				nums[i] = -1
		}

		var i = 0
		while (i <= nums.lastIndex) {
			if (nums[i] > 0) {

				val temp = nums[nums[i]]
				if (temp > 0 && temp <= nums.lastIndex) {
					if (nums[i] == i) {
						nums[i] = 0 //mark visited
					} else {
						//we need to preserve this
						nums[nums[i]] = 0  //mark visited
						nums[i] = temp
						i-- // as it is preserved, we need to re-evaluate this
					}
				} else nums[nums[i]] = 0
			}

			i++
		}

		for (i in 0..nums.lastIndex)
			print("${nums[i]}, ")

		for (i in 1..nums.lastIndex)
			if (nums[i] != 0) return i

		return if (isSizeElementPresent) nums.size + 1 else nums.size
	}
}

class Solution {
	fun firstMissingPositive(nums: IntArray): Int {
		var result = Int.MIN_VALUE
		for (value in 1..Int.MAX_VALUE) {
			if (!nums.contains(value)) {
				result = value
				break
			}
		}

		return result
	}
}

class Solution {
	fun firstMissingPositive(nums: IntArray): Int {
		for (k in 0..nums.size) {
			if (k + 1 in nums)
				continue
			else
				return k + 1
		}

		return 1
	}
}

class Solution {
	fun firstMissingPositive(nums: IntArray): Int {
		for (i in 1..nums.size) {
			if (i in nums)
				continue
			return i
		}
		return nums.size + 1
	}
}