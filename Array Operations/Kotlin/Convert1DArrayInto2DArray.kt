/*
* Convert 1D Array Into 2D Array
You are given a 0-indexed 1-dimensional (1D) integer array original, and two integers, m and n.
You are tasked with creating a 2-dimensional (2D) array with  m rows and n columns using all the elements from original.
The elements from indices 0 to n - 1 (inclusive) of original should form the first row of the constructed 2D array, the elements from indices n to 2 * n - 1 (inclusive) should form the second row of the constructed 2D array, and so on.
Return an m x n 2D array constructed according to the above procedure, or an empty 2D array if it is impossible.

Example 1:
Input: original = [1,2,3,4], m = 2, n = 2
Output: [[1,2],[3,4]]
Explanation: The constructed 2D array should contain 2 rows and 2 columns.
The first group of n=2 elements in original, [1,2], becomes the first row in the constructed 2D array.
The second group of n=2 elements in original, [3,4], becomes the second row in the constructed 2D array.

Example 2:
Input: original = [1,2,3], m = 1, n = 3
Output: [[1,2,3]]
Explanation: The constructed 2D array should contain 1 row and 3 columns.
Put all three elements in original into the first row of the constructed 2D array.

Example 3:
Input: original = [1,2], m = 1, n = 1
Output: []
Explanation: There are 2 elements in original.
It is impossible to fit 2 elements in a 1x1 2D array, so return an empty 2D array.

Constraints:
1 <= original.length <= 5 * 104
1 <= original[i] <= 105
1 <= m, n <= 4 * 104
*/

fun main() {
    var result = construct2DArray(intArrayOf(1, 2, 3, 4), 2, 2)
    printMatrix(result)
    result = construct2DArray(intArrayOf(1, 2, 3), 1, 3)
    printMatrix(result)
}

fun construct2DArray(original: IntArray, m: Int, n: Int): Array<IntArray> {
    if (m * n != original.size) return emptyArray()

    val rows = original.toList()
        .chunked(n) // Breakdown array in `n` size
        .map { it.toIntArray() } // Map each list to array.
        .toTypedArray() // Convert to Array.

    /*val result = mutableListOf<IntArray>()
    rows.forEach { result.add(it) }

    return result.toTypedArray()*/

    return buildList {
        rows.forEach { add(it) }
    }.toTypedArray()
}

fun printMatrix(matrix: Array<IntArray>) {
    matrix.forEach { row ->
        row.forEach {
            print("$it ")
        }
        println()
    }
}

class Solution {
    fun construct2DArray(original: IntArray, m: Int, n: Int): Array<IntArray> {
        if (original.size != n * m) return emptyArray()
        return Array(m) { row ->
            IntArray(n) { col ->
                original[row * n + col]
            }
        }
    }
}

class Solution {
    fun construct2DArray(original: IntArray, m: Int, n: Int): Array<IntArray> {
        if (original.size != m * n) return arrayOf()

        val matrix = Array(m) { IntArray(n) }
        for (index in original.indices) {
            val row = index / n
            val colum = index % n
            matrix[row][colum] = original[index]
        }

        return matrix
    }
}