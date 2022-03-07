#include <stdio.h>
#include <conio.h>

int main(void)
{
    int m, n;
    printf("Enter the number of Row and Column");
    scanf("%d %d", &m, &n);
    int a[m][n], b[m][n], c[m][n];

    printf("\nEnter first matrix value\n");
    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
            scanf("%d", &a[i][j]);

    printf("\nEnter second matrix value\n");
    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
            scanf("%d", &b[i][j]);

    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
            c[i][j] = a[i][j] + b[i][j];

    printf("\nResult :-\n");
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
            printf("%2d ", c[i][j]);
        printf("\n");
    }
}
