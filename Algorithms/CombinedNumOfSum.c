/*
I'm trying to figure out how many numbers are added to a number.
For example, to print all combinations of numbers that make the number 10 be the addition of three numbers.
All of these washes should be different.
i.e. as 1+2+7=10 1+3+6=10 1+4+5=10 2+3+5=10
*/

#include <stdio.h>

int s = 10;
int n = 3;
int k[3 + 1];

int promising(int i)
{
    int sum = 0;

    for (int j = 1; j <= i; j++)
        sum += k[j];

    if (sum >= s)
        return 0;
    else
        return s - sum;
}

void sumOfPermutations(int i, int bound)
{
    int j;
    if (i == n - 1)
    {
        k[n] = bound;

        for (j = 1; j < n; j++)
            printf("%d+", k[j]);
        printf("%d=%d\n", k[j], s);
    }
    else
    {
        i++;
        for (j = k[i - 1] + 1; j < s; j++)
        {
            k[i] = j;
            bound = promising(i);
            if (bound == 0 || k[i] >= bound)
                break;

            sumOfPermutations(i, bound);
        }
    }
}

int main(void)
{
    k[0] = 0;
    sumOfPermutations(0, s);
}

/*
s = 20;
n = 5;
k[3 + 1];

1+2+3+4+10=20
1+2+3+5+9=20
1+2+3+6+8=20
1+2+4+5+8=20
1+2+4+6+7=20
1+3+4+5+7=20
2+3+4+5+6=20


s = 10;
n = 3;
k[3 + 1];

1+2+7=10
1+3+6=10
1+4+5=10
2+3+5=10
*/
