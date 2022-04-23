#include <stdio.h>
#include <string.h>

int MagicSquare(int n)
{
    int Magic[n][n], num;

    memset(Magic, 0, sizeof(Magic));

    // initialize postion for 1
    int i = n / 2;
    int j = n - 1;

    // Put value one by one in Sqaure
    for (num = 1; num <= n * n;)
    {
        if (i == -1 && j == n)
        {
            j = n - 2;
            i = 0;
        }
        else
        {
            // If next number goes outside of square's right side
            if (j == n)
                j = 0;

            // If next number goes outside of square's upper side
            if (i < 0)
                i = n - 1;
        }
        if (Magic[i][j])
        {
            j = j - 2;
            i++;
            continue;
        }
        else
            Magic[i][j] = num++;

        j++;
        i--;
    }

    // Print Magic Square
    printf("Magic Square for n = %d\nSum of each row & column: %d\n", n, n * ((n * n) + 1) / 2);

    for (i = 0; i < n; i++)
    {
        for (j = 0; j < n; j++)
            printf("%3d", Magic[i][j]);
        printf("\n");
    }
}

int main(void)
{
    int n;
    printf("\nEnter a number(Odd numbers): ");
    scanf("%d", &n);
    MagicSquare(n);
}

/*
Enter a number(Odd numbers): 3
Magic Square for n = 3
Sum of each row & column: 15
  2  7  6
  9  5  1
  4  3  8
*/
