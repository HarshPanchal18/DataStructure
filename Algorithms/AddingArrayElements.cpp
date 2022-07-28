#include <bits/stdc++.h>

using namespace std;

/*
Given an array Arr[] of size N and an integer K, you have to add the first two minimum elements
of the array until all the elements are greater than or equal to K and find the number of such operations required.

Example 1:
Input:
N = 6, K = 6
Arr[] = {1, 10, 12, 9, 2, 3}
Output: 2
Explanation: First we add (1 + 2), now the new list becomes 3 10 12 9 3, then we add
(3 + 3), now the new list becomes 6 10 12 9, Now all the elements in the list are greater than 6.
Hence the output is 2 i:e 2 operations are required to do this.

Example 2:
Input:
N = 4, K = 4
Arr[] = {5, 4, 6, 4}
Output: 0
Explanation: Every element in the given array is greater than or equal to K.

*/

int minOperations(int arr[], int n, int k)
{
    int count = 0;
    priority_queue<int, vector<int>, greater<int>> pqueue;

    for (int i = 0; i < n; i++)
        pqueue.push(arr[i]);

    while (pqueue.top() < k)
    {
        int x = pqueue.top();
        pqueue.pop();

        int y = pqueue.top();
        pqueue.pop();

        int z = x + y;
        pqueue.push(z);

        count++;
    }
    return count;
}

int main()
{
    int n, k;
    cin >> n >> k;
    int arr[n];

    for (int i = 0; i < n; i++)
        cin >> arr[i];

    cout << minOperations(arr, n, k) << "\n";
    return 0;
}
