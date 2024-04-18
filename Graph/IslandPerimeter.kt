/*
* Island Perimeter
You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.
Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example 1:
Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
Output: 16
Explanation: The perimeter is the 16 yellow stripes in the image above.

Example 2:
Input: grid = [[1]]
Output: 4

Example 3:
Input: grid = [[1,0]]
Output: 4

Constraints:
row == grid.length
col == grid[i].length
1 <= row, col <= 100
grid[i][j] is 0 or 1.
There is exactly one island in grid.
*/

fun main() {
	print(
		islandPerimeter(
			arrayOf(
				intArrayOf(0, 1, 0, 0),
				intArrayOf(1, 1, 1, 0),
				intArrayOf(0, 1, 0, 0),
				intArrayOf(1, 1, 0, 0)
			)
		)
	)
}

fun islandPerimeter(grid: Array<IntArray>): Int {
	var perimeter = 0
	val rows = grid.size
	val cols = grid.first().size

	for (r in 0 until rows) {
		for (c in 0 until cols) {
			if (grid[r][c] == 1) {
				perimeter += 4

				if (r > 0 && grid[r - 1][c] == 1)
					perimeter -= 2

				if (c > 0 && grid[r][c - 1] == 1)
					perimeter -= 2

			}
		}
	}

	return perimeter
}

class Solution {
	fun islandPerimeter(grid: Array<IntArray>): Int {
		var perimeter = 0
		val visited = Array(grid.size) { BooleanArray(grid.first().size) }

		fun dfs(grid: Array<IntArray>, row: Int, col: Int) {
			if (row < 0 || row > grid.lastIndex || col < 0 || col > grid.first().lastIndex) {
				perimeter++
				return
			}

			if (grid[row][col] == 0) {
				perimeter++
				return
			}

			if (visited[row][col])
				return

			visited[row][col] = true

			dfs(grid, row + 1, col)
			dfs(grid, row - 1, col)
			dfs(grid, row, col + 1)
			dfs(grid, row, col - 1)
		}

		for (row in grid.indices) {
			for (col in grid[0].indices) {
				if (grid[row][col] == 1) {
					dfs(grid, row, col)
					break
				}
			}
		}

		return perimeter
	}
}

class Solution {
	val directions = listOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))

	fun islandPerimeter(grid: Array<IntArray>): Int {
		for (i in grid.indices) {
			for (j in grid.first().indices) {
				if (grid[i][j] == 1)
					return getPerimeter(grid, i, j)
			}
		}

		return 0
	}

	private fun getPerimeter(grid: Array<IntArray>, i: Int, j: Int): Int {
		if (i < 0 || j < 0 || i > grid.size - 1 || j > grid[0].size - 1)
			return 1

		if (grid[i][j] != 1)
			return grid[i][j] + 1

		grid[i][j] = -1
		var edges = 0

		for (direction in directions) {
			edges += getPerimeter(grid, i + direction.first, j + direction.second)
		}

		return edges
	}
}

class Solution {
	fun islandPerimeter(grid: Array<IntArray>): Int {
		val visitedSet = mutableSetOf<Pair<Int, Int>>()
		fun dfs(i: Int, j: Int): Int {
			if (i < 0 || j < 0 || i >= grid.size || j >= grid[0].size || grid[i][j] == 0)
				return 1

			if (visitedSet.contains(i to j))
				return 0

			visitedSet.add(i to j)

			return dfs(i, j - 1) + dfs(i - 1, j) + dfs(i, j + 1) + dfs(i + 1, j)
		}

		for (i in 0 until grid.size)
			for (j in 0 until grid[0].size)
				if (grid[i][j] == 1 && visitedSet.contains(i to j).not())
					return dfs(i, j)

		return 0
	}
}

class Solution {
	fun islandPerimeter(grid: Array<IntArray>): Int {
		var ans = 0
		val m = grid.size
		val n = grid.first().size

		for (r in grid.indices) {
			for (c in grid[r].indices) {
				if (grid[r][c] == 0)
					continue
				if (r == 0 || grid[r - 1][c] == 0)
					ans++
				if (r == m - 1 || grid[r + 1][c] == 0)
					ans++
				if (c == 0 || grid[r][c - 1] == 0)
					ans++
				if (c == n - 1 || grid[r][c + 1] == 0)
					ans++
			}
		}

		return ans
	}
}

class Solution {
	fun islandPerimeter(grid: Array<IntArray>): Int {
		var res = 0

		for (i in 0..grid.lastIndex) {
			for (j in 0..grid.first().lastIndex) {
				val isIsland = grid[i][j] == 1
				if (isIsland)
					res += countPerimeter(grid, i, j)
			}
		}

		return res
	}

	fun countPerimeter(grid: Array<IntArray>, i: Int, j: Int): Int {
		val top = if (isOutOfBounds(i - 1, j, grid.size, grid[0].size) || grid[i - 1][j] == 0) 1 else 0
		val bottom = if (isOutOfBounds(i + 1, j, grid.size, grid[0].size) || grid[i + 1][j] == 0) 1 else 0
		val left = if (isOutOfBounds(i, j - 1, grid.size, grid[0].size) || grid[i][j - 1] == 0) 1 else 0
		val right = if (isOutOfBounds(i, j + 1, grid.size, grid[0].size) || grid[i][j + 1] == 0) 1 else 0

		return top + bottom + left + right
	}

	fun isOutOfBounds(i: Int, j: Int, gridSizeI: Int, gridSizeJ: Int): Boolean {
		return i < 0 || i >= gridSizeI || j < 0 || j >= gridSizeJ
	}
}