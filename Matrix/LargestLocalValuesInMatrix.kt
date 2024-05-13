/*
* Largest Local Values in a Matrix - https://leetcode.com/problems/largest-local-values-in-a-matrix/
You are given an n x n integer matrix grid.
Generate an integer matrix maxLocal of size (n - 2) x (n - 2) such that:
* maxLocal[i][j] is equal to the largest value of the 3 x 3 matrix in grid centered around row i + 1 and column j + 1.
In other words, we want to find the largest value in every contiguous 3 x 3 matrix in grid.
Return the generated matrix.

Example 1:
Input: grid = [[9,9,8,1],[5,6,2,6],[8,2,6,4],[6,2,2,2]]
Output: [[9,9],[8,6]]
Explanation: The diagram above shows the original matrix and the generated matrix.
Notice that each value in the generated matrix corresponds to the largest value of a contiguous 3 x 3 matrix in grid.

Example 2:
Input: grid = [[1,1,1,1,1],[1,1,1,1,1],[1,1,2,1,1],[1,1,1,1,1],[1,1,1,1,1]]
Output: [[2,2,2],[2,2,2],[2,2,2]]
Explanation: Notice that the 2 is contained within every contiguous 3 x 3 matrix in grid.

Constraints:
n == grid.length == grid[i].length
3 <= n <= 100
1 <= grid[i][j] <= 100
*/

fun main() {
	val result = largestLocal(
		arrayOf(
			intArrayOf(1, 1, 1, 1, 1),
			intArrayOf(1, 1, 2, 1, 1),
			intArrayOf(1, 2, 1, 2, 1),
			intArrayOf(1, 1, 2, 1, 1),
			intArrayOf(1, 1, 1, 1, 1),
		)
	)

	result.forEach { row ->
		row.forEach { element ->
			print("$element ")
		}
		println()
	}
}

fun largestLocal(grid: Array<IntArray>): Array<IntArray> {
	val n = grid.size
	val answer = Array(n - 2) { IntArray(grid.first().size - 2) }

	for (i in answer.indices) {
		for (j in answer.first().indices) {

			var max = Int.MIN_VALUE

			for (k in i until i + 3) {
				for (l in j until j + 3) {
					max = maxOf(max, grid[k][l])
				}
			}

			answer[i][j] = max
		}
	}

	return answer
}

class Solution {
	fun largestLocal(grid: Array<IntArray>): Array<IntArray> {

		val size = grid.size
		val result = Array(grid.size - 2) {
			IntArray(grid.size - 2) {
				0
			}
		}

		for (i in 0..size - 3) {
			for (j in 0..size - 3) {
				result[i][j] = findMaxInA3x3Matrix(grid, i, j)
			}
		}

		return result
	}

	private fun findMaxInA3x3Matrix(grid: Array<IntArray>, i: Int, j: Int): Int {
		var max = Int.MIN_VALUE
		for (k in i..i + 2) {
			for (l in j..j + 2) {
				max = maxOf(max, grid[k][l])
			}
		}
		return max
	}
}

class Solution {
	fun largestLocal(grid: Array<IntArray>): Array<IntArray> {
		val maxLocal = Array(grid.size - 2) { IntArray(grid.size - 2) }

		for (i in 1 until grid.lastIndex) {
			for (j in 1 until grid.lastIndex) {
				for (k in i - 1..i + 1) {
					for (l in j - 1..j + 1) {
						maxLocal[i - 1][j - 1] = maxOf(maxLocal[i - 1][j - 1], grid[k][l])
					}
				}
			}
		}

		return maxLocal
	}
}

class Solution {

	fun largestLocal(grid: Array<IntArray>): Array<IntArray> {
		val gridSize = grid.size - 2
		val offsets = (0..2).map { row ->
			(0..2).map { col -> Pair(row, col) }
		}.flatten()

		val result = Array(gridSize) { IntArray(gridSize) { 0 } }

		(0 until gridSize).forEach { row ->
			(0 until gridSize).forEach { col ->
				result[row][col] = offsets.maxOf { (rowOffset, colOffset) ->
					grid[row + rowOffset][col + colOffset]
				}
			}
		}
		return result
	}
}

class Solution {
	fun largestLocal(grid: Array<IntArray>): Array<IntArray> =
		(0..grid.size - 3).fold(Array(grid.size - 2) { IntArray(grid.size - 2) }) { r, i ->
			(0..grid.size - 3).forEach { j ->
				r[i][j] = maxOf(r[i][j], grid.slice(i..<i + 3).maxOf { r -> r.slice(j..<j + 3).max() })
			}
			r
		}
}

class Solution {
	fun largestLocal(grid: Array<IntArray>): Array<IntArray> {
		val result = Array(grid.size - 2) { IntArray(grid.size - 2) { 0 } }

		fun largestFromPos(y: Int, x: Int): Int {
			var max = grid[y][x]

			// top 3
			max = maxOf(max, grid[y - 1][x - 1])
			max = maxOf(max, grid[y - 1][x])
			max = maxOf(max, grid[y - 1][x + 1])

			// middle 3
			max = maxOf(max, grid[y][x - 1])
			max = maxOf(max, grid[y][x])
			max = maxOf(max, grid[y][x + 1])

			// bottom 3
			max = maxOf(max, grid[y + 1][x - 1])
			max = maxOf(max, grid[y + 1][x])
			max = maxOf(max, grid[y + 1][x + 1])

			return max
		}

		for (y in 1 until grid.size - 1) {
			for (x in 1 until grid.size - 1) {
				result[y - 1][x - 1] = largestFromPos(y, x)
			}
		}

		return result
	}
}

class Solution {
	fun largestLocal(grid: Array<IntArray>): Array<IntArray> {
		val matrixSize = grid.size - 2
		val length = 2
		val result = Array(matrixSize) { IntArray(matrixSize) { 0 } }

		for (i in 0 until matrixSize) {
			for (j in 0 until matrixSize) {
				result[i][j] = grid.slice(i..length + i)
					.map { it.slice(j..length + j) }
					.flatten().max()!!
			}
		}
		return result
	}
}