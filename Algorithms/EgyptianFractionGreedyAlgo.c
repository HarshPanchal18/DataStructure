#include <stdio.h>

// https://en.wikipedia.org/wiki/Egyptian_fraction

int main(void)
{
    int numerator = 0;
    int denominator = 0;
    int reverse = 0;

    scanf("%d/%d", &numerator, &denominator);

    do
    {
        reverse = denominator / numerator;

        printf("1/%d + ", reverse + 1);

        numerator *= (reverse + 1) - denominator;
        denominator *= (reverse + 1);
      
    } while (numerator != 1);

    printf("1/%d\n", denominator);
}
