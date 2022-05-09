#include <bits/stdc++.h>
using namespace std;

/*
Given two sorted arrays A and B of length L1 and L2,
we can get a set of sums(add one element from the first and one from second).
Find the Nth element in the set considered in sorted order.
Note: Set of sums should have unique elements.

Input
L1,L2: 5 4
L1: 1 3 4 8 10
L2: 20 22 30 40
N:  4

array: 21 23 24 25 26 28 30 31 32 33 34 38 40 41 43 44 48 50 25 (19 elements)
Here N-1 = 4-1 = 3rd Index

Output: 25
*/

class Solution
{
public:
    int nthItem(int L1, int L2, int A[], int B[], int N)
    {
        set<int> array;
        int c = 0;
        for (int i = 0; i < L1; i++)
            for (int j = 0; j < L2; j++)
                array.insert(A[i] + B[j]);

        /*for(auto i:array)
            cout<<i<<" ";*/

        for (auto i : array)
            if (c++ == N - 1)
                return i;

        return -1;
    }
};

int main(void)
{
    int L1, L2, N;
    cin >> L1 >> L2;

    int A[L1], B[L2];

    for (int i = 0; i < L1; i++)
        cin >> A[i];
    for (int i = 0; i < L2; i++)
        cin >> B[i];

    cin >> N; // Nth item

    Solution ob;
    cout << ob.nthItem(L1, L2, A, B, N) << endl;
}
