/*
Find the possible minimum difference of the height of shortest and longest towers after you have modified each tower.

Input:
K = 3, N = 5
Arr[] = {3, 9, 12, 16, 20}
Output:
11
Explanation:
The array can be modified as
{6, 12, 9, 13, 17}. The difference between
the largest and the smallest is 17-6 = 11.
*/

#include <iostream>
#include <algorithm>

using namespace std;

int getMinDiff(int arr[], int n, int k)
{
    sort(arr, arr + n);

    // store the difference between first and last value.
    int ans = arr[n - 1] - arr[0];

    for (int i = 0; i < n - 1; i++)
    {
        if (arr[i + 1] < k)
            continue;

        int currMin = min(arr[i + 1] - k, arr[0] + k); // minimum value after update
        cout << currMin << " ";
        int currMax = max(arr[i] + k, arr[n - 1] - k); // maximum value after update
        cout << currMax << " ";

        ans = min(ans, currMax - currMin); // minimum value between previous answers and current difference will be saved in ans variable.
        cout << ans << endl;
    }
    return ans;
}

int main(void)
{
    int n, k;
    cin >> n; // Accept length of the array
    cin >> k; // K is the number by which the values will be increased/decreased

    int arr[n];
    for (int i = 0; i < n; i++)
        cin >> arr[i];

    int min_diff = getMinDiff(arr, n, k);

    cout << "The difference between the largest and smallest is: " << min_diff << "\n";
}
