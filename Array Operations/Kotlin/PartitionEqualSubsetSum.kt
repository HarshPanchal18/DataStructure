/*
* Partition Equal Subset Sum

Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.

Example 1:
Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:
Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.

Constraints:
1 <= nums.length <= 200
1 <= nums[i] <= 100
*/

fun main() {
    println(canPartition(intArrayOf(1, 5, 11, 5)))
    println(canPartition(intArrayOf(3, 3, 3, 4, 5)))
}

fun canPartition(nums: IntArray): Boolean {
    val sum = nums.sum()
    if (sum.mod(2) != 0) return false

    var dp = mutableSetOf<Int>()
    dp.add(0)
    val target = sum / 2
    println("Target = $target")

    for (n in nums) {
        val temp = mutableSetOf<Int>()

        for (ele in dp) {
            if (n + ele == target) return true

            temp.add(ele)
            temp.add(n + ele)
        }

        dp = temp
        println(dp)
    }

    return dp.contains(target)
}

class Solution {
    fun canPartition(nums: IntArray): Boolean {
        val sum = nums.reduce { acc, i -> acc + i }
        if (sum % 2 == 1) return false

        val partSum = sum / 2
        val availableNums = IntArray(nums.size) { -1 }

        val cache = HashMap<Int, Boolean>()
        return findCanPart(nums, availableNums, partSum, cache)
    }

    fun findCanPart(nums: IntArray, availableNums: IntArray, partSum: Int, cache: HashMap<Int, Boolean>): Boolean {
        if (cache[partSum] != null) return cache[partSum]!!

        if (partSum == 0) return true
        if (partSum < 0) return false

        nums.forEachIndexed { index, i ->
            if (availableNums[index] == -1) {
                availableNums[index] = 1
                if (findCanPart(nums, availableNums, partSum - i, cache)) {
                    cache[partSum - i] = true
                    return true
                }
                availableNums[index] = -1
            }
        }

        cache[partSum] = false
        return false
    }
}

class Solution {
    fun canPartition(nums: IntArray): Boolean {
        val sumByTwo = if (nums.sum() % 2 == 0) nums.sum() / 2 else return false
        val sum = mutableSetOf<Int>()
        val numsList = nums.toMutableList()
        recur(numsList, sum)
        return sum.contains(sumByTwo)
    }

    fun recur(numsList: MutableList<Int>, sum: MutableSet<Int>) {
        val first = (if (numsList.isEmpty()) null else numsList.removeFirst()) ?: return
        sum.addAll(sum.map { it + first })
        sum.add(first)
        recur(numsList, sum)
    }

}

class Solution {
    fun canPartition(nums: IntArray): Boolean {
        val n = nums.count()
        var currentSum = 0
        val prefixSum = IntArray(n) { i ->
            currentSum += nums[i]
            currentSum
        }
        val dp = Array(n + 1) { BooleanArray(prefixSum[n - 1] + 1) { false } }

        dp[0][0] = true
        for (i in 1..n) {
            for (j in 0..prefixSum[i - 1]) {
                if (j == 0) dp[i][j] = true
                else if (nums[i - 1] <= j) dp[i][j] = dp[i - 1][j] or dp[i - 1][j - nums[i - 1]]
                else dp[i][j] = dp[i - 1][j]
            }
        }

        return prefixSum[n - 1] % 2 == 0 && dp[n][prefixSum[n - 1] / 2]
    }
}

class Solution {
    fun canPartition(nums: IntArray): Boolean {
        val total = nums.sum()

        if (total % 2 != 0) return false

        val target = total / 2
        val dp = BooleanArray(target + 1) { false }
        dp[0] = true

        for (num in nums)
            for (j in target downTo num)
                if (dp[j - num])
                    dp[j] = true

        return dp[target]
    }
}

class Solution {
    fun canPartition(nums: IntArray): Boolean {
        val sum = nums.sum()
        if (sum % 2 != 0) return false

        val subsetSum = sum / 2
        return dp(nums, nums.size - 1, subsetSum, mutableMapOf())
    }

    private fun dp(nums: IntArray, i: Int, subsetSum: Int, memoMap: MutableMap<Pair<Int, Int>, Boolean>): Boolean {
        if (subsetSum == 0) return true
        if (i == 0 || subsetSum < 0) return false

        val key = Pair(i, subsetSum)
        if (memoMap[key] != null) return memoMap[key]!!

        memoMap[key] = dp(nums, i - 1, subsetSum - nums[i - 1], memoMap) || dp(nums, i - 1, subsetSum, memoMap)
        return memoMap[key]!!
    }
}