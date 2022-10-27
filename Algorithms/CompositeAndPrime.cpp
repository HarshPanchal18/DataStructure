#include <bits/stdc++.h>
using namespace std;

/*
Composite and Prime
Given two integers L and R find (number of composites - number of primes) between the range L and R (both inclusive).

Example 1:
Input: L = 4, R = 6
Output: 1
Explanation: Composite no. are 4 and 6.
And prime is 5.

Example 2:
Input: L = 4, R = 4
Output: 1
Explanation: There is no prime no. in [L,R]
*/

class Solution
{
public:
    int Count(int L, int R)
    {
        int count = R - L + 1;

        for (int i = L; i <= R; i++)
        {
            int prime = 0;
            if (i == 1)
            {
                count--;
                continue;
            }
            for (int j = 2; j * j <= i; j++)
                if (int(i % j) == 0)
                    prime = 1;

            if (prime == 0)
                count -= 2;
        }
        return count;
    }

    //
public:
    int check(int n)
    {
        bool flag = true;
        int low = 2, high = n;
        while (low < high)
        {
            if (n % low == 0)
            {
                return 0;
                flag = false;
            }
            high = high - high / low;
            low = low + 1;
        }
        if (flag)
            return 1;
    }

    int Count(int L, int R)
    {
        int ccount = 0, pcount = 0;
        if (L <= 1)
            pcount++;

        for (int i = L; i <= R; i++)
            if (check(i))
                ccount++;
            else
                pcount++;
        return pcount - ccount;
    }
};

class Solution
{
public:
    int Count(int l, int r)
    {
        vector<bool> isprime(r + 1, true);
        int prime = 0;

        for (int i = 2; i <= r; i++)
        {
            if (isprime[i])
            {
                if (i >= l)
                    prime++;
                for (int j = i + i; j <= r; j = j + i)
                    isprime[j] = false;
            }
        }

        int comp = r - l + 1 - prime;
        if (l == 1)
            return comp - prime - 1;

        return comp - prime;
    }
};

int main()
{
    int L, R;
    cin >> L >> R;
    Solution obj;
    int ans = obj.Count(L, R);
    cout << ans << "\n";
    return 0;
}
