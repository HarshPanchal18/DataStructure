/*
* Sum of Square Numbers
Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.

Example 1:
Input: c = 5
Output: true
Explanation: 1 * 1 + 2 * 2 = 5

Example 2:
Input: c = 3
Output: false

Constraints:
0 <= c <= 231 - 1
*/

fun main() {
    println(judgeSquareSum(5))
}

fun judgeSquareSum(c: Int): Boolean {
    val limit = Math.sqrt(c.toDouble()).toInt()
    val reminders = hashSetOf<Int>()

    for(i in 0..limit) {
        val current = i * i
        if(reminders.contains(c - current) || (current + current) == c)
            return true
        reminders.add(current)
    }

    return false
}

class Solution {
    fun judgeSquareSum(c: Int): Boolean {
        for (a in 0..sqrt(c.toDouble()).toInt()) {
            val b2 = c - (a * a)
            val b = sqrt(b2.toDouble()).toInt()
            if (b2 == (b*b)) return true
        }
        return false
    }
}

fun judgeSquareSum(c: Int): Boolean {

    val set = HashSet<Int>()

    val root = Math.sqrt(c.toDouble()).toInt()
    for(i in 0..root) {
        set.add(c - i * i)
        if(set.contains(i*i))
            return true
    }

    return false
}

fun judgeSquareSum(c: Int): Boolean {

    var l = 0
    var r = Math.sqrt(c.toDouble()).toInt()

    while(l <= r){
        val sum = l*l + r*r
        if(sum == c) return true

        // added sum < 0 for sum overflow cases
        if(sum > c || sum < 0) r--
        else l++
    }
    return false
}


fun judgeSquareSum(c: Int): Boolean {
    val s = Math.sqrt(c.toDouble()).toLong()
    for (a in 0..s) {
        var lo = 0L; var hi = s
        while (lo <= hi) {
            val mid = lo + (hi - lo) / 2
            val sum = a * a + mid * mid
            if (sum == c.toLong()) return true
            if (sum > c.toLong()) hi = mid - 1
            else lo = mid + 1
        }
    }
    return false
}

class Solution {
    fun judgeSquareSum(c: Int): Boolean {
        var a = 0
        var b = sqrt(c.toDouble()).toInt()
        while (a <= b) {
            val a2 = a * a
            val b2 = b * b

            val res = c - a2 - b2
            if (res < 0) {
                --b
            } else if (res > 0) {
                ++a
            } else {
                return true
            }
        }
        return false
    }
}