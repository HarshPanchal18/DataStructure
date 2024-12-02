/*
* Maximum Number of Moves in a Grid

You are given a 0-indexed m x n matrix grid consisting of positive integers.
You can start at any cell in the first column of the matrix, and traverse the grid in the following way:
From a cell (row, col), you can move to any of the cells: (row - 1, col + 1), (row, col + 1) and (row + 1, col + 1) such that the value of the cell you move to, should be strictly bigger than the value of the current cell.
Return the maximum number of moves that you can perform.

Example 1:
Input: grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
Output: 3
Explanation: We can start at the cell (0, 0) and make the following moves:
- (0, 0) -> (0, 1).
- (0, 1) -> (1, 2).
- (1, 2) -> (2, 3).
It can be shown that it is the maximum number of moves that can be made.

Example 2:
Input: grid = [[3,2,4],[2,1,9],[1,1,7]]
Output: 0
Explanation: Starting from any cell in the first column we cannot perform any moves.

Constraints:
m == grid.length
n == grid[i].length
2 <= m, n <= 1000
4 <= m * n <= 105
1 <= grid[i][j] <= 106
*/

fun main() {
    println(
        maxMoves(
            arrayOf(
                intArrayOf(2, 4, 3, 5),
                intArrayOf(5, 4, 9, 3),
                intArrayOf(3, 4, 2, 11),
                intArrayOf(10, 9, 13, 15),
            )
        )
    )
}

fun maxMoves(grid: Array<IntArray>): Int {
    val row = grid.size
    val col = grid[0].size
    val dp = Array(row) { BooleanArray(col) }

    for (i in 0..<row)
        dp[i][0] = true

    for (j in 1..<col) {
        for (i in 0..<row) {
            dp[i][j] = (i - 1 >= 0 && grid[i][j] > grid[i - 1][j - 1] && dp[i - 1][j - 1]) ||
                                            (grid[i][j] > grid[i][j - 1] && dp[i][j - 1]) ||
                        (i + 1 < row && grid[i][j] > grid[i + 1][j - 1] && dp[i + 1][j - 1])
        }
    }

    for (j in col - 1 downTo 0)
        for (i in 0..<row)
            if (dp[i][j])
                return j

    return 0
}

class Solution {
    fun maxMoves(grid: Array<IntArray>): Int {
        var max = 0
        val couRow = grid.size
        val couCol = grid[0].size
        var arr1 = IntArray(couRow)

        for (i in 1 until couCol - 1) {
            val arr2 = IntArray(couRow)
            if ((grid[0][i - 1] < grid[0][i] && arr1[0] == i - 1) ||
                (grid[1][i - 1] < grid[0][i] && arr1[1] == i - 1)
            ) {
                max = i
                arr2[0] = i
            }

            for (j in 1 until couRow - 1) {
                if ((grid[j - 1][i - 1] < grid[j][i] && arr1[j - 1] == i - 1) ||
                    (grid[j][i - 1] < grid[j][i] && arr1[j] == i - 1) ||
                    (grid[j + 1][i - 1] < grid[j][i] && arr1[j + 1] == i - 1)
                ) {
                    max = i
                    arr2[j] = i
                }
            }

            if ((grid[couRow - 2][i - 1] < grid[couRow - 1][i] && arr1[couRow - 2] == i - 1) ||
                (grid[couRow - 1][i - 1] < grid[couRow - 1][i] && arr1[couRow - 1] == i - 1)
            ) {
                max = i
                arr2[couRow - 1] = i
            }
            if (max != i) return max
            arr1 = arr2
        }

        if ((grid[0][couCol - 2] < grid[0][couCol - 1] && arr1[0] == couCol - 2) ||
            (grid[1][couCol - 2] < grid[0][couCol - 1] && arr1[1] == couCol - 2)
        ) {
            return couCol - 1
        }

        for (j in 1 until couRow - 1) {
            if ((grid[j - 1][couCol - 2] < grid[j][couCol - 1] && arr1[j - 1] == couCol - 2) ||
                (grid[j][couCol - 2] < grid[j][couCol - 1] && arr1[j] == couCol - 2) ||
                (grid[j + 1][couCol - 2] < grid[j][couCol - 1] && arr1[j + 1] == couCol - 2)
            ) {
                return couCol - 1
            }
        }

        if ((grid[couRow - 2][couCol - 2] < grid[couRow - 1][couCol - 1] && arr1[couRow - 2] == couCol - 2) ||
            (grid[couRow - 1][couCol - 2] < grid[couRow - 1][couCol - 1] && arr1[couRow - 1] == couCol - 2)
        ) {
            return couCol - 1
        }

        return max
    }
}

class Solution {
    private fun getMaxMoves(i: Int, j: Int, grid: Array<IntArray>, dp: Array<IntArray>, m: Int, n: Int): Int {
        if (j == n - 1) return 0
        if (dp[i][j] == -1) {
            //north-east
            var ne = 0
            if (i > 0 && grid[i - 1][j + 1] > grid[i][j]) ne = 1 + getMaxMoves(i - 1, j + 1, grid, dp, m, n)

            //east
            var e = 0
            if (grid[i][j + 1] > grid[i][j]) e = 1 + getMaxMoves(i, j + 1, grid, dp, m, n)

            //south-east
            var se = 0
            if (i < m - 1 && grid[i + 1][j + 1] > grid[i][j]) se = 1 + getMaxMoves(i + 1, j + 1, grid, dp, m, n)

            dp[i][j] = maxOf(ne, e, se)
        }
        return dp[i][j]
    }

    fun maxMoves(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        val dp = Array(m) { IntArray(n) { -1 } }
        var result = Int.MIN_VALUE

        for (i in 0 until m) {
            result = maxOf(result, getMaxMoves(i, 0, grid, dp, m, n))
        }

        return result
    }

    fun maxMoves(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        val dp = Array(m) { IntArray(n) { 0 } }

        for (j in n - 2 downTo 0) {
            for (i in 0 until m) {
                //north-east
                var ne = 0
                if (i > 0 && grid[i - 1][j + 1] > grid[i][j]) ne = 1 + dp[i - 1][j + 1]
                //east
                var e = 0
                if (grid[i][j + 1] > grid[i][j]) e = 1 + dp[i][j + 1]
                //south-east
                var se = 0
                if (i < m - 1 && grid[i + 1][j + 1] > grid[i][j]) se = 1 + dp[i + 1][j + 1]

                dp[i][j] = maxOf(ne, e, se)
            }
        }
        var result = Int.MIN_VALUE

        for (i in 0 until m) {
            result = maxOf(result, dp[i][0])
        }
        return result

    }

    fun maxMoves(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        var prev = MutableList(m) { 0 };
        var curr = MutableList(m) { 0 }
        for (j in n - 2 downTo 0) {
            for (i in 0 until m) {
                //north-east
                var ne = 0
                if (i > 0 && grid[i - 1][j + 1] > grid[i][j]) ne = 1 + prev[i - 1]
                //east
                var e = 0
                if (grid[i][j + 1] > grid[i][j]) e = 1 + prev[i]
                //south-east
                var se = 0
                if (i < m - 1 && grid[i + 1][j + 1] > grid[i][j]) se = 1 + prev[i + 1]

                curr[i] = maxOf(ne, e, se)
            }
            prev = curr.toMutableList()
        }
        var result = Int.MIN_VALUE
        for (i in 0 until m) {
            result = maxOf(result, prev[i])
        }
        return result
    }

}

class Solution {
    fun maxMoves(grid: Array<IntArray>): Int {
        val n = grid.size
        val m = grid[0].size
        val memo = Array(n) { IntArray(m) { -1 } }  // Use -1 to indicate unvisited cells
        var response = 0
        for (i in 0 until n) {
            response = maxOf(response, dfsMaxMoves(grid, memo, i, 0))
        }
        return response
    }

    fun dfsMaxMoves(matrix: Array<IntArray>, memo: Array<IntArray>, x: Int, y: Int): Int {
        // Check bounds
        if (x < 0 || x >= matrix.size || y < 0 || y >= matrix[0].size) return 0

        // Check memoization
        if (memo[x][y] != -1) return memo[x][y]

        // Calculate the maximum moves from current position
        var maxSteps = 0

        if (x - 1 >= 0 && y + 1 < matrix[0].size && matrix[x - 1][y + 1] > matrix[x][y])
            maxSteps = maxOf(maxSteps, dfsMaxMoves(matrix, memo, x - 1, y + 1) + 1)

        if (y + 1 < matrix[0].size && matrix[x][y + 1] > matrix[x][y])
            maxSteps = maxOf(maxSteps, dfsMaxMoves(matrix, memo, x, y + 1) + 1)

        if (x + 1 < matrix.size && y + 1 < matrix[0].size && matrix[x + 1][y + 1] > matrix[x][y])
            maxSteps = maxOf(maxSteps, dfsMaxMoves(matrix, memo, x + 1, y + 1) + 1)

        memo[x][y] = maxSteps
        return maxSteps
    }
}