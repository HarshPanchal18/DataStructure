#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void)
{
    char s[] = "Dynamic Mobility Producer";

    printf("\nBefore Memset(): %s", s);

    memset(s + 7, '.', 10 * sizeof(char));

    printf("\nAfter Memset(): %s", s);
}

/*
(s + 7) points to first space (0 based index) of the string “Dynamic Mobility Producer”,
and memset() sets the character ‘.’ starting from first ‘ ‘ of the string up to 10 character positions of the given string.
*/