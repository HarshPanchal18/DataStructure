#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int altChar(char *s)
{
    int k = 0;

    for (int i = 0; i < strlen(s); i++)
        // Comparing characters
        // If both are same, then one has to deleted
        if (s[i] == s[i + 1])
            k++; // number of deletions to be done gets incremented

    return k;
}

int main(void)
{
    char str[10];
    scanf("%s", str);
    printf("%d", altChar(str));
}
/*
aaaabb
4

(a - 3 times, b - once)
*/
