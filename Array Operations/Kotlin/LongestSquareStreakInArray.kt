import kotlin.math.pow

/*
* Longest Square Streak in an Array

You are given an integer array nums. A subsequence of nums is called a square streak if:
The length of the subsequence is at least 2, and
after sorting the subsequence, each element (except the first element) is the square of the previous number.
Return the length of the longest square streak in nums, or return -1 if there is no square streak.
A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

Example 1:
Input: nums = [4,3,6,16,8,2]
Output: 3
Explanation: Choose the subsequence [4,16,2]. After sorting it, it becomes [2,4,16].
- 4 = 2 * 2.
- 16 = 4 * 4.
Therefore, [4,16,2] is a square streak.
It can be shown that every subsequence of length 4 is not a square streak.

Example 2:
Input: nums = [2,3,5,6,7]
Output: -1
Explanation: There is no square streak in nums so return -1.

Constraints:
2 <= nums.length <= 105
2 <= nums[i] <= 105
*/

fun main() {
    println(longestSquareStreak(intArrayOf(4, 3, 6, 16, 8, 2)))
}

fun longestSquareStreak(nums: IntArray): Int {
    nums.sort()
    val map = mutableMapOf<Int, Int>()
    for (i in nums.indices.reversed())
        map[nums[i]] = map.getOrDefault(nums[i] * nums[i], 0) + 1

    return if (map.values.max() == 1) -1
    else map.values.max()
}

class Solution {
    fun longestSquareStreak(nums: IntArray): Int {
        marker++

        for (n in nums) {
            SQUARES[n]?.marker = marker
        }

        var maxChain = -1
        for (i in 1..316) {
            if (SQUARES[i]?.marker == marker) {
                var chain = 1
                var s: Square? = SQUARES[i]
                while (s?.next?.marker == marker) {
                    chain++
                    maxChain = maxOf(maxChain, chain)
                    s.marker = 0
                    s = s.next
                }
            }
        }

        return maxChain
    }

    companion object {
        val SQUARES = Array<Square?>(100_001) { null }
        var marker = 0

        init {
            for (x in 100_000 downTo 1) {
                SQUARES[x] = Square(x, if (x * x >= 0 && x * x < SQUARES.size) SQUARES[x * x] else null)
            }
        }
    }
}

class Square(val number: Int, val next: Square? = null) : Comparable<Square> {
    var marker: Int = 0
    override fun compareTo(other: Square): Int = number - other.number
    override fun toString(): String = "$number*$number=${number * number}"
}

class Solution {
    fun longestSquareStreak(nums: IntArray): Int {
        var maxLength = -1
        val originalSet = nums.toSet()

        for (element in nums) {
            var tmp = 1

            for (j in 1..<5) {
                if (originalSet.contains(element.toFloat().pow(2f.pow(j)).toInt())) {
                    tmp++
                } else break
            }

            if (tmp > 1) maxLength = maxOf(maxLength, tmp)
        }
        return maxLength
    }
}

class Solution {
    fun longestSquareStreak(nums: IntArray): Int {
        val streak = mutableMapOf<Int, Int>()
        return nums.sorted().maxOf { n ->
            (1 + (streak[n] ?: 0)).also { streak[n * n] = it }
        }.takeIf { it > 1 } ?: -1
    }
}

class Solution {
    fun longestSquareStreak(nums: IntArray): Int {
        val dict = HashMap<Int, Int>()
        for (num in nums.sortedDescending())
            dict[num] = (if (num > 1000) 0 else dict[num * num] ?: 0) + 1
        return dict.values.max().let { if (it == 1) -1 else it }
    }
}