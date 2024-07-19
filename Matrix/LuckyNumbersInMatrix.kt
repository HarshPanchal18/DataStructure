import java.util.LinkedList

/*
* Lucky Numbers in a Matrix
Given an m x n matrix of distinct numbers, return all lucky numbers in the matrix in any order.
A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.

Example 1:
Input: matrix = [[3,7,8],[9,11,13],[15,16,17]]
Output: [15]
Explanation: 15 is the only lucky number since it is the minimum in its row and the maximum in its column.

Example 2:
Input: matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
Output: [12]
Explanation: 12 is the only lucky number since it is the minimum in its row and the maximum in its column.

Example 3:
Input: matrix = [[7,8],[1,2]]
Output: [7]
Explanation: 7 is the only lucky number since it is the minimum in its row and the maximum in its column.

Constraints:
m == mat.length
n == mat[i].length
1 <= n, m <= 50
1 <= matrix[i][j] <= 105.
All elements in the matrix are distinct.
*/

fun main() {
    println(
        luckyNumbers(
            arrayOf(
                intArrayOf(3, 7, 8),
                intArrayOf(9, 11, 13),
                intArrayOf(15, 16, 17)
            )
        )
    )
}

fun luckyNumbers(matrix: Array<IntArray>): List<Int> {
    return (matrix[0].indices).map { x ->
        matrix.maxOf { it[x] }
    }.toSet().let { maxes ->
        matrix.map { it.min() }
            .filter { it in maxes }
    }
}

class Solution {
    fun luckyNumbers(matrix: Array<IntArray>): List<Int> {
        result.clear()

        for (element in matrix) {
            val (min, x) = minX(element)
            if (isMax(matrix, min, x)) {
                result.add(min)
            }
        }

        return result
    }

    private fun isMax(matrix: Array<IntArray>, max: Int, x: Int): Boolean {
        for (element in matrix)
            if (element[x] > max)
                return false

        return true
    }

    private fun minX(line: IntArray): Pair<Int, Int> {
        var min = line[0]
        var minX = 0
        for (x in line.indices) {
            if (min > line[x]) {
                min = line[x]
                minX = x
            }
        }
        return min to minX
    }

    companion object {
        val result = LinkedList<Int>()
    }
}

class Solution {

    fun luckyNumbers(matrix: Array<IntArray>): List<Int> {
        val minRow = IntArray(matrix.size) { Int.MAX_VALUE }
        val maxCol = IntArray(matrix[0].size)

        for (i in 0 until matrix.size) {
            val line = matrix[i]
            for (j in 0 until line.size) {
                minRow[i] = minOf(minRow[i], line[j])
                maxCol[j] = maxOf(maxCol[j], line[j])
            }
        }

        val res = LinkedList<Int>()
        for (i in matrix.indices) {
            val line = matrix[i]
            for (j in line.indices) {
                if (line[j] == minRow[i] && line[j] == maxCol[j])
                    res.add(line[j])
            }
        }
        return res
    }
}

class Solution {
    fun luckyNumbers(matrix: Array<IntArray>): List<Int> {
        val (m, n) = arrayOf(matrix.size, matrix.first().size)
        val rows = matrix.map { it.min() }
        val cols = (0 until n)
            .map { col ->
                (0 until m).map { matrix[it][col] }.max()
            }

        return (0 until m).flatMap { i ->
            (0 until n).flatMap { j ->
                val cur = matrix[i][j]
                if (cur == rows[i] && cur == cols[j])
                    listOf(cur)
                else listOf()
            }
        }.toList()
    }
}

class Solution {
    fun luckyNumbers(matrix: Array<IntArray>): List<Int> {
        val colMaxes = IntArray(matrix[0].size) { Integer.MIN_VALUE }
        val rowMinSet = mutableSetOf<Int>()

        for (j in matrix.indices) {
            var rowMin = Integer.MAX_VALUE

            for (i in matrix[j].indices) {
                colMaxes[i] = maxOf(colMaxes[i], matrix[j][i])
                rowMin = minOf(rowMin, matrix[j][i])
            }

            rowMinSet.add(rowMin)
        }

        // return colMaxes.filter { it in rowMinSet }
        colMaxes.forEach {
            if (it in rowMinSet)
                return listOf(it)
        }

        return emptyList()
    }
}

class Solution {

    fun luckyNumbers(matrix: Array<IntArray>): List<Int> {

        val n = matrix.size
        val m = matrix[0].size

        val row = mutableListOf<Int>()
        for (i in 0..<n) {
            var min = Int.MAX_VALUE
            for (j in 0..<m)
                min = minOf(min, matrix[i][j])
            row.add(min)
        }

        val col = mutableListOf<Int>()
        for (i in 0..<m) {
            var max = Int.MIN_VALUE
            for (j in 0..<n)
                max = maxOf(max, matrix[j][i])
            col.add(max)
        }

        val res = mutableListOf<Int>()
        for (i in 0..<n) {
            for (j in 0..<m) {
                if (matrix[i][j] == row[i] && matrix[i][j] == col[j])
                    res.add(matrix[i][j])
            }
        }

        return res
    }
}

class Solution {
    fun luckyNumbers(matrix: Array<IntArray>): List<Int> {
        val couStr = matrix.size
        val couStol = matrix[0].size
        val maxStol = IntArray(couStol) { Int.MIN_VALUE }
        var minMax = Int.MIN_VALUE

        for (i in 0 until couStr) {
            var minStr = Int.MAX_VALUE
            for (j in 0 until couStol) {
                minStr = minOf(minStr, matrix[i][j])
                maxStol[j] = maxOf(maxStol[j], matrix[i][j])
            }
            minMax = maxOf(minMax, minStr)
        }

        // println("min_str=${min_str.toList()}")
        // println("max_stol=${max_stol.toList()}")

        var maxMin = maxStol[0]
        for (i in 1 until couStol)
            maxMin = minOf(maxMin, maxStol[i])

        return if (minMax != maxMin) mutableListOf() else mutableListOf(minMax)
    }
}

class Solution {
    fun luckyNumbers(matrix: Array<IntArray>): List<Int> {
        val minRowValues = mutableListOf<Pair<Int, Int>>()
        val maxColValues = mutableMapOf<Int, Int>()

        // Step 1: Find minimum values in each row and their column indices
        for (i in matrix.indices) {
            var minVal = Int.MAX_VALUE
            var minColIdx = -1
            for (j in matrix[i].indices) {
                if (matrix[i][j] < minVal) {
                    minVal = matrix[i][j]
                    minColIdx = j
                }
            }
            minRowValues.add(Pair(minVal, minColIdx))
        }

        // Step 2: Find maximum values in each column
        for (j in matrix[0].indices) {
            var maxVal = Int.MIN_VALUE
            for (i in matrix.indices) {
                if (matrix[i][j] > maxVal)
                    maxVal = matrix[i][j]
            }
            maxColValues[j] = maxVal
        }

        // Step 3: Find lucky numbers by comparing minRowValues and maxColValues
        val luckyNumbers = mutableListOf<Int>()
        for ((value, colIdx) in minRowValues) {
            if (maxColValues[colIdx] == value)
                luckyNumbers.add(value)
        }

        return luckyNumbers
    }
}