/*
Given a sorted array Arr[](0-index based) consisting of N distinct integers and an integer k,
the task is to find the index of k, if it’s present in the array Arr[].
Otherwise, find the index where k must be inserted to keep the array sorted.

Input:
N = 4
Arr = {1 , 3, 5, 6}
k = 5
Output: 2
Explaination: Since 5 is found at index 2
as arr[2] = 5, the output is 2.

Input:
N = 4
Arr = {1, 3, 5, 6}
k = 2
Output: 1
Explaination: Since 2 is not present in
the array but can be inserted at index 1
to make the array sorted.
*/

#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    int searchInsertK(vector<int> Arr, int N, int k)
    {
        for (int i = 0; i < N; i++)
            if (Arr[i] == k)
                return i;

        Arr.push_back(k);

        sort(Arr.begin(), Arr.end());

        for (int i = 0; i < N; i++)
            if (Arr[i] == k)
                return i;
    }
};

int main(void)
{
    int N;
    cin >> N;

    vector<int> Arr(N);

    for (int i = 0; i < N; i++)
        cin >> Arr[i];

    int k;
    cin >> k;

    Solution obj;
    cout << obj.searchInsertK(Arr, N, k) << endl;
}
