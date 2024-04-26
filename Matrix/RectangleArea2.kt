import java.util.*
import java.util.stream.Collectors
import java.util.stream.IntStream

/*
* Rectangle Area II
You are given a 2D array of axis-aligned rectangles. Each rectangle[i] = [xi1, yi1, xi2, yi2] denotes the ith rectangle where (xi1, yi1) are the coordinates of the bottom-left corner, and (xi2, yi2) are the coordinates of the top-right corner.

Calculate the total area covered by all rectangles in the plane. Any area covered by two or more rectangles should only be counted once.

Return the total area. Since the answer may be too large, return it modulo 109 + 7.

Example 1:
Input: rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
Output: 6
Explanation: A total area of 6 is covered by all three rectangles, as illustrated in the picture.
From (1,1) to (2,2), the green and red rectangles overlap.
From (1,0) to (2,3), all three rectangles overlap.

Example 2:
Input: rectangles = [[0,0,1000000000,1000000000]]
Output: 49
Explanation: The answer is 1018 modulo (109 + 7), which is 49.

Constraints:
1 <= rectangles.length <= 200
rectanges[i].length == 4
0 <= xi1, yi1, xi2, yi2 <= 109
xi1 <= xi2
yi1 <= yi2

*/

fun main() {
	print(
		rectangleArea(
			arrayOf(
				intArrayOf(0, 0, 2, 2),
				intArrayOf(1, 0, 2, 3),
				intArrayOf(1, 0, 3, 1),
			)
		)
	)
}

/*fun rectangleArea(rectangles: Array<IntArray>): Int {
	return rectangles[0].findArea()
}

fun IntArray.findArea(): Int {
	return (this[0] + this[2]) * (this[1] + this[3])
}*/

fun rectangleArea(rectangles: Array<IntArray>): Int {
	val mod = (1e9 + 7).toInt()
	val x: MutableSet<Int> = TreeSet()
	val L: MutableList<IntArray> = ArrayList()
	for (r in rectangles) {
		x.add(r[0])
		x.add(r[2])
		L.add(intArrayOf(r[1], r[0], r[2], 1))
		L.add(intArrayOf(r[3], r[0], r[2], -1))
	}

	val unique: List<Int> = ArrayList(x)
	val xMap = IntStream.range(0, unique.size).boxed().collect(
		Collectors.toMap(
			{ index: Int? -> unique[index!!] },
			{ i: Int -> i })
	)
	val cnt = IntArray(unique.size)

	L.sortWith { a: IntArray, b: IntArray ->
		if (a[0] == b[0])
			if (a[1] == b[1])
				if (a[2] == b[2]) a[3].compareTo(b[3])
				else a[2].compareTo(b[2])
			else a[1].compareTo(b[1])
		else a[0].compareTo(b[0])
	}

	var curY: Long = 0
	var curXSum: Long = 0
	var area: Long = 0

	for (l in L) {
		val y = l[0].toLong()
		val x1 = l[1].toLong()
		val x2 = l[2].toLong()
		val sig = l[3].toLong()

		area = (area + (y - curY) * curXSum) % mod
		curY = y

		for (i in xMap[x1.toInt()]!!..<xMap[x2.toInt()]!!)
			cnt[i] += sig.toInt()

		curXSum = 0
		for (i in unique.indices) {
			if (cnt[i] > 0)
				curXSum += (unique[i + 1] - unique[i]).toLong()
		}
	}
	return area.toInt()
}

class Solution {
	object Range {
		private val lines = TreeMap<Int, Int>()
		val range: Long
			get() {
				var res = 0L
				if (lines.isNotEmpty()) {
					var (lastKey, accValue) = listOf(lines.firstKey(), 0)
					lines.forEach { (key, value) ->
						if (accValue > 0) {
							res += key - lastKey
						}
						lastKey = key
						accValue += value
					}
				}
				return res
			}

		enum class Operation(val value: Int) {
			ADD(1), REMOVE(-1)
		}

		fun operateLine(start: Int, end: Int, operation: Operation) {
			lines[start] = (lines[start] ?: 0) + operation.value
			lines[end] = (lines[end] ?: 0) - operation.value
		}
	}

	data class Rectangle(val x: Int, val yStart: Int, val yEnd: Int, val operation: Range.Operation)

	fun rectangleArea(rectangles: Array<IntArray>): Int {
		var res = 0L
		var lastX = 0
		rectangles.fold(mutableListOf<Rectangle>()) { acc, ints ->
			acc.add(Rectangle(ints[0], ints[1], ints[3], Range.Operation.ADD))
			acc.add(Rectangle(ints[2], ints[1], ints[3], Range.Operation.REMOVE))
			acc
		}.apply {
			sortBy { it.x }
		}.forEach {
			if (it.x > lastX) {
				res = (res + Range.range * (it.x - lastX)) % 1000000007
				lastX = it.x
			}
			Range.operateLine(it.yStart, it.yEnd, it.operation)
		}
		return res.toInt()
	}
}