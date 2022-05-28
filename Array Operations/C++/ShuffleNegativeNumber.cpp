#include <iostream>
using namespace std;

void segregateElements(int arr[], int n)
{
    int res[n]; // to store the resultant array
    int j = 0;  // index of result array

    for (int i = 0; i < n; i++)
        if (arr[i] >= 0)
            res[j++] = arr[i];

    if (j == n || j == 0)
        return;

    for (int i = 0; i < n; i++)
        if (arr[i] < 0)
            res[j++] = arr[i];

    memcpy(arr, res, sizeof(res)); // copy to arr[]
}

int main(void)
{
    int nums[] = {5, -8, 7, -5, -1, 3};
    int n = sizeof(nums) / sizeof(nums[0]);
    cout << "Original array: ";
    for (int i = 0; i < n; i++)
        cout << nums[i] << " ";

    segregateElements(nums, n);

    printf("\nArray elements after rearrange: ");
    for (int i = 0; i < n; i++)
        cout << nums[i] << " ";
}

/*
Original array: 5 -8 7 -5 -1 3
Array elements after rearrange: 5 7 3 -8 -5 -1
*/
