/*
* Number of Islands
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
*/

fun main() {
	println(
		numIslands(
			arrayOf(
				charArrayOf('1', '1', '1', '1', '0'),
				charArrayOf('1', '1', '0', '1', '0'),
				charArrayOf('1', '1', '0', '0', '0'),
				charArrayOf('0', '0', '0', '0', '0')
			)
		)
	)
}

fun numIslands(grid: Array<CharArray>): Int {
	var count = 0
	val row = grid.size
	val col = grid.first().size

	for (i in 0 until row) {
		for (j in 0 until col) {
			if (grid[i][j] == '1') {
				count++
				dfs(grid, i, j)
			}
		}
	}

	return count
}

fun dfs(grid: Array<CharArray>, row: Int, col: Int) {
	if (row < 0 || col < 0 || row == grid.size || col == grid.first().size)
		return

	if (grid[row][col] == '0')
		return

	if (grid[row][col] == '1')
		grid[row][col] = '0'

	dfs(grid, row, col - 1) // Left direction
	dfs(grid, row, col + 1) // Right direction
	dfs(grid, row - 1, col) // Top direction
	dfs(grid, row + 1, col) // Bottom direction

}

class Solution {
	fun numIslands(grid: Array<CharArray>): Int {
		var result = 0
		for (x in grid.indices)
			for (y in grid[x].indices)
				result += dfs(grid, x, y)

		return result
	}

	fun dfs(grid: Array<CharArray>, x: Int, y: Int): Int {
		if (!isCoordsValid(grid, x, y) || grid[x][y] == '0')
			return 0

		grid[x][y] = '0'

		dfs(grid, x + 1, y)
		dfs(grid, x, y + 1)
		dfs(grid, x - 1, y)
		dfs(grid, x, y - 1)

		return 1
	}

	fun isCoordsValid(grid: Array<CharArray>, x: Int, y: Int) =
		x >= 0 && y >= 0 && x < grid.size && y < grid.first().size
}

class Solution {
	fun numIslands(grid: Array<CharArray>): Int {
		val visitedNodes = ArrayList<Pair<Int, Int>>()
		var numIslands = 0
		grid.indices.forEach { r ->
			grid[r].indices.forEach { c ->
				if (grid[r][c] == '1') {
					numIslands++
					grid[r][c] = 'x'
					performBfs(r, c, grid)
				}
			}
		}
		return numIslands
	}

	fun performBfs(r: Int, c: Int, grid: Array<CharArray>) {
		val rowMax = grid.size
		val columnMax = grid.first().size

		if (c + 1 < columnMax && grid[r][c + 1] == '1') {
			grid[r][c + 1] = 'x'
			performBfs(r, c + 1, grid)
		}

		if (r + 1 < rowMax && grid[r + 1][c] == '1') {
			grid[r + 1][c] = 'x'
			performBfs(r + 1, c, grid)
		}

		if (r - 1 >= 0 && grid[r - 1][c] == '1') {
			grid[r - 1][c] = 'x'
			performBfs(r - 1, c, grid)
		}

		if (c - 1 >= 0 && grid[r][c - 1] == '1') {
			grid[r][c - 1] = 'x'
			performBfs(r, c - 1, grid)
		}

	}
}

class Solution {

	private var p = IntArray(0)

	fun numIslands(grid: Array<CharArray>): Int {
		p = IntArray(grid.size * grid.first().size) { it }
		var numIslands = 0
		val rowLen = grid.first().size

		for (i in grid.indices) {
			for (j in grid[i].indices) {

				val arrayIndex = arrayIndex(i, j, rowLen)

				if (grid[i][j] == '0') {
					p[arrayIndex] = -1
				} else {
					if (j != grid[i].lastIndex && (grid[i][j] == grid[i][j + 1])) {
						union(arrayIndex, arrayIndex + 1)
					}
					if (i != grid.lastIndex && grid[i][j] == grid[i + 1][j]) {
						union(arrayIndex, arrayIndex + rowLen)
					}
				}
			}
		}

		p.forEachIndexed { i, value -> if (i == value) numIslands++ }
		return numIslands
	}

	private fun arrayIndex(i: Int, j: Int, k: Int) = (i * k) + j

	private fun union(i: Int, j: Int) {
		p[find(j)] = find(i)
	}

	private fun find(i: Int): Int {
		if (p[i] == i)
			return i
		return find(p[i])
	}
}

class Solution {

	var visited = mutableSetOf<Point>()

	fun numIslands(grid: Array<CharArray>): Int {
		var currentIslandNumber = 0

		for (currentRow in 0..grid.lastIndex) {
			for (currentColumn in 0..grid.first().lastIndex) {
				if (grid[currentRow][currentColumn] == '0')
					continue

				val currentPoint = Point(currentRow, currentColumn)
				if (!visited.contains(currentPoint)) {
					visitNeighbors(currentPoint, grid)
					currentIslandNumber++
				}
			}
		}

		return currentIslandNumber
	}

	fun visitNeighbors(currentPoint: Point, grid: Array<CharArray>) {
		getNeighbors(currentPoint, grid)
			.forEach {
				if (isRelevant(it, grid)) {
					visited.add(it)
					visitNeighbors(it, grid)
				}
			}
	}

	fun getNeighbors(currentPoint: Point, grid: Array<CharArray>): List<Point> {
		return listOf(
			Point(currentPoint.x - 1, currentPoint.y),
			Point(currentPoint.x + 1, currentPoint.y),
			Point(currentPoint.x, currentPoint.y - 1),
			Point(currentPoint.x, currentPoint.y + 1),
		)
	}

	fun isRelevant(currentPoint: Point, grid: Array<CharArray>) =
		!visited.contains(currentPoint)
				&& currentPoint.isValid(grid.size, grid.first().size)
				&& grid[currentPoint.x][currentPoint.y] == '1'

	data class Point(
		val x: Int,
		val y: Int
	) {
		fun isValid(maxX: Int, maxY: Int): Boolean = x >= 0 && x < maxX && y >= 0 && y < maxY
	}
}