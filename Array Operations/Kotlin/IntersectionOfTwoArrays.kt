/*
* Intersection of Two Arrays
Given two integer arrays nums1 and nums2, return an array of their intersection.
Each element in the result must be unique and you may return the result in any order.

Example 1:
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

Example 2:
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.

Constraints:
1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000
*/

fun main() {
	val result = intersection(intArrayOf(1, 2, 2, 1), intArrayOf(2, 2))
	result.forEach { println(it) }
}

fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
	return (nums1 intersect nums2.toSet()).toIntArray()
}

fun intersection2(nums1: IntArray, nums2: IntArray): IntArray {
	val answer = mutableListOf<Int>()
	val set = mutableSetOf<Int>()
	var i = 0
	var j = 0

	while (i < nums1.size && j < nums2.size) {
		when {
			nums1[i] == nums2[j] -> {
				set.add(nums1[i++]); j++
			}

			nums1[i] < nums2[j] -> i++
			nums1[i] > nums2[j] -> j++
		}
	}

	answer.plus(set)

	return answer.toIntArray()
}

class Solution {
	fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
		val map = mutableMapOf<Int, Int>()
		val res = HashMap<Int, Int>()

		nums1.forEach {
			if (map[it] == null)
				map[it] = it
		}

		nums2.forEach {
			map[it]?.let { _ ->
				res[it] = it
			}
		}

		return res.keys.toIntArray()
	}
}

class Solution {
	fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
		val map = mutableMapOf<Int, Int>()
		for (num in nums1)
			map[num] = map.getOrDefault(num, 0) + 1

		val resultList = mutableListOf<Int>()
		for (num in nums2) {
			if (map.containsKey(num)) {
				resultList.add(num)
				map.remove(num)
			}
		}

		return resultList.toIntArray()
	}
}

class Solution {
	fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
		val len1 = nums1.size
		val len2 = nums2.size
		val set = HashSet<Int>()
		if (len1 > len2) {
			nums2.forEach {
				if (it in nums1) set.add(it)
			}
		} else {
			nums1.forEach {
				if (it in nums2) set.add(it)
			}
		}
		return set.toIntArray()
	}
}

class Solution {
	fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
		val list: HashSet<Int> = hashSetOf()

		nums2.forEach { item ->
			if (nums1.contains(item))
				list.add(item)
		}

		return list.toIntArray()
	}
}

class Solution {
	fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
		val x = mutableListOf<Int>()
		val set1 = HashSet<Int>()
		val set2 = HashSet<Int>()

		set1.plus(nums1)
		set2.plus(nums2)

		set1.forEach {
			if (set2.contains(it))
				x.add(it)
		}

		val y = IntArray(x.size)
		var counter = 0

		x.forEach {
			y[counter++] = it
		}

		return y
	}
}