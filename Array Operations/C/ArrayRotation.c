#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

void swap(int *a, int *b)
{
    int temp = *a;
    *a = *b;
    *b = temp;
}

void Reverse(int *a, int start, int end)
{
    // reverse an array from index 'start' to index 'end'
    while (start < end)
    {
        swap(&a[start++], &a[end--]);
        //++start;
        //--end;
    }
}

void LeftRotate(int *a, int n, int r)
{
    // leftRotate - rotate an array of length n with r rotations in left direction
    r = r % n;
    Reverse(a, 0, r - 1);
    Reverse(a, r, n - 1);
    Reverse(a, 0, n - 1);
}

void RightRotate(int *a, int n, int r)
{
    r = r % n;
    Reverse(a, 0, n - 1);
    Reverse(a, 0, r - 1);
    Reverse(a, r, n - 1);
}

int main(void)
{
    int n, r, d;
    printf("\nEnter the number of elements: ");
    scanf("%d", &n);

    int arr[n];
    printf("\nEnter array elements:\n");
    for (int i = 0; i < n; i++)
        scanf("%d", &arr[i]);

    printf("\nEnter the number of rotations you need: ");
    scanf("%d", &r);

    printf("\nDirection (left=0 right=1): ");
    scanf("%d", &d);

    assert((d == 0) || (d == 1));

    if (d == 0)
        LeftRotate(arr, n, r);
    else
        RightRotate(arr, n, r);

    printf("\nThe Rotation of array:\n");
    for (int i = 0; i < n; i++)
        printf("%d\t", arr[i]);
}
