import java.util.*
import kotlin.math.min

/*
* Minimum Falling Path Sum II - https://leetcode.com/problems/minimum-falling-path-sum-ii/ (Array | Dynamic Programming | Matrix)
Given an n x n integer matrix grid, return the minimum sum of a falling path with non-zero shifts.
A falling path with non-zero shifts is a choice of exactly one element from each row of grid such that no two elements chosen in adjacent rows are in the same column.

Example 1:
Input: grid = [
	[1,2,3],
	[4,5,6],
	[7,8,9]
]
Output: 13
Explanation:
The possible falling paths are:
[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
The falling path with the smallest sum is [1,5,7], so the answer is 13.

Example 2:
Input: grid = [[7]]
Output: 7

Constraints:
n == grid.length == grid[i].length
1 <= n <= 200
-99 <= grid[i][j] <= 99
*/

fun main() {
	println(minFallingPathSum(intArrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9))))
}

fun minFallingPathSum(grid: Array<IntArray>): Int {
	return minFallingPathSum(0, grid).minSum
}

fun minFallingPathSum(row: Int, grid: Array<IntArray>): Triplet {
	// Base case: if the current row is beyond the grid's bounds, return a Triplet with zero sum
	if (row == grid.size)
		return Triplet(0, 0, 0)

	// Recursively calculate the minimum falling path sum for the next row
	val nextRowTriplet = minFallingPathSum(row + 1, grid)

	// Initialize the current row's Triplet with maximum values
	var currentTriplet = Triplet(Int.MAX_VALUE, Int.MAX_VALUE, -1)

	// Iterate through each column in the current row
	for (col in grid.first().indices) {
		// Calculate the sum for choosing the current cell and the appropriate sum from the next row
		val sum = grid[row][col] +
				if (col != nextRowTriplet.minSumIndex)
					nextRowTriplet.minSum
				else
					nextRowTriplet.secondMinSum

		// Update the currentTriplet with the minimum and second minimum sums and their indices
		if (sum <= currentTriplet.minSum) {
			currentTriplet = currentTriplet.copy(secondMinSum = currentTriplet.minSum, minSum = sum, minSumIndex = col)
		} else if (sum < currentTriplet.secondMinSum) {
			currentTriplet = currentTriplet.copy(secondMinSum = sum)
		}
	}

	return currentTriplet
}

data class Triplet(
	var minSum: Int,
	var secondMinSum: Int,
	var minSumIndex: Int
)

class Solution {
	fun minFallingPathSum(grid: Array<IntArray>): Int {
		val n = grid.size
		val m = grid.first().size
		var res = Int.MAX_VALUE
		val dp = Array(n) { IntArray(m) }

		for (row in dp) {
			Arrays.fill(row, -1)
		}

		for (j in 0 until m) {
			dp[0][j] = grid[0][j]
		}

		for (i in 1 until n) {
			for (j in 0 until m) {
				var temp = Int.MAX_VALUE

				for (k in 0 until m) {
					if (j != k)
						temp = min(temp, (grid[i][j] + dp[i - 1][k]))
					dp[i][j] = temp
				}
			}
		}

		for (j in 0 until m) {
			res = min(res, dp[n - 1][j])
		}

		return res
	}
}

class Solution {
	fun minFallingPathSum(grid: Array<IntArray>): Int {
		val n = grid.size

		for (i in 1 until n) {
			for (j in 0 until n) {

				var best = Int.MAX_VALUE

				for (k in 0 until n)
					if (k != j)
						best = min(best, grid[i - 1][k])

				grid[i][j] += best
			}
		}

		var result = Int.MAX_VALUE
		for (i in 0 until n)
			result = min(result, grid[n - 1][i])

		return result
	}
}

class Solution {
	fun minFallingPathSum(grid: Array<IntArray>): Int {

		if (grid.size == 1)
			return grid[0][0]

		var minSums = IntArray(grid.first().size)
		var min = Integer.MAX_VALUE
		var minIndex = -1
		var nextMin = Integer.MAX_VALUE

		for (i in 0 until grid[0].size) {
			minSums[i] = grid[lastIndex][i]

			// keep the 2 smallest values in the row and their index
			if (grid[grid.lastIndex][i] < min) {
				nextMin = min
				min = grid[lastIndex][i]
				minIndex = i
			} else if (grid[lastIndex][i] < nextMin) {
				nextMin = grid[lastIndex][i]
			}
		}

		for (j in grid.size - 2 downTo 0) {
			val newMinSums = IntArray(grid[0].size)
			var newMin = Integer.MAX_VALUE
			var newMinIndex = -1
			var newNextMin = Integer.MAX_VALUE

			for (i in 0 until grid[0].size) {
				if (i == minIndex)
					newMinSums[i] = grid[j][i] + nextMin
				else
					newMinSums[i] = grid[j][i] + min


				// keep the 2 smallest values in the row and their index
				if (newMinSums[i] < newMin) {
					newNextMin = newMin
					newMin = newMinSums[i]
					newMinIndex = i
				} else if (newMinSums[i] < newNextMin) {
					newNextMin = newMinSums[i]
				}
			}
			minSums = newMinSums
			min = newMin
			minIndex = newMinIndex
			nextMin = newNextMin
		}

		return minSums.min()
	}
}