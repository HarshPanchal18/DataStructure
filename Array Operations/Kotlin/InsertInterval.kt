/*
* Insert Interval
You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
Return intervals after the insertion.

* Note that you don't need to modify intervals in-place. You can make a new array and return it.

Example 1:
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]

Example 2:
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

Constraints:
0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 105
intervals is sorted by starti in ascending order.
newInterval.length == 2
0 <= start <= end <= 105
*/

fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
    val n = intervals.size
    var i = 0
    val result = mutableListOf<IntArray>()

    while (i < n) {
        if (intervals[i][1] < newInterval[0])
            result.add(intervals[i])
        else if (intervals[i][0] > newInterval[1]) // start of current interval is greater than the end of the newInterval
            break
        else { // overlap
            newInterval[0] = minOf(newInterval[0], intervals[i][0]) // minimum start
            newInterval[1] = maxOf(newInterval[1], intervals[i][1]) // maximum end
        }
        i++
    }

    result.add(newInterval) // add final interval, once all overlapping are merged.

    while (i < n) // remaining intervals
        result.add(intervals[i++])

    return result.toTypedArray()
}

class Solution {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val size = intervals.size
        intervals.sortBy { it[0] }
        var (newStart, newEnd) = newInterval
        val res = mutableListOf<IntArray>()
        var i = 0
        while (i < size) {
            val (start, end) = intervals[i]
            if (end < newStart) {
                res.add(intArrayOf(start, end))
            } else if (start <= newEnd && newStart <= end) {
                newStart = minOf(newStart, start)
                newEnd = maxOf(newEnd, end)
            } else {
                break
            }
            i++
        }
        res.add(intArrayOf(newStart, newEnd))
        while (i < size) {
            res.add(intervals[i].clone())
            i++
        }
        return res.toTypedArray()
    } //tc: O(nlogn), sc: O(n)
}

class Solution {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val result = mutableListOf<IntArray>()

        var i = 0

        while (i < intervals.size && intervals[i][1] < newInterval[0]) result.add(intervals[i++])

        var previous = newInterval

        while (i < intervals.size) {
            if (intervals[i][0] <= previous[1]
                && previous[0] <= intervals[i][1]
            ) {
                previous = intArrayOf(
                    minOf(intervals[i][0], previous[0]),
                    maxOf(intervals[i][1], previous[1])
                )
            } else {
                result.add(previous)
                previous = intervals[i]
            }
            i++
        }

        result.add(previous)

        return result.toTypedArray()
    }
}

class Solution {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val answer = ArrayList<IntArray>()
        var i = 0
        val start: Int
        var end: Int

        while (i < intervals.size) {
            if (newInterval[1] < intervals[i][0]) {
                answer.add(newInterval)
                answer.add(intervals[i++])
                while (i < intervals.size)
                    answer.add(intervals[i++])
                return answer.toTypedArray()
            } else if (intervals[i][1] < newInterval[0]) {
                answer.add(intervals[i++])
            } else {
                start = newInterval[0].coerceAtMost(intervals[i][0])
                end = newInterval[1].coerceAtLeast(intervals[i][1])
                i++
                while (i < intervals.size && end >= intervals[i][0]) {
                    end = end.coerceAtLeast(intervals[i][1])
                    i++
                }
                answer.add(intArrayOf(start, end))
                while (i < intervals.size) {
                    answer.add(intervals[i++])
                }
                return answer.toTypedArray()
            }
        }
        answer.add(newInterval)
        return answer.toTypedArray()
    }
}