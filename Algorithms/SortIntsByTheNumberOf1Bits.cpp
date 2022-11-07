#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

/*
* Sort Integers by The Number of 1 Bits

You are given an integer array arr.
Sort the integers in the array in ascending order by the number of 1's in their binary representation
and in case of two or more integers have the same number of 1's you have to sort them in ascending order.

Return the array after sorting it.

Example 1:
Input: arr = [0,1,2,3,4,5,6,7,8]
Output: [0,1,2,4,8,3,5,6,7]
Explantion: [0] is the only integer with 0 bits.
[1,2,4,8] all have 1 bit.
[3,5,6] have 2 bits.
[7] has 3 bits.
The sorted array by bits is [0,1,2,4,8,3,5,6,7]

Example 2:
Input: arr = [1024,512,256,128,64,32,16,8,4,2,1]
Output: [1,2,4,8,16,32,64,128,256,512,1024]
Explantion: All integers have 1 bit in the binary representation, you should just sort them in ascending order.
*/

int setbits(int x)
{
    int res = 0;
    while (x > 0)
    {
        x &= x - 1;
        res++;
    }
    return res;
}

bool comp(int a, int b)
{
    int resA = setbits(a);
    int resB = setbits(b);

    return (resA == resB) ? a < b : resB > resA;
}

class Solution
{
public:
    vector<int> sortByBits(vector<int> &arr)
    {
        sort(arr.begin(), arr.end(), comp);
        return arr;
    }
};

// Counts set bits of the integer
int setBits(int a)
{
    int count = 0;
    while (a != 0)
    {
        if ((a & 1) == 1)
            count++;
        a >>= 1;
    }
    return count;
}
// Define custom comparator to be used in sort function
bool myComparator(int i, int j)
{
    int i_bits = setBits(i);
    int j_bits = setBits(j);
    if (i_bits == j_bits)
        return i < j;

    return i_bits < j_bits;
}

class Solution
{
public:
    vector<int> sortByBits(vector<int> &arr)
    {
        sort(arr.begin(), arr.end(), myComparator);
        return arr;
    }
};

//
class Solution
{
public:
    vector<int> sortByBits(vector<int> &arr)
    {
        vector<pair<int, int>> bits;
        vector<int> ans;

        int n = arr.size();

        // step-1
        for (int i = 0; i < n; i++)
        {
            int cnt = 0;
            int x = arr[i];
            // Brian Kernighan’s Algorithm to find set bit of a number
            while (x)
            {
                x &= (x - 1);
                cnt++;
            }
            bits.push_back({cnt, arr[i]});
        }

        // step-2
        sort(bits.begin(), bits.end());

        // step-3
        for (int i = 0; i < n; i++)
            ans.push_back(bits[i].second);

        return ans;
    }
};
