// Author: Ankur S ( www.github.com/s-ankur )
// Input: <number of characters> <string>
// Output: <ciphertext>

#include <stdio.h>
int main()
{
    char s[20];
    int n;
    scanf("%d %s", &n, s);
    for (char *sp = s; *sp && (*sp = (*sp + n - 'a' + 26) % 26 + 'a'); sp++)
        ;
    printf("%s", s);
}
