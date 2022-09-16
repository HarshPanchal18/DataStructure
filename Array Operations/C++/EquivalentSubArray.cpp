#include <bits/stdc++.h>
using namespace std;

/*
Given an array arr[] of n integers.
Count the total number of sub-array having total distinct elements
same as that of total distinct elements of the original array.

Example 1:
Input:
N=5
arr[] = {2, 1, 3, 2, 3}
Output: 5
Explanation:
Total distinct elements in array is 3
Total sub-arrays that satisfy the conditions are:
Subarray from index 0 to 2
Subarray from index 0 to 3
Subarray from index 0 to 4
Subarray from index 1 to 3
Subarray from index 1 to 4

Example 2:
Input:
N=5
arr[] = {2, 4, 4, 2, 4}
Output: 9
*/

class Solution
{
public:
    int countDistinctSubarray(int arr[], int n)
    {
        set<int> s;

        for (int i = 0; i < n; i++)
            s.insert(arr[i]);

        int count = 0;
        int uniqueEles = s.size();

        for (int i = 0; i < n; i++)
        {
            set<int> s1;
            for (int j = i; j < n; j++)
            {
                s1.insert(arr[j]);
                if (s1.size() == uniqueEles)
                {
                    count += (n - j);
                    break;
                }
            }
        }
        return count;
    }

    int method2(int arr[], int n)
    {
        unordered_set<int> s;
        unordered_map<int, int> m;

        int ans = 0;

        for (int i = 0; i < n; i++)
            s.insert(arr[i]);

        int k = s.size();
        int j = 0;

        for (int i = 0; i < n; i++)
        {
            m[arr[i]]++;
            while (m.size() >= k && j <= i)
            {
                ans += n - i;
                m[arr[j]]--;

                if (m[arr[j]] == 0)
                    m.erase(arr[j]);
                j++;
            }
        }
        return ans;
    }

    int method3(int arr[], int n)
    {
        map<int, int> occurs;

        for (int i = 0; i < n; i++)
            occurs[arr[i]]++;

        int unique = occurs.size();
        occurs.clear();
        int i = 0, j = 0;
        int sub_count = 0;

        while (j < n)
        {
            occurs[arr[j]]++;
            while (occurs.size() == unique)
            {
                sub_count += (n - j); // 1 + (n - j - 1)
                if (--occurs[arr[i]] == 0)
                    occurs.erase(arr[i]);
                i++;
            }
            j++;
        }
        return sub_count;
    }
};

int main(void)
{
    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; ++i)
        cin >> a[i];
  
    Solution ob;
    cout << ob.countDistinctSubarray(a, n) << endl;
    return 0;
}
