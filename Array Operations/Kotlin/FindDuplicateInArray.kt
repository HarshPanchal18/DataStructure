import kotlin.math.abs

/*
* Find All Duplicates in an Array

Given an integer array nums of length n where all the integers of nums are in the range [1, n] and each integer appears once or twice, return an array of all the integers that appears twice.
You must write an algorithm that runs in O(n) time and uses only constant extra space.

Example 1:
Input: nums = [4,3,2,7,8,2,3,1]
Output: [2,3]

Example 2:
Input: nums = [1,1,2]
Output: [1]

Example 3:
Input: nums = [1]
Output: []

Constraints:
n == nums.length
1 <= n <= 105
1 <= nums[i] <= n
Each element in nums appears once or twice.
*/

fun main() {
	val result = findDuplicates(intArrayOf(4, 5, 9, 4, 1, 9))
	result.forEach { print("$it") }
}

fun findDuplicates(nums: IntArray): List<Int> {
	val duplicates = mutableListOf<Int>()
	nums.sort()

	for (i in 1..nums.lastIndex) {
		if (nums[i] == nums[i - 1])
			duplicates.add(nums[i])
	}

	return duplicates.toList()
}

class Solution {
	fun findDuplicates(nums: IntArray): List<Int> {
		val out = ArrayList<Int>()

		val set = HashSet<Int>()

		nums.forEach {

			if (set.contains(it)) {
				out.add(it)
			} else {
				set.add(it)
			}
		}

		return out
	}
}

class Solution {
	fun findDuplicates(nums: IntArray): List<Int> =
		buildList {
			for (x in nums) {
				val n = abs(x)

				if (nums[n - 1] < 0)
					add(n)

				nums[n - 1] = -nums[n - 1]
			}
		}
}

class Solution {
	fun findDuplicates(nums: IntArray): List<Int> {
		val counters = IntArray(nums.size + 1)

		for (i in nums.indices)
			counters[nums[i]] = counters[nums[i]] + 1

		val res = mutableListOf<Int>()
		for (i in counters.indices)
			if (counters[i] > 1)
				res.add(i)

		return res
	}
}

class Solution {
	fun findDuplicates(nums: IntArray): List<Int> {
		val output = mutableListOf<Int>()

		for (n in nums) {
			val index = abs(n) - 1
			if (nums[index] < 0)
				output.add(index + 1)

			nums[index] *= -1
		}
		return output
	}
}

class Solution {
	fun findDuplicates(nums: IntArray): List<Int> {
		val map: HashMap<Int, Boolean> = hashMapOf()
		val answer = arrayListOf<Int>()

		nums.forEach {
			if (map[it] == null)
				map[it] = true
			else
				answer.add(it)
		}

		return answer
	}
}

class Solution {
	fun findDuplicates(nums: IntArray): List<Int> {
		val result = mutableListOf<Int>()
		val map = mutableMapOf<Int, Int>()

		for (num in nums) {
			if (map.containsKey(num))
				result.add(num)

			map[num] = 0
		}
		return result
	}
}