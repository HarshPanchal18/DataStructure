#include <iostream>
using namespace std;

int main(void)
{
    int n, k;
    cin >> n;
    int arr[n];

    for (int i = 0; i < n; i++)
        cin >> arr[i];

    for (int i = 0; i < n; ++i)
        for (int j = i + 1; j < n; ++j)
            if (arr[i] == arr[j])
                for (--n, k = j--; k < n; ++k)
                    arr[k] = arr[k + 1];

    cout << n << " unique" << endl;
    for (int i = 0; i < n; ++i)
        cout << arr[i] << ' ';
    cout << endl;
    return 0;
}

/*
5
8 7 8 9 8
3 unique
8 7 9
*/
