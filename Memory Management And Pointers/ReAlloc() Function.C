  #include <stdio.h>
#include <malloc.h>
#include <process.h>

int main(void)
{
    int *p;
    int i, x, n, s;
    printf("\nEnter the size of array: ");
    scanf("%d", &n);
    // you can use either  of both
    p = (int *)malloc(n * sizeof(int));
    // p = (int *)calloc(n, sizeof(int));

    if (!p)
        printf("\nUnable to process...");
    else
    {
        for (i = 0; i < n; i++)
        {
            printf("\nEnter %d values: ", i + 1);
            scanf("%d", p + i);
        }

        printf("\nValues are: ");
        s = 0;
        for (i = 0; i < n; i++)
        {
            printf("\t%d", *(p + i));
            s = s + *(p + i);
        }
        printf("\nSum of values are: %d ", s);
        x = n;

        printf("\nEnter the new size of the array: ");
        scanf("%d", &n);

        if (n < 0)
            n = n * (-1);

        realloc(p, n * sizeof(int));

        if (!p)
        {
            printf("\nUnable to allocate memory...\n");
            exit(0);
        }
        for (i = x; i < n; i++)
        {
            printf("\nEnter %d values: ", i);
            scanf("%d", (p + i));
        }

        printf("\nValues are: ");
        s = 0;
        for (i = 0; i < n; i++)
        {
            printf("\t%d", *(p + i));
            s = s + *(p + i);
        }
        printf("\nSum of Elements: %d\n", s);
    }
    free(p);
}
