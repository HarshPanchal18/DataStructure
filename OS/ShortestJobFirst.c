#include <stdio.h>
#include <conio.h>

int main()
{
    int i, j, n, process[10], total = 0, wtime[10], ptime[10], temp, ptemp;
    float avg = 0;

    printf("\nEnter the number of processes");
    scanf("%d", &n);
    for (i = 0; i < n; i++)
    {
        printf("\nEnter process %d ID:", i + 1);
        scanf("%d", &process[i]);
        printf("\nEnter process %d time:", i + 1);
        scanf("%d", &ptime[i]);
    }

    for (i = 0; i < n - 1; i++)

        for (j = i + 1; j < n; j++)

            if (ptime[i] > ptime[j])
            {
                temp = ptime[i];
                ptime[i] = ptime[j];
                ptime[j] = temp;
                ptemp = process[i];
                process[i] = process[j];
                process[j] = ptemp;
            }

    wtime[0] = 0;

    for (i = 1; i < n; i++)
    {
        wtime[i] = wtime[i - 1] + ptime[i - 1];
        total = total + wtime[i];
    }
    avg = (float)total / n;

    printf("\nP_ID\tP_TIME\tW_TIME\n");
    for (i = 0; i < n; i++)
        printf("%d\t%d\t%d\n", process[i], ptime[i], wtime[i]);

    printf("\nTotal waiting time:%d\nAverage waiting time:%f", total, avg);

    getch();
}

/*OUTPUT

Enter the number of processes 3

Enter process 1 ID:0
Enter process 1 time:4

Enter process 2 ID:2
Enter process 2 time:1

Enter process 3 ID:3
Enter process 3 time:5

P_ID    P_TIME  W_TIME
2       1       0
0       4       1
3       5       5

Total waiting time:6
Average waiting time:2.000000

*/
