/*
* Continuous Subarray Sum
Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.

A good subarray is a subarray where:
* its length is at least two, and
* the sum of the elements of the subarray is a multiple of k.

Note that:
* A subarray is a contiguous part of the array.
* An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.

Example 1:
Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.

Example 2:
Input: nums = [23,2,6,4,7], k = 6
Output: true
Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.

Example 3:
Input: nums = [23,2,6,4,7], k = 13
Output: false

Constraints:
1 <= nums.length <= 105
0 <= nums[i] <= 109
0 <= sum(nums[i]) <= 231 - 1
1 <= k <= 231 - 1
*/

fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
    val sums = hashMapOf<Int, Int>()
    sums[0] = -1
    var sum = 0

    return nums.withIndex().any { (index, value) ->
    sum += value
    sums.getOrPut(sum % k) { index } < index - 1
    }
}

class Solution {
    fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
        if (nums.size < 2)
            return false

        var sum = 0
        var prevSums = hashMapOf<Int, Int>()
        prevSums[0] = -1

        nums.forEachIndexed { i, n ->
            sum = (sum + n) % k
            if (sum in prevSums && i - prevSums[sum]!! > 1)
                return true

            prevSums[sum] = prevSums[sum] ?: i
        }

        return false
    }
}

class Solution {
    fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
        val map = hashMapOf<Int,Int>()
        map[0] = -1

        var sum = 0
        for(i in nums.indices) {
            sum += nums[i]

            if(map.containsKey(sum % k)) {
                if(i - map[sum % k]!! >= 2)
                    return true
            }
            else map[sum % k] = i
        }

        return false
    }
}

class Solution {
    // 5 0 0 0       k = 3   true??
    // 1 0 1 0 1     k = 4
    // 23   2   6   2   5    k = 6
    // 23                    23: 6*4 = 24(1), 6*5 = 30(7), 6*6 = 36(13)
    //     25                25:           6*5 = 30(5)... 2: 6*1=6(4),6*2=12(8)
    //         31            31: 6*6=36(5),6*7=42(11).., (2+6)=8:
    //             33
    //                 38
    //    [..........]
    //             [33 - 23] % k = [2+6+2] % k
    //              33 % k = [23+2+6+2] % k
    // 0 1 0 3 0 4 0 4 0
    // 23 2   4  6  6
    // 23
    //    25
    //       29
    //          35
    fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
        val sums = HashMap<Int, Int>()
        var sum = 0

        for ((i, n) in nums.withIndex()) {
            if (n == 0) {
                if (nums.getOrNull(i - 1) == 0) return true
                else continue
            }

            sum += n
            if (sum % k == 0 && i > 0)
                return true

            val prev = sums[sum % k]

            if (prev != null && prev < i - 1)
                return true

            sums[sum % k] = i
        }

        return false
    }
}

class Solution {
  fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
    val map = mutableMapOf(0 to -1)
    var runningSum = 0

    for (i in nums.indices) {
        runningSum += nums[i]
        var remainder = 0
        if (k != 0)
            remainder = runningSum%k

        val prev = map[remainder]
        if (prev != null) {
            if (i - prev > 1) return true
        } else map[remainder] = i
    }

    return false
}
}