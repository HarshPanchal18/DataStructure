/*
* Make Sum Divisible by P

Given an array of positive integers nums, remove the smallest subarray (possibly empty) such that the sum of the remaining elements is divisible by p. It is not allowed to remove the whole array.
Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
A subarray is defined as a contiguous block of elements in the array.

Example 1:
Input: nums = [3,1,4,2], p = 6
Output: 1
Explanation: The sum of the elements in nums is 10, which is not divisible by 6. We can remove the subarray [4], and the sum of the remaining elements is 6, which is divisible by 6.

Example 2:
Input: nums = [6,3,5,2], p = 9
Output: 2
Explanation: We cannot remove a single element to get a sum divisible by 9. The best way is to remove the subarray [5,2], leaving us with [6,3] with sum 9.

Example 3:
Input: nums = [1,2,3], p = 3
Output: 0
Explanation: Here the sum is 6. which is already divisible by 3. Thus we do not need to remove anything.

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 109
1 <= p <= 109
*/

fun main() {
    minSubarray(intArrayOf(3, 1, 4, 2), 6)
    minSubarray(intArrayOf(6, 3, 5, 2), 9)
}

fun minSubarray(nums: IntArray, p: Int): Int {
    val n = nums.size
    val prefix = LongArray(n + 1) { 0 }

    for (i in nums.indices) {
        prefix[i + 1] = prefix[i] + nums[i]
    }

    val required = prefix[n] % p
    if (required.toInt() == 0)
        return 0

    for (windowSize in 1 until n) {
        var j = 0
        while (j + windowSize - 1 < n) {
            // when you get the required modulo from a window, return window size
            if ((prefix[j + windowSize] - prefix[j]) % p == required)
                return windowSize
            j++
        }
    }

    return -1
}

class Solution {
    fun minSubarray(nums: IntArray, p: Int): Int {
        return minSubarray(nums.map { it.toLong() }.toLongArray(), p)
    }

    fun minSubarray(nums: LongArray, p: Int): Int {
        val remainder = nums.sum() % p
        if (remainder == 0L) return 0

        val subSums = LongArray(nums.size)
        for (length in 1..<nums.size) {
            for (start in 0..(nums.size - length)) {
                subSums[start] += nums[start + length - 1]

                if (subSums[start] % p == remainder)
                    return length
            }
        }
        return -1
    }
}

class Solution {
    fun minSubarray(nums: IntArray, p: Int): Int {
        var sum = 0

        for (i in nums.indices) {
            nums[i] %= p
            sum += nums[i]
            if (sum >= p)
                sum -= p
        }

        if (sum == 0) return 0

        val subArraySum = sum
        var minLength = nums.size
        val modIndexMap = HashMap<Int, Int>()
        modIndexMap[0] = -1
        sum = 0

        for (i in nums.indices) {
            sum += nums[i]
            if (sum >= p) sum -= p
            var pairedSum = (sum - subArraySum + p)
            if (pairedSum >= p) pairedSum -= p

            val pairedIndex = modIndexMap[pairedSum]
            if (pairedIndex != null) {
                minLength = minOf(minLength, i - pairedIndex)
            }
            modIndexMap[sum] = i
        }

        return if (minLength == nums.size) -1 else minLength
    }
}

class Solution {
    fun minSubarray(nums: IntArray, p: Int): Int {
        val remToInd = HashMap<Long, Int>()
        remToInd[0] = -1

        var sum = 0L
        val target = nums.sumOf { it.toLong() % p } % p

        return nums.withIndex().minOf { (i, n) ->
            sum = (sum + n % p) % p
            remToInd[sum] = i
            i - (remToInd[(p + sum - target) % p] ?: -nums.size)
        }.takeIf { it < nums.size } ?: -1
    }
}

class Solution {
    fun minSubarray(nums: IntArray, p: Int): Int {
        var total = nums.sum().toLong()
        val hp = HashMap<Long, Int>()

        val rem = total % p
        hp[0] = -1

        if (rem == 0L) return 0

        var ans = Integer.MAX_VALUE
        total = 0L

        for ((i, n) in nums.withIndex()) {
            total += n

            val cur = total % p
            val target = (cur - rem + p) % p

            if (hp.contains(target))
                ans = minOf(ans, i - hp[target]!!)

            hp[cur] = i

        }

        return if (ans >= nums.size) -1 else ans

    }
}

class Solution {
    fun minSubarray(nums: IntArray, p: Int): Int {
        // Remainder of sum of the array. Accumulated in `Long` to avoid modulo operation on each summation and because total sum does not fit into `Int`
        val totalSumRem = (nums.fold(0L) { acc, value -> acc + value } % p).toInt()
        // nothing to do here since remainder is 0 already
        if (totalSumRem == 0) return 0

        // index of a most recent prefix sum with certain remainder
        val idxByRem = HashMap<Int, Int>(nums.size)
        // initial prefix sum is 0 and its index is -1
        idxByRem[0] = -1

        var currentRem = 0
        var shortestSubArrayLength = nums.size

        for (idx in nums.indices) {
            currentRem = (currentRem + nums[idx]) % p

            // we need: currentRem - someOtherRem = totalSumRem
            // it means that -someOtherRem = totalSumRem - currentRem
            // it means that someOtherRem = currentRem - totalSumRem
            val requiredRem = (currentRem - totalSumRem + p) % p
            val requiredRemIdx = idxByRem[requiredRem]
            if (requiredRemIdx != null && shortestSubArrayLength > idx - requiredRemIdx) {
                shortestSubArrayLength = idx - requiredRemIdx
            }

            // record index of current remainder each time to increase chances of getting shorter   sub-array
            idxByRem[currentRem] = idx
        }

        return if (shortestSubArrayLength == nums.size) -1 else shortestSubArrayLength
    }
}