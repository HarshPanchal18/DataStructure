#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

/*
Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:
Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]

Example 2:
Input: nums = [0]
Output: [[],[0]]
*/

vector<vector<int>> subsetsWithDup(vector<int> &nums)
{
    sort(nums.begin(), nums.end());
    vector<vector<int>> result = {{}};
    int start;
    for (int i = 0; i < nums.size(); i++)
    {
        if (i == 0 || nums[i] != nums[i - 1])
            start = 0;
        for (int end = result.size(); start < end; start++)
        {
            vector<int> clone = result[start];
            clone.push_back(nums[i]);
            result.push_back(clone);
        }
    }
    return result;
}

class Solution
{
public:
    vector<vector<int>> subsetsWithDup(vector<int> &S)
    {
        vector<vector<int>> totalset = {{}};
        sort(S.begin(), S.end());
        for (int i = 0; i < S.size();)
        {
            int count = 0; // num of elements are the same
            while (count + i < S.size() && S[count + i] == S[i])
                count++;
            int previousN = totalset.size();
            for (int k = 0; k < previousN; k++)
            {
                vector<int> instance = totalset[k];
                for (int j = 0; j < count; j++)
                {
                    instance.push_back(S[i]);
                    totalset.push_back(instance);
                }
            }
            i += count;
        }
        return totalset;
    }
};

class Solution
{
public:
    void solve(int i, int &n, vector<int> &tmp, vector<vector<int>> &ans, vector<int> &num)
    {
        ans.push_back(tmp);
        if (i > n)
            return;
        for (int j = i; j < n; j++)
        {
            tmp.push_back(num[j]);
            solve(j + 1, n, tmp, ans, num);
            while (j < n - 1 && num[j] == num[j + 1])
                j++;
            tmp.pop_back();
        }
    }
    vector<vector<int>> subsetsWithDup(vector<int> &nums)
    {
        vector<vector<int>> ans;
        vector<int> tmp;
        int n = nums.size();
        sort(nums.begin(), nums.end());
        solve(0, n, tmp, ans, nums);
        return ans;
    }
};

// Recursive
void findSubsets(int ind, vector<int> &nums, vector<int> &ds, vector<vector<int>> &ans)
{
    ans.push_back(ds);
    for (int i = ind; i < nums.size(); i++)
    {
        if (i != ind && nums[i] == nums[i - 1])
            continue;
        ds.push_back(nums[i]);
        findSubsets(i + 1, nums, ds, ans);
        ds.pop_back();
    }
}

vector<vector<int>> subsets(vector<int> &nums)
{
    vector<vector<int>> ans;
    vector<int> ds;
    sort(nums.begin(), nums.end());
    findSubsets(0, nums, ds, ans);
    return ans;
}
