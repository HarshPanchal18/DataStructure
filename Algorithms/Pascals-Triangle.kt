/*
* Pascal's Triangle - https://leetcode.com/problems/pascals-triangle

Given an integer numRows, return the first numRows of Pascal's triangle.
In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

Example 1:
Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

Example 2:
Input: numRows = 1
Output: [[1]]
*/

fun main(args: Array<String>) {
    val triangle = generate(5)
}

fun generate(numRows: Int): List<List<Int>> {
    val result = ArrayList<List<Int>>()

    result.add(listOf(1))
    for(i in 2..numRows) {
        val lastRow = result.last() // Pick last row for generating the current one
        val row = mutableListOf<Int>()
        row.add(lastRow[0]) // first value of current row

        for(j in 1..lastRow.lastIndex)
            row.add(lastRow[j-1]+lastRow[j])

        row.add(lastRow[0])
        result.add(row)
    }
    return result
}

fun generate(numRows: Int): List<List<Int>> {
    val list = MutableList(numRows) {mutableListOf<Int>()}

    repeat(numRows) { row->
    val innerList = list[row]
        repeat(row + 1) { cell ->
            if (cell == 0 || cell == row) {
                innerList.add(1)
            } else {
                innerList.add(list[row -1][cell-1] + list[row -1][cell])
            }
        }
    }

    return list
}