#include <stdio.h>

void swap(int *a, int *b)
{
    int temp = *a;
    *a = *b;
    *b = temp;
}

int FindKth(int arr[], int n, int k)
{
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            if (arr[j] > arr[j + 1]) // put < for Largest
                swap(&arr[j], &arr[j + 1]);

    return arr[k - 1];
    // return arr[k]; // Return this for the largest
}

int main(void)
{
    int n;
    printf("\nEnter the number of elements: ");
    scanf("%d", &n);

    int arr[n];

    printf("\nEnter %d elements:\n", n);
    for (int i = 0; i < n; i++)
        scanf("%d", &arr[i]);

    int k;
    printf("\nEnter Kth (smallest element to found): ");
    scanf("%d", &k);

    printf("\n%d th smallest number form array is %d", k, FindKth(arr, n, k));
}
