import java.util.*
import kotlin.math.ceil

/*
* Maximal Score After Applying K Operations

You are given a 0-indexed integer array nums and an integer k. You have a starting score of 0.

In one operation:

* choose an index i such that 0 <= i < nums.length,
* increase your score by nums[i], and
* replace nums[i] with ceil(nums[i] / 3).

Return the maximum possible score you can attain after applying exactly k operations.

The ceiling function ceil(val) is the least integer greater than or equal to val.

Example 1:
Input: nums = [10,10,10,10,10], k = 5
Output: 50
Explanation: Apply the operation to each array element exactly once. The final score is 10 + 10 + 10 + 10 + 10 = 50.

Example 2:
Input: nums = [1,10,3,3,3], k = 3
Output: 17
Explanation: You can do the following operations:
Operation 1: Select i = 1, so nums becomes [1,4,3,3,3]. Your score increases by 10.
Operation 2: Select i = 1, so nums becomes [1,2,3,3,3]. Your score increases by 4.
Operation 3: Select i = 2, so nums becomes [1,1,1,3,3]. Your score increases by 3.
The final score is 10 + 4 + 3 = 17.

Constraints:
1 <= nums.length, k <= 105
1 <= nums[i] <= 109
*/

fun main() {
    println(maxKelements(intArrayOf(10, 10, 10, 10, 10), 5))
}

fun maxKelements(nums: IntArray, k: Int): Long {
    val pq = PriorityQueue<Int>(compareByDescending { it })
    var sum = 0L
    pq.addAll(nums.toList())

    for (i in 0..<k) {
        val n = pq.poll().toDouble()
        pq.add(ceil(n / 3.0).toInt())
        sum += n.toInt()
    }

    return sum
}

class Solution {
    fun maxKelements(nums: IntArray, k: Int): Long {
        fun genNewData(input: Int): Int {
            return if (input % 3 == 0) {
                input / 3
            } else {
                (input / 3) + 1
            }
        }

        val pq = PriorityQueue<Int> { p1, p2 -> p2 - p1 }
        nums.forEach {
            pq.offer(it)
        }

        var result = 0L
        repeat(k) {
            val pop = pq.poll()
            result += pop
            pq.add(genNewData(pop))
        }
        return result
    }
}