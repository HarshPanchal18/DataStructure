#include <bits/stdc++.h>
using namespace std;

void leftRotateBy1(int arr[], int n)
{
    int temp = arr[0];
    for (int i = 1; i < n; i++)
        arr[i - 1] = arr[i]; // move other element back bye one

    arr[n - 1] = temp; // insert temp element at end
}

void leftRotateByD(int arr[], int n, int d)
{
    for (int i = 0; i < d; i++)
        leftRotateBy1(arr, n);
}

int main(void)
{
    int n, d;
    cout << "Enter the number of elements\n";
    cin >> n;

    int arr[n];
    cout << "Enter " << n << "elements: ";
    for (int i = 0; i < n; i++)
        cin >> arr[i];

    cout << "Enter the number of rotations: ";
    cin >> d;

    leftRotateByD(arr, n, d);

    for (int i = 0; i < n; i++)
        cout << arr[i] << " ";
}
