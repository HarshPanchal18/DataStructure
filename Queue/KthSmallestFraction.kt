import java.util.PriorityQueue

/*
* K-th Smallest Prime Fraction
You are given a sorted integer array arr containing 1 and prime numbers, where all the integers of arr are unique. You are also given an integer k.
For every i and j where 0 <= i < j < arr.length, we consider the fraction arr[i] / arr[j].
Return the kth smallest fraction considered. Return your answer as an array of integers of size 2, where answer[0] == arr[i] and answer[1] == arr[j].

Example 1:
Input: arr = [1,2,3,5], k = 3
Output: [2,5]
Explanation: The fractions to be considered in sorted order are:
1/5, 1/3, 2/5, 1/2, 3/5, and 2/3.
The third fraction is 2/5.

Example 2:
Input: arr = [1,7], k = 1
Output: [1,7]

Constraints:
2 <= arr.length <= 1000
1 <= arr[i] <= 3 * 104
arr[0] == 1
arr[i] is a prime number for i > 0.
All the numbers of arr are unique and sorted in strictly increasing order.
1 <= k <= arr.length * (arr.length - 1) / 2
*/

fun main() {
	val result = kthSmallestPrimeFraction(intArrayOf(1, 2, 3, 5), 3)
}

fun kthSmallestPrimeFraction(arr: IntArray, k: Int): IntArray {
	val pq = PriorityQueue<IntArray> { first, second ->
		if (first[0].toDouble() / first[1].toDouble() > second[0].toDouble() / second[1].toDouble()) 1
		else -1
	}

	for (i in arr.indices) {
		for (j in i + 1 until arr.size)
			pq.add(intArrayOf(arr[i], arr[j]))
	}

	repeat(k - 1) { pq.remove() }

	return pq.remove()
}

/*fun kThSmallestPrimeFraction(arr: IntArray, k: Int) {
	var left = 0
	val fractions = mutableListOf<Double>()

	while (left <= arr.lastIndex) {
		var right = left + 1

		while (right <= arr.lastIndex) {
			val fraction: Double = (arr[left]).toDouble() / (arr[right]).toDouble()
			fractions.add(fraction)
			right++
		}
		left++
		println(fractions.sorted())
	}

}*/

class Solution {
	fun kthSmallestPrimeFraction(arr: IntArray, k: Int): IntArray {
		val heap = PriorityQueue<Pair<Int, Int>> { a, b ->
			arr[a.first] * arr[b.second] - arr[b.first] * arr[a.second]
		}

		for (i in arr.indices)
			for (j in i + 1..arr.lastIndex)
				heap.add(i to j)

		var pair = heap.poll()
		repeat(k - 1) { pair = heap.poll() }

		return intArrayOf(arr[pair.first], arr[pair.second])
	}
}

class Solution {
	fun kthSmallestPrimeFraction(arr: IntArray, k: Int): IntArray {
		val pq = PriorityQueue<DoubleArray> { a, b ->
			if (a[0] / a[1] > b[0] / b[1]) 1
			else -1
		}

		for (l in arr.indices) {
			for (r in l..arr.lastIndex) {
				if (l != r)
					pq.add(doubleArrayOf(arr[l].toDouble(), arr[r].toDouble()))
			}
		}

		var nums = pq.remove()
		repeat(k - 1) { nums = pq.remove() }

		return intArrayOf(nums[0].toInt(), nums[1].toInt())
	}
}

class Solution {
	fun kthSmallestPrimeFraction(arr: IntArray, k: Int) =
		(arr.indices).flatMap { i ->
			(arr.lastIndex downTo i + 1)
				.map { j -> arr[i] to arr[j] }
		}
			.sortedBy { (i, j) -> i / j.toDouble() }[k - 1]
			.let { (i, j) -> intArrayOf(i, j) }
}