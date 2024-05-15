/*
* Product of Array Except Self
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
You must write an algorithm that runs in O(n) time and without using the division operation.

Example 1:
Input: nums = [1,2,3,4]
Output: [24,12,8,6]

Example 2:
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]

Constraints:
2 <= nums.length <= 105
-30 <= nums[i] <= 30
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
*/

fun main() {
    Solution().productExceptSelf(intArrayOf(1, 2, 3, 4))
}

fun productExceptSelf(nums: IntArray): IntArray {
    val numList = nums.toMutableList()
    val answer = mutableListOf<Int>()
    val n = nums.size

    for (i in 0 until n) {
        val temp = numList.removeAt(i)
        answer.add(numList.mul())
        //println(numList.mul())
        numList.add(i, temp)
    }

    return answer.toIntArray()
}

fun Iterable<Int>.mul(): Int {
    var answer = 1
    for (n in this) {
        answer *= n
    }
    return answer
}

class Solution {
    fun productExceptSelf(nums: IntArray): IntArray {
        val n = nums.size
        val left = IntArray(n) { 1 }
        val right = IntArray(n) { 1 }


        print("   ")
        for (i in 1 until n)
            left[i] = left[i - 1] * nums[i - 1]

        for (i in n - 2 downTo 0)
            right[i] = right[i + 1] * nums[i + 1]

        val result = IntArray(n) { 0 }
        for (i in 0 until n)
            result[i] = left[i] * right[i]

        return result
    }
}

class Solution {
    fun productExceptSelf(nums: IntArray): IntArray {
        val result = IntArray(nums.size)
        var prod = 1
        var i = nums.size

        while (i > 0) {
            i--
            result[i] = prod
            prod *= nums[i]
        }

        prod = 1
        while (i < nums.size) {
            result[i] *= prod
            prod *= nums[i]
            i++
        }

        return result
    }
}

fun productExceptSelf(nums: IntArray): IntArray {
    var prod = 1
    var i = 0

    while (i != nums.size) {
        val prev = prod
        prod *= nums[i]
        println(prev)
        nums[i] = ((prev shl 6) and 0x7FFFFFE0) or (nums[i] and 0x3F)
        i++
    }

    prod = 1
    i = nums.size

    while (i != 0) {
        i--

        var ni = nums[i] and 0x3F
        if (ni and 0x20 != 0) {
            ni -= 0x40
        }

        var left = nums[i] shr 6
        if (left and 0x1000000 != 0) {
            left -= 0x2000000
        }
        nums[i] = left * prod
        prod *= ni
    }

    return nums
}

class Solution {    // one loop
    fun productExceptSelf(nums: IntArray): IntArray {
        val end = nums.lastIndex
        val res = IntArray(nums.size) { 1 }
        var left = 1
        var right = 1

        for (i in nums.indices) {
            res[i] *= left
            res[end - i] *= right

            right *= nums[end - i]
            left *= nums[i]
        }

        return res
    }
}

class Solution {    // two loops
    fun productExceptSelf(nums: IntArray): IntArray {
        val res = IntArray(nums.size)
        var mod = 1

        for (i in nums.indices) {
            res[i] = mod
            mod *= nums[i]
        }

        mod = 1
        for (i in nums.lastIndex downTo 0) {
            res[i] *= mod
            mod *= nums[i]
        }

        return res
    }
}

class Solution {        // two loops
    fun productExceptSelf(nums: IntArray): IntArray {
        val left = IntArray(nums.size)
        val right = IntArray(nums.size)

        var mod = 1
        for (i in nums.indices) {
            left[i] = mod
            mod *= nums[i]
        }

        mod = 1
        for (i in nums.lastIndex downTo 0) {
            right[i] = mod
            mod *= nums[i]
        }

        return IntArray(nums.size) { i -> left[i] * right[i] }
    }
}