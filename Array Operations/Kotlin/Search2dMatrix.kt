/* Search a 2D Matrix
You are given an m x n integer matrix matrix with the following two properties:
Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.
You must write a solution in O(log(m * n)) time complexity.

Input: matrix = [
        [1,3,5,7],
        [10,11,16,20],
        [23,30,34,60]
    ],

when target = 3
    Output: true
when target = 13
    Output: false

*/

fun main(args:Array<String>) {
    val matrix = arrayOf(intArrayOf(1))
    print(searchMatrix(matrix,0))
}

fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    val row = matrix.size
    val col = matrix[0].size
    var i = 0 // top-left
    var j = row*col - 1 // bottom-right

    while (i <= j) {
        val mid = i + (j - i) / 2
        val num = matrix[mid/col][mid%col]
        if (num > target) j = mid - 1
        else if (num < target) i = mid + 1
        else return true
    }
    return false
}

fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    var left = 0
    var right = matrix[0].size - 1

    while (left < matrix.size && right >= 0) {
        if (matrix[left][right] == target) return true else if (matrix[left][right] > target) --right else ++left
    }
    return false
}