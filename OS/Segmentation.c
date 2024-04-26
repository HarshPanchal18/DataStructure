#include <stdio.h>
#include <conio.h>

int main()
{
    int i, n, t, psize[20], frame[20], pno, size, data[20], page[20], mdata, phy;

    srand((unsigned)time(&t));
    n = rand() % 10;

    printf("No of Pages Available: %d", n);
    for (i = 0; i < n; i++)
    {
        page[i] = i;
        psize[i] = rand() % 10;
        printf("\nPage size of page %d : %d", page[i], psize[i]);
    }

    printf("\nframe no of the pages are :");
    printf("\npage no\t\tframe no");
    for (i = 0; i < n; i++)
    {
        frame[i] = rand() % 10;
        printf("\npage %d\t\tframe %d", page[i], frame[i]);
    }

    printf("\nEnter Page no to view data : ");
    scanf("%d", &pno);
    if (pno <= n)
    {
        if (pno < 0)
            printf("\nPage does not exist");

        size = psize[pno];

        printf("\ndata stored :");
        for (i = 0; i < size; i++)
        {
            data[i] = rand() % 20;
            printf("\n%d", data[i]);
        }

        printf("\nEnter the data for which mapping address if found :");
        scanf("%d", &mdata);
        for (i = 0; i < size; i++)
        {
            if (mdata == data[i])
            {
                phy = ((frame[pno] * psize[pno]) + (mdata % psize[pno]));
                printf("\nPhysical address of the data in data sagment is :%d", phy);
            }
        }
    }
    else
        printf("\nPage does not exist");

    getch();
    return 0;
}
