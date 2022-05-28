/* C Program to Check Two Matrices are Equal or Not */

#include <stdio.h>

int main()
{
    int i, j, rows, columns, isEqual;

    printf("\nEnter Number of rows and columns :  ");
    scanf("%d %d", &i, &j);
    int a[i][j], b[i][j];

    printf("\nEnter the First Matrix Elements\n");
    for (rows = 0; rows < i; rows++)
        for (columns = 0; columns < j; columns++)
            scanf("%d", &a[rows][columns]);

    printf("\nEnter the Second Matrix Elements\n");
    for (rows = 0; rows < i; rows++)
        for (columns = 0; columns < j; columns++)
            scanf("%d", &b[rows][columns]);

    isEqual = 1;

    for (rows = 0; rows < i; rows++)
        for (columns = 0; columns < j; columns++)
            if (a[rows][columns] != b[rows][columns])
            {
                isEqual = 0;
                break;
            }

    if (isEqual)
        printf("\nMatrix \"A\" == Matrix \"B\"");
    else
        printf("\nMatrix \"A\" != Matrix \"B\"");
    return 0;
}
