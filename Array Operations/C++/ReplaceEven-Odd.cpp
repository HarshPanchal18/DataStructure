/*
Given an array arr[] of size N containing equal number of odd and even numbers.
Arrange the numbers in such a way that all the even numbers get the even index and odd numbers get the odd index.

Note: There are multiple possible solutions, Print any one of them. Also, 0-based indexing is considered.

Input:
arr[6] = {3, 6, 12, 1, 5, 8}
Output:
1
Explanation:
6 3 12 1 8 5 is a possible solution.
The output will always be 1 if your
rearrangement is correct.
*/

#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    void reArrange(int arr[], int N)
    {
        /*for(int i=0;i<N;i+=2)
        {
            if(arr[i+1]%2==0)
                swap(arr[i],arr[i+1]);
        }*/

        int i = 0, j = 1;
        while (i < N && j < N)
        {
            while (i < N && arr[i] % 2 == 0)
                i += 2;

            while (j < N && arr[j] % 2 == 1)
                j += 2;

            swap(arr[i], arr[j]);
            i += 2;
            j += 2;
        }
    }
};

int check(int arr[], int n)
{
    int flag = 1;
    int c = 0, d = 0;
    for (int i = 0; i < n; i++)
    {
        if (i % 2 == 0)
        {
            if (arr[i] % 2)
            {
                flag = 0;
                break;
            }
            else
                c++;
        }
        else
        {
            if (arr[i] % 2 == 0)
            {
                flag = 0;
                break;
            }
            else
                d++;
        }
    }
    if (c != d)
        flag = 0;

    return flag;
}

int main()
{
    int N;
    cin >> N;

    int arr[N];
    for (int i = 0; i < N; i++)
        cin >> arr[i];

    Solution ob;
    ob.reArrange(arr, N);

    cout << check(arr, N) << endl;
}
