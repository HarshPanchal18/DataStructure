#include <iostream>
using namespace std;

/*
You are given an equation of the form x1+x2+...+xN=K.
You need to find the total number of positive integral solutions of this equation.

Example 1:
Input: s = a+b=5
Output: 4
Explanation: (4,1) , (1,4) , (2,3) , (3,2)

Example 2:
Input: s = a+b=1
Output: 0
Explanation: No solution exist.
*/

class Solution
{
public:
    long compute(int n, int val)
    {
        if (val > n)
            return 0;

        if (val > n - val)
            val = n - val;

        int dp[val + 1];

        for (int i = 0; i <= val; i++)
            dp[i] = 0;

        dp[0] = 1;

        for (int i = 1; i <= n; i++)
            for (int j = min(i, val); j > 0; j--)
                dp[j] = dp[j] + dp[j - 1];

        return dp[val];
    }

    long posIntSol(string s)
    {
        int n = 1;
        int start = 0;

        for (int i = 0; i < s.size(); i++)
        {
            if (s[i] == '+')
                n++; // total count of operands
            if (s[i] == '=')
                start = i;
        }

        string str = s.substr(start + 1);
        int val = stoi(str);
        val--;
        n--;

        return compute(val, n);
    }
};

int main()
{
    string s;
    cin >> s;
    Solution obj;
    cout << obj.posIntSol(s) << endl;
}
