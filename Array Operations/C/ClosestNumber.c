#include <stdio.h>

/*
Method to compare which one is the more close.
We find the closest by taking the difference between the target and both values.
It assumes that val2 is greater than val1 and target lies between these two.
*/

int getCLosest(int v1, int v2, int target)
{
    return (target - v1 >= v2 - target) ? v2 : v1;
}

int findClosest(int arr[], int n, int target)
{
    // Lower than first or Bigger than last
    if (target <= arr[0])
        return arr[0];
    if (target >= arr[n - 1])
        return arr[n - 1];

    int i = 0, j = n, mid = 0;

    while (i < j)
    {
        mid = (i + j) / 2;

        if (arr[mid] == target)
            return arr[mid];

        // If target is less than array element, then search in left
        if (target < arr[mid])
        {
            // If target is greater than prev. to mid, return closest of two
            if (mid > 0 && target > arr[mid - 1])
                return getCLosest(arr[mid - 1], arr[mid], target);

            j = mid; // continue for left
        }

        else
        {
            if (mid < n - 1 && target < arr[mid + 1])
                return getCLosest(arr[mid], arr[mid + 1], target);

            i = mid + 1;
        }
    }
    return arr[mid];
}

int main(void)
{
    int arr[] = {1, 2, 5, 7, 9, 14};
    int n = sizeof(arr) / sizeof(arr[0]);
    int target = 12;

    printf("%d is closest to ", target);
    printf("%d in array..", findClosest(arr, n, target));
}
