/*
An investor wants to make the most of his money by investing money in several companies.
However, investment can be made in units of 10,000 won, and the more each company invests,
the more profits are returned to the investor.
If you don't invest your money, of course you won't get any profit.
*/

#include <stdio.h>

#define COM 20         // Upto 20 companies
#define MAX_INVEST 300 // Invest upto 3 Million won

int dynamic[COM][MAX_INVEST];
int invest[COM][MAX_INVEST]; // Amount invested in COMth company.

int c = 2; // 2 companies
int m = 4; // 4 million won

int main(void)
{
    int in[MAX_INVEST][COM] = {{0, 0, 0}, {0, 5, 1}, {0, 6, 5}, {0, 7, 9}, {0, 8, 15}};

    for (int i = 0; i <= c; i++)
        dynamic[0][i] = 0;

    for (int i = 0; i <= m; i++)
        dynamic[i][0] = 0;

    for (int i = 1; i <= c; i++)
        for (int j = 1; j <= m; j++)
            for (int k = 0; k <= j; k++)
                if (dynamic[j][i] < dynamic[k][i - 1] + in[j - k][i])
                {
                    dynamic[j][i] = dynamic[k][i - 1] + in[j - k][i];
                    invest[i][j] = j - k;
                }

    printf("Max benefit: %d", dynamic[m][c]);
}

// Max benefit: 15
