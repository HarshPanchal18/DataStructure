#include <stdio.h>

int MaxValIndex(const int *arr, int low, int high)
{
    if (low >= high)
        return low;

    int mid = low + (high - low) / 2;

    int LeftIndex = MaxValIndex(arr, low, mid);
    int RightIndex = MaxValIndex(arr, mid + 1, high);

    return arr[LeftIndex] >= arr[RightIndex] ? LeftIndex : RightIndex; // Put < for the smallest value
}

int main(void)
{
    int arr[] = {10, 5, 6, 9, 17, 5, 15, 9};
    int len = sizeof(arr) / sizeof(arr[0]);

    printf("\nArray Elements:");
    for (int i = 0; i < len; i++)
        printf("\t%2d", arr[i]);
    printf("\n\t");

    for (int i = 0; i < len; i++)
        printf("\t--");
    printf("\n");

    printf("Index: \t");
    for (int i = 0; i < len; i++)
        printf("\t%2d", i);
    printf("\n");

    printf("\nIndex of Max. Element: %d\n\n", MaxValIndex(arr, 0, len - 1));
}