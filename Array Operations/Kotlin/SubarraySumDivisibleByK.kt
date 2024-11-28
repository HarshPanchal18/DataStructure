/*
* Subarray Sums Divisible by K
Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.

A subarray is a contiguous part of an array.

Example 1:
Input: nums = [4,5,0,-2,-3,1], k = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by k = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]

Example 2:
Input: nums = [5], k = 9
Output: 0

Constraints:
1 <= nums.length <= 3 * 104
-104 <= nums[i] <= 104
2 <= k <= 104
*/

class Solution {
    fun subarraysDivByK(nums: IntArray, k: Int): Int {
        val map = hashMapOf<Int,Int>()
        var sum = 0
        var count = 0
        map.put(0, 1)

        for (num in nums) {
            sum+= num
            val remainder = (sum % k + k) % k
            count += map.getOrDefault(remainder, 0)
            map[remainder] = map.getOrDefault(remainder, 0) + 1
        }

        return count
    }
}


class Solution {
    fun subarraysDivByK(nums: IntArray, k: Int): Int {
        var res = 0
        var prefixSum = 0
        val freq = IntArray(k)
        freq[0] = 1

        for (num in nums) {
            prefixSum = ((prefixSum + num) % k + k) % k
            res += freq[prefixSum]
            freq[prefixSum]++
        }

        return res
    }
}

class Solution {
    fun subarraysDivByK(nums: IntArray, k: Int): Int {
        var prefixMod = 0
        var count = 0
        val map = mutableMapOf<Int, Int>()
        map[0] = 1

        for (i in nums.indices) {
            prefixMod = (prefixMod + nums[i] % k + k) % k
            map[prefixMod]?.let { count += it }
            map[prefixMod] = map.getOrDefault(prefixMod, 0) + 1
        }

        return count
    }
}