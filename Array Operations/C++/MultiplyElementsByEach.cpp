/*
This problem was asked by Uber.
Given an array of integers, return a new array such that each element at index i of the new array
    is the product of all the numbers in the original array except the one at i.
For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24].
If our input was [3, 2, 1], the expected output would be [2, 3, 6].
Follow-up: what if you can't use division?
*/

#include <bits/stdc++.h>

using std::cin;
using std::cout;

int main(void)
{
    cout << "\nEnter the number of elements: ";
    int n;
    cin >> n;
    int ve[n], l[n], r[n];

    for (int i = 0; i < n; i++)
        cin >> ve[i];

    int temp = 1;
    for (int i = 0; i < n; i++)
    {
        temp = temp * ve[i];
        l[i] = temp; // stores from starting
    }

    temp = 1;
    for (int i = n - 1; i >= 0; i--)
    {
        temp = temp * ve[i];
        r[i] = temp; // stores from ending
    }

    /*for (int i = 0; i < n; i++)
        cout << l[i] << " ";

    cout << std::endl;

    for (int i = 0; i < n; i++)
        cout << r[i] << " ";

    cout << std::endl;
*/

    for (int i = 0; i < n; i++)
    {
        temp = 1;
        if (i - 1 >= 0)
            temp = temp * l[i - 1];

        if (i + 1 < n)
            temp = temp * r[i + 1];

        cout << temp << " ";
    }
}
