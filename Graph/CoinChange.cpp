#include <vector>
#include <iostream>
#include <cstring>
using namespace std;

/*
* Coin Change

You are given an integer array coins representing coins of different denominations
and an amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.
You may assume that you have an infinite number of each kind of coin.

Example 1:
Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1

Example 3:
Input: coins = [1], amount = 0
Output: 0

I learnt it from the DP series by Aditya Verma on Youtube. I will highly suggest it to anyone who wants to learn DP.
*/

/*
If you carefully observe the below 3 codes. You will see that the DP Memoization is dervied
from the Recursion code just by changing 3 lines and the DP Tabulation is derived from the DP Memoization.

Recursion
Time: O(2^n)
Space: O(n)

Writing a recursive function is all about find two things:

The base case: Just calculate the output for the smallest possible input
The choice diagram: For any given input, just see what all choices do we have.
*/
class Solution
{
public:
    int findLowestCoins(vector<int> &coins, int cur, int amount)
    {
        if (cur >= coins.size() || amount <= 0)
            return (amount == 0) ? 0 : INT_MAX - 1;

        int res = -1;
        if (coins[cur] > amount)
        {
            int doNotTakeCoin = 0 + findLowestCoins(coins, cur + 1, amount - 0);
            res = doNotTakeCoin;
        }
        else
        {
            int takeCoin = 1 + findLowestCoins(coins, cur + 0, amount - coins[cur]);
            int doNotTakeCoin = 0 + findLowestCoins(coins, cur + 1, amount - 0);
            res = min(takeCoin, doNotTakeCoin);
        }
        return res;
    }

    int coinChange(vector<int> &coins, int amount)
    {
        int res = findLowestCoins(coins, 0, amount);
        return (res == INT_MAX - 1) ? -1 : res;
    }
};
/*
DP Memoization
Time : O(n.m)
Space : O(n.m)
In the above recursive case,
we were doing repeated work in the form of subproblems.
Hence we store the results of those subproblems in a table to reduce the number of recursive calls.
*/
class Solution
{
public:
    int dp[12 + 1][10000 + 1];

    int findLowestCoins(vector<int> &coins, int cur, int amount)
    {
        if (cur == coins.size() || amount <= 0)
            return (amount == 0) ? 0 : INT_MAX - 1;

        if (dp[cur][amount] != -1)
            return dp[cur][amount];

        int res = -1;
        if (coins[cur] > amount)
        {
            int doNotTakeCoin = 0 + findLowestCoins(coins, cur + 1, amount - 0);
            dp[cur][amount] = res = doNotTakeCoin;
        }
        else
        {
            int takeCoin = 1 + findLowestCoins(coins, cur + 0, amount - coins[cur]);
            int doNotTakeCoin = 0 + findLowestCoins(coins, cur + 1, amount - 0);
            dp[cur][amount] = res = min(takeCoin, doNotTakeCoin);
        }
        return dp[cur][amount] = res;
    }

    int coinChange(vector<int> &coins, int amount)
    {
        memset(dp, -1, sizeof(dp));
        int res = findLowestCoins(coins, 0, amount);
        return (res == INT_MAX - 1) ? -1 : res;
    }
};
/*
DP Tabulation
Time: O(n.m)
Space: O(n.m)

We have reached the best conceivable run time for this question but since we have recursive calls
in the previous algorithm. It might lead to stackoverflow error in the worst case when recursive calls are a lot.
Hence we want to totally emit the notion of recursions. To do that, we simply convert the recursion into iterative code.

The below code is bottom up dynamic programming because we are starting from the first element in the 2D array
and filling the DP from this first element till the last element. And eventually, the last cell stores our final result.
*/
class Solution
{
public:
    int dp[12 + 1][10000 + 1];

    int findLowestCoins(vector<int> &coins, int arraySize, int amount)
    {
        for (int i = 0; i < arraySize + 1; i++)
            for (int j = 0; j < amount + 1; j++)
                if (i == 0 || j == 0)
                    dp[i][j] = (j == 0) ? 0 : INT_MAX - 1;

        for (int i = 1; i < arraySize + 1; i++)
            for (int j = 1; j < amount + 1; j++)
                if (coins[i - 1] > j)
                    dp[i][j] = 0 + dp[i - 1][j];
                else
                    dp[i][j] = min(0 + dp[i - 1][j], 1 + dp[i][j - coins[i - 1]]);

        return dp[arraySize][amount];
    }

    int coinChange(vector<int> &coins, int amount)
    {
        int res = findLowestCoins(coins, coins.size(), amount);
        return (res == INT_MAX - 1) ? -1 : res;
    }
};

/*
Naive method :
For every coin We will take it or we will not take it so we will get 2 paths to walk on.
If our total amount is reached then we will take min of these two paths and return it.
*/
class Solution
{
public:
    int help(vector<int> &coins, int amount, int n)
    {
        if (amount == 0)
            return 0;
        if (n < 0 || amount < 0)
            return INT_MAX - 1;

        int one = help(coins, amount, n - 1);
        int two = 1 + help(coins, amount - coins[n], n);
        return min(one, two);
    }

    int coinChange(vector<int> &coins, int amount)
    {
        int ans = help(coins, amount, coins.size() - 1);
        return (ans < INT_MAX - 1) ? ans : -1;
    }
};

/*
Memoization
To reduce time we will just use an 2D array to store our result.
Since we are solving our problem recursively you can store the value you got for certain {amount,n}
and store it and use it for next time. implementation is:
*/
class Solution
{
public:
    int help(vector<int> &coins, int amount, int n, vector<vector<int>> &h)
    {
        if (amount == 0)
            return 0;
        if (n < 0 || amount < 0)
            return INT_MAX - 1;
        if (h[n][amount] != -1)
            return h[n][amount];

        int one = help(coins, amount, n - 1, h);
        int two = 1 + help(coins, amount - coins[n], n, h);
        h[n][amount] = min(one, two);
        return h[n][amount];
    }

    int coinChange(vector<int> &coins, int amount)
    {
        vector<vector<int>> h(coins.size() + 1, vector<int>(amount + 1, -1));
        int ans = help(coins, amount, coins.size() - 1, h);
        return (ans < INT_MAX - 1) ? ans : -1;
    }
};

/*
Tabulation
In fact for this que we can do 2D Tabulation and 1D tabulation Both,
But I will talk about 1D tabulation only Here. Now According to our name suggestion we are using an 1D array.
We will set every element to infinity and one by one check from coins array that if we can use this coin
to get result or not and take min of thos values and store it. we will focus on these 3 lines from our recursion

int one = help(coins,amount,n-1);
int two = 1+help(coins,amount-coins[n],n);
return min(one,two);
Now here is implementation:
*/
class Solution
{
public:
    int coinChange(vector<int> &coins, int amount)
    {
        vector<int> h((amount + 1), INT_MAX - 1); // INT_MAX-1 is nice trick to avoid overflow
        h[0] = 0;
        for (int i = 0; i < h.size(); i++)
            for (int j = 0; j < coins.size(); j++)
                if (i >= coins[j])
                    h[i] = min(h[i], 1 + h[i - coins[j]]);

        return (h[h.size() - 1] < INT_MAX - 1) ? h[h.size() - 1] : -1;
    }
};
/*
Why Tabulation is better than Memoization?
Although auxillary time complexity is same we see real time time complexity of memoization will be higher.
As for space: cleary tabulation is a Better option
*/

// Space Optimized Tabulation
class Solution
{ // Time : O(N*M), Space : O(M)
public:
    int coinChange(vector<int> &coins, int amount)
    {
        vector<int> prev(10001), now(10001);
        for (int am = 0; am <= amount; am++)
            prev[am] = am % coins[0] == 0 ? am / coins[0] : 1e9;
        for (int cur = 1; cur < coins.size(); cur++)
        {
            for (int am = 0; am <= amount; am++)
            {
                int doNotTakeCoin = 0 + prev[am];
                int takeCoin = INT_MAX;
                if (coins[cur] <= am)
                    takeCoin = 1 + now[am - coins[cur]];
                now[am] = min(takeCoin, doNotTakeCoin);
            }
            prev = now;
        }
        return (prev[amount] >= 1e9) ? -1 : prev[amount];
    }
};

// Tabulation Bottom - Up
class Solution
{ // Time : O(N*M), Space : O(N*M)
public:
    int coinChange(vector<int> &coins, int amount)
    {
        int dp[13][10001];
        for (int am = 0; am <= amount; am++)
            dp[0][am] = am % coins[0] == 0 ? am / coins[0] : 1e9;

        for (int cur = 1; cur < coins.size(); cur++)
        {
            for (int am = 0; am <= amount; am++)
            {
                int doNotTakeCoin = 0 + dp[cur - 1][am];
                int takeCoin = INT_MAX;

                if (coins[cur] <= am)
                    takeCoin = 1 + dp[cur][am - coins[cur]];

                dp[cur][am] = min(takeCoin, doNotTakeCoin);
            }
        }
        return (dp[coins.size() - 1][amount] >= 1e9) ? -1 : dp[coins.size() - 1][amount];
    }
};

// Recursion + Memoization
class Solution
{ // Time : O(N*M), Space : O(N*M) + Auxilary Space of recursion stack
public:
    int dp[13][10001];
    int findLowestCoins(vector<int> &coins, int cur, int amount)
    {
        if (cur == 0)
            return (amount % coins[0] == 0) ? amount / coins[0] : 1e9;
        if (dp[cur][amount] != -1)
            return dp[cur][amount];

        int doNotTakeCoin = 0 + findLowestCoins(coins, cur - 1, amount);
        int takeCoin = INT_MAX;
        if (coins[cur] <= amount)
            takeCoin = 1 + findLowestCoins(coins, cur, amount - coins[cur]);

        return dp[cur][amount] = min(takeCoin, doNotTakeCoin);
    }

    int coinChange(vector<int> &coins, int amount)
    {
        memset(dp, -1, sizeof(dp));
        int res = findLowestCoins(coins, coins.size() - 1, amount);
        return (res >= 1e9) ? -1 : res;
    }
};

// Recusion TLE
class Solution
{ // Time : O(2^N), Space : O(N) (Time, Space a lot more as we are standing on same index)
public:
    int findLowestCoins(vector<int> &coins, int cur, int amount)
    {
        if (cur == 0)
            return (amount % coins[0] == 0) ? amount / coins[0] : 1e9;

        int doNotTakeCoin = 0 + findLowestCoins(coins, cur - 1, amount);
        int takeCoin = INT_MAX;

        if (coins[cur] <= amount)
            takeCoin = 1 + findLowestCoins(coins, cur, amount - coins[cur]);

        return min(takeCoin, doNotTakeCoin);
    }
    int coinChange(vector<int> &coins, int amount)
    {
        int res = findLowestCoins(coins, coins.size() - 1, amount);
        return (res >= 1e9) ? -1 : res;
    }
};
