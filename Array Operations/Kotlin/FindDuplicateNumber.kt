/*
* Find the Duplicate Number
Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
There is only one repeated number in nums, return this repeated number.
You must solve the problem without modifying the array nums and uses only constant extra space.

Example 1:
Input: nums = [1,3,4,2,2]
Output: 2

Example 2:
Input: nums = [3,1,3,4,2]
Output: 3
*/

fun main(args: Array<String>) {
    val arr = intArrayOf(1,3,4,2,2)
    println(findDuplicate(arr))
}

fun findDuplicate(nums: IntArray): Int {
    var duplicate = 0
    val sorted = nums.sorted()

    for(i in 1..nums.size) {
        if(sorted[i-1]==sorted[i])
            duplicate = sorted[i]
    }

    return duplicate
}

fun findDuplicate(nums: IntArray): Int {
    var slow = 0
    var fast = 0

    while(true) {
        slow = nums[slow]
        fast = nums[nums[fast]]
        if (fast == slow) break
    }

    var slow2 = 0
    while(slow != slow2) {
        slow = nums[slow]
        slow2 = nums[slow2]
    }

    return slow
}

fun findDuplicate(nums: IntArray): Int {
    return nums.toList()
            .sorted()
            .zipWithNext()
            .first { it.first == it.second }
            .first
}

fun findDuplicate(nums: IntArray): Int {
    val set = nums.toSet()
    set.forEach { i ->
        if(nums.indexOf(i) != nums.lastIndexOf(i))
            return i
    }

    return 0
}