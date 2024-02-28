/*
* Missing Number
Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.

Example 1:
Input: nums = [3,0,1]
Output: 2
Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.

Example 2:
Input: nums = [0,1]
Output: 2
Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.

Example 3:
Input: nums = [9,6,4,2,3,5,7,0,1]
Output: 8
Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.

Constraints:
n == nums.length
1 <= n <= 104
0 <= nums[i] <= n
All the numbers of nums are unique.

Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
*/

fun main() {
    print(missingNumber(intArrayOf(3, 0, 1)))
}

fun missingNumber(nums: IntArray): Int {
    nums.sort()
    for (num in nums.indices)
        if (nums[num] != num)
            return num

    return nums.max() + 1
}

internal class Solution {
    fun missingNumber(nums: IntArray): Int {
        val n = nums.size
        val v = IntArray(n + 1)
        Arrays.fill(v, -1)

        for (i in nums.indices)
            v[nums[i]] = nums[i]

        for (i in v.indices)
            if (v[i] == -1)
                return i

        return 0
    }
}

class Solution {
    fun missingNumber(nums: IntArray): Int {
        val n = nums.size
        var ans = 0

        for (i in 1..n)
            ans = ans xor i

        for (i in nums.indices)
            ans = ans xor nums[i]

        return ans
    }
}

internal class Solution {
    fun missingNumber(nums: IntArray): Int {
        val n = nums.size
        val Tsum = (n * (n + 1)) / 2
        val actualSum = Arrays.stream(nums).sum()
        return Tsum - actualSum
    }
}

internal class Solution {
    fun missingNumber(nums: IntArray): Int {
        Arrays.sort(nums)
        val n = nums.size

        // Case 1
        if (nums[0] != 0)
            return 0

        // Case 2
        if (nums[n - 1] != n)
            return n

        // Case 3
        for (i in 1..<nums.size)
            if (nums[i] != i) return i

        return 0
    }
}

internal class Solution {
    fun missingNumber(nums: IntArray): Int {
        val n = nums.size
        var sum = n * (n + 1) / 2

        for (num in nums)
            sum -= num

        return sum
    }
}

class Solution {
    fun missingNumber(nums: IntArray): Int {
        var n = 0
        var i = 0
        while (i < nums.size) {
            n -= nums[i]
            n += i
            i++
        }
        n += nums.size
        return n
    }
}

class Solution {
    fun missingNumber(nums: IntArray): Int {
        var result = nums.size
        result *= nums.size + 1
        result = result shr 1
        result -= sum(nums)
        return result
    }

    private fun sum(nums: IntArray): Int {
        var sum = 0
        var i = 0
        while (i < nums.size) {
            sum += nums[i]
            i++
        }
        return sum
    }
}

class Solution {
    fun missingNumber(nums: IntArray): Int {
        var result = nums.size
        result *= nums.size + 1
        result = result shr 1
        result -= nums.sum()
        return result
    }
}

class Solution {
    fun missingNumber(nums: IntArray): Int {
        var sum = nums.size * (nums.size + 1) / 2
        nums.forEach { sum -= it }
        return sum
    }
}

class Solution {
    fun missingNumber(nums: IntArray): Int =
        nums.reduceIndexed { index, acc, i -> acc xor index xor i } xor nums.size
}

class Solution {
    fun missingNumber(nums: IntArray): Int =
        nums.fold((nums.size + 1) * nums.size / 2) { sum, n -> sum - n }
}

class Solution {
    fun missingNumber(nums: IntArray): Int {
        return (nums.size * (nums.size + 1)) / 2 - nums.sum()
    }
}

class Solution {
    fun missingNumber(nums: IntArray): Int {

        for (i in nums.indices) {
            for (j in nums) {
                if (i == j)
                    break
                if (j == nums[nums.size - 1])
                    return i
            }
        }

        return nums.size
    }
}