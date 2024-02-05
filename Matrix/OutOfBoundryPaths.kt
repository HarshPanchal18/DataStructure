/*
* Out of Boundary Paths https://leetcode.com/problems/out-of-boundary-paths
There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn]. You are allowed to move the ball to one of the four adjacent cells in the grid (possibly out of the grid crossing the grid boundary). You can apply at most maxMove moves to the ball.
Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball out of the grid boundary. Since the answer can be very large, return it modulo 109 + 7.

Example 1:
Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
Output: 6

Example 2:
Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
Output: 12

Constraints:
1 <= m, n <= 50
0 <= maxMove <= 50
0 <= startRow < m
0 <= startColumn < n
*/

fun main() {
    var result = findPaths(2,2,2,0,0)
    println(result)
    result = findPaths(1,3,3,0,1)
    print(result)
}

fun findPaths(m: Int, n: Int, maxMoves: Int, x: Int, y: Int): Int {
    val M = 1_000_000_007
    var dp = Array(m) { IntArray(n) }
    dp[x][y] = 1
    var count = 0

    for (moves in 1..maxMoves) {
        val temp = Array(m) { IntArray(n) }

        for (row in 0..<m) {
            for (col in 0..<n) {
                if (row == m - 1) count = (count + dp[row][col]) % M
                if (col == n - 1) count = (count + dp[row][col]) % M
                if (row == 0) count = (count + dp[row][col]) % M
                if (col == 0) count = (count + dp[row][col]) % M
                temp[row][col] = (
                        ((if (row > 0) dp[row - 1][col] else 0) + (if (row < m - 1) dp[row + 1][col] else 0)) % M +
                        ((if (col > 0) dp[row][col - 1] else 0) + (if (col < n - 1) dp[row][col + 1] else 0)) % M
                        ) % M
            }
        }
        dp = temp
    }
    return count
}

fun findPaths(m: Int, n: Int, maxMove: Int, startRow: Int, startColumn: Int): Int {
    if (maxMove == 0) return 0
    var dpCurr = Array(m + 2) { IntArray(n + 2) }
    var dpLast = Array(m + 2) { IntArray(n + 2) }
    for (i in 1..m) {
        dpCurr[i][1]++
        dpCurr[i][n]++
    }
    for (j in 1..n) {
        dpCurr[1][j]++
        dpCurr[m][j]++
    }
    var ans = dpCurr[startRow + 1][startColumn + 1]
    for (d in 1 until maxMove) {
        val temp = dpCurr
        dpCurr = dpLast
        dpLast = temp
        for (i in 1..m) for (j in 1..n) dpCurr[i][j] =
            ((dpLast[i - 1][j].toLong() + dpLast[i + 1][j] + dpLast[i][j - 1] + dpLast[i][j + 1]) % 1000000007L).toInt()
        ans = (ans + dpCurr[startRow + 1][startColumn + 1]) % 1000000007
    }
    return ans
}