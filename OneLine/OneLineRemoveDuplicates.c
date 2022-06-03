#include <stdio.h>

int main(void)
{
    int n, k;
    scanf("%d", &n);
    int arr[n];

    for (int i = 0; i < n; i++)
        scanf("%d", &arr[i]);

    for (int i = 0; i < n; ++i)
        for (int j = i + 1; j < n; ++j)
            if (arr[i] == arr[j])
                for (--n, k = j--; k < n; ++k)
                    arr[k] = arr[k + 1];

    printf("%d unique\n", n);

    for (int i = 0; i < n; ++i)
        printf("%d ", arr[i]);
    printf("\n");
    return 0;
}

/*
5
8 7 8 9 8
3 unique
8 7 9
*/
