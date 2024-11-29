package main

/*
* Subarray Sums Divisible by K
Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
A subarray is a contiguous part of an array.

Example 1:
Input: nums = [4,5,0,-2,-3,1], k = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by k = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]

Example 2:
Input: nums = [5], k = 9
Output: 0

Constraints:
1 <= nums.length <= 3 * 104
-104 <= nums[i] <= 104
2 <= k <= 104
*/

func subarraysDivByK(nums []int, k int) int {
	count := 0
	freq := make(map[int]int)
	freq[0] = 1
	sum := 0

	for _, num := range nums {
		sum += num
		rem := (sum%k + k) % k

		if _, ok := freq[rem]; ok {
			count += freq[rem]
		}

		freq[rem]++
	}

	return count
}

func subarraysDivByK1(nums []int, k int) int {
	var (
		remainders = map[int]int{
			0: 1,
		}
		sum   = 0
		count = 0
	)

	for _, v := range nums {
		sum = sum + v
		remainder := sum % k

		// adjust to be positive
		if remainder < 0 {
			remainder = remainder + k
		}

		count = count + remainders[remainder]
		remainders[remainder] = remainders[remainder] + 1
	}

	return count
}

func subarraysDivByK2(nums []int, k int) int {
	rmCount := make([]int, k)
	sum := 0
	ans := 0
	rmCount[0]++

	for i := 0; i < len(nums); i++ {
		sum += nums[i]
		rem := (sum%k + k) % k
		ans += rmCount[rem]
		rmCount[rem]++
	}

	return ans
}

func subarraysDivByK3(nums []int, k int) int {
	prefixSumRemainderCount := make(map[int]int)

	kDivisorSubArrayCount, prefixSum := 0, 0
	prefixSumRemainderCount[0] = 1
	for _, num := range nums {
		prefixSum += num
		prefixSumRemainder := ((prefixSum % k) + k) % k
		kDivisorSubArrayCount += prefixSumRemainderCount[prefixSumRemainder]
		prefixSumRemainderCount[prefixSumRemainder] += 1
	}

	return kDivisorSubArrayCount
}