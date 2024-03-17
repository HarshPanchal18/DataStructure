/*

* Contiguous Array

Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.

Example 1:
Input: nums = [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.

Example 2:
Input: nums = [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

Constraints:
1 <= nums.length <= 105
nums[i] is either 0 or 1.
*/

/* It achieves this by maintaining a running sum and using a HashMap to store the sum and its corresponding index, allowing it to efficiently determine the length of the subarray when the sum value is encountered again in the array. */
fun findMaxLength(nums: IntArray): Int {
    val map = HashMap<Int, Int>() // store the sum and its corresponding index.
    var sum = 0
    var subArrayLength = 0

    nums.forEachIndexed { index, n ->

        sum += if (n == 0) -1 else 1

        if (sum == 0)
            subArrayLength = index + 1 // subarray from index 0 to the current index has equal number of 0s and 1s.
        else if (map.containsKey(sum)) // update the subArrayLength
            subArrayLength = maxOf(
                subArrayLength,
                index - map[sum]!! // current value and the difference between the current index and the index stored for the same sum in the map.
            )
        else
            map[sum] = index // else store the sum in the map with its corresponding index.
    }

    return subArrayLength
}

/*
Test case [0,0,1,0,0,0,1,1]:
Iteration 1: sum = 0, mp[0] = 0, subArray_length = 1
Iteration 2: sum = -1, mp[-1] = 1, subArray_length = 1
Iteration 3: sum = 0, subArray_length = 2 (since sum becomes 0)
Iteration 4: sum = -1, subArray_length = 2 (previously encountered sum)
Iteration 5: sum = -2, mp[-2] = 4, subArray_length = 2
Iteration 6: sum = -3, mp[-3] = 5, subArray_length = 2
Iteration 7: sum = -2, subArray_length = 6 (previously encountered sum)
Iteration 8: sum = -1, subArray_length = 6 (previously encountered sum)
So, the maximum length of a contiguous subarray with an equal number of 0s and 1s is 6.

*/

class Solution {
    // hint: `maximum length of a contiguous subarray`
    // while sliding window would be intuitive to use, actually we cant use it here.
    // Maybe we could use a cache to store zeros,ones.

    fun findMaxLength(nums: IntArray): Int {
        // store ones,zeros in hash and calculate diffs when then appear.
        val counts = hashMapOf<Int, Int>() // count | index

        counts[0] = -1

        var maxLen = 0
        var count = 0
        // One trick we can use here is, to add 1 for ones and, subtract 1 for zeros,
        // this way when count is equal then we know for sure that we have equal number of zeros and ones.
        for (i in nums.indices) {
            val num = nums[i]

            if (num == 1) count += 1
            else count -= 1

            val lastIndex = counts[count] // bad-name
            if (lastIndex == null) {
                counts[count] = i
                continue
            }

            // The way this works is:
            // if we encounter a `count` that already exists in the map,
            // then we know for sure that we have an equal number of zeros and ones.
            // To get the len out of this, we have to diff this points (to be precise indexes).
            maxLen = maxOf(maxLen, i - lastIndex)
        }
        return maxLen
    }

    fun findMaxLength(nums: IntArray): Int {
        var maxLen = 0
        var zeros: Int
        var ones: Int

        for (i in nums.indices) {
            zeros = 0
            ones = 0

            for (j in i..<nums.size) {

                if (nums[j] == 0) zeros++
                else ones++

                if (zeros == ones)
                    maxLen = maxOf(maxLen, j - i + 1)

            }
        }
        return maxLen
    }
}