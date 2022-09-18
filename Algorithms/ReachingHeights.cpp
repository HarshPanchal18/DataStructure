#include <bits/stdc++.h>
using namespace std;

/*
The teacher gives a mental ability question to Raju.
The question is as follows:-
Raju is in an elevator.
Given by his teacher is an array of size N which denotes the number of floors and has a 1 based indexing.
The elevator starts from the ground and moves up and down, X and Y floors respectively.
There is a code used in the elevator according to which it moves up X floors given at odd indexes of the array
and moves down Y floors given at even indexes of the array. He is asked to go to the highest floor possible.
Help him to sort the array such that he reaches the highest floor after traversing the whole array
from starting till the end, without skipping any index.
He always prefers to move more number of floors up and less number of floors down.
Once he gets into the elevator, the elevator should not reach the ground again, if it does return -1.

Input : arr[ ] = {2, 3, 4, 5}
Output : 5 2 4 3
Explanation:
Array can be arranged as {5,3,4,2} or {4,3,5,2} or {4,2,5,3} but it will get
arranged as {5,2,4,3} because he always prefer to move more number of floors up and less number of floors down.

Input : arr[ ] = {1, 1}
Output :  Not Possible
*/

vector<int> reaching_height(int n, int a[])
{
    sort(a, a + n, greater<int>());
    vector<int> v;
    int k = n - 1;
    int i = 0;
    int res = 0;

    while (i <= k)
    {
        v.push_back(a[i++]);
        v.push_back(a[k--]);
    }
    for (int i = 0; i < n; i++)
    {
        if (i % 2 == 0)
            res += v[i];
        else
            res -= v[i];
        if (res == 0)
            return {-1};
    }
    if (res > 0)
        if (n % 2 == 1)
            v.pop_back();
    return v;
}

int main()
{
    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++)
        cin >> a[i];

    vector<int> v;
    v = reaching_height(n, a);

    if (v.size() == 1 and v[0] == -1)
        cout << "Not Possible\n";
    {
        for (int i : v)
            cout << i << " ";
        cout << endl;
    }
    return 0;
}
