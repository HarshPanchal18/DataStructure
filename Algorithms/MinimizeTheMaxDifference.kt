/*
* Minimize the Maximum Difference of Pairs
You are given a 0-indexed integer array nums and an integer p.
Find p pairs of indices of nums such that the maximum difference amongst all the pairs is minimized.
Also, ensure no index appears more than once amongst the p pairs.
Note that for a pair of elements at the index i and j, the difference of this pair is |nums[i] - nums[j]|, where |x| represents the absolute value of x.
Return the minimum maximum difference among all p pairs.
We define the maximum of an empty set to be zero.

Example 1:
Input: nums = [10,1,2,7,1,3], p = 2
Output: 1
Explanation: The first pair is formed from the indices 1 and 4, and the second pair is formed from the indices 2 and 5.
The maximum difference is max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) = max(0, 1) = 1. Therefore, we return 1.

Example 2:
Input: nums = [4,2,1,2], p = 1
Output: 0
Explanation: Let the indices 1 and 3 form a pair. The difference of that pair is |2 - 2| = 0, which is the minimum we can attain.
*/

fun main(args:Array<String>) {
    val arr = intArrayOf(4,5,6,7,0,1,2)
    println(minimizeMax(arr,4).toString())
}

fun minimizeMax(nums:IntArray, p:Int): Int {
    nums.sort()

    var low = 0
    var high = nums[nums.size-1] - nums[0]
    var res = 0 // Minimum difference

    while(low<=high) {
        var mid = low + (high - low) / 2

        if(processor(nums,p,mid)) {
            res = mid
            high = mid - 1
        } else
            low = mid + 1
    }
    return res
}

fun processor(nums:IntArray,p:Int,mid:Int): Boolean {
    var count = 0
    var i = 1

    // Check if the difference between adjacent elements <= middle value
    while(i<nums.size) {
        if(nums[i]-nums[i-1] <= mid) {
            count++
            i++
        }
        // if count reaches the same value as p, return true
        if(count>=p) return true
        i++
    }
    return false
}