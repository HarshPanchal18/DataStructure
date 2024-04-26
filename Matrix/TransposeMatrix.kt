/*
* Transpose Matrix
Given a 2D integer array matrix, return the transpose of matrix.
The transpose of a matrix is the matrix flipped over its main diagonal, switching the matrix's row and column indices.

Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[1,4,7],[2,5,8],[3,6,9]]

Example 2:
Input: matrix = [[1,2,3],[4,5,6]]
Output: [[1,4],[2,5],[3,6]]


Constraints:
m == matrix.length
n == matrix[i].length
1 <= m, n <= 1000
1 <= m * n <= 10^5
-10^9 <= matrix[i][j] <= 10^9

*/

fun transposeMatrix(matrix: Array<IntArray>): Array<IntArray> {
    return Array(matrix.first().size) { j ->
        IntArray(matrix.size) { matrix[it][j] }
    }
}

fun transpose(matrix: Array<IntArray>): Array<IntArray> {
    val newMatrix = mutableListOf<IntArray>()

    for (i in matrix.first().indices) {
        val new = mutableListOf<Int>()
        for (j in matrix.indices)
            new.add(matrix[j][i])

        newMatrix.add(new.toIntArray())
    }

    return newMatrix.toTypedArray()
}

fun transpose(matrix: Array<IntArray>): Array<IntArray> {
    val ans: Array<IntArray> = Array(matrix[0].size) { IntArray(matrix.size) { 0 } }
    for (i: Int in matrix.indices) {
        for (j: Int in 0..<matrix[0].size) {
            ans[j][i] = matrix[i][j]
        }
    }

    return ans
}