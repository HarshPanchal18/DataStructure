/*
* Check if There is a Valid Partition For The Array
You are given a 0-indexed integer array nums. You have to partition the array into one or more contiguous subarrays.
We call a partition of the array valid if each of the obtained subarrays satisfies one of the following conditions:
The subarray consists of exactly 2 equal elements. For example, the subarray [2,2] is good.
The subarray consists of exactly 3 equal elements. For example, the subarray [4,4,4] is good.
The subarray consists of exactly 3 consecutive increasing elements, that is, the difference between adjacent elements is 1. For example, the subarray [3,4,5] is good, but the subarray [1,3,5] is not.
Return true if the array has at least one valid partition. Otherwise, return false.

Example 1:
Input: nums = [4,4,4,5,6]
Output: true
Explanation: The array can be partitioned into the subarrays [4,4] and [4,5,6].
This partition is valid, so we return true.

Example 2:
Input: nums = [1,1,1,2]
Output: false
Explanation: There is no valid partition for this array.
*/

fun validPartition(nums: IntArray): Boolean {
    val stack = ArrayDeque<Int>()
    stack.push(nums.size - 1)
    while(stack.size > 0) {
        val i = stack.pop() // Popping the top item.
        when (i) {
            (i == -1) -> return true // End of our list, no more items.
            (i < 1) -> continue
            (nums[i] == nums[i-1]) -> stack.push(i - 2) // Check and push (number -2) if a number in our list is equal to itself or one less than itself.
            (i < 2) -> continue // number -3 was popped off of our list before this iteration started.
            (nums[i] == nums[i-1] && nums[i] == nums[i-2]) -> stack.push(i - 3)
            (nums[i] == nums[i-1] + 1 && nums[i] == nums[i-2] + 2) -> stack.push(i - 3)
        }
    }
    return false
}