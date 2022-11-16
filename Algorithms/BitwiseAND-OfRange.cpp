#include <iostream>
#include <cmath>
using namespace std;

/*
* Bitwise AND of Numbers Range

Given two integers left and right that represent the range [left, right],
return the bitwise AND of all numbers in this range, inclusive.

Example 1:
Input: left = 5, right = 7
Output: 4

Example 2:
Input: left = 0, right = 0
Output: 0

Example 3:
Input: left = 1, right = 2147483647
Output: 0
*/

class Solution
{
public:
    /*
    Algorithm:

    (1) First check whether there exists a transition (0-->1 / 1-->0) at a
    particular bit position for any two consecutive elements in the given
    range.

    (2) If transition exists , then the final bit will be set to zero(0) for that bit
    index , else set to one(1).

    (3) Another major observation would be , if there is a transition at bit
    position 'i' , then definitely there is a transition at bit position 'i-1' , 'i-2' ,
    'i-3' , ...... , '0'.

    (4) So we will traverse from the right side until and unless the left and
    right values are equal to each other.

    10100 --> 1010 --> 101 left==right
    10101 --> 1010 --> 101
    10110 --> 1011 --> 101
    10111 --> 1011 --> 101 right==left

    (5) Finally , bitwise and for these range of numbers , [left...right] will be
    (remaining_left) after the right shift operations.
    */
    int rangeBitwiseAnd(int left, int right)
    {
        int count = 0;
        while (left != right)
        {
            left >>= 1;
            count++;
            right >>= 1;
        }
        return left << count;
    }

    // ----------------
    int rangeBitwiseAnd(int m, int n)
    {
        // edge case - zero AND anything will always stay zero
        if ((m == 0) || (n == 0))
            return 0;

        // if there is a different amount of digits in binary - always will be zero
        if ((int)log2(m) != (int)log2(n))
            return 0;

        // None of the above - not too many numbers left to calculate one by one...
        int res = m;
        for (long i = m; i <= n; i++)
            res &= i;

        return res;
    }

    // Consider the bits from low to high.if n > m, the lowest bit will be 0,
    // and then we could transfer the problem to sub - problem : rangeBitwiseAnd(m >> 1, n >> 1).
    int rangeBitwiseAnd(int m, int n)
    {
        return (n > m) ? (rangeBitwiseAnd(m / 2, n / 2) << 1) : m;
    }

    // ----------------
    int rangeBitwiseAnd(int left, int right)
    {
        return left != right ? rangeBitwiseAnd(left >> 1, right >> 1) << 1 : left;
    }
};
