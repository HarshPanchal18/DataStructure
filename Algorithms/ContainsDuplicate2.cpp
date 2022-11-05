#include <unordered_map>
#include <vector>
using namespace std;

/*
Contains Duplicate II

Given an integer array nums and an integer k,
return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.

Example 1:
Input: nums = [1,2,3,1], k = 3
Output: true

Example 2:
Input: nums = [1,0,1,1], k = 1
Output: true

Example 3:
Input: nums = [1,2,3,1,2,3], k = 2
Output: false
*/

class Solution
{
public:
    bool containsNearbyDuplicate(vector<int> &nums, int k)
    {
        unordered_map<int, int> mp;
        int n = nums.size();

        for (int i = 0; i < n; i++)
        {
            if (mp.count(nums[i]))             // mp.count() will tell whatever ith index that I want, have I seen it before?
                if (abs(i - mp[nums[i]]) <= k) // if I have already seen this number, then check for condition abs(i - j) <= k
                    return true;

            // if I have not seen this number before, insert the number with its position in the map
            // and if the number is already present in the map, then update the position of that number
            mp[nums[i]] = i;
        }
        // after the complete traversal, if we don't find a pair to satisfy the condition, return false
        return false;
    }
};

class Solution
{
public:
    // Hash map
    bool containsNearbyDuplicate(vector<int> &nu, int k)
    {
        int n = nu.size();
        unordered_map<int, int> mp;
        for (int p = 0; p < n; p++)
            mp[nu[p]]++;

        for (int p = 0; p < n; p++)
        {
            if (mp[nu[p]] == 1)
                continue;

            for (int q = p + 1; q < n; q++)
                if (nu[q] == nu[p])
                    if ((q - p) <= k)
                        return true;
        }

        return false;
    }
};

class Solution
{
public:
    // Hash map 2
    bool containsNearbyDuplicate(vector<int> &nums, int k)
    {
        // [1] all encountered numbers with their corresponding
        //     indices will be stored in a HashSet
        unordered_map<int, int> seen;

        // [2] in this cycle, we both check for correct
        //     duplicates and update the dictionary
        for (int i = 0; i < nums.size(); i++)
            if (seen.find(nums[i]) != seen.end() && i - seen[nums[i]] <= k)
                return true;
            else
                seen[nums[i]] = i;

        return false;
    }
};
