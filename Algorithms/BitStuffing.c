#include <stdio.h>

// Bit stuffing algorithm for error correction in Data link layer of OSI model

int main()
{
    int size;
    printf("Enter bit size: ");
    scanf("%d", &size);
    int bits[size];

    printf("Enter bit pattern: ");
    for (int i = 0; i < size; i++)
        scanf("%d", &bits[i]);

    int ones = 0;
    for (int i = 0; i < size; i++)
    {
        if (bits[i] == 1)
            ones++;

        if (ones == 6)
        {
            ones = 0;
            printf("0");
            continue;
        }

        printf("%d", bits[i]);
    }
    return 0;
}
