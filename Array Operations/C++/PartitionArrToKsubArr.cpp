#include <bits/stdc++.h>
using namespace std;

/*
* Given an integer array a[ ] of N elements and an integer K, the task is to check the
if the array a[ ] could be divided into K non-empty subsets with equal sum of elements.
! Note: All elements of this array should be part of exactly one partition.

Example 1:
Input:
N = 5
a[] = {2,1,4,5,6}
K = 3
Output:
1
Explanation: we can divide above array into 3 parts with equal sum as (2, 4), (1, 5), (6)

Example 2:
Input:
N = 5
a[] = {2,1,5,5,6}
K = 3
Output:
0
Explanation: It is not possible to divide above array into 3 parts with equal sum.
*/

class Solution
{
public:
    bool isKPartitionPossible(int a[], int n, int k)
    {
        if (k == 1)
            return true;
        else if (n < k)
            return false;
        else
        {
            int sum = 0;
            for (int i = 0; i < n; i++)
                sum += a[i];

            if (sum % k != 0)
                return false;

            else
            {
                int i = 0, j = n - 1, target = sum / k;
                sort(a, a + n);

                while (i < j)
                {
                    if (a[i] + a[j] > target)
                    {
                        if (a[j] == target)
                            j--;
                        else
                            return false;
                    }
                    else
                    {
                        i++;
                        j--;
                    }
                }
                return true;
            }
        }
    }
};

int main()
{
    int n;
    cin >> n;
    int a[n];

    for (int i = 0; i < n; i++)
        cin >> a[i];

    int k;
    cin >> k;

    Solution obj;
    cout << obj.isKPartitionPossible(a, n, k) << endl;
}
