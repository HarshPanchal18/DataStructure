/*
* 2 Keys Keyboard
There is only one character 'A' on the screen of a notepad.
You can perform one of two operations on this notepad for each step:
Copy All: You can copy all the characters present on the screen (a partial copy is not allowed).
Paste: You can paste the characters which are copied last time.
Given an integer n, return the minimum number of operations to get the character 'A' exactly n times on the screen.

Example 1:
Input: n = 3
Output: 3
Explanation: Initially, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.

Example 2:
Input: n = 1
Output: 0

Constraints:
1 <= n <= 1000
*/

fun main(args: Array<String>) {
    println(minSteps(3))
}

fun minSteps(n: Int): Int {
    if(n == 1)
        return 0

    var result = 0
    var currentN = n

    var divisor = 2
    while(currentN > 1) { // Factorize N
        while(currentN % divisor == 0) {
            result += divisor // Sum factors
            currentN = currentN / divisor
        }
        divisor++
    }

    return result
}

class Solution {
    fun solve(n: Int): Int {
        var divider: Int = -1
        for (i in 2 until n / 2) {
            if (n % i == 0) {
                divider = i
                break
            }
        }

        return if (divider == -1 || divider == n) {
            n
        } else {
            divider + solve(n / divider)
        }
    }

    fun minSteps(n: Int): Int {
        if (n == 1) return 0
        return solve(n)
    }
}

class Solution {
    fun minSteps(n: Int): Int {
        if (n == 1) return 0
        if (n == 2) return 2
        return 2 + _minSteps(2, n, 1, Array(n+1) { IntArray(n) })
    }

    fun _minSteps(c: Int, n: Int, p: Int, dp: Array<IntArray>): Int {
        if (dp[c][p] != 0)
            return dp[c][p]

        var min = 10000

        if (c+p == n)
            return 1
        else if (c+p < n)
            min = 1 + _minSteps(c+p, n, p, dp)


        if (c*2 == n)
            return 2
        else if (c*2 < n)
            min = minOf(min, 2+_minSteps(c*2, n, c, dp))

        dp[c][p] = min

        return min
    }
}

class Solution {
    val cache = mutableMapOf<Int, Int>()

    fun minSteps(n: Int): Int {
        if (n == 1)
            return 0

        if (cache[n] != null)
            return cache[n]!!

        var cur = n

        for (i in 1 until (n / 2) + 1) {
            if (n % i == 0) {
                val new = minSteps(i) + (n / i)
                cur = min(new , cur)
            }
        }
        cache[n] = cur

        return cur
    }
}