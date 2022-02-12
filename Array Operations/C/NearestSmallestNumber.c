#include <stdio.h>

void PrintLeftSmallest(int a[], int n)
{
    printf("_, "); // for first element

    for (int i = 1; i < n; i++)
    {
        // look for small element on left of 'i'
        int j;
        for (j = i - 1; j >= 0; j--)
        {
            if (a[j] < a[i])
            {
                printf("%d, ", a[j]);
                break;
            }
        }

        if (j == -1) // if there is no smaller element on left of 'i'
            printf("_, ");
    }
}

int main(void)
{
    int n;
    printf("\nEnter the number of elements: ");
    scanf("%d", &n);

    int a[n];

    printf("Enter %d elements: \n", n);
    for (int i = 0; i < n; i++)
        scanf("%d", &a[i]);

    PrintLeftSmallest(a, n);
}

/*
Enter the number of elements: 6
Enter 6 elements:
4 8 1 5 6 7
_, 4, _, 1, 5, 6,
*/