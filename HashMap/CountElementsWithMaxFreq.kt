/*
* Count Elements With Maximum Frequency

You are given an array nums consisting of positive integers.
Return the total frequencies of elements in nums such that those elements all have the maximum frequency.
The frequency of an element is the number of occurrences of that element in the array.

Example 1:
Input: nums = [1,2,2,3,1,4]
Output: 4
Explanation: The elements 1 and 2 have a frequency of 2 which is the maximum frequency in the array.
So the number of elements in the array with maximum frequency is 4.

Example 2:
Input: nums = [1,2,3,4,5]
Output: 5
Explanation: All elements of the array have a frequency of 1 which is the maximum.
So the number of elements in the array with maximum frequency is 5.

Constraints:
1 <= nums.length <= 100
1 <= nums[i] <= 100
*/

fun main() {
	print(maxFrequencyElements(intArrayOf(1, 2, 2, 3, 1, 4)))
}

fun maxFrequencyElements(nums: IntArray): Int {
	val freq = HashMap<Int, Int>()

	nums.forEach { n ->
		val previous = freq.getOrDefault(n, 0)
		freq[n] = previous + 1
	}

	//println("${freq.keys} - ${freq.values}")

	val maximums = freq.values.max()
	return freq.values.filter { it == maximums }.sum()
}

class Solution {

	fun maxFrequencyElements(nums: IntArray): Int {
		val freqMap = HashMap<Int, Int>()
		fun updateFreq(n: Int) {
			val currentFreq = freqMap[n]
			if (currentFreq == null)
				freqMap[n] = 1
			else
				freqMap[n] = currentFreq + 1

		}
		nums.forEach { updateFreq(it) }
		var maxFreq = Int.MIN_VALUE
		freqMap.forEach { (n, freq) ->
			if (freq > maxFreq) {
				maxFreq = freq
			}
		}

		val filtered = freqMap.filter { entry ->
			entry.value == maxFreq
		}
		return filtered.values.sum()
	}
}

class Solution {
	fun maxFrequencyElements(nums: IntArray): Int {
		val qtyMap = mutableMapOf<Int, Int>()
		var max = 0
		var totalFrequencies = 0

		nums.forEach {
			qtyMap[it] = qtyMap.getOrDefault(it, 0) + 1
			val qty = qtyMap[it]!!

			when {
				qty > max -> {
					totalFrequencies = qty
					max = qty
				}

				qty == max -> {
					totalFrequencies += qty
				}
			}
		}

		return totalFrequencies
	}
}

class Solution {
	fun maxFrequencyElements(nums: IntArray): Int {
		val freq = IntArray(101)
		var m = 0
		for (item in nums) {
			freq[item]++
			m = m.coerceAtLeast(freq[item])
		}

		var result = 0
		for (item in freq)
			if (item == m)
				result += m

		return result
	}
}

class Solution {
	fun maxFrequencyElements(nums: IntArray): Int {
		val counts = IntArray(101)
		nums.forEach { num -> counts[num]++ }

		val max = counts.max()
		var sum = 0
		counts.forEach { count ->
			if (count == max)
				sum += count
		}

		return sum
	}
}

class Solution {
	fun maxFrequencyElements(nums: IntArray): Int {
		val counts = IntArray(101)

		for (num in nums)
			counts[num]++

		var maxCount = 0
		var answer = 0
		for (count in counts) {
			if (count > maxCount) {
				maxCount = count
				answer = count
			} else if (count == maxCount) {
				answer += count
			}
		}
		return answer
	}
}

class Solution {
	fun maxFrequencyElements(nums: IntArray): Int {
		val map = mutableMapOf<Int, Int>()
		var max = 0
		var sum = 0

		for (i in nums.indices) {
			val updated = map.getOrDefault(nums[i], 0) + 1
			if (updated > max)
				max = updated

			map[nums[i]] = updated
		}

		/*for (e in map)
			if (e.value == max)
				sum += max*/
		sum = map.values.filter { it == max }.sum()

		return sum
	}
}