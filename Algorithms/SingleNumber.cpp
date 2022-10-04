#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>

/*
Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
You must implement a solution with a linear runtime complexity and use only constant extra space.

Example 1:
Input: nums = [2,2,1]
Output: 1

Example 2:
Input: nums = [4,1,2,1,2]
Output: 4

Example 3:
Input: nums = [1]
Output: 1
*/

using namespace std;
class Solution
{
public:
    // Method 1
    int singleNumber(vector<int> &nums)
    {
        unordered_map<int, int> arr;

        for (auto i : nums)
            arr[i]++;

        for (auto m : arr)
            if (m.second == 1)
                return m.first;

        return -1;
    }

    // Method 2
    int singleNumber(vector<int> &nums)
    {
        sort(nums.begin(), nums.end());
        for (int i = 1; i < nums.size(); i += 2)
            if (nums[i] != nums[i - 1])
                return nums[i - 1];

        return nums[nums.size() - 1];
    }

    // Method 3
    int singleNumber(vector<int> &nums)
    {
        int ans = 0;
        for (auto x : nums)
            ans ^= x;
        return ans;
    }

    /*
    PS : METHOD 4: SUM OF ELEMENTS
All the unique elements , in the array have a frequency of 2 , except one element.

Store all the unique elements in set.
Add the elements of the set and multiply by 2 (SUM_1).
Add all the elements of the array(ARRAY_SUM).
Return (SUM_1 - ARRAY_SUM) .
Why does this work ??
ARRAY_SUM = 2*(a1+a2+a3...+ak) + a(k+1)
SUM_1 = 2*(a1+a2+a3+....+ak+ a(k+1))

a(x) represents the xth unique element in the array.
a(k+1) represents the element with frequency=1.
    */

    // Method 5
#include <numeric>
    int singleNumber(vector<int> &nums)
    {
        return accumulate(nums.begin(), nums.end(), 0, bit_xor<int>());
    }
};
