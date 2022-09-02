/*
Shekhar is a bomb defusal specialist.
He once encountered a bomb that can be defused only by a secret code.
He is given a number N and a number K.
And he is also given permutation of first N natural numbers.
The defusal code is the largest permutation possible by doing atmost K swaps among a pair of the given permutation.
Help him to find the final permutation.

Sample Input
n=5
swaps=2
3 4 1 2 5

Sample Output
5 4 3 2 1

Explanation
First we can swap 5 with 3 which gives us 5 4 1 2 3 and then we can swap 3 and 1 which gives us 5 4 3 2 1.
*/

#include <bits/stdc++.h>

using namespace std;

int main(void)
{
    int n, k;
    cout << "\nEnter the number of elements: ";
    cin >> n;

    int arr[n + 1], index[n + 1];
    for (int i = 1; i <= n; i++)
    {
        cin >> arr[i];
        index[arr[i]] = i;
    }

    cout << "\nEnter the number of swaps: ";
    cin >> k;

    int temp = n, i = 1;

    while (k-- && i <= n)
    {
        if (arr[i] != temp)
        {
            int pos = index[temp];
            index[arr[i]] = pos;

            swap(arr[i], arr[pos]);
            k--;
        }
        i++;
        temp--;
    }

    for (int i = 1; i <= n; i++)
        cout << arr[i] << " ";
}
