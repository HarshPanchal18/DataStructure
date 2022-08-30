#include <stdio.h>

/*
Given an integer array Arr of size N.
For each element in the array, check whether the right adjacent element (on the next immediate position) of the array is smaller. If next element is smaller, update the current index to that element. If not, then  -1.

Example 1:
Input:
N = 5
Arr[] = {4, 2, 1, 5, 3}
Output: 2 1 -1 3 -1
Explanation: Array elements are 4, 2, 1, 5, 3.
Next to 4 is 2 which is smaller, so we print 2.
Next of 2 is 1 which is smaller, so we print 1. Next of 1 is 5 which is greater, so we print -1.
Next of 5 is 3 which is smaller, so we print 3.
Note that for last element, output is always going to be -1 because there is no element on right.

Example 2:
Input:
N = 6
Arr[] = {5, 6, 2, 3, 1, 7}
Output: -1 2 -1 1 -1 -1
Explanation: Next to 5 is 6 which is greater, so we print -1.
Next of 6 is 2 which is smaller, so we print 2.
Next of 2 is 3 which is greater, so we print -1.
Next of 3 is 1 which is smaller, so we print 1.
Next of 1 is 7 which is greater, so we print -1.
Note that for last element, output is always going to be -1 because there is no element on right.
*/

void immediateSmaller(int arr[], int n)
{
    for (int i = 1; i < n; i++)
        if (arr[i - 1] > arr[i])
            arr[i - 1] = arr[i];
        else
            arr[i - 1] = -1;
    arr[n - 1] = -1;

    /* method2
    for (int i = 0; i < n - 1; i++)
        if (arr[i + 1] < arr[i])
            arr[i] = arr[i + 1];
        else
            arr[i] = -1;
    arr[n - 1] = -1;*/
}

int main()
{
    int n;
    scanf("%d", &n);
    int arr[n];

    for (int i = 0; i < n; i++)
        scanf("%d", &arr[i]);

    immediateSmaller(arr, n);

    for (int i = 0; i < n; i++)
        printf("%d ", arr[i]);

    return 0;
}
