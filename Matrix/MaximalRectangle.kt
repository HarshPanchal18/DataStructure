import java.util.*
import kotlin.math.max

/*
* Maximal Rectangle
Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example 1:
Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.

Example 2:
Input: matrix = [["0"]]
Output: 0

Example 3:
Input: matrix = [["1"]]
Output: 1

Constraints:
rows == matrix.length
cols == matrix[i].length
1 <= row, cols <= 200
matrix[i][j] is '0' or '1'.
*/

fun main() {
	val matrix = arrayOf(
		charArrayOf('1', '0', '1', '0', '0'),
		charArrayOf('1', '0', '1', '1', '1'),
		charArrayOf('1', '1', '1', '1', '1'),
		charArrayOf('1', '0', '0', '1', '0')
	)

	println(maximalRectangle(matrix))
}

fun maximalRectangle(matrix: Array<CharArray>?): Int {
	if (matrix.isNullOrEmpty() || matrix.first().isEmpty())
		return 0

	val cols = matrix.first().size
	val heights = IntArray(cols + 1) // Include an extra element for easier calculation
	var maxArea = 0

	for (row in matrix) {
		for (i in 0 until cols) {
			heights[i] = if ((row[i] == '1')) heights[i] + 1 else 0
			//print("${heights[i]} ")
		}
		//println()

		// Calculate max area using stack-based method
		val stack = Stack<Int>()
		for (i in heights.indices) {
			while (stack.isNotEmpty() && heights[i] < heights[stack.peek()]) {
				val height = heights[stack.pop()]
				val width = if (stack.isEmpty()) i else i - stack.peek() - 1
				maxArea = max(maxArea, (height * width))
			}
			stack.push(i)
		}
	}

	return maxArea
}

/*fun maximalRectangle(matrix: Array<CharArray>): Int {
	if (matrix.isEmpty() || matrix.first().isEmpty())
		return 0

	var maxArea = 0
	val rows = matrix.size
	val cols = matrix.first().size
	val heights = IntArray(cols + 1)

	for (i in 0..<rows) {
		for (j in 0..<cols) {
			heights[j] = if (matrix[i][j] == '1') heights[j] + 1 else 0
			print("${heights[j]} ")
		}
		println()
	}

	/*for (i in 0..<n) {
		for (j in i..<n) {
			var minHeight = Int.MAX_VALUE
			for (k in i..j) {
				minHeight = minOf(minHeight, heights[k])
				maxArea = maxOf(maxArea, minHeight * (j - i + 1))
			}
		}
	}*/

	/*val stack = Stack<Int>()

	for (i in heights.indices) {
		while (stack.isNotEmpty() && heights[i] < heights[stack.peek()]) {
			val height = heights[stack.pop()]
			val width = if (stack.isEmpty()) i else stack.peek() - i - 1
			maxArea = maxOf(maxArea, height * width)
		}
		stack.push(i)
	}*/

	for (row in matrix) {
		for (i in 0..<cols)
			heights[i] = if ((row[i] == '1')) heights[i] + 1 else 0

		// Calculate max area using stack-based method
		val stack = Stack<Int>()
		for (i in heights.indices) {
			while (stack.isNotEmpty() && heights[i] < heights[stack.peek()]) {
				val h = heights[stack.pop()]
				val w = if (stack.isEmpty()) i else i - stack.peek() - 1
				maxArea = max(maxArea, (h * w))
			}
			stack.push(i)
		}
	}

	return maxArea
}*/

class Solution {
	fun maximalRectangle(matrix: Array<CharArray>): Int {

		var max = 0
		val sumArray = mutableListOf<Int>()
		for (i in matrix.indices) {
			val arr = matrix[i].map { it.code - '0'.code }.toList()
			if (i == 0) {
				sumArray.addAll(arr)
			} else {
				for (j in arr.indices) {
					if (arr[j] == 0) {
						sumArray[j] = 0
					} else {
						sumArray[j] = arr[j] + sumArray[j]
					}
				}
			}

			val area = maxArea(sumArray)
			max = maxOf(area, max)
		}

		return max

	}

	fun maxArea(list: List<Int>): Int {
		var max = 0
		for (i in list.indices) {
			val l = left(i - 1, list)
			val r = right(i + 1, list)
			val res = (l + r + 1) * list[i]
			max = maxOf(max, res)
		}

		return max
	}

	fun left(i: Int, list: List<Int>): Int {
		var current = i
		if (i < 0) return 0
		while (current > -1 && list[current] >= list[i + 1]) {
			current -= 1
		}

		return i - current
	}


	fun right(i: Int, list: List<Int>): Int {
		var current = i
		if (i > list.size - 1) return 0
		while (current <= list.size - 1 && list[current] >= list[i - 1]) {
			current += 1
		}

		return current - i
	}

}

internal class Solution {
	fun maximalRectangle(matrix: Array<CharArray>?): Int {
		if (matrix.isNullOrEmpty() || matrix.first().isEmpty()) {
			return 0
		}

		val m = matrix.size
		val n = matrix.first().size

		val heights = IntArray(n)
		val leftBoundaries = IntArray(n)
		val rightBoundaries = IntArray(n)
		Arrays.fill(rightBoundaries, n)

		var maxRectangle = 0

		for (i in 0 until m) {
			val left = 0

			updateHeightsAndLeftBoundaries(matrix[i], heights, leftBoundaries, left)

			updateRightBoundaries(matrix[i], rightBoundaries, n)

			maxRectangle = calculateMaxRectangle(heights, leftBoundaries, rightBoundaries, maxRectangle)
		}

		return maxRectangle
	}

	private fun updateHeightsAndLeftBoundaries(row: CharArray, heights: IntArray, leftBoundaries: IntArray, left: Int) {
		var left = left
		for (j in heights.indices) {
			if (row[j] == '1') {
				heights[j]++
				leftBoundaries[j] = max(leftBoundaries[j].toDouble(), left.toDouble()).toInt()
			} else {
				heights[j] = 0
				leftBoundaries[j] = 0
				left = j + 1
			}
		}
	}

	private fun updateRightBoundaries(row: CharArray, rightBoundaries: IntArray, right: Int) {
		var right = right
		for (j in rightBoundaries.indices.reversed()) {
			if (row[j] == '1') {
				rightBoundaries[j] = min(rightBoundaries[j].toDouble(), right.toDouble()).toInt()
			} else {
				rightBoundaries[j] = right
				right = j
			}
		}
	}

	private fun calculateMaxRectangle(
		heights: IntArray,
		leftBoundaries: IntArray,
		rightBoundaries: IntArray,
		maxRectangle: Int
	): Int {
		var maxRectangle = maxRectangle
		for (j in heights.indices) {
			val width = rightBoundaries[j] - leftBoundaries[j]
			val area = heights[j] * width
			maxRectangle = max(maxRectangle.toDouble(), area.toDouble()).toInt()
		}
		return maxRectangle
	}
}

class Solution {
	fun maximalRectangle(matrix: Array<CharArray>): Int {
		if (matrix.isEmpty() || matrix[0].isEmpty()) return 0

		val rows = matrix.size
		val cols = matrix[0].size
		val heights = IntArray(cols)
		var maxArea = 0

		for (i in 0 until rows) {
			for (j in 0 until cols) {
				if (matrix[i][j] == '1') {
					heights[j]++
				} else {
					heights[j] = 0
				}
			}
			maxArea = maxOf(maxArea, largestRectangleArea(heights))
		}

		return maxArea
	}

	private fun largestRectangleArea(heights: IntArray): Int {
		val stack = mutableListOf<Int>()
		var maxArea = 0
		var i = 0

		while (i < heights.size) {
			if (stack.isEmpty() || heights[i] >= heights[stack.last()]) {
				stack.add(i++)
			} else {
				val top = stack.removeAt(stack.size - 1)
				val width = if (stack.isEmpty()) i else i - stack.last() - 1
				maxArea = maxOf(maxArea, heights[top] * width)
			}
		}

		while (stack.isNotEmpty()) {
			val top = stack.removeAt(stack.size - 1)
			val width = if (stack.isEmpty()) i else i - stack.last() - 1
			maxArea = maxOf(maxArea, heights[top] * width)
		}

		return maxArea
	}
}

class Solution {
	fun maximalRectangle(matrix: Array<CharArray>): Int {
		val prev = IntArray(matrix[0].size)
		var max = 0
		for (i in 0 until matrix.size) {
			for (j in 0 until matrix[0].size) {
				if (matrix[i][j] == '1') {
					prev[j] += 1
				} else {
					prev[j] = 0
				}
			}
			max = max(max, maxHisto(prev))
		}
		return max
	}

	fun maxHisto(heights: IntArray): Int {
		val left = IntArray(heights.size)
		val right = IntArray(heights.size)
		val stack = Stack<Int>() // index

		for (i in heights.indices) {
			while (stack.isNotEmpty() && heights[stack.peek()] >= heights[i]) {
				stack.pop()
			}
			left[i] = if (stack.isEmpty()) 0 else stack.peek() + 1
			stack.push(i)
		}

		stack.clear()

		for (i in heights.size - 1 downTo 0) {
			while (stack.isNotEmpty() && heights[stack.peek()] >= heights[i]) {
				stack.pop()
			}
			right[i] = if (stack.isEmpty()) heights.size - 1 else stack.peek() - 1
			stack.push(i)
		}

		var max = 0
		for (i in heights.indices) {
			val width = right[i] - left[i] + 1
			max = max(max, width * heights[i])
		}

		return max
	}
}