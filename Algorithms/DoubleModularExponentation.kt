import kotlin.math.pow

/*
* Double Modular Exponentiation
You are given a 0-indexed 2D array variables where variables[i] = [ai, bi, ci, mi], and an integer target.
An index i is good if the following formula holds:
* 0 <= i < variables.length
* ((ai^bi % 10)^ci) % mi == target

Return an array consisting of good indices in any order.

Example 1:
Input: variables = [[2,3,3,10],[3,3,3,1],[6,1,1,4]], target = 2
Output: [0,2]
Explanation: For each index i in the variables array:
1) For the index 0, variables[0] = [2,3,3,10], (23 % 10)3 % 10 = 2.
2) For the index 1, variables[1] = [3,3,3,1], (33 % 10)3 % 1 = 0.
3) For the index 2, variables[2] = [6,1,1,4], (61 % 10)1 % 4 = 2.
Therefore we return [0,2] as the answer.

Example 2:
Input: variables = [[39,3,1000,1000]], target = 17
Output: []
Explanation: For each index i in the variables array:
1) For the index 0, variables[0] = [39,3,1000,1000], (393 % 10)1000 % 1000 = 1.
Therefore we return [] as the answer.

Constraints:
1 <= variables.length <= 100
variables[i] == [ai, bi, ci, mi]
1 <= ai, bi, ci, mi <= 103
0 <= target <= 103
*/

fun main() {
}

fun getGoodIndices(variables: Array<IntArray>, target: Int): List<Int> {
	val ans: MutableList<Int> = ArrayList()
	for (i in variables.indices) {
		var a = variables[i][0]
		val b = variables[i][1]
		val c = variables[i][2]
		val m = variables[i][3]
		a %= 10

		var t = 1
		for (j in 0 until b) {
			t = (t * a) % 10
		}

		var x = 1
		for (j in 0 until c) {
			x = (x * t) % m
		}

		if (x == target)
			ans.add(i)
	}
	return ans
}

class Solution {
	fun getGoodIndices(variables: Array<IntArray>, target: Int): List<Int> {
		val result = mutableListOf<Int>()

		for (index in variables.indices) {
			if (
				(variables[index][0].toDouble().pow(variables[index][1]))
					.mod(10.0).pow(variables[index][2])
					.mod(variables[index][3].toDouble()).toInt() == target
			) {
				result.add(index)
			}
		}

		return result
	}
}

class Solution {
	fun getGoodIndices(variables: Array<IntArray>, target: Int): List<Int> {
		val res = mutableListOf<Int>()

		for (i in 0 until variables.size) {
			if (isGoodIndex(variables[i], target))
				res.add(i)
		}

		return res
	}

	private fun isGoodIndex(arr: IntArray, target: Int): Boolean {
		val f = power(arr[0], arr[1], 10)
		val s = power(f, arr[2], arr[3])
		return target == s
	}

	private fun power(f: Int, s: Int, m: Int): Int {
		var res = 1

		for (i in 0 until s) {
			res *= f
			res %= m
		}

		return res
	}
}

class Solution {
	fun getGoodIndices(variables: Array<IntArray>, target: Int): List<Int> =
		variables.foldIndexed(mutableListOf()) { i, r, v ->
			r.also { if (modPow(modPow(v[0], v[1], 10), v[2], v[3]) == target) r.add(i) }
		}

	private fun modPow(x: Int, y: Int, m: Int): Int {
		return if (y == 0) 1
		    else {
                val p = (modPow(x, y / 2, m) % m).let { (it * it) % m }
                if (y % 2 != 0) (p * x) % m else p
            }
    }
}