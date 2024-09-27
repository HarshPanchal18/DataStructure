/*
* Merge Intervals
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].

Example 2:
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

Constraints:
1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
*/

fun main() {
    var intervals = arrayOf(
        intArrayOf(1, 3),
        intArrayOf(2, 6),
        intArrayOf(8, 10),
        intArrayOf(15, 18),
    )

    intervals = merge(intervals)
    intervals.forEach { interval ->
        print("[")
        interval.forEach { print("$it ") }
        println("]")
    }
}

fun merge(intervals: Array<IntArray>): Array<IntArray> {
    intervals.sortBy { it[0] }
    val result = mutableListOf(intervals[0])

    for (interval in intervals) {
        if (result.last()[1] < interval[0]) { // no overlaps
            result.add(interval)
        } else { // append with max bound
            result.last()[1] = maxOf(result.last()[1], interval[1])
        }
    }

    return result.toTypedArray()
}

class Solution {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {

        intervals.sortWith { a: IntArray, b: IntArray -> a[0] - b[0] }

        val result = ArrayList<IntArray>()
        result.add(intervals[0])
        for (ii in 1..<intervals.size) {
            val interval = intervals[ii]
            if (interval[0] >= result.last()[0] && interval[0] <= result.last()[1]) {
                result.last()[1] = maxOf(interval[1], result.last()[1])
            } else {
                result.add(interval)
            }
        }

        val arr = Array(result.size) { idx -> result[idx] }
        return arr
    }
}

class Solution {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val result = ArrayList<IntArray>()
        var i = 0

        intervals.sortBy { it.first() }
        // intervals.forEach {
        //     println(it.first())
        // }

        // i = 0
        println()
        while (i < intervals.size) {
            var first = intervals[i][0] // 0
            var second = intervals[i][1] // 2

            while (i < intervals.size - 1 && second >= intervals[i + 1][0]) {
                first = minOf(intervals[i + 1][0], first) // 0
                println(first) // 0
                second = maxOf(intervals[i + 1][1], second) // 4
                i++
            }
            result.add(intArrayOf(first, second))
            i++
        }

        return result.toTypedArray()
    }
}

class Solution {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortWith { o1, o2 ->
            val start = o1[0].compareTo(o2[0])
            return@sortWith if (start != 0) start else o1[1].compareTo(o2[1])
        }
        val list = mutableListOf<IntArray>()

        var next = 0
        var left = intervals[next++]
        while (next < intervals.size) {
            val right = intervals[next]
            if (right[0] <= left[1]) {
                left[1] = maxOf(left[1], right[1])
            } else {
                list.add(left)
                left = intervals[next]
            }
            next++
        }

        list.add(left)

        return list.toTypedArray()
    }
}


const val EMPTY = 0.toByte()
const val START = 1.toByte()
const val END = 2.toByte()

class Solution {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val arr = ByteArray(10_001)
        val min = 0//Int.MAX_VALUE
        var max = 0
        for (interval in intervals) {
            val (first, last) = interval
//            min = min(min, first)
            max = maxOf(max, last)
            for (i in first..<last)
                arr[i] = START

            if (arr[last] == EMPTY)
                arr[last] = END

        }
        val result = mutableListOf<IntArray>()
        var start = -1

        for (i in min..max) {
            when (arr[i]) {
                START -> if (start == -1) start = i
                END -> {
                    if (start == -1) start = i
                    result.add(intArrayOf(start, i))
                    start = -1
                }
            }
        }
        return result.toTypedArray()
    }
}

class Solution {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val starts = IntArray(10001)
        val ends = IntArray(10001)
        for (interval in intervals) {
            starts[interval[0]]++
            ends[interval[1]]++
        }
        var currentStart = 0
        var count = 0
        val result = arrayListOf<IntArray>()

        for (i in starts.indices) {
            if (count == 0 && starts[i] > 0) currentStart = i
            count += starts[i]
            count -= ends[i]
            if (count == 0 && ends[i] > 0) result += intArrayOf(currentStart, i)
        }

        return result.toTypedArray()
    }
}