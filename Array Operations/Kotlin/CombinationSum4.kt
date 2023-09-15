/*
* Combination Sum IV
Given an array of distinct integers nums and a target integer target,
return the number of possible combinations that add up to target.
The test cases are generated so that the answer can fit in a 32-bit integer.

Example 1:
Input: nums = [1,2,3], target = 4
Output: 7
Explanation:
The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Example 2:
Input: nums = [9], target = 3
Output: 0
*/

fun main(args: Array<String>) {

    var result = combinationSum4(intArrayOf(1,2,3),4)
    print(result)

    result = combinationSum4(intArrayOf(1,2,3),4)
    print(result)

}

fun combinationSum4(nums: IntArray, target: Int): Int {
    val dp = IntArray(target+1)
    dp[0]=1

    for(i in 1..target) {
        for(num in nums) {
            if(i>=num) {
                dp[i]+=dp[i-num]
            }
        }
        //println("*${i}-${dp[i]}") // 1,2,4,7
    }

    return dp[target]
}

//Time Complexity: O(target * n)
//Space Complexity: O(target)
fun combinationSum4(nums: IntArray, target: Int): Int {

    // bottom-up dp approach
    val dp = IntArray(target + 1)
    dp[0] = 1

    // Traverse the values from 1..target and for each value to calculate
    // that how many ways are available to reach that value and finally `return dp[target]`
    for(value in 1..target) {
        for(num in nums) {
            // if(value - num < 0) continue // skips this num since it is not possible to reach that value with this num
            if(value-num>=0)
                dp[value] = dp[value] + dp[value - num] // sums up the combinations

        }
    }
    return dp[target] // return the target, it will be the latest element
}