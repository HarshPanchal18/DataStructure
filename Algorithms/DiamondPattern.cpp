#include <bits/stdc++.h>
using namespace std;

/*
Write a program that receives a number n as input and prints it in the following format as shown below.
Like for n = 2 the pattern will be:
1*2*5*6
--3*4

Example 1:
Input: n = 3
Output:

1*2*3*10*11*12
--4*5*8*9
----6*7

Explaination: If the pattern shown in question is followed, this will be the output.
*/

class Solution
{
public:
    vector<string> pattern(int n)
    {
        vector<string> v(n);
        int n2 = n * (n + 1);
        int n1 = n2 - n + 1;
        int t = n;
        int c1 = 1, c2 = n1;

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < i; j++)
                v[i] += "--";

            int c = 0;
            for (int l = 0; l < n - i; l++)
            {
                v[i] += to_string(c1++);
                v[i] += "*";
                // c1++;
                c++;
            }

            for (int k = 0; k < n - i; k++)
            {
                v[i] += to_string(c2++);
                if (k != n - i - 1)
                    v[i] += "*";
                // c2++;
            }

            c2 = n1 - c + 1;
            n1 = c2;
        }
        return v;
    }
};

int main()
{
    int n;
    cin >> n;

    Solution ob;
    vector<string> ans = ob.pattern(n);
    for (int i = 0; i < n; i++)
        cout << ans[i] << "\n";
    return 0;
}
