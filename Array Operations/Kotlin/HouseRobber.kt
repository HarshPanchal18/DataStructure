/*
* House Robber
You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 2:
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.

Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 400
*/

class Solution {
    fun rob(nums: IntArray): Int {
        when (val n = nums.size) {
            1 -> return nums[0]

            2 -> return max(nums[0],nums[1])

            else -> {
                val houses = IntArray(n)
                houses[n - 1] = nums[n - 1]
                houses[n - 2] = nums[n - 2]
                houses[n - 3] = nums[n - 3] + houses[n - 1]

                for (i in (n - 4).downTo(0))
                    houses[i] = nums[i] + max(houses[i + 2], houses[i + 3])

                return max(houses[0], houses[1])
            }
        }
    }
}

//remember that the key is that we are discovering  the maximum amount that can be robbed from the first i houses, so al lot of info is lost in this algo like the track of which houses we need to rob, but because
//of this, this algo is faster than the others

class Solution {
    fun rob(houseValues: IntArray): Int {
        // 'twoHousesBackMax' stores the max amount that can be robbed up to two houses back.
        var twoHousesBackMax = 0
        // 'oneHouseBackMax' stores the max amount that can be robbed up to the previous house.
        var oneHouseBackMax = 0

        // Iterate through each house in the array.
        for (currentHouse in houseValues) {
            // Calculate the max amount that can be robbed if the current house is included.
            // This is the sum of the current house value and the max amount from two houses back.
            // Compare it with the max amount from one house back (where the current house is not robbed).
            val currentMax = maxOf(twoHousesBackMax + currentHouse, oneHouseBackMax)

            // Update the values for the next iteration.
            // The previous 'oneHouseBackMax' becomes 'twoHousesBackMax'.
            // The 'currentMax' becomes the new 'oneHouseBackMax'.
            twoHousesBackMax = oneHouseBackMax
            oneHouseBackMax = currentMax
        }

        // After iterating through all houses, 'oneHouseBackMax' contains the maximum amount
        //that can be robbed.
        return oneHouseBackMax
    }
}

// Recursion + memo
class Solution {
    val memo = arrayOfNulls<Int>(100)

    fun rob(nums: IntArray): Int {
        return robFrom(0, nums)
    }

    fun robFrom(i: Int, nums: IntArray): Int {
        if (i >= nums.size) return 0
        if (memo[i] != null ) return memo[i]!!

        val ans = max(robFrom(i+1, nums), robFrom(i+2, nums) + nums[i])
        memo[i] = ans

        return ans
    }
}

// DP + TopDown
class Solution {
    fun rob(nums: IntArray): Int {
        if (nums.isEmpty()) return 0

        val maxRobbedAmount = arrayOfNulls<Int>(nums.size+1)
        val N = nums.size

        maxRobbedAmount[N] = 0
        maxRobbedAmount[N-1] = nums[N-1]

        for (i in N-2 downTo 0)
            maxRobbedAmount[i] = kotlin.math.max(maxRobbedAmount[i+1]!!, maxRobbedAmount[i+2]!! + nums[i])

        return maxRobbedAmount[0]!!
    }
}