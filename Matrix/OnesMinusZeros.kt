/*
* Difference Between Ones and Zeros in Row and Column (https://leetcode.com/problems/difference-between-ones-and-zeros-in-row-and-column/)
You are given a 0-indexed m x n binary matrix grid.

A 0-indexed m x n difference matrix diff is created with the following procedure:
Let the number of ones in the ith row be onesRowi.
Let the number of ones in the jth column be onesColj.
Let the number of zeros in the ith row be zerosRowi.
Let the number of zeros in the jth column be zerosColj.
diff[i][j] = onesRowi + onesColj - zerosRowi - zerosColj

Return the difference matrix diff.

Example 1:
Input: grid = [[0,1,1],[1,0,1],[0,0,1]]
Output: [[0,0,4],[0,0,4],[-2,-2,2]]
Explanation:
- diff[0][0] = onesRow0 + onesCol0 - zerosRow0 - zerosCol0 = 2 + 1 - 1 - 2 = 0
- diff[0][1] = onesRow0 + onesCol1 - zerosRow0 - zerosCol1 = 2 + 1 - 1 - 2 = 0
- diff[0][2] = onesRow0 + onesCol2 - zerosRow0 - zerosCol2 = 2 + 3 - 1 - 0 = 4
- diff[1][0] = onesRow1 + onesCol0 - zerosRow1 - zerosCol0 = 2 + 1 - 1 - 2 = 0
- diff[1][1] = onesRow1 + onesCol1 - zerosRow1 - zerosCol1 = 2 + 1 - 1 - 2 = 0
- diff[1][2] = onesRow1 + onesCol2 - zerosRow1 - zerosCol2 = 2 + 3 - 1 - 0 = 4
- diff[2][0] = onesRow2 + onesCol0 - zerosRow2 - zerosCol0 = 1 + 1 - 2 - 2 = -2
- diff[2][1] = onesRow2 + onesCol1 - zerosRow2 - zerosCol1 = 1 + 1 - 2 - 2 = -2
- diff[2][2] = onesRow2 + onesCol2 - zerosRow2 - zerosCol2 = 1 + 3 - 2 - 0 = 2

Example 2:
Input: grid = [[1,1,1],[1,1,1]]
Output: [[5,5,5],[5,5,5]]
Explanation:
- diff[0][0] = onesRow0 + onesCol0 - zerosRow0 - zerosCol0 = 3 + 2 - 0 - 0 = 5
- diff[0][1] = onesRow0 + onesCol1 - zerosRow0 - zerosCol1 = 3 + 2 - 0 - 0 = 5
- diff[0][2] = onesRow0 + onesCol2 - zerosRow0 - zerosCol2 = 3 + 2 - 0 - 0 = 5
- diff[1][0] = onesRow1 + onesCol0 - zerosRow1 - zerosCol0 = 3 + 2 - 0 - 0 = 5
- diff[1][1] = onesRow1 + onesCol1 - zerosRow1 - zerosCol1 = 3 + 2 - 0 - 0 = 5
- diff[1][2] = onesRow1 + onesCol2 - zerosRow1 - zerosCol2 = 3 + 2 - 0 - 0 = 5


Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 105
1 <= m * n <= 105
grid[i][j] is either 0 or 1.

*/

fun main() {
    val grid = arrayOf(
        intArrayOf(0, 1, 1),
        intArrayOf(1, 0, 1),
        intArrayOf(0, 0, 1),
    )
    val result = onesMinusZeros(grid)

    val size = grid.first().size
    for (row in 0..<size) {
        for (col in 0..<size) {
            print("${result[row][col]} ")
        }
        println()
    }

}

fun onesMinusZeros(grid: Array<IntArray>): Array<IntArray> {
    val rOnes = grid.map { row -> row.count { it == 1 } }
    val rZeros = grid.map { row -> row.count { it == 0 } }
    val cOnes = grid[0].indices.map { col -> grid.indices.count { grid[it][col] == 1 } }
    val cZeros = grid[0].indices.map { col -> grid.indices.count { grid[it][col] == 0 } }

    return Array(grid.size) { y ->
        IntArray(grid[y].size) { x ->
            rOnes[y] + cOnes[x] - rZeros[y] - cZeros[x]
        }
    }
}

fun onesMinusZeros(grid: Array<IntArray>): Array<IntArray> {
    // rowSum[i] = oneRow[i] - zeroRow[i]
    // colSum[j] = oneCol[j] - zeroCol[j]
    // => diff[i][j] = rowSum[i] + colSum[j]
    val rowSum = IntArray(grid.size)
    val colSum = IntArray(grid[0].size)
    // Use forEachIndex to dispatch the rowIndex and colIndex to calculate the rowSum and colSum
    grid.forEachIndexed { rowIndex, rowArray ->
        rowArray.forEachIndexed { colIndex, num ->
            rowSum[rowIndex] += if (num == 0) -1 else 1
            colSum[colIndex] += if (num == 0) -1 else 1
        }
    }
    return Array(grid.size) { row ->
        IntArray(grid[0].size) { col ->
            rowSum[row] + colSum[col]
        }
    }
}

fun onesMinusZeros(grid: Array<IntArray>): Array<IntArray> {
    val m = grid.size
    val n = grid[0].size

    val onesRow = IntArray(m)
    val onesCol = IntArray(n)
    val zerosRow = IntArray(m)
    val zerosCol = IntArray(n)


    for (i in 0..<m) {
        for (j in 0..<n) {
            onesRow[i] += grid[i][j]
            onesCol[j] += grid[i][j]

            if (grid[i][j] == 0) {
                zerosRow[i]++
                zerosCol[j]++
            }
        }
    }

    val diff = Array(m) { IntArray(n) }

    for (i in 0..<m)
        for (j in 0..<n)
            diff[i][j] = onesRow[i] + onesCol[j] - zerosRow[i] - zerosCol[j]

    return diff
}

fun onesMinusZeros(grid: Array<IntArray>): Array<IntArray> {
    val sum =
        grid.foldIndexed(Pair(IntArray(grid.size), IntArray(grid[0].size))) { i, acc, row ->
            row.forEachIndexed { j, value -> acc.first[i] += value; acc.second[j] += value }
            acc
        }
    return grid.mapIndexed { i, row ->
        row.mapIndexed { j, value ->
            2 * (sum.first[i] + sum.second[j]) - grid.size - grid[0].size
        }.toIntArray()
    }.toTypedArray()
}

/*
1. Calculate the length of the column and row. They will be useful later
2. Create arrays into which we will record the difference between the number of ones and the number of zeros
3. Bypassing the matrix. If the number is 1 we increase the counter in the row/column corresponding to the index, if it is zero - we decrease the counter by 1.
4. Bypassing the matrix again. Correlate the traversal index with the values in the array of differences between zeros and ones. Summarize these values using indexCol for the array of row differences (columnDiffs) and indexRow for the array of column differences (rowDiffs). Write the sum into the original matrix, so as not to allocate extra memory for the second matrix
5. Return the original matrix
 */

fun onesMinusZeros(grid: Array<IntArray>): Array<IntArray> {
    // #1
    val rowSize = grid[0].size
    val columnSize = grid.size

    // #2
    val rowDiffs = IntArray(rowSize)
    val columnDiffs = IntArray(columnSize)

    // #3
    grid.forEachIndexed { indexCol, row ->
        row.forEachIndexed { indexRow, num ->
            when (num) {
                0 -> {
                    columnDiffs[indexCol]--
                    rowDiffs[indexRow]--
                }

                1 -> {
                    columnDiffs[indexCol]++
                    rowDiffs[indexRow]++
                }
            }
        }
    }

    // #4
    grid.forEachIndexed { indexCol, row ->
        row.forEachIndexed { indexRow, _ ->
            grid[indexCol][indexRow] = rowDiffs[indexRow] + columnDiffs[indexCol]
        }
    }

    // #5
    return grid
}

fun onesMinusZeros(grid: Array<IntArray>): Array<IntArray> {
    val columnDiff = IntArray(grid[0].size)

    var i = 0
    var rOnes = 0
    var j = 0

    while (i < grid.size) {
        val line = grid[i++]
        rOnes = 0
        j = 0
        while (j < line.size) {
            if (line[j] == 1) {
                rOnes++
                columnDiff[j]++
            }
            j++
        }

        rOnes = (rOnes shl 1) - line.size  // ones - (line.size - ones) = ones * 2 - line.size = (ones << 1) - line.size
        j = 0
        while (j < line.size) {
            line[j] = rOnes
            j++
        }
    }

    i = 0
    while (i < columnDiff.size) {
        columnDiff[i] = (columnDiff[i] shl 1) - grid.size
        i++
    }

    i = 0
    while (i < grid.size) {
        val line = grid[i++]
        j = 0
        while (j < line.size) {
            line[j] += columnDiff[j]
            j++
        }
    }

    return grid
}