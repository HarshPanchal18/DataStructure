/*
* Majority Element
Microsoft | Yahoo | Google | Amazon
Given an array nums of size n, return the majority element.
The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

Example 1:
Input: nums = [3,2,3]
Output: 3

Example 2:
Input: nums = [2,2,1,1,1,2,2]
Output: 2

Constraints:
n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109
*/

fun main() {
    print(majorityElement(intArrayOf(3,2,3)))
}

fun majorityElement(nums: IntArray): Int {
    nums.forEach { n ->
        if (nums.count { it == n } > nums.size / 2)
            return n
    }
    return 0
}

fun majorityElement(nums: IntArray): Int {
    val map: MutableMap<Int, Int> = HashMap()
    val n = nums.size / 2

    for (num in nums) {
        map[num] = map.getOrDefault(num, 0) + 1
    }

    var ans = 0

    for ((key, value) in map) {
        if (value > n) {
            ans = key
            break
        }
    }

    return ans
}

// Boyer-Moore Majority Voting Algorithm
fun majorityElement(nums: IntArray): Int {
    var count = 0
    var element = 0

    for (num in nums) {
        if (count == 0) {
            element = num
            count = 1
        } else if (element == num) {
            count++
        } else {
            count--
        }
    }

    return element
}

fun majorityElement(nums: IntArray): Int {
    nums.sort()
    return nums[nums.size / 2]
}

fun majorityElement(nums: IntArray): Int {
    var cnt = 0
    var ele = -1
    nums.indices.forEach { i ->
        when {
            cnt == 0 -> {
                cnt = 1
                ele = nums[i]
            }

            nums[i] == ele -> cnt++
            else -> cnt--
        }
    }
    return ele
}

fun majorityElement(nums: IntArray): Int {
    val occ = HashMap<Int, Int>()
    nums.forEach {
        occ[it] = occ.getOrDefault(it, 0) + 1
    }
    return occ.maxBy { it.value }.key
}

fun majorityElement(nums: IntArray): Int {
    val count = HashMap<Int, Int>()
    for (n in nums) {
        count[n] = count.getOrDefault(n, 0) + 1
        if (count[n]!! > nums.size / 2) return n
    }
    return -1
}

fun majorityElement(ar: IntArray) = ar.find { n -> ar.count { it == n } > ar.size / 2 }