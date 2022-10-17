#include <vector>
#include <iostream>
using namespace std;

/*
* Jump Game II

You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].

Each element nums[i] represents the maximum length of a forward jump from index i.
In other words, if you are at nums[i], you can jump to any nums[i + j] where:

0 <= j <= nums[i] and
i + j < n

Return the minimum number of jumps to reach nums[n - 1].
The test cases are generated such that you can reach nums[n - 1].

Example 1:
Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: nums = [2,3,0,1,4]
Output: 2
*/

class Solution
{
public:
    int jump(vector<int> &nums)
    {
        int n = nums.size();
        vector<int> dp(n, INT_MAX);
        dp[0] = 0;

        for (int i = 0; i < n; i++)
        {
            int step = nums[i];
            for (int j = 1; j <= step and i + j < n; j++)
                dp[i + j] = min(dp[i + j], dp[i] + 1);
        }

        return dp[n - 1];
    }

    // Recursion
    int jump(vector<int> &nums)
    {
        int n = nums.size();
        return solve(0, n, nums);
    }

    int solve(int ind, int n, vector<int> &nums)
    {
        if (ind == n - 1)
            return 0;

        int jump = nums[ind], mini = 1e9;
        for (int i = ind + 1; i <= min(ind + jump, n - 1); i++)
        {
            int val = 1 + solve(i, n, nums);
            mini = min(mini, val);
        }
        return mini;
    }

    // Memoization
    int jump(vector<int> &nums)
    {
        int n = nums.size();
        vector<int> dp(n, -1);
        return f(0, n, nums, dp);
    }

    int f(int ind, int n, vector<int> &nums, vector<int> &dp)
    {
        if (ind == n - 1)
            return 0;

        if (dp[ind] != -1)
            return dp[ind];

        int jump = nums[ind], mini = 1e9;

        for (int i = ind + 1; i <= min(ind + jump, n - 1); i++)
        {
            int val = 1 + f(i, n, nums, dp);
            mini = min(mini, val);
        }

        return dp[ind] = mini;
    }

    // Tabulation
    int jump(vector<int> &nums)
    {
        int n = nums.size();
        vector<int> dp(n, 0);

        for (int ind = n - 2; ind >= 0; ind--)
        {
            int jump = nums[ind], mini = 1e9;
            for (int i = ind + 1; i <= min(ind + jump, n - 1); i++)
            {
                int val = 1 + dp[i];
                mini = min(mini, val);
            }
            dp[ind] = mini;
        }
        return dp[0];
    }

    // optimal
    int jump(vector<int> &nums)
    {
        int jump_cnt = 0, curLimit = 0, limit = 0;
        for (int i = 0; i < nums.size() - 1; i++)
        {
            limit = max(limit, i + nums[i]);
            if (i == curLimit)
            {
                jump_cnt++;
                curLimit = limit;
            }
        }
        return jump_cnt;
    }

    //
    int jump(vector<int> &nums)
    {
        int n = nums.size();
        if (n < 2)
            return 0;

        int jump = 1, maxi = nums[0], curr = nums[0];
        for (int i = 0; i < n - 1; i++)
        {
            maxi = max(maxi, nums[i] + i);
            if (curr == i)
            {
                jump++;
                curr = maxi;
            }
        }
        return jump;
    }
};
