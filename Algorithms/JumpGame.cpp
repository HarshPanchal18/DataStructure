#include <iostream>
#include <vector>
using namespace std;

/*
* Jump Game
You are given an integer array nums.
You are initially positioned at the array's first index, and each element in the array
represents your maximum jump length at that position.
Return true if you can reach the last index, or false otherwise.

Example 1:
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0,
which makes it impossible to reach the last index.
*/

class Solution
{
public:
    bool canJump(vector<int> &nums)
    {
        vector<bool> dp(nums.size(), false);
        dp[0] = true;

        int n = nums.size();
        // int i = 0;
        // while (i < n and !dp[n - 1])
        for (int i = 0; i < n && !dp[n - 1]; i++)
        {
            if (dp[i])
            {
                int step_length = nums[i];
                for (int j = 0; j <= step_length and j + i < n; j++)
                    dp[i + j] = true;
            }
            // i++;
        }
        return dp[n - 1];
    }

    // Recursive
    bool canJump(vector<int> &nums)
    {
        int n = nums.size();
        return solve(0, n, nums);
    }

    bool solve(int ind, int n, vector<int> &nums)
    {
        if (ind == n - 1)
            return true;

        int jump = nums[ind];
        bool check = false;
        for (int i = ind + 1; i <= min(n - 1, ind + jump); i++)
            check = check || solve(i, n, nums);

        return check;
    }

    // Memoization
    bool canJump(vector<int> &nums)
    {
        int n = nums.size();
        vector<int> dp(n, -1);
        return solve(0, n, nums, dp);
    }

    bool solve(int ind, int n, vector<int> &nums, vector<int> &dp)
    {
        if (ind == n - 1)
            return true;
        if (dp[ind] != -1)
            return dp[ind];

        int jump = nums[ind];
        bool check = false;
        for (int i = ind + 1; i <= min(n - 1, ind + jump); i++)
            check = check || solve(i, n, nums, dp);

        return dp[ind] = check;
    }

    // Tabulation OR linear
    bool canJump(vector<int> &nums)
    {
        int i = 0, n = nums.size();
        for (int reach = 0; i < n && i <= reach; i++)
            reach = max(reach, nums[i] + i);
        return i == n;
    }

    // No need to scan whole array when already know we can reach the end.
    bool canJump(vector<int> &nums)
    {
        int dis = 0;
        for (int i = 0; i <= dis; i++)
        {
            dis = max(dis, i + nums[i]);
            if (dis >= nums.size() - 1)
                return true;
        }
        return false;
    }

    // Looking from the end and at each point ahead checking the best possible way to reach the end
    bool canJump(vector<int> &nums)
    {
        int n = nums.size();
        vector<bool> jump(n, false);
        jump[n - 1] = true;

        for (int i = n - 2; i >= 0; i--)
            for (int j = 0; j <= nums[i] && i + j < n; j++)
                if (jump[i + j] == true)
                {
                    jump[i] = true;
                    break;
                }

        return jump[0];
    }

    // Greedy - Looking from the start and selecting the locally optimum in the hope of reaching global optimum
    bool canJump(vector<int> &nums)
    {
        int n = nums.size(), farest = 0;
        for (int i = 0; i < n; i++)
        {
            if (farest < i)
                return false;
            farest = max(i + nums[i], farest);
        }
        return true;
    }
};
