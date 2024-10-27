/*
* Count Square Submatrices With All Ones

Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.

Example 1:
Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation:
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.

Example 2:
Input: matrix =
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
Explanation:
There are 6 squares of side 1.
There is 1 square of side 2.
Total number of squares = 6 + 1 = 7.

Constraints:
1 <= arr.length <= 300
1 <= arr[0].length <= 300
0 <= arr[i][j] <= 1
*/

fun main(args: Array<String>) {
    println(countSquares(arrayOf(intArrayOf(0, 1, 1, 1), intArrayOf(1, 1, 1, 1), intArrayOf(0, 1, 1, 1))))
}

fun countSquares(matrix: Array<IntArray>): Int {
    var count = 0

    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            if (matrix[i][j] == 1 && i > 0 && j > 0) { // check if the element is 1 and if it is not the first element in the row and column
                matrix[i][j] = minOf(matrix[i - 1][j - 1], matrix[i - 1][j], matrix[i][j - 1]) + 1 // update the value of the element from the previous row and column
            }
            count += matrix[i][j]
        }
    }

    return count
}

class Solution {
    fun countSquares(matrix: Array<IntArray>): Int {
        var res = 0

        val m = matrix.size
        val n = matrix[0].size
        val dp = Array(m){ IntArray(n) }

        for(i in 0 until m) {
            for(j in 0 until n) {
                if(i==0 || j==0) {
                    dp[i][j]= matrix[i][j]
                } else {
                    if(matrix[i][j]==1) {
                        dp[i][j] = 1+ minOf(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])
                    } else {
                        dp[i][j]=0
                    }
                }
            }
        }

        for(i in 0 until m) {
            for(j in 0 until n) {
                res += dp[i][j]
            }
        }

        return res
    }
}

// Brute force O(m*n*k)
class Solution {
    fun countSquares(matrix: Array<IntArray>): Int {
        val (m, n) = Pair(matrix.size, matrix[0].size)
        var (result, min) = Pair(0, minOf(m, n)-1)

        for (k in 0..min) {
            for (i in 0..m-1-k) {
                for (j in 0..n-1-k) {
                    if (isValid(matrix, m, n, i, j, k))
                        result++
                }
            }
        }

        return result
    }

    private fun isValid(mat: Array<IntArray>, m: Int, n: Int, i: Int, j: Int, s: Int): Boolean {

        for (x in i..i+s) {
            for (y in j..j+s) {
                if (mat[x][y] == 0)
                    return false
            }
        }

        return true
    }
}

class Solution {
    fun countSquares(matrix: Array<IntArray>): Int {
        var totalSquares = 0

        matrix.forEachIndexed { outerIndex, thisIntArray ->
            thisIntArray.forEachIndexed { innerIndex, thisValue ->
                if (thisValue == 1 && outerIndex > 0 && innerIndex > 0) {
					val leftVal = matrix[outerIndex - 1][innerIndex]
					val topLeftVal = matrix[outerIndex-1][innerIndex - 1]
					val topVal = matrix[outerIndex][innerIndex - 1]
					val minVal = minOf(leftVal, topLeftVal, topVal)
                    matrix[outerIndex][innerIndex] += minVal
                }

                totalSquares += matrix[outerIndex][innerIndex]
            }
        }

        return totalSquares
    }
}