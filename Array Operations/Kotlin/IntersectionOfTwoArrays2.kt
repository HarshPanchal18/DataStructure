/*
* Intersection of Two Arrays II
Given two integer arrays nums1 and nums2, return an array of their intersection.
Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.

Example 1:
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]

Example 2:
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Explanation: [9,4] is also accepted.

Constraints:
1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000

Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
*/

fun main() {
	var result = intersect(intArrayOf(1, 2, 2, 1), intArrayOf(2, 2))
	result.forEach { println(it) }

	result = intersect(intArrayOf(4, 9, 5), intArrayOf(9, 4, 9, 8, 4))
	result.forEach { println(it) }
}

fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
	val resultSet = (nums1 intersect nums2.toSet())
	val frequency = mutableMapOf<Int, Int>()

	resultSet.forEach { n ->
		frequency[n] = minOf(nums1.count { it == n }, nums2.count { it == n })
	}

	println(frequency)
	val answer = mutableListOf<Int>()
	for ((key, value) in frequency) {
		for (i in 1..value)
			answer.add(key)
	}

	println(answer)
	return answer.toIntArray()
}

class Solution {
	fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
		val map1 = nums1.toList().groupingBy { it }.eachCount()
		val map2 = nums2.toList().groupingBy { it }.eachCount()
		val result = mutableListOf<Int>()

		for ((k, v) in map1) {
			map2[k]?.let { minCount ->
				repeat(minOf(v, minCount)) {
					result.add(k)
				}
			}
		}

		return result.toIntArray()
	}
}

class Solution {
	fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
		nums1.sort()
		nums2.sort()

		var i = 0
		var j = 0
		val intersection = mutableListOf<Int>()

		while (i < nums1.size && j < nums2.size) {
			if (nums1[i] < nums2[j]) {
				i++
			} else if (nums1[i] > nums2[j]) {
				j++
			} else {
				intersection.add(nums1[i])
				i++
				j++
			}
		}

		return intersection.toIntArray()
	}
}

class Solution {
	fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
		val map = hashMapOf<Int, Int>()
		val result = mutableListOf<Int>()

		nums1.forEach { v ->
			map[v] = map.getOrDefault(v, 0) + 1
		}

		nums2.forEach { v ->
			map[v]?.let { count ->
				if (count > 0) {
					map[v] = count - 1
					result.add(v)
				}
			}
		}

		return result.toIntArray()
	}
}

class Solution {
	fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
		nums1.sort()
		nums2.sort()

		var i = 0
		var j = 0
		var k = 0

		while (i < nums1.size && j < nums2.size) {
			if (nums1[i] < nums2[j]) {
				i++
			} else if (nums1[i] > nums2[j]) {
				j++
			} else {
				nums1[k++] = nums1[i++]
				j++
			}
		}

		return nums1.copyOfRange(0, k)
	}
}

class Solution {
	fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
		val counts = IntArray(1024)
		for (num in nums1)
			counts[num]++

		val untrimmedIntersection = IntArray(1024)
		var i = 0

		for (num in nums2) {
			if (counts[num] == 0)
				continue

			untrimmedIntersection[i] = num
			counts[num]--
			i++
		}

		val intersection = IntArray(i)
		System.arraycopy(untrimmedIntersection, 0, intersection, 0, i)
		return intersection
	}
}

class Solution {
	fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
		if (nums1.size > nums2.size)
			return intersect(nums2, nums1)

		val cnt = mutableMapOf<Int, Int>()
		for (num in nums1)
			cnt[num] = cnt.getOrDefault(num, 0) + 1

		val ans = mutableListOf<Int>()
		for (x in nums2) {
			if (cnt.getOrDefault(x, 0) > 0) {
				ans.add(x)
				cnt[x] = cnt[x]!! - 1
			}
		}

		return ans.toIntArray()
	}
}