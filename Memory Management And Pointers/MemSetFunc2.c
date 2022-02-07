#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void)
{
    int n = 2;
    char arr[n];

    printf("\nEnter the string: ");
    gets(arr);

    memset(arr, 2, n * sizeof(arr[0]));

    printf("%s ", arr);
}

/*
Enter the string: pirate
☻☻rate
*/