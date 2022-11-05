#include <vector>
#include <set>
#include <map>
#include <unordered_map>
#include <unordered_set>
#include <algorithm>
using namespace std;

/*
* Contains Duplicate

Given an integer array nums, return true if any value appears at least twice in the array,
and return false if every element is distinct.

Example 1:
Input: nums = [1,2,3,1]
Output: true

Example 2:
Input: nums = [1,2,3,4]
Output: false

Example 3:
Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true
*/

bool containsDuplicate(vector<int> &nums)
{
    sort(nums.begin(), nums.end());
    for (int i = 0; i < nums.size() - 1; i++)
        if (nums[i] == nums[i + 1])
            return true;

    return false;
}

//
class Solution
{
public:
    bool containsDuplicate(vector<int> &nums)
    {

        set<int> st(nums.begin(), nums.end()); // inserting all values of nums into set.
        return st.size() < nums.size();
        /* as we know set can only stores unique values,
        then if size of nums is greater than our sets size the it is
                    confirmed that the given nums consists of duplicates.*/
    }
};

class Solution
{
public:
    bool containsDuplicate(vector<int> &nums)
    {
        unordered_map<int, int> mp; // automatically value of each key in the hashmap is 0
        for (int i = 0; i < nums.size(); i++)
        {
            if (mp[nums[i]] == 1) // check whether the value is repeated
                return true;
            mp[nums[i]]++;
        }
        return false;
    }
};

class Solution
{
public:
    bool containsDuplicate(vector<int> &nums)
    {
        // using set
        set<int> s;
        for (int i = 0; i < nums.size(); i++)
        {
            if (s.find(nums[i]) != s.end())
                return true;
            s.insert(nums[i]);
        }
        return false;
    }

    // using hashmap
    bool containsDuplicate(vector<int> &nums)
    {
        map<int, int> m;
        for (int i = 0; i < nums.size(); i++)
        {
            if (m.find(nums[i]) != m.end())
                return true;
            m[nums[i]]++;
        }
        return false;
    }

    // using unordered set
    bool containsDuplicate(vector<int> &nums)
    {
        unordered_set<int> s;
        for (int i : nums)
            if (s.insert(i).second == false)
                return true;
        return false;
    }
};
