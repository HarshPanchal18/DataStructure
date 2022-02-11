#include <stdio.h>
#define MAX 10
int main(void)
{
    int Smallest, SecSmallest;
    int arr[MAX], no, i;

    printf("\nEnter the number of elements: ");
    scanf("%d", &no);

    printf("\nEnter %d elements: \n", no);
    for (i = 0; i < no; i++)
        scanf("%d", &arr[i]);

    if (arr[0] < arr[1])
    {
        Smallest = arr[0];
        SecSmallest = arr[1];
    }
    else
    {
        Smallest = arr[1];
        SecSmallest = arr[0];
    }

    for (i = 2; i < no; i++)
    {
        if (arr[i] < Smallest)
        {
            SecSmallest = Smallest;
            Smallest = arr[i];
        }
        else if (arr[i] < SecSmallest)
            SecSmallest = arr[i];
    }
    printf("\nSecond Smallest = %d\n", SecSmallest);
}

/*
21   65   12   96   19

small=21
Sec=65

1.
arr[i]=12
if 12 < 21
    Sec=21
    Small=12

2.
    arr[i]= 96
    if 96<12-> skip
    elif 96<21-> skip

3.
    arr[i]=19
    if 19<12 -> skip
    elif 19<21
    Sec=19

Hence, Second Smallest = 19
*/