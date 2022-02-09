#include <stdio.h>

int main(void)
{
    int n;
    printf("\nEnter the number of elements: ");
    scanf("%d", &n);

    int arr[n];

    printf("\nEnter %d elements:\n", n);
    for (int i = 0; i < n; i++)
        scanf("%d", &arr[i]);

    int k = 0;
    for (int i = 0; i < n; i++)
        if (arr[i] != 0)
            arr[k++] = arr[i];

    for (int i = k; i < n; i++)
        arr[i] = 0;

    printf("\nResultant Array:\n");
    for (int i = 0; i < n; i++)
        printf("%d ", arr[i]);
}
/*
Enter the number of elements: 8

Enter 8 elements:
26
0
18
0
69
15
0
37

Resultant Array:
26 18 69 15 37 0 0 0
*/
