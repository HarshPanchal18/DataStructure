/*
Given an array find the number which comes with maximum frequency.
It must work in O(n) time complexity.
Sample Input
5
1 2 2 2 3
Sample Output
2
Explanation
2 has the highest frequency in the array i.e. 3.
*/

#include <bits/stdc++.h>
#include <unordered_map>

using namespace std;

int main(void)
{
    int n;
    cout << "\nEnter the number of elements: ";
    cin >> n;

    int a[n];

    for (int i = 0; i < n; i++)
        cin >> a[i];

    unordered_map<int, int> h{0};

    for (int i = 0; i < n; i++)
        h[a[i]]++;

    int res = -1;
    int maxCount = 0;

    for (auto x : h)
        if (x.second >= maxCount)
        {
            res = x.first;
            maxCount = x.second;
        }

    cout << res;
}
