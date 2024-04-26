#include <stdio.h>
#include <conio.h>
#include <stdlib.h>

void display(int c, int stack[])
{
    int i;
    printf("\n------------------------------------------------------------------------------\n");

    if (c == 0)
        printf("\tStack is EMPTY\n\t\t(Now It is sleeping)");
    else
        for (i = 1; i <= c; i++)
            printf("\t%d", stack[i]);

    printf("\n-------------------------------------------------------------------------------\n");
}

int main()
{
    int ch, n, c1 = 0, c2 = 0, produce[23], consume[23];

    printf("\n\n\n\n\n\t\n\n\t\t\tEnter Stack Size :  ", n);
    scanf("%d", &n);
    while (1)
    {

        printf("\t\tProducer Stack (Stack Size : %d)\n\t\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", n);
        display(c1, produce);

        printf("\n\n\t\tConsumer Stack (Stack Size : %d)\n\t\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", n);
        display(c2, consume);

        printf("\n\t\tCHOICES\n\t\t~~~~~~~\n\t1.Producer\n\t2.Consumer\n\t3.Exit\nEnter your choice :  ");
        scanf("%d", &ch);
        switch (ch)
        {
        case 1:
            if (c1 == n)
                printf("Produer stack is FULL.So Producer goes to SLEEP\n");
            else
            {
                c1++;
                printf("\t\tEnter PRODUCE item is :");
                scanf("%d", &produce[c1]);
            }
            break;

        case 2:
            if (c2 == n)
                printf("Consumer Stack is FULL.So it goes to SLEEP!..........\n\tReset the Cosumer Stack\n", c2 = 0);
            else if (c1 == 0)
                printf("\tProducer stack is EMPTY\n");
            else
            {
                c2++;
                consume[c2] = produce[c1];
                printf("\t\tCONSUME one item");
                c1--;
            }
            break;

        case 3:
            exit(0);

        default:
            printf("\tIt is Wrong choice,Please enter correct choice!............\n");
        }

        getch();
    }
    return 0;
}
