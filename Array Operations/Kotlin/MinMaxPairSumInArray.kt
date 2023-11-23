/*
* Minimize Maximum Pair Sum in Array
The pair sum of a pair (a,b) is equal to a + b.
The maximum pair sum is the largest pair sum in a list of pairs.
For example, if we have pairs (1,5), (2,3), and (4,4), the maximum pair sum would be max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8.
Given an array nums of even length n, pair up the elements of nums into n / 2 pairs such that:

Each element of nums is in exactly one pair, and The maximum pair sum is minimized.
Return the minimized maximum pair sum after optimally pairing up the elements.

Example 1:
Input: nums = [3,5,2,3]
Output: 7
Explanation: The elements can be paired up into pairs (3,3) and (5,2).
The maximum pair sum is max(3+3, 5+2) = max(6, 7) = 7.

Example 2:
Input: nums = [3,5,4,2,4,6]
Output: 8
Explanation: The elements can be paired up into pairs (3,5), (4,4), and (6,2).
The maximum pair sum is max(3+5, 4+4, 6+2) = max(8, 8, 8) = 8.

Constraints:
n == nums.length
2 <= n <= 105
n is even.
1 <= nums[i] <= 105
*/

fun main(args: Array<String?>) {
    val array = intArrayOf(4,8,9,6,3,7,4)
    val element = minPairSum(array)
    print(element)
}

fun minPairSum(nums:IntArray):Int {
    return nums.run {
        sort()
        indices.take(size / 2).maxOf{ nums[it] + nums[lastIndex-it] }
    }
}

fun minPairSum(nums: IntArray): Int {
    nums.sort()

    var start = 0
    var end = nums.size - 1
    var maxPairSum = Int.MIN_VALUE

    while (start < end) {
        val current = nums[start++] + nums[end--]
        maxPairSum = max(maxPairSum, current)
    }

    return maxPairSum
}

fun minPairSum(nums: IntArray): Int {
    nums.sort()
    return nums.asSequence()
        .take(nums.size / 2)
        .zip(nums.asList().asReversed().asSequence().take(nums.size / 2))
        .maxOf { (a, b) -> a + b }
}

fun minPairSum(n: IntArray): Int = IntArray(n.sort().let{n.size/2}) { n[it] + n[n.size - 1 - it] }.max()

fun minPairSum(nums: IntArray): Int {
    nums.sort()

    var i = 0
    var j = nums.lastIndex
    val pairSumList = mutableListOf<Int>()

    while (i < j) {
        pairSumList.add(nums[i++] + nums[j--])
    }
    var maximumPair = 0

    for (pairSum in pairSumList) {
        maximumPair = maxOf(maximumPair, pairSum)
    }

    return maximumPair
}

fun minPairSum(nums: IntArray): Int {
    return nums.sorted().run {
        zip(asReversed()).maxOf { it.first + it.second }
    }
}