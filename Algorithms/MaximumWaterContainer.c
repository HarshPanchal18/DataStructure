#include <stdio.h>

int maxArea(int arr[], int n)
{
    int start = 0;
    int end = n - 1;
    int area = 0;
    int min;

    while (start < end)
    {
        if (arr[start] < arr[end])
            min = arr[start];
        else
            min = arr[end];

        int t = end - start;

        if (area < (min * t))
            area = min * t;

        if (arr[start] < arr[end])
            start++;
        else
            end--;
    }
    return area;
}

int main(void)
{
    int size1, size2;
    printf("\nEnter the size of first array: ");
    scanf("%d", &size1);
    printf("\nEnter the size of second array: ");
    scanf("%d", &size2);

    int a[size1], b[size2];

    printf("Enter the elements of a container :\n");
    for (int i = 0; i < size1; i++)
        scanf("%d", &a[i]);

    printf("Enter the elements of b container\n");
    for (int i = 0; i < size2; i++)
        scanf("%d", &b[i]);

    int A = maxArea(a, size1);
    int B = maxArea(b, size1);

    if (A > B)
        printf("Container A contains more");
    else
        printf("Container B contains more");
}

/*
Enter the size of first array: 2

Enter the size of second array: 2
Enter the elements of a container :
5 7
Enter the elements of b container
9 7
Container B contains more
*/
