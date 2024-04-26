/*
* Subarray Product Less Than K
Given an array of integers nums and an integer k.
Return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.

Example 1:
Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.

Example 2:
Input: nums = [1,2,3], k = 0
Output: 0

Constraints:
1 <= nums.length <= 3 * 104
1 <= nums[i] <= 1000
0 <= k <= 106
*/

fun main() {
    print(numSubarrayProductLessThanK(intArrayOf(10,5,2,6),100))
}

fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {

    if(k == 0 || nums.size == 0)
        return 0

    var pairs = 0
    var j = 0
    var prod = 1

    for(i in 0..<nums.size) {
        prod *= nums[i]

        while(j <= i && prod >= k)
            prod /= nums[j++]

        pairs += (i - j) + 1
    }

    return pairs
}

fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
    var start = 0
    var ret = 0
    var sum = 1
    nums.forEachIndexed { end, num ->
        sum *= num
        while (sum >= k && start <= end) {
            sum /= nums[start++]
        }

        ret += end - start + 1
    }

    return ret
}

fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
    if (k <= 1) return 0
    var left = 0
    var currentProduct = 1
    var answer = 0

    for (right in nums.indices) {
        currentProduct *= nums[right]

        while (currentProduct >= k) {
            currentProduct /= nums[left]
            left++
        }

        answer = answer + (right - left + 1)
    }

    return answer
}

class Solution {
    fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
        if (k == 0) return 0
        var cnt = 0
        for (i in nums.indices) {
            if (nums[i] >= k) continue
            var j = i + 1
            var p = 1L * nums[i]
            while (j < nums.size && p * nums[j] < k) {
                p *= nums[j]
                j++
            }
            cnt += j - i
        }
        return cnt
    }
}

class Solution {
    fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
        var result = 0
        for (i in 0..nums.size) {
            var acc = 1
            for (j in 0..(nums.size - i - 1)) {
                acc *= nums[i + j]
                if (acc < k) {
                    result++
                } else {
                    break
                }
            }
        }
        return result
    }
}

class Solution {
    fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
        var left = 0
        var product = 1
        var result = 0

        for (right in nums.indices) {
            while (product * nums[right] >= k && left < right)
                product = product/nums[left++]

            product *= nums[right]

            if (product < k)
                result += right - left + 1

        }

        return result
    }
}