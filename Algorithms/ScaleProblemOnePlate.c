/*
When trying to weigh using a double-armed scale with a plate on the left and right,
In order to minimize the type and number of weights to be used,
which weight should be used and how Write a program that prints 1.

If only one plate can use the weight
*/

#include <stdio.h>

int weight = 18;
int chu = 1;

void base(int n)
{
    int r;
    if (n == 0)
        return;

    else
    {
        r = n % 2;
        if (r == 1)
            printf("put %dg weight on the left\n", chu);
        chu *= 2;
        base(n / 2);
    }
}

int main(void)
{
    base(weight);
}

// weight = 18, put 2g weight on the left put 16g weight on the left
// weight = 4,  put 4g weight on the left
