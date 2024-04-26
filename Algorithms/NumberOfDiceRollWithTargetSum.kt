/*
* Number of Dice Rolls With Target Sum
You have n dice, and each die has k faces numbered from 1 to k.
Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the dice, so the sum of the face-up numbers equals target. Since the answer may be too large, return it modulo 10^9 + 7.

Example 1:
Input: n = 1, k = 6, target = 3
Output: 1
Explanation: You throw one die with 6 faces.
There is only one way to get a sum of 3.

Example 2:
Input: n = 2, k = 6, target = 7
Output: 6
Explanation: You throw two dice, each with 6 faces.
There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.

Example 3:
Input: n = 30, k = 30, target = 500
Output: 222616187
Explanation: The answer must be returned modulo 109 + 7.

Constraints:
1 <= n, k <= 30
1 <= target <= 1000

*/

fun main() {
    println(numRollsToTarget(1,6,3))
}

fun numRollsToTarget(n: Int,k: Int, target: Int): Int {
    val dp = IntArray(target + 1) { 0 }
    dp[0] = 1

    for (i in 1..target) {
        for (j in 1..k) {
            if (i - j >= 0)
                dp[i] += dp[i - j]
            //println(dp[i])
        }
        dp[i] %= 1_000_000_007
    }

    return dp[target]
}

val mod = 1e9.toLong() + 7

fun numRollsToTarget(n: Int, k: Int, target: Int): Int {
    return (solve(n, k, target, 0) % mod).toInt();
}

fun solve(n: Int, k: Int, target: Int, sum: Int): Long {
    if(n == 0) {
        if(sum == target) return 1
        return 0
    }

    if(sum == target) {
        return if(n == 0) 1 else 0
    }

    var ans: Long = 0
    for(i in 1..k) {
        if(sum + i <= target) ans = (ans + solve(n-1, k, target, sum + i)) % mod
        else break
    }

    return ans
}

class Solution {
    companion object {
        const val MOD = (1e9 + 7).toInt()
    }

    private lateinit var memo: Array<IntArray>

    fun numRollsToTarget(n: Int, k: Int, target: Int): Int {
        this.memo = Array(n + 1) { IntArray(target + 1) { -1 } }
        return dp(n, k, target)
    }

    private fun dp(n: Int, k: Int, target: Int): Int {
        if (n == 0 && target == 0) return 1
        if (n == 0 || target < 0) return 0
        if (this.memo[n][target] == -1) {
            this.memo[n][target] = 0
            for (i in 1..k) {
                this.memo[n][target] += dp(n - 1, k, target - i)
                this.memo[n][target] %= MOD
            }
        }
        return this.memo[n][target]
    }
}