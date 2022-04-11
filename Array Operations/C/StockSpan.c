#include <stdio.h>

void calculate(int a[], int n, int s[])
{
    s[0] = 1;

    for (int i = 1; i < n; i++)
    {
        s[i] = 1;
        for (int j = i - 1; (j >= 0 && a[i] >= a[j]); j--)
            s[i]++;
    }
}

int main(void)
{
    int a[] = {10, 80, 40, 0, 22, 50};
    int n = sizeof(a) / sizeof(a[0]);
    int s[n];

    calculate(a, n, s);

    for (int i = 0; i < n; i++)
        printf("%d ", s[i]);
}
