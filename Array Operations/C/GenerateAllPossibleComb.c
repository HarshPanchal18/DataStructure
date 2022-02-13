#include <stdio.h>
#include <string.h>

void Print(int *num, int n)
{
    for (int i = 0; i < n; i++)
        printf("%d ", num[i]);
    printf("\n");
}

int main(void)
{
    int n;
    printf("\nEnter the number of elements: ");
    scanf("%d", &n);

    int arr[n];
    printf("\nEnter %d elements: \n", n);
    for (int i = 0; i < n; i++)
        scanf("%d", &arr[i]);

    printf("\nResult:\n");
    for (int j = 1; j <= n; j++)
    {
        for (int i = 0; i < n - 1; i++)
        {
            int temp = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = temp;
            Print(arr, n);
        }
        printf("\n");
    }
}