import kotlin.math.max
import kotlin.math.min

/*
* Trapping Rain Water - https://leetcode.com/problems/trapping-rain-water/
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Example 2:
Input: height = [4,2,0,3,2,5]
Output: 9

Constraints:
n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
*/

fun main() {
	println(trap(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)))
}

fun trap(height: IntArray): Int {
	val n = height.size

	if (height.isEmpty())
		return 0

	val left = IntArray(n)
	val right = IntArray(n)
	var storedWater = 0

	// Fill left array
	left[0] = height[0]
	for (i in 1..<n) {
		left[i] = max(left[i - 1], height[i])
	}

	// Fill right array
	right[n - 1] = height[n - 1]
	for (i in n - 2 downTo 0) {
		right[i] = max(right[i + 1], height[i])
	}

	// Calculate trapped water
	for (i in 0..<n) {
		val minHeight = min(left[i], right[i])
		storedWater += minHeight - height[i]
	}

	return storedWater
}

class Solution {
	fun trap(height: IntArray): Int {
		var totalArea = 0

		var leftHeight = 0
		var rightHeight = 0
		var leftIndex = 0

		for (i in height.indices) {
			if (height[i] > leftHeight) {
				leftHeight = height[i]
				leftIndex = i
			} else if (height[i] < leftHeight) {
				totalArea = totalArea + leftHeight - height[i]
			}
		}

		for (i in height.size - 1 downTo leftIndex) {
			if (height[i] > rightHeight) {
				totalArea -= (leftHeight - height[i])
				rightHeight = height[i]
			} else if (height[i] <= rightHeight) {
				totalArea -= (leftHeight - rightHeight)
			}
		}

		return totalArea
	}
}

class Solution {
	fun trap(height: IntArray): Int {
		var leftIndex = 1
		var rightIndex = height.lastIndex - 1

		var leftWall = height.first()
		var rightWall = height.last()
		var output = 0

		while (leftIndex <= rightIndex) {
			leftWall = maxOf(leftWall, height[leftIndex])
			rightWall = maxOf(rightWall, height[rightIndex])

			if (leftWall <= rightWall) {
				output += leftWall - height[leftIndex++]
			} else {
				output += rightWall - height[rightIndex--]
			}
		}

		return output
	}
}

class Solution {
	fun trap(height: IntArray): Int {
		if (height.size < 3) return 0

		val localMaxIndexes = mutableListOf<Int>()

		fun addLocalMax(index: Int) {
			while (localMaxIndexes.size > 1 &&
				height[index] >= height[localMaxIndexes.last()] &&
				height[localMaxIndexes[localMaxIndexes.lastIndex - 1]] >= height[localMaxIndexes.last()]
			) {
				localMaxIndexes.removeLast()
			}

			localMaxIndexes.add(index)
		}

		height.forEachIndexed { i, currentHeight ->
			if (i == 0) {
				if (height[1] < currentHeight) {
					addLocalMax(i)
				}
			} else if (i == height.lastIndex) {
				if (height[i - 1] < currentHeight) {
					addLocalMax(i)
				}
			} else {
				if (height[i - 1] <= currentHeight && height[i + 1] <= currentHeight) {
					addLocalMax(i)
				}
			}
		}

		if (localMaxIndexes.size < 2) return 0

		println(localMaxIndexes.joinToString())

		var trappedWater = 0
		for (i in 1..localMaxIndexes.lastIndex) {
			val trapHeight = minOf(
				height[localMaxIndexes[i - 1]],
				height[localMaxIndexes[i]],
			)

			for (j in localMaxIndexes[i - 1] + 1..<localMaxIndexes[i]) {
				trappedWater += maxOf(0, trapHeight - height[j])
			}
		}

		return trappedWater
	}
}

class Solution {
	fun trap(height: IntArray): Int {
		val possibleWater = IntArray(height.size)

		var i = 0
		var j = height.size - 1

		while (i < j) {
			val min = min(height[i], height[j])

			for (k in i..<j)
				possibleWater[k] = max(possibleWater[k], min)

			if (height[i] <= height[j]) {
				i++
			} else {
				j--
			}
		}

		var water = 0

		for (k in height.indices) {
			if (possibleWater[k] - height[k] > 0)
				water += possibleWater[k] - height[k]
		}

		return water
	}
}

class Solution {
	fun trap(height: IntArray): Int {
		var result = 0

		for (i in 1..<height.lastIndex) {
			val e = height[i]

			var (left, right) = 0 to 0
			var (l, r) = 0 to height.lastIndex

			while (l < i) {
				if (height[l] > e)
					left = max(left, height[l])
				l++
			}

			while (r > i) {
				if (height[r] > e)
					right = max(right, height[r])
				r--
			}

			if (left > 0 && right > 0)
				result += (min(left, right) - e)

		}

		return result
	}
}