/*
* Find All Groups of Farmland - https://leetcode.com/problems/find-all-groups-of-farmland/
You are given a 0-indexed m x n binary matrix land where a 0 represents a hectare of forested land and a 1 represents a hectare of farmland.
To keep the land organized, there are designated rectangular areas of hectares that consist entirely of farmland. These rectangular areas are called groups. No two groups are adjacent, meaning farmland in one group is not four-directionally adjacent to another farmland in a different group.
land can be represented by a coordinate system where the top left corner of land is (0, 0) and the bottom right corner of land is (m-1, n-1). Find the coordinates of the top left and bottom right corner of each group of farmland. A group of farmland with a top left corner at (r1, c1) and a bottom right corner at (r2, c2) is represented by the 4-length array [r1, c1, r2, c2].
Return a 2D array containing the 4-length arrays described above for each group of farmland in land. If there are no groups of farmland, return an empty array. You may return the answer in any order.

Example 1:
Input: land = [[1,0,0],[0,1,1],[0,1,1]]
Output: [[0,0,0,0],[1,1,2,2]]
Explanation:
The first group has a top left corner at land[0][0] and a bottom right corner at land[0][0].
The second group has a top left corner at land[1][1] and a bottom right corner at land[2][2].

Example 2:
Input: land = [[1,1],[1,1]]
Output: [[0,0,1,1]]
Explanation:
The first group has a top left corner at land[0][0] and a bottom right corner at land[1][1].

Example 3:
Input: land = [[0]]
Output: []
Explanation:
There are no groups of farmland.

Constraints:
m == land.length
n == land[i].length
1 <= m, n <= 300
land consists of only 0's and 1's.
Groups of farmland are rectangular in shape.
*/

fun main() {
	val result = findFarmland(
		arrayOf(
			intArrayOf(1, 0, 0),
			intArrayOf(0, 1, 1),
			intArrayOf(0, 1, 1)
		)
	)

	result.forEach { print("$it ") }
}

// TC: O(M*N)
// SC: O(1)
fun findFarmland(land: Array<IntArray>): Array<IntArray> {

	/*
	Start travesing using 2 for-loops in top-left to bottom-right fashion.
	If you ever find a farmland, do the following:
	Use 2 loops:

	loop to go length wise(inc on row); break if we encounter forest land
	loop to go width wise(inc on col); break if we encounter forest land
	You've got your coordinates, just mark the cells visited so that we'll never visit them again.
	*/

	val farmLands = mutableListOf<IntArray>()

	for (i in land.indices) {
		for (j in land[0].indices) {
			// If you ever find a land
			if (land[i][j] == 1) {
				var i2 = i
				var j2 = j

				// go length wise
				for (k in i2 + 1..land.lastIndex) {
					if (land[k][j] == 1)
						i2++
					else
						break
				}

				// Go width wise
				for (k in j2 + 1..land.first().lastIndex) {
					if (land[i][k] == 1)
						j2++
					else
						break
				}

				// mark cells visited
				for (k in i..i2)
					for (l in j..j2)
						land[k][l] = 0

				// add to res
				farmLands.add(intArrayOf(i, j, i2, j2))
			}
		}
	}

	return farmLands.toTypedArray()
}

class Solution {
	fun findFarmland(land: Array<IntArray>): Array<IntArray> =
		land.indices.fold(mutableListOf<IntArray>()) { r, i ->
			var j = 0
			while (j < land[0].size) {
				if (land[i][j] == 1) {
					var (x, y) = i to j

					while (y < land[0].size && land[i][y] != 0)
						y++
					while (x < land.size && land[x][j] != 0)
						land[x++][j] = y - j + 1

					r.add(intArrayOf(i, j, x - 1, y - 1))
				}
				j += if (land[i][j] > 1) land[i][j] else 1
			}
			r
		}.toTypedArray()
}

class Solution {
	fun findFarmland(land: Array<IntArray>): Array<IntArray> {
		val maxRow = land.size
		val maxCol = land[0].size
		val result = mutableListOf<IntArray>()

		for (i in 0 until maxRow) {
			for (j in 0 until maxCol) {
				if (land[i][j] == 1) {
					land[i][j] = -1
					if (j > 0 && land[i][j - 1] == -1 || i > 0 && land[i - 1][j] == -1) {
						continue
					}
					val width = getWidth(land, i, j + 1)
					val height = getHeight(land, i + 1, j)
					result.add(intArrayOf(i, j, i + height, j + width))
				}
			}
		}

		return result.toTypedArray()
	}

	fun getWidth(land: Array<IntArray>, i: Int, j: Int): Int {
		if (j == land[0].size || land[i][j] == 0) return 0
		land[i][j] = -1
		return 1 + getWidth(land, i, j + 1)
	}

	fun getHeight(land: Array<IntArray>, i: Int, j: Int): Int {
		if (i == land.size || land[i][j] == 0) return 0
		land[i][j] = -1
		return 1 + getHeight(land, i + 1, j)
	}
}

class Solution {
	fun findFarmland(land: Array<IntArray>): Array<IntArray> {
		val rows = land.size
		val cols = land.first().size

		val ans = mutableListOf<IntArray>()

		for (row in 0..<rows) {
			for (col in 0..<cols) {

				if (land[row][col] == 1) {
					val arr = IntArray(4)
					arr[0] = row
					arr[1] = col

					var maxRow = row
					for (i in row..<rows) {
						if (land[i][col] == 1) maxRow = i
						else break
					}

					var maxCol = col
					for (j in col..<cols) {
						if (land[row][j] == 1) maxCol = j
						else break
					}

					arr[2] = maxRow
					arr[3] = maxCol

					ans += arr

					for (i in arr[0]..arr[2])
						for (j in arr[1]..arr[3])
							land[i][j] = 0

				}
			}
		}

		return ans.toTypedArray()
	}
}