#include <stdio.h>

int main(void)
{
    int i, j, count = 1;
    int size, row = 0, col = 0;
    int Bstart = 0, Bend;
    int no, a[20][20], evenRowCol = 0, evenCol = 0, evenRow = 0;

    printf("\nEnter the number of rows: ");
    scanf("%d", &size);

    Bend = size - 1;

    for (i = 0; i < size; i++) // resetting the array elements to 0
        for (j = 0; j < size; j++)
            a[i][j] = 0;

    while (count <= (size * size))
    {
        if (evenRowCol % 2 == 0)
        {
            if (evenRow % 2 == 0)
            {
                for (i = col; (i <= Bend) && (count <= size * size); i++)
                {
                    a[row][i] = count;
                    count++;
                }
                evenRow++;
                col = i - 1;
                row++;
            }
            else
            {
                for (i = col; (i >= Bstart) && (count <= size * size); i--)
                {
                    a[row][i] = count;
                    count++;
                }
                evenRow++;
                col = i + 1;
                row--;
                Bstart++;
            }
            evenRowCol++;
        }
        else
        {
            if (evenCol % 2 == 0)
            {
                for (i = row; (i <= Bend) && (count <= size * size); i++)
                {
                    a[i][col] = count;
                    count++;
                }
                row = i - 1;
                col--;
                evenCol++;
            }
            else
            {
                for (i = row; (i >= Bstart) && (count <= size * size); i--)
                {
                    a[i][col] = count;
                    count++;
                }
                row = i + 1;
                col++;
                evenCol++;
                Bend--;
            }
            evenRowCol++;
        }
    }

    for (i = 0; i < size; i++)
    {
        for (j = 0; j < size; j++)
            printf("%3d ", a[i][j]);
        printf("\n");
    }
}

/*

Row=5

  1  2  3  4  5         
 16 17 18 19  6         
 15 24 25 20  7         
 14 23 22 21  8         
 13 12 11 10  9         

 Biggest Number in Spiral is Row*Row (e.g. 25)
*/
