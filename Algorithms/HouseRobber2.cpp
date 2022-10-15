#include <iostream>
#include <vector>
#include <cstring>
using namespace std;

/*
* House Robber II
https://leetcode.com/problems/house-robber-ii/
You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed.
All houses at this place are arranged in a circle.
That means the first house is the neighbor of the last one.
Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police
    if two adjacent houses were broken into on the same night.
Given an integer array nums representing the amount of money of each house,
    return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.

Example 2:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
*/

class Solution
{
public:
    int rob(vector<int> &nums)
    {
        int n = nums.size();
        if (n < 2)
            return n ? nums[0] : 0;

        return max(robber(nums, 0, n - 2), robber(nums, 1, n - 1));
    }

    int robber(vector<int> &nums, int l, int r)
    {
        int pre = 0, cur = 0;
        for (int i = l; i <= r; i++)
        {
            int temp = max(pre + nums[i], cur);
            pre = cur;
            cur = temp;
        }
        return cur;
    }

    // Short DP solution
    int houseRobber(vector<int> &nums)
    {
        // dynamic programming - decide each problem by its sub-problems:
        int dp[nums.size() + 1];
        dp[0] = nums[0];
        dp[1] = max(nums[0], nums[1]);

        for (int i = 2; i < nums.size(); i++)
            dp[i] = max(dp[i - 1], nums[i] + dp[i - 2]);

        return dp[nums.size() - 1];
    }

    int rob(vector<int> &nums)
    {
        // edge cases:
        if (nums.size() == 0)
            return 0;

        if (nums.size() == 1)
            return nums[0];

        if (nums.size() == 2)
            return max(nums[0], nums[1]);

        // either use first house and can't use last or last and not first:
        vector<int> v1(nums.begin(), nums.end() - 1);
        vector<int> v2(nums.begin() + 1, nums.end());

        return max(houseRobber(v1), houseRobber(v2));
    }

    /*
    If you have already solved the problem House Robber I, then it's just an extension to it.
    As houses are circular and we cannot rob house 1 and n together, so we have 2 choices:
    1> Rob houses from 1 to n-1
    2> Rob houses from 2 to n
    Here we can use two dp arrays to store the maximum profit for these two cases like this:
    */
    int rob(vector<int> &nums)
    {
        int n = nums.size();
        if (n == 1)
            return nums[0];

        if (n == 2)
            return max(nums[0], nums[1]);

        // dp1 -> 1 to n-1
        // dp2 -> 2 to n

        int dp1[n], dp2[n];

        // if we have robbed the first house, then we can't rob the second house
        dp1[0] = nums[0];
        dp1[1] = nums[0];

        // if we didn't rob the first house, we will definetely rob the second
        dp2[0] = 0;
        dp2[1] = nums[1];

        for (int i = 2; i < n; i++)
        {
            // make optimal choice at each house
            dp1[i] = max(nums[i] + dp1[i - 2], dp1[i - 1]);
            dp2[i] = max(nums[i] + dp2[i - 2], dp2[i - 1]);
        }

        // dp1 is for nums[1 - (n-1)] so we won't consider the last house in this case and dp1[n-2] will be max profit
        // and dp2 is for nums[2 - n] so we can take the last house
        return max(dp1[n - 2], dp2[n - 1]);
    }

    // Recursive
    int getMax(vector<int> &nums, int i, bool robFirst)
    {
        if (i >= nums.size() || (i == nums.size() - 1 && robFirst))
            return 0;
        int rob = 0, notRob = 0;

        if (i == 0)
            rob = getMax(nums, i + 2, 1) + nums[i];
        else
            rob = getMax(nums, i + 2, robFirst) + nums[i];

        notRob = getMax(nums, i + 1, robFirst);
        return max(rob, notRob);
    }

    int rob(vector<int> &nums)
    {
        return getMax(nums, 0, 0);
    }

    // Memoization
    int dp[101][2];
    int getMax(vector<int> &nums, int i, bool robFirst)
    {
        if (i >= nums.size() || (i == nums.size() - 1 && robFirst))
            return 0;

        if (dp[i][robFirst] != -1)
            return dp[i][robFirst];

        int rob = 0, notRob = 0;
        if (i == 0)
            rob = getMax(nums, i + 2, 1) + nums[i];
        else
            rob = getMax(nums, i + 2, robFirst) + nums[i];

        notRob = getMax(nums, i + 1, robFirst);
        return dp[i][robFirst] = max(rob, notRob);
    }

    int rob(vector<int> &nums)
    {
        memset(dp, -1, sizeof dp); // <cstring>
        return getMax(nums, 0, 0);
    }

    // Tabulation
    int rob(vector<int> &nums)
    {
        int n = nums.size();
        int dp[n + 1], dp1[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        dp1[0] = 0;
        dp1[1] = 0;
        for (int i = 2; i <= n; i++)
        {
            if (i == n)
                dp[i] = dp[i - 1];
            else
                dp[i] = max(dp[i - 1], dp[i - 2] + nums[i - 1]);
            dp1[i] = max(dp1[i - 1], dp1[i - 2] + nums[i - 1]);
        }
        return max(dp[n], dp1[n]);
    }

    // 2 variable dp
    int rob(vector<int> &nums)
    {
        int n = nums.size();
        int a1 = 0, b1 = nums[0], a2 = 0, b2 = 0, temp1, temp2;
        for (int i = 2; i <= n; i++)
        {
            if (i != n)
            {
                temp1 = b1;
                b1 = max(a1 + nums[i - 1], b1);
                a1 = temp1;
            }
            temp2 = b2;
            b2 = max(a2 + nums[i - 1], b2);
            a2 = temp2;
        }
        return max(b1, b2);
    }
};
