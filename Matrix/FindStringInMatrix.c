/*
A character matrix of size N*N and a string S are given as input.The program must check if the string is present
in the matrix row-wise and print the row number of the rows with the given string in it.

*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void)
{
    int n;
    scanf("%d", &n);
    char matrix[n][n], str[20];

    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            scanf(" %c", &matrix[i][j]);

    scanf("%s", str);
    int k = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
            if (str[k] == matrix[i][j])
                k++;

        if (k == strlen(str))
            printf("%d\n", i + 1);

        k = 0;
    }
}

/*
Input:
4
h e l l
k t h e
a t c k
t h e k
the
Output:
2
4
*/
