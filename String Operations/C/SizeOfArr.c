#include <stdio.h>

// * This is simple trick you can discovery size of array without sizeof...

int main()
{
    char str[] = "just another test";
    int len0 = (&str)[1] - str;
    int len1 = *(&str + 1) - str;

    printf("char %d\nchar %d\n", len0, len1);

    return 0;
}
