/*
* Minimum Operations to Reduce X to Zero
You are given an integer array nums and an integer x.
In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x.
Note that this modifies the array for future operations.
Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.

Example 1:
Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.

Example 2:
Input: nums = [5,6,7,8,9], x = 4
Output: -1

Example 3:
Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.
*/

fun main(args: Array<String>) {
    val arr = intArrayOf(1,1,4,2,3)
    val x = 5
    print(minOperations(arr,x))
}

fun minOperations(nums: IntArray, x: Int): Int {
    val n = nums.size
    var target = nums.sum() - x
    var maxLen = 0
    var sum = 0
    var left = 0

    for(right in 0 until n) {
        sum += nums[right]

        while(left <= right &&  sum > target)
            sum -= nums[left++]

        if(sum == target)
            maxLen = Math.max(maxLen, right - left + 1)
    }

    return when {
        maxLen != 0 -> n - maxLen
        else -> -1
    }
}

fun minOperations(nums: IntArray, x: Int): Int {
    //find longest subarray sum to total_sum - x
    var target = nums.sum() - x; var sum = 0
    var left = 0; var ans = -1;

    for(right in 0 until nums.size)
    {
        sum += nums[right]
        while(left <= right && sum > target) //move left pointer
            sum -= nums[left++];
        if(target == sum)
            ans = max(ans, right - left + 1)
    }
    return if(ans == - 1) -1 else nums.size - ans
}
