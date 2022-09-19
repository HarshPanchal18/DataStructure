#include <bits/stdc++.h>
using namespace std;

/*
* Minimum Sum of Absolute Differences of Pairs
You are given two arrays A and B of equal length N.
Your task is to pair each element of array A to an element in array B,
such that the sum of the absolute differences of all the pairs is minimum.

Example 1:
Input:
N = 4
A = {4,1,8,7}
B = {2,3,6,5}
Output:
6
Explanation:
If we take the pairings as (1,2), (4,3), (7,5), and (8,6), the sum will be
S = |1 - 2| + |4 - 3| + |7 - 5| + |8 - 6| = 6.
It can be shown that this is the minimum sum we can get.


Example 2:
Input:
N = 3
A = {4,1,2}
B = {2,4,1}
Output:
0
Explanation:
If we take the pairings as (4,4), (1,1), and (2,2), the sum will be
S = |4 - 4| + |1 - 1| + |2 - 2| = 0.
It can be shown that this is the minimum sum we can get.
*/

class Solution
{
public:
    long long findMinSum(vector<int> &A, vector<int> &B, int N)
    {
        long long sum = 0;
        sort(A.begin(), A.end());
        sort(B.begin(), B.end());

        for (int i = 0; i < N; i++)
            sum += abs(A[i] - B[i]);

        return sum;
    }

    // Method 2
    long long findMinSum(vector<int> &A, vector<int> &B, int N)
    {
        long long sum = 0;
        long long count = 0;

        sort(A.begin(), A.end());
        sort(B.begin(), B.end());

        for (int i = 0; i < N; i++)
        {
            // sum= abs(A[i]-B[i]);
            if (A[i] > B[i])
                sum = A[i] - B[i];
            else
                sum = B[i] - A[i];

            count += sum;
        }
        return count;
    }
};

int main()
{
    int N;
    cin >> N;
    vector<int> A(N), B(N);

    for (int i = 0; i < N; i++)
        cin >> A[i];

    for (int i = 0; i < N; i++)
        cin >> B[i];

    Solution ob;
    cout << ob.findMinSum(A, B, N) << endl;
}
