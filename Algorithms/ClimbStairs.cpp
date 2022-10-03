#include <iostream>
#include <vector>
using namespace std;

/*
Climbing Stairs

You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps.
In how many distinct ways can you climb to the top?

Example 1:
Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

Example 2:
Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
*/

class Solution
{
public:
    int climbStairs1(int n)
    {

        /* M2
        int dp[46]={0};
        if(n<=2) return n;
        if(dp[n]!=0)
            return dp[n];
        return climbStairs(n-2)+climbStairs(n-1);
        */

        /* M3

        */
        int arr[46];
        arr[1] = 1;
        arr[2] = 2;

        for (int i = 3; i <= n; i++)
            arr[i] = arr[i - 2] + arr[i - 1];

        return arr[n];
    }

    int dp[46] = {0};
    int climbStairs3(int n)
    {
        if (n <= 2)
            return n;

        if (dp[n] != 0)
            return dp[n];

        return dp[n] = climbStairs(n - 1) + climbStairs(n - 2);
    }

    int recursive(int n)
    {
        if (n == 1 || n == 0)
            return 1;
        return climbStairs(n - 2) + climbStairs(n - 1);
    }

    int memoization(int n, vector<int> &dp)
    {
        if (n == 0 || n == 1)
            return 1;
        if (dp[n] != -1)
            return dp[n];
        return dp[n] = memoization(n - 1, dp) + memoization(n - 2, dp);
    }

    int tabulation(int n, vector<int> &dp)
    {
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }

    int spaceOptimization(int n)
    {
        if (n == 0 || n == 1)
            return 1;

        int prev1 = 1;
        int prev2 = 1;
        int curr = 0;

        for (int i = 2; i <= n; i++)
        {
            curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return curr;
    }

    int climbStairs(int n)
    {
        int ans1 = recursive(n);
        return ans1;

        vector<int> dp(n + 1, -1);
        int ans2 = memoization(n, dp);
        return ans2;

        vector<int> dp1(n + 1, 0);
        int ans3 = tabulation(n, dp1);
        return ans3;

        int ans4 = spaceOptimization(n);
        return ans4;
    }
};

int main(void)
{
    Solution obj;
    cout << obj.climbStairs(5);
}
