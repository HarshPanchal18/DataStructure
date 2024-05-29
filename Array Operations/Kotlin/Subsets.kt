import java.util.*
import kotlin.collections.ArrayList

/*
* Subsets
Given an integer array nums of unique elements, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

Example 2:
Input: nums = [0]
Output: [[],[0]]

Constraints:
1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
*/

fun main() {
    val result = subsets(intArrayOf(1, 2, 3))
    result.forEach { arr ->
        arr.forEach { print("$it ") }
        println()
    }
}

fun subsets(nums: IntArray): List<List<Int>> {
    val result = ArrayList<List<Int>>()
    backtrack(nums, 0, ArrayList(), result)
    return result
}

fun backtrack(nums: IntArray, start: Int, path: MutableList<Int>, result: MutableList<List<Int>>) {
    result.add(ArrayList(path))

    for (i in start until nums.size) {
        path.add(nums[i])
        backtrack(nums, i + 1, path, result)
        path.removeLast()
    }
}

class Solution {
    fun subsets(nums: IntArray): List<List<Int>> {
        val result: MutableList<List<Int>> = ArrayList()
        val n = nums.size

        for (i in 0 until (1 shl n)) {
            val subset: MutableList<Int> = ArrayList()
            for (j in 0 until n) {
                if ((i and (1 shl j)) > 0)
                    subset.add(nums[j])
            }
            result.add(subset)
        }

        return result
    }
}

class Solution {
    fun subsets(nums: IntArray): List<List<Int>> {
        val result: MutableList<List<Int>> = ArrayList()
        result.add(ArrayList()) // Add the empty subset

        for (num in nums) {
            val size = result.size
            for (i in 0 until size) {
                val subset = ArrayList(result[i])
                subset.add(num)
                result.add(subset)
            }
        }

        return result
    }
}

class Solution {
    fun subsets(nums: IntArray): List<List<Int>> {
        val sebsets: MutableList<List<Int>> = mutableListOf()
        val sofar: MutableList<Int> = mutableListOf()

        dfs(nums, 0, sofar, sebsets)

        return sebsets
    }

    fun dfs(nums: IntArray, index: Int, sofar: MutableList<Int>, sebsets: MutableList<List<Int>>) {
        //base case. when index is out of bounds, we add list collected 'sofar' to 'sebsets'
        if (index == nums.size) {
            val result: List<Int> = sofar.toList()
            sebsets.add(result)
            return
        }

        //exclude current value: do not add current value into 'sofar' list
        dfs(nums, index + 1, sofar, sebsets)

        //include current value: add current value into 'sofar' list
        sofar.add(nums[index])
        dfs(nums, index + 1, sofar, sebsets)

        //tricky part. we need to remove last value added so that 'sofar' list will be correct
        sofar.removeLast()
    }
}

/**
nums = [1,2,3]
[]

[1]                             []

[1,2]            [1]             [2]               []

[1,2,3]    [1,2]   [1,3]    [1]    [2,3]    [2]       [3]     []

 */

class Solution {
    fun subsets(nums: IntArray): List<List<Int>> {
        val result: MutableList<List<Int>> = mutableListOf()
        repeat(1 shl nums.size) { n ->
            result += nums.filterIndexed { i, _ ->
                (1 shl i) and n > 0
            }
        }
        return result
    }
}

class Solution {

    fun subsets(nums: IntArray): List<List<Int>> {
        var byteMap = (1 shl nums.size) - 1

        val subs = arrayListOf(listOf<Int>())
        while (byteMap != 0) {
            subs.add(nums.filterIndexed { index, _ ->
                ((byteMap shr index) and 1) == 1
            })
            byteMap -= 1
        }

        return subs
    }
}

class Solution {
    fun subsets(nums: IntArray): List<List<Int>> {
        val ans: MutableList<List<Int>> = mutableListOf(listOf())

        for (i in 1..nums.size) {
            val temp = IntArray(i) { 0 }
            subChoose(nums, ans, temp, 0, i)
        }

        return ans.toList()
    }

    fun subChoose(nums: IntArray, ans: MutableList<List<Int>>, combination: IntArray, startIndex: Int, k: Int): Unit {
        if (k == 1) {
            for (i in startIndex..nums.lastIndex) {
                combination[combination.lastIndex] = nums[i]
                ans.add(combination.toList())
            }
        } else if (k != 0) {
            for (i in startIndex..nums.size - k) {
                combination[combination.size - k] = nums[i]
                subChoose(nums, ans, combination, i + 1, k - 1)
            }
        } else {
            ans.add(combination.toList())
        }
    }
}

class Solution {
    fun subsets(nums: IntArray): List<List<Int>> {
        val results: MutableList<List<Int>> = mutableListOf()

        fun dfs(index: Int, path: Deque<Int>) {
            results.add(ArrayList(path))

            for (i in index until nums.size) {
                path.add(nums[i])
                dfs(i + 1, path)
                path.removeLast()
            }
        }

        dfs(0, LinkedList())
        return results
    }
}