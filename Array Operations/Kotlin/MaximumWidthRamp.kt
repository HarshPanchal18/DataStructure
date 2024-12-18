import java.util.*

/*
* Maximum Width Ramp

A ramp in an integer array nums is a pair (i, j) for which i < j and nums[i] <= nums[j]. The width of such a ramp is j - i.
Given an integer array nums, return the maximum width of a ramp in nums. If there is no ramp in nums, return 0.

Example 1:
Input: nums = [6,0,8,2,1,5]
Output: 4
Explanation: The maximum width ramp is achieved at (i, j) = (1, 5): nums[1] = 0 and nums[5] = 5.

Example 2:
Input: nums = [9,8,1,0,1,9,4,0,4,1]
Output: 7
Explanation: The maximum width ramp is achieved at (i, j) = (2, 9): nums[2] = 1 and nums[9] = 1.

Constraints:
2 <= nums.length <= 5 * 104
0 <= nums[i] <= 5 * 104
*/

fun main() {
    println(maxWidthRamp(intArrayOf(6, 0, 8, 2, 1, 5)))
}

// TL Exceeded - Brute force
fun maxWidthRamp(nums: IntArray): Int {
    var maxRamp = 0
    var left = 0
//    var right: Int

    while (left < nums.lastIndex) {
        // right = left
        /* while (right < nums.size) {
                    if (nums[left] <= nums[right]) {
                        maxRamp = maxOf(maxRamp, right - left)
                    }
                    right++
                }*/

        for (right in left + 1..nums.lastIndex) {
            if (nums[left] <= nums[right]) {
                maxRamp = maxOf(maxRamp, right - left)
            }
        }

        left++
    }

    return maxRamp
}

class Solution {
    fun maxWidthRamp(nums: IntArray): Int {
        val n = nums.size
        val indices = IntArray(n) { it }

        // Sort indices based on corresponding values in nums and ensure stability
        indices.sortedWith { i: Int, j: Int ->
            if (nums[i] != nums[j]) nums[i] - nums[j]
            else i - j
        }

        var minIndex = n // Minimum index encountered so far
        var maxWidth = 0

        // Calculate maximum width ramp
        for (i in indices) {
            maxWidth = maxOf(maxWidth, i - minIndex)
            minIndex = minOf(minIndex, i)
        }

        return maxWidth
    }
}

class Solution {
    fun maxWidthRamp(nums: IntArray): Int {
        val n = nums.size
        val rightMax = IntArray(n)

        // Fill rightMax array with the maximum values from the right
        rightMax[n - 1] = nums[n - 1]
        for (i in n - 2 downTo 0) {
            rightMax[i] = maxOf(rightMax[i + 1], nums[i])
        }

        var left = 0
        var right = 0
        var maxWidth = 0

        // Traverse the array using left and right pointers
        while (right < n) {

            // Move left pointer forward if current left exceeds rightMax
            while (left < right && nums[left] > rightMax[right]) {
                left++
            }

            maxWidth = maxOf(maxWidth, right - left)
            right++
        }

        return maxWidth
    }
}

class Solution {
    fun maxWidthRamp(nums: IntArray): Int {
        val indicesStack = Stack<Int>()

        // Fill the stack with indices in increasing order of their values
        for (i in nums.indices) {
            if (indicesStack.isEmpty() || nums[indicesStack.peek()] > nums[i]) {
                indicesStack.push(i)
            }
        }

        var maxWidth = 0

        // Traverse the array from the end to the start
        for (j in nums.indices.reversed()) {
            while (indicesStack.isNotEmpty() && nums[indicesStack.peek()] <= nums[j]) {
                maxWidth = maxOf(maxWidth, j - indicesStack.peek())
                indicesStack.pop() // Pop the index since it's already processed
            }
        }

        return maxWidth
    }
}