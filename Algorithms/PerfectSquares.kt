/*
* Perfect Squares
Given an integer n, return the least number of perfect square numbers that sum to n.
A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.

Example 1:
Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.

Example 2:
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.

Constraints:
1 <= n <= 104
*/

fun main() {
    println(numSquares(20))
}

fun numSquares(n: Int): Int {
    val dp = IntArray(n + 1) { it }
    for (i in 1..n)
        for (j in 1..Math.sqrt(i.toDouble()).toInt())
            dp[i] = minOf(dp[i], dp[i - j * j] + 1)
    return dp[n]
}

fun numSquares(n: Int): Int {
    val dp = IntArray(n + 1) { it }
    val sq = IntArray(100) { (it + 1) * (it + 1) }
    for (i in 1..n)
        for (v in sq) {
            if (v > i) break
            dp[i] = minOf(dp[i], 1 + dp[i - v])
        }
    return dp[n]
}

fun numSquares(n: Int): Int
{
    var num = n

    //if the number itself a perfect square
    if ((n.toDouble().pow(0.5))%1==0.0) return 1

    //we will look are there any prime multipliers 4k+3 type
    var TwoSquares: Boolean = true
    for (i in 1..n) {
        if ((n%i==0) and ((i-3)%4==0 )) {
            //if we've found a prime multipliers 4k+3 type, we count it's power
            var counter=0
            while (num%i==0) {
                counter+=1
                num/=i
            }
            if (counter%2!=0)
                TwoSquares=false
        }

        if (!TwoSquares)
            break //if we have found prime multiplier 4k+3 type in odd power - break the cycle
    }

    if (TwoSquares) return 2
    else { //if we'found prime multiplier 4k+3 type in odd power
        // we check, is the number 4^k*(8n+7) type
        num=n
        while (num%4==0) num/=4
        num-=7

        if (num%8==0) return 4
        else return 3
    }
}

class Solution {
    fun numSquares(n: Int): Int {
        return getOrDefault(n) {
            var result = n
            var i = 2
            while (true) {
                val square = squares[i]
                if (square > n) break
                val perfectSquares = n / square
                val remainedArea = n % square
                result = Math.min(result, perfectSquares + numSquares(remainedArea))
                i++
            }
            result
        }
    }

    private fun getOrDefault(key: Int, computation: () -> Int): Int {
        val result = cache[key]
        return when {
            result != -1 -> result
            else -> computation().apply {
                cache[key] = this
            }
        }
    }

    companion object {
        val squares = IntArray(101) { it * it }
        val cache = IntArray(10_001) { -1 }
    }
}

fun numSquares(n: Int): Int {
    val dp = IntArray(n + 1)
    for (x in 1..n)
        dp[x] = (1..sqrt(x.toFloat()).toInt())
            .minOf { j ->
                1 + dp[x - j * j]
            }
    return dp[n]
}

class Solution {
    fun numSquares(n: Int): Int {
        return if (n.isSqr()) 1
        else {
            var m = n
            while (m.and(3) == 0) m = m.shr(2)
            when {
                    (m.and(7) == 7)                             -> 4
                    (1..m.sqrt()).any { (m - it * it).isSqr() } -> 2
                    else                                        -> 3
            }
        }
    }

    private fun Int.sqrt() = sqrt(this.toFloat()).toInt()
    private fun Int.isSqr() = this.sqrt().let { it * it == this }
}

// Recurssive Solution - Slowest
class Solution {
    fun numSquares(n: Int, map: MutableMap<Int, Int> = mutableMapOf<Int, Int>()): Int {
        if(n == 0)
            return 0

        if(map[n] != null){ return map[n]!! }

        var count = Int.MAX_VALUE
        var sqrt = Math.sqrt(n.toDouble()).toInt()

        (1..sqrt).forEach {
            count = Math.min(count, numSquares(n - it*it, map))
        }
        map[n] = count +1
        return count + 1
    }
}

// DP - DFS - With Static Map - Still better
val map = IntArray(10001){Int.MAX_VALUE}
class Solution {
    fun numSquares(n: Int): Int {
        map[0] = 0
        map[1] = 1
        map[2] = 2
        map[3] = 3

        (4..n).forEach{ main ->
            var sqrt = Math.sqrt(main.toDouble()).toInt()
            if(sqrt*sqrt == main) {
                map[main] = 1
            } else {
                (1..sqrt).forEach{
                   map[main] = Math.min(map[main], map[main - it*it] + 1)
                }
            }
        }
        return map[n]
    }
}

// Recurssive - DP - DFS - Fastest
val map = IntArray(10001){Int.MAX_VALUE}.also{
    it[0] = 0
    it[1] = 1
    it[2] = 2
    it[3] = 3
}

class Solution {
    fun numSquares(n: Int): Int {
        if(n == 0)
            return 0

        if(map[n] != Int.MAX_VALUE) { return map[n] }
        var count = Int.MAX_VALUE
        var i = 1
        while(i*i <= n) {
            count = Math.min(count, numSquares(n - i*i))
            ++i
        }
        map[n] = count + 1
        return map[n]
    }
}

// BFS
class Solution {
    fun numSquares(n: Int): Int {
        val map = BooleanArray(10001){false}
        val queue = LinkedList<Int>()
        var depth = 0

        queue.offer(n)

        while(!queue.isEmpty()) {
            var size = queue.size
            while(size-- > 0) {
                val current = queue.poll()
                if(current == 0)
                    return@numSquares depth

                var i = 1
                while(i*i <= current) {
                    var temp = current - (i * i)
                    if(map[temp] == false) {
                        queue.offer(temp)
                        map[temp] = true
                    }
                    ++i
                }
            }
            depth++
        }
        return -1
    }
}