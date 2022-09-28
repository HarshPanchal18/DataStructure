#include <stdio.h>
#include <stdlib.h>

void ganttchart(int s[], int n)
{
    for (int i = 0; i < n; i++)
        printf("---");
    printf("\n");

    for (int i = 0; i < n; i++)
    {
        if (s[i] == 10)
            printf("|ID");
        if (s[i] != s[i - 1] && s[i] != 10)
            printf("|P%d", s[i]);
        if (s[i] == s[i - 1])
            printf("   ");
        if (i == n - 1)
            printf("|");
    }
    
    printf("\n");
    for (int i = 0; i < n; i++)
        printf("---");
    printf("\n");

    for (int i = 0; i < n; i++)
    {
        if ((s[i] == 10) || (s[i] != s[i - 1] && s[i] != 10))
            printf("%d", i);
        if (s[i] == s[i - 1])
            printf("   ");
        if (i == n - 1)
            printf("%d", n);
    }
}

void drawTable(int a[], int b[], int pr[], int c[], int tt[], int w[], int p)
{
    printf("\n-----------------------------------------------------------------------------------------------------------------\n");
    printf("|    Programs   | Arrival Time  |  Burst Time   |   Priority    |Completion Time|Turnaround Time| Waiting Time  |");
    printf("\n-----------------------------------------------------------------------------------------------------------------\n");
    for (int i = 0; i < p; i++)
    {
        printf("|\tP%d\t|\t%d\t|\t%d\t|\t%d\t|\t%d\t|\t%d\t|\t%d\t|\n", i + 1, a[i], b[i], pr[i], c[i], tt[i], w[i]);
        // printf("%d ", prog[i]);
    }
    printf("-----------------------------------------------------------------------------------------------------------------\n");
}

int maint()
{
    int n;
    printf("PREEMPTIVE PRIORITY SCHEDULING:");
    printf("\nEnter the number of processes: ");
    scanf("%d", &n);

    int b[n], w[n], a[n], c[n], t[n], p[n];
    int wtime[n], tatime[n], s[50];
    int largest, count = 0, time;
    double wt = 0, tat = 0, ct, awt, atat;

    for (int i = 0; i < n; i++)
    {
        printf("\nEnter the Arrival time of P%d: ", i + 1);
        scanf("%d", &a[i]);
        printf("\nEnter the Burst time of P%d: ", i + 1);
        scanf("%d", &b[i]);
        printf("\nEnter the Priority time of P%d: ", i + 1);
        scanf("%d", &p[i]);
        t[i] = b[i];
    }
    p[n - 1] = 0;

    for (time = 0; count != n; time++)
    {
        largest = 9;
        for (int i = 0; i < n; i++)
        {
            if (a[i] <= time && p[i] >= p[largest] && b[i] > 0)
            {
                if (p[i] == largest)
                {
                    if (a[i] < a[largest])
                        largest = i;
                }
                else
                    largest = i;
            }
        }
        s[time] = largest + 1;
        b[largest]--;

        if (b[largest] == 0)
        {
            count++;
            ct = time + 1;
            c[largest] = ct;
            wt += ct - a[largest] - t[largest];
            tat += ct - a[largest];
        }
    }
    for (int i = 0; i < n; i++)
    {
        tatime[i] = c[i] - a[i];
        wtime[i] = tatime[i] - t[i];
    }

    drawTable(a, t, p, c, tatime, wtime, n);
    ganttchart(s, time);
    awt = wt / n;
    atat = tat / n;

    printf("\n\nAverage Waiting Time: %lf", awt);
    printf("\nAverage Turnaround Time: %lf", atat);
    return 0;
}
