#include <stdio.h>

void FindWaitingTime(int n, int bt[], int wt[])
{
    // waiting time for first process is 0
    wt[0] = 0;

    // calculating waiting time
    for (int i = 1; i < n; i++)
        wt[i] = bt[i - 1] + wt[i - 1];
}

void FindTurnAroundTime(int n, int bt[], int wt[], int tat[])
{
    for (int i = 0; i < n; i++)
        tat[i] = bt[i] + wt[i];
}

void FindAvgTime(int n, int bt[])
{
    int wt[n], tat[n];
    int Tot_Tat = 0, Tot_wt = 0;

    FindWaitingTime(n, bt, wt);

    FindTurnAroundTime(n, bt, wt, tat);

    printf("Processes   Burst Time   Waiting Time   Turn Around Time\n");

    for (int i = 0; i < n; i++)
    {
        Tot_Tat = Tot_Tat + tat[i];
        Tot_wt = Tot_wt + wt[i];

        printf("  %d \t\t%d\t   %d\t\t%d\n", i + 1, bt[i], wt[i], tat[i]);
    }

    printf("\nAverage waiting Time : %f", (float)Tot_wt / n);
    printf("\nAverage Turn Around Time : %f", (float)Tot_Tat / n);
}

int main(void)
{
    int Processes[] = {1, 2, 4, 3};
    int n = sizeof(Processes) / sizeof(Processes[0]);

    int BurstTime[] = {10, 5, 7, 8};

    FindAvgTime(n, BurstTime);
}
