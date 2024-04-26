/*
* Decode ways
A message containing letters from A-Z can be encoded into numbers using the following mapping:
'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:

"AAJF" with the grouping (1 1 10 6)
"KJF" with the grouping (11 10 6)
Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

Given a string s containing only digits, return the number of ways to decode it.

The test cases are generated so that the answer fits in a 32-bit integer.
Example 1:
Input: s = "12"
Output: 2
Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).

Example 2:
Input: s = "226"
Output: 3
Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

Example 3:
Input: s = "06"
Output: 0
Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").

Constraints:
1 <= s.length <= 100
s contains only digits and may contain leading zero(s).
*/

fun main(args: Array<String>) {
    println(numDecodings("226"))
}

fun numDecodings(str:String): Int {
    val n = str.length
    val dp = IntArray(n+1)
    dp[0] = 1
    dp[1] = if (str[0] == '0') 0 else 1
    for (i in 2..n) {
        val first = str.substring(i-1, i)
        val second = str.substring(i-2, i)
        if (first.toInt() in 1..9) {
            dp[i] += dp[i-1]
        }
        if (second.toInt() in 10..26) {
            dp[i] += dp[i-2]
        }
    }
    return dp[n]
}

fun numDecodings(str:String): Int {
    val n = str.length
    val arr = IntArray(n) { 0 }
    return recursion(0,str,n,arr)
}

fun recursion(currIndex: Int,str:String,len:Int,dp:IntArray) {

    if (currIndex <= len - 1 && str[currIndex] == '0')
        return dp[currIndex] = 0

    if (currIndex == len - 1 || currIndex == len)
        return 1

    if (dp[currIndex] != 0)
        return dp[currIndex]

    // val char1 = str[currIndex] - '0',
    val char2 = 10 * (str[currIndex] - '0') + (str[currIndex + 1] - '0')
    dp[currIndex] = recursion(currIndex + 1, str, len, dp)

    if (char2 <= 26)
        dp[currIndex] += recursion(currIndex + 2, str, len, dp)

    return dp[currIndex]
}

fun numDecodings(s: String): Int {
    var dp = IntArray(s.length + 1);
    dp[s.length] = 1;
    if (s[s.length - 1] != '0'){
        dp[s.length - 1] = 1;
    }

    for (i in s.length - 2 downTo 0){
        if (s[i] != '0'){
            dp[i] += dp[i + 1];
        }
        if (s[i] == '1' || s[i] == '2' && s[i + 1] < '7'){
            dp[i] += dp[i + 2];
        }
    }
    return dp[0];
}

fun numDecodings(s: String): Int {
    val memo = Array<IntArray>(s.length) { IntArray(27) { -1 } }

    fun dfs(pos: Int, num: Int): Int {
        if (pos == s.length) return if (1 <= num && num <= 26) 1 else 0
        if (num > 26) return 0
        if (memo[pos][num] != -1) return memo[pos][num]

        val c = s[pos].toInt() - '0'.toInt()
        val c2 = num*10+c
        val start = if (c == 0) 0 else dfs(pos+1, c)
        val cont = if (num == 0) 0 else dfs(pos+1, c2)

        memo[pos][num] = start + cont

        return start + cont
    }
    return dfs(0, 0)
}

// Brute force
fun numDecodings(s: String): Int {
    if (s[0] == '0') return 0
    return numWays(s, 0)
}

private fun numWays(s: String, i: Int): Int {
    if (i == s.length) return 1
    if (s[i] == '0') return 0
    var n = Character.getNumericValue(s[i])
    var next = i
    var contribution = 0
    while (n <= 26) {
        contribution += numWays(s, next+1)
        next++
        if (next >= s.length) break
        n = n*10 + Character.getNumericValue(s[next])
    }
    return contribution
}

// Memoized recursion
class Solution {
    private lateinit var memo: IntArray

    fun numDecodings(s: String): Int {
        if (s[0] == '0') return 0
        memo = IntArray(s.length) { -1 }
        return numWays(s, 0)
    }

    private fun numWays(s: String, i: Int): Int {
        if (i == s.length) return 1
        if (memo[i] != -1) return memo[i]
        if (s[i] == '0') {
            memo[i] = 0
            return 0
        }
        var n = Character.getNumericValue(s[i])
        var next = i
        var contribution = 0
        while (n <= 26) {
            contribution += numWays(s, ++next)
            if (next >= s.length) break
            n = n*10 + Character.getNumericValue(s[next])
        }
        memo[i] = contribution
        return memo[i]
    }
}

// Iterative O(n)
fun numDecodings(s: String): Int {
    val memo = IntArray(s.length+1) { -1 }
    memo[s.length] = 1

    for (i in s.length-1 downTo 0) {
        var n = Character.getNumericValue(s[i])
        memo[i] = 0
        if (n == 0) continue

        var next = i
        while (n <= 26) {
            memo[i] += memo[next+1]
            next++
            if (next >= s.length) break
            n = n*10 + Character.getNumericValue(s[next])
        }
    }

    return memo[0]
}