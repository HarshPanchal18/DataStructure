/*
* Rearrange Array Elements by Sign
You are given a 0-indexed integer array nums of even length consisting of an equal number of positive and negative integers.
You should rearrange the elements of nums such that the modified array follows the given conditions:
* Every consecutive pair of integers have opposite signs.
* For all integers with the same sign, the order in which they were present in nums is preserved.
* The rearranged array begins with a positive integer.
* Return the modified array after rearranging the elements to satisfy the aforementioned conditions.

Example 1:
Input: nums = [3,1,-2,-5,2,-4]
Output: [3,-2,1,-5,2,-4]
Explanation:
The positive integers in nums are [3,1,2]. The negative integers are [-2,-5,-4].
The only possible way to rearrange them such that they satisfy all conditions is [3,-2,1,-5,2,-4].
Other ways such as [1,-2,2,-5,3,-4], [3,1,2,-2,-5,-4], [-2,3,-5,1,-4,2] are incorrect because they do not satisfy one or more conditions.

Example 2:
Input: nums = [-1,1]
Output: [1,-1]
Explanation:
1 is the only positive integer and -1 the only negative integer in nums.
So nums is rearranged to [1,-1].

Constraints:
2 <= nums.length <= 2 * 105
nums.length is even
1 <= |nums[i]| <= 105
nums consists of equal number of positive and negative integers.
*/

fun main() {
    val nums = intArrayOf(3, 1, -2, -5, 2, -4)
    val result = rearrangeArray(nums)
    result.forEach { println(it) }
}

fun rearrangeArray(nums: IntArray): IntArray {
    val positives = nums.filter { it > 0 }
    val negatives = nums.filter { it < 0 }

    val result = mutableListOf<Int>()
    var current = 0
    repeat(nums.size / 2) {
        result.add(positives[current])
        result.add(negatives[current])
        current++
    }
    return result.toIntArray()
}

internal class Solution {
    fun rearrangeArray(nums: IntArray): IntArray {
        var p = 0
        var n = 1
        val res = IntArray(nums.size)

        for (i in res.indices) {
            if (nums[i] > 0) {
                res[p] = nums[i]
                p += 2
            } else {
                res[n] = nums[i]
                n += 2
            }
        }
        return res
    }
}

class Solution {

    fun rearrangeArray(nums: IntArray): IntArray {
        for (i in nums.indices) {
            val isNegativeAtWrongPlace = i % 2 == 0 && nums[i] < 0
            val isPositiveAtWrongPlace = i % 2 == 1 && nums[i] > 0

            if (isNegativeAtWrongPlace || isPositiveAtWrongPlace) {
                var nearestOppositeIndex = i

                if (isNegativeAtWrongPlace) {
                    while (nums[nearestOppositeIndex] < 0) nearestOppositeIndex++
                } else {
                    while (nums[nearestOppositeIndex] >= 0) nearestOppositeIndex++
                }

                val temp = nums[nearestOppositeIndex]
                for (j in nearestOppositeIndex downTo i + 1) nums[j] = nums[j - 1]

                nums[i] = temp
            }
        }

        return nums
    }
}

fun rearrangeArray(nums: IntArray): IntArray {
    var i = nums.indexOfFirst { it > 0 }
    var j = nums.indexOfFirst { it < 0 }
    return IntArray(nums.size) {
        nums[i].also { n ->
            i = (i + 1..<nums.size)
                .find { n > 0 == nums[it] > 0 } ?: 0
            i = j.also { j = i }
        }
    }
}