/* Extended Euclidean Algorithm
  GCD of two numbers is the largest number that divides both of them.
  A simple way to find GCD is to factorize both numbers and multiply
  common factors.

     GCD(a,b) = ax + by

  If we can find the value of  x and y then we can easily find the
  value of GCD(a,b) by replacing (x,y) with their respective values.
*/

#include <stdio.h>

int extendGCD(int a, int b, int *x, int *y)
{
    if (a == 0)
    {
        *x = 0;
        *y = 0;
        return b;
    }

    int x1, y1;

    int ans = extendGCD(b % a, a, &x1, &y1);

    *x = y1 - (b / a) * x1;
    *y = x1;

    return ans;
}

int main(void)
{
    int x, y, a, b;

    printf("Enter A & B: ");
    scanf("%d%d", &a, &b);

    int gcd = extendGCD(a, b, &x, &y);
    printf("GCD using extended euclidean algorithm: %d", gcd);
}

/*
Enter A & B: 40 6
GCD using extended euclidean algorithm: 2
*/
