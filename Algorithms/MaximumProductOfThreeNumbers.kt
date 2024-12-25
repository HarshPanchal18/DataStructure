/*
* Maximum Product of Three Numbers
Given an integer array nums, find three numbers whose product is maximum and return the maximum product.

Example 1:
Input: nums = [1,2,3]
Output: 6

Example 2:
Input: nums = [1,2,3,4]
Output: 24

Example 3:
Input: nums = [-1,-2,-3]
Output: -6

Constraints:
3 <= nums.length <= 104
-1000 <= nums[i] <= 1000
*/

fun maximumProduct(nums: IntArray): Int {
    nums.sort()
    return maxOf(
        nums.first() * nums[1] * nums.last(),
        nums[nums.lastIndex - 2] * nums[nums.lastIndex - 1] * nums.last(),
    )
}

class Solution {
    fun maximumProduct(nums: IntArray): Int {
        val sorted = nums.sortedDescending()
        val a1 = sorted.slice(0..2)
        val a2 = listOf(sorted[0], sorted[nums.lastIndex-1], sorted[nums.lastIndex])

        val p1 = a1[0] * a1[1] * a1[2]
        val p2 = a2[0] * a2[1] * a2[2]

        return max(p1,p2)
    }
}