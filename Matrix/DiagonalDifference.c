/*
1 2 3
4 5 6
9 8 9

The left-to-right diagonal 1+5+9=15.
The right to left diagonal 3+5+9=17.
Their absolute difference is |15-17|=2.*/

#include <stdio.h>
int main()
{
    int n, f, d1 = 0, d2 = 0;
    printf("Enter the value of n where n is number of columns or rows:- ");
    scanf("%d", &n);
    int a[n][n];

    printf("Enter the value of elements of array:- \n");
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            scanf("%d", &a[i][j]);

    int k = n - 1;

    for (int i = 0; i < n; k -= 2, i++)
        for (int j = 0; j < n; j++)
            if (i == j)
                d1 = d1 + a[i][j];
            else if (j - i == k)
                d2 = d2 + a[i][j];

    if (n % 2 != 0)
        d2 = d2 + a[n / 2][n / 2];

    if (d1 > d2)
        f = d1 - d2;
    else
        f = d2 - d1;

    printf("Difference between the diagonals is:- %d", f);
    return 0;
}

/*
Enter the value of n where n is number of columns or rows:- 3
Enter the value of elements of array:-
1 2 3
4 5 6
9 8 9
Difference between the diagonals is:- 2
*/
