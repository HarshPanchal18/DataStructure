#include <stdio.h>
#include <math.h>

// Algorithm:
// 1.find the mid element
// 2.check whether the k lies before the middle element or not if it lies than return(n-1,k)
// 3.if it lies above mid then return its complement
// BASE CONDITION--> if(n==1 && k==1)since its 1 based indexing and the inputs are shorter than return 0

/*
Suppose on the first row, we have a 0. Now in every subsequent row,
we look at the previous row and replace each occurrence of 0 by 01,
and each occurrence of 1 by 10.
Suppose we have N rows and index K, we have to find the K-th indexed symbol in row N.
(The values of K are 1-indexed.) (1 indexed).
So if N = 4 and K = 5, then the output will be 1. This is because −

Row 1: 0
Row 2: 01
Row 3: 0110
Row 4: 01101001

To solve this, we will follow these steps −

Suppose the name of the method is kthGrammar. This takes N and K.
if N is 1, then return 0
if k is even, return 1 when then kthGrammar(N – 1, K/2) is 0, otherwise 0
otherwise return kthGrammar(N – 1, (K + 1)/2)
*/
int getKGramm(int n, int k)
{
    if (n == 1 && k == 1)
        return 0;

    int mid = pow(2, n - 1) / 2;

    if (k <= mid)
        return getKGramm(n - 1, k);
    else
        return !getKGramm(n - 1, k - mid);
}

int kthGrammar(int n, int k)
{
    if (n == 1)
        return 0;

    if (k % 2 == 0)
        return !kthGrammar(n - 1, k / 2);
    else
        return kthGrammar(n - 1, k / 2 + 1);
}

int kthGramm(int n, int k)
{
    if (n == 1)
        return 0;
    return kthGramm(n - 1, (k - 1) / 2 + 1) == (k % 2);
}

int main(void)
{
    int n, k;
    scanf("%d%d", &n, &k);

    printf("%d", getKGramm(n, k));
    printf("\n%d", kthGrammar(n, k));
    printf("\n%d", kthGramm(n, k));
}

/*
Input: n = 2, k = 1
Output: 0
Explanation:
row 1: 0
row 2: 01 (kth index-1)

Input: n = 2, k = 2
Output: 1
Explanation:
row 1: 0
row 2: 01
*/
