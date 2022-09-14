#include <bits/stdc++.h>
using namespace std;

/*
Given an array of N positive integers where all numbers occur even number of times
except one number which occurs odd number of times. Find the exceptional number.

Example 1:
Input:
N = 7
Arr[] = {1, 2, 3, 2, 3, 1, 3}
Output: 3
Explaination: 3 occurs three times.

Example 2:
Input:
N = 7
Arr[] = {5, 7, 2, 7, 5, 2, 5}
Output: 5
Explaination: 5 occurs three times.

*/

class Solution
{
public:
    int getOddOccurrence(int arr[], int n)
    {
        // method1
        int ans = 1;
        sort(arr, arr + n);

        for (int i = 0; i < n; i++)
        {
            if (arr[i] == arr[i + 1])
                ans++;
            else
            {
                if (ans % 2 != 0)
                    return arr[i];
                ans = 1;
            }
        }

        // method2
        map<int, int> m;
        for (int i = 0; i < n; i++)
            m[arr[i]]++;

        for (auto i : m)
            if (i.second % 2 != 0)
                return i.first;

        // method3
        int a = 0;
        for (int i = 0; i < n; i++)
            a ^= arr[i];
        return a;
    }
};

int main()
{
    int n;
    cin >> n;
    int arr[n];
    for (int i = 0; i < n; i++)
        cin >> arr[i];

    Solution ob;
    auto ans = ob.getOddOccurrence(arr, n);
    cout << ans << "\n";
    return 0;
}
