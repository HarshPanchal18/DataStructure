#include <stdio.h>
#include <stdlib.h>

struct Node
{
    int data;
    struct Node *next;
};

void position()
{
    struct Node *p, *q;
    int n, gap;

    printf("\nEnter the number of horses: ");
    scanf("%d", &n);

    printf("\nEnter Gap: ");
    scanf("%d", &gap);

    p = q = (struct Node *)malloc(sizeof(struct Node));
    p->data = 1;

    for (int i = 2; i <= n; i++)
    {
        p->next = malloc(sizeof(struct Node));
        p = p->next;
        p->data = i;
    }
    p->next = q;

    for (int i = n; i > 1; i--)
    {
        for (int j = 0; j < gap - 1; j++)
            p = p->next;

        p->next = p->next->next;
        i--;
    }
    printf("\nSafe Position is: %d\n", p->data);
}

int main(void)
{
    position();
}

/*
Josephus Problem is a theoretical Counting Game Problem.
N people stand in a circle and are numbered from 1 to N in clockwise direction.
Starting from 1 in clockwise direction each person kills the Kth person next to him and this continues till a single person is left.
The task is to choose a suitable position in initial circle to avoid execution.

https://www.exploringbinary.com/powers-of-two-in-the-josephus-problem/
*/
