#include <stdio.h>

/*
Replace every element with the smallest element on its left side.
Enter the number of elements: 6
Enter 6 elements:
1 2 5 2 2 5

Result: 16 14 9 7 5 0
*/

void Replace(int a[], int n)
{
    int sum = 0;

    for (int i = 0; i < n; i++)
        sum += a[i];

    for (int i = 0; i < n; i++)
    {
        a[i] = sum - a[i];
        sum = a[i];
    }
}

int main(void)
{
    int n;
    printf("\nEnter the number of elements: ");
    scanf("%d", &n);

    int a[n];

    printf("Enter %d elements: \n");
    for (int i = 0; i < n; i++)
        scanf("%d", &a[i]);

    Replace(a, n);

    printf("\nResult: ");
    for (int i = 0; i < n; i++)
        printf("%d ", a[i]);
}
