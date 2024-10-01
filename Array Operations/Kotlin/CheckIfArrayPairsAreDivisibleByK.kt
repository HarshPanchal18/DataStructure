/*
* Check If Array Pairs Are Divisible by k

Given an array of integers arr of even length n and an integer k.
We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.
Return true If you can find a way to do that or false otherwise.

Example 1:
Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
Output: true
Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).

Example 2:
Input: arr = [1,2,3,4,5,6], k = 7
Output: true
Explanation: Pairs are (1,6),(2,5) and(3,4).

Example 3:
Input: arr = [1,2,3,4,5,6], k = 10
Output: false
Explanation: You can try all possible pairs to see that there is no way to divide arr into 3 pairs each with sum divisible by 10.

Constraints:
arr.length == n
1 <= n <= 105
n is even.
-109 <= arr[i] <= 109
1 <= k <= 105
*/

fun main() {
    println(canArrange(intArrayOf(1, 2, 3, 4, 5, 10, 6, 7, 8, 9), 5))
}

fun canArrange(arr: IntArray, k: Int): Boolean {
    val count = IntArray(k)

    for (n in arr) {
        val reminder = ((n % k) + k) % k
        count[reminder]++
    }

    for (i in 1..<k) {
        if (count[i] != count[k - i])
            return false
    }

    return count[0] % 2 == 0
}

class Solution {
    fun canArrange(arr: IntArray, k: Int): Boolean {
        val mods = IntArray(k)
        var count = 0

        for (i in arr) {
            var mod = i % k

            if (mod < 0) mod += k
            val partner = if (mod == 0) 0 else k - mod

            if (mods[partner] > 0) {
                mods[partner]--
                count--
            } else {
                mods[mod]++
                count++
            }
        }

        return count == 0
    }
}