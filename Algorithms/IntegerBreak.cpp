#include <iostream>
#include <cmath>
#include <vector>
using namespace std;

/*
* Integer Break
Given an integer n, break it into the sum of k positive integers, where k >= 2,
and maximize the product of those integers.

Return the maximum product you can get.

Example 1:
Input: n = 2
Output: 1
Explanation: 2 = 1 + 1, 1 × 1 = 1.

Example 2:
Input: n = 10
Output: 36
Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
*/

int integerBreak(int n)
{
    if (n <= 3)
        return n - 1;

    else if (n % 3 == 0)
        return pow(3, n / 3);

    else if (n % 3 == 1)
        return 4 * pow(3, (n - 4) / 3);

    else
        return 2 * pow(3, n / 3);
}

int main(void)
{
    cout << integerBreak(15);
}

class Solution
{
public:
    /*
    The first thing we should consider is : What is the max product if we break a number N into two factors?
    I use a function to express this product: f=x(N-x)
    When x=N/2, we get the maximum of this function.
    However, factors should be integers. Thus the maximum is (N/2)*(N/2) when N is even or (N-1)/2 *(N+1)/2 when N is odd.
    When the maximum of f is larger than N, we should do the break.
    (N/2)*(N/2)>=N, then N>=4
    (N-1)/2 *(N+1)/2>=N, then N>=5
    These two expressions mean that factors should be less than 4, otherwise we can do the break and get a better product.
    The factors in last result should be 1, 2 or 3. Obviously, 1 should be abandoned.
    Thus, the factors of the perfect product should be 2 or 3.
    The reason why we should use 3 as many as possible is
    For 6, 3 * 3>2 * 2 * 2. Thus, the optimal product should contain no more than three 2.
    */
    int integerBreak(int n)
    {
        if (n == 2)
            return 1;
        if (n == 3)
            return 2;
        if (n == 4)
            return 4;

        int product = 1;
        while (n > 4)
        {
            product *= 3;
            n -= 3;
        }

        product *= n;
        return product;
    }
};

class Solution
{
public:
    int integerBreak(int n)
    {
        if (n == 2)
            return 1;
        if (n == 3)
            return 2;

        int num_3 = (int)n / 3;
        int remainder = n % 3;
        if (remainder == 1)
        {
            remainder = 4;
            num_3--;
        }
        else if (remainder == 0)
            remainder = 1;

        return (int)pow(3, num_3) * remainder;
    }
};

// Recursion
int solve2(int i, int sum)
{
    if (i == 1)
        return 1;
    int notpick = solve2(i - 1, sum);
    int pick = 0;
    if (sum >= i)
        pick = i * solve2(i, sum - i);
    return max(pick, notpick);
}

int integerBreak(int n)
{
    return solve2(n - 1, n);
}

// Recursion Stack
int solve(int i, int sum, vector<vector<int>> &dp)
{
    if (i == 1)
        return 1;
    if (dp[i][sum] != -1)
        return dp[i][sum];

    int notpick = solve(i - 1, sum, dp);
    int pick = 0;

    if (sum >= i)
        pick = i * solve(i, sum - i, dp);

    return dp[i][sum] = max(pick, notpick);
}

int integerBreak(int n)
{
    vector<vector<int>> dp(n, vector<int>(n + 1, -1));
    return solve(n - 1, n, dp);
}

// Tabulation
int integerBreak(int n)
{
    vector<vector<int>> dp(n, vector<int>(n + 1, 0));
    for (int j = 0; j < n + 1; j++)
        dp[1][j] = 1;

    for (int i = 2; i < n; i++)
        for (int sum = 0; sum <= n; sum++)
        {
            int notpick = dp[i - 1][sum];
            int pick = 0;

            if (sum >= i)
                pick = i * dp[i][sum - i];

            dp[i][sum] = max(pick, notpick);
        }

    return dp[n - 1][n];
}

// Space Optimization
int integerBreak(int n)
{
    vector<int> prev(n + 1, 1), curr(n + 1, 0);
    for (int i = 2; i < n; i++)
    {
        for (int sum = 0; sum <= n; sum++)
        {
            int notpick = prev[sum];
            int pick = 0;

            if (sum >= i)
                pick = i * curr[sum - i];

            curr[sum] = max(pick, notpick);
        }
        prev = curr;
    }
    return prev[n];
}
