#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(void)
{
    int m, posx, posy, posxx, posyy, i, j;
    scanf("%d", &m);

    char grid[100][100] = {};

    for (i = 0; i < m; i++)
    {
        scanf("%s", grid[i]);

        for (j = 0; j < m; j++)
        {
            if (grid[i][j] == 'm')
            {
                posx = i;
                posy = j;
            }
            else
            {
                if (grid[i][j] == 'p')
                {
                    posxx = i;
                    posyy = j;
                }
            }
        }

        while (abs(posxx - posx) != 0)
        {
            if (posx > posxx)
            {
                posx--;
                printf("UP\n");
            }
            else
            {
                posx++;
                printf("DOWN\n");
            }
        }
        while (abs(posyy - posy) != 0)
        {
            if (posy > posyy)
            {
                posy--;
                printf("LEFT\n");
            }
            else
            {
                posy++;
                printf("RIGHT\n");
            }
        }
    }
}
