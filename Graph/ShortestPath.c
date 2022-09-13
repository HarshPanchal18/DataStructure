#include <stdio.h>

#define MAX 100

int dist[6], found[6], via[6];
int n = 6;

int cost[6][6] = {
    {0, 50, 10, MAX, 45, MAX},
    {MAX, 0, 15, MAX, 10, MAX},
    {20, MAX, 0, 15, MAX, MAX},
    {MAX, 20, MAX, 0, 35, MAX},
    {MAX, MAX, MAX, 30, 0, MAX},
    {MAX, MAX, MAX, 5, MAX, 0}};

int select()
{
    int min = MAX, minPos = -1;

    for (int i = 0; i < n; i++)
        if (dist[i] < min && !found[i])
        {
            min = dist[i];
            minPos = i;
        }

    return minPos;
}

void printVia(int i)
{
    int j = via[i];

    if (via[j] != -1)
        printVia(j);
    printf("V%d ", j);
}

void printRes()
{
    for (int i = 0; i < n; i++)
        if (dist[i] != MAX && dist[i] != 0)
        {
            printf("%d : V0 ", dist[i]);
            if (via[i] != -1)
                printVia(i);
            printf("V%d \n", i);
        }
        else
            printf("V%d access impossible\n", i);
}

int main(void)
{
    for (int i = 0; i < n; i++)
    {
        found[i] = 0;
        dist[i] = cost[0][i];
        via[i] = -1;
    }

    found[0] = 1;
    dist[0] = 0;
    int x;

    for (int i = 0; i < n - 1; i++)
    {
        x = select();
        found[x] = 1;

        for (int j = 0; j < n; j++)
            if (!found[j])
                if (dist[x] + cost[x][j] < dist[j])
                {
                    dist[j] = dist[x] + cost[x][j];
                    via[j] = x;
                }
    }

    printRes();
}
