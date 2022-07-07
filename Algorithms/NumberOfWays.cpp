#include <bits/stdc++.h>
using namespace std;

/*
Given a value N. In how many ways you can construct a grid of size N x 4 using tiles of size 1 x 4.
Example 1:
Input: N = 1
Output: 1
Explaination: We can arrange the tiles horizontally and this is the only way.

Example 2:
Input: N = 4
Output: 2
Explaination: The first way can be when all the 1 x 4 tiles are arranged horizontally.
Second way can be when all the 1 x 4 tiles are arranged vertically.
*/

class Solution
{
public:
    long long int arrangeTiles(int n)
    {
        vector<long long int> dp(n + 1, 0);
        for (int i = 1; i <= n; i++)
            if (i < 4)
                dp[i] = 1;
            else if (i == 4)
                dp[i] = 2;
            else
                dp[i] = dp[i - 1] + dp[i - 4];

        return dp[n];
    }
};

int main()
{
    int N;
    cin >> N;

    Solution ob;
    cout << ob.arrangeTiles(N) << "\n";
    return 0;
}
