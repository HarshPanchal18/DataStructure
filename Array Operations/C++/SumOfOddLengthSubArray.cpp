#include <vector>
#include <iostream>
#include <cmath>
using namespace std;

/*
* Sum of All Odd Length Subarrays

Given an array of positive integers arr, return the sum of all possible odd-length subarrays of arr.

A subarray is a contiguous subsequence of the array.

Example 1:
Input: arr = [1,4,2,5,3]
Output: 58
Explanation: The odd-length subarrays of arr and their sums are:
[1] = 1
[4] = 4
[2] = 2
[5] = 5
[3] = 3
[1,4,2] = 7
[4,2,5] = 11
[2,5,3] = 10
[1,4,2,5,3] = 15
If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58

Example 2:
Input: arr = [1,2]
Output: 3
Explanation: There are only 2 subarrays of odd length, [1] and [2]. Their sum is 3.
Example 3:

Input: arr = [10,11,12]
Output: 66
*/

class Solution
{
public:
    int sumOddLengthSubarrays(vector<int> &arr)
    {
        int win = 1, ans = 0;
        while (win <= arr.size())
        {
            for (int i = 0; i <= arr.size() - win; i++)
                for (int j = i; j < i + win; j++)
                    ans += arr[j];

            win += 2;
        }
        return ans;
    }
};

int sumOddLengthSubarrays2(vector<int> &A)
{
    int res = 0, n = A.size();

    for (int i = 0; i < n; ++i)
        res += ((i + 1) * (n - i) + 1) / 2 * A[i];

    return res;
}

int sumOddLengthSubarrays(vector<int> &arr)
{

    int ans = 0;

    for (int i = 0; i < arr.size(); i++)
    {
        int temp = ceil((i + 1) * (arr.size() - i) / 2.0);
        ans += (temp * arr[i]);
    }

    return ans;
}

int sumOddLengthSubarrays(vector<int> &arr)
{
    int ans = 0;

    for (int i = 0; i < arr.size(); i++)
        ans += ((((arr.size() - i) * (i + 1)) + 1) / 2) * arr[i];

    return ans;
}

int sumOddLengthSubarrays(vector<int> &arr)
{
    /*
    here: Dp[i] stores sum of all the odd length subarrays ending with arr[i].
    odd and even variables stores no of odd subarrays and even subarrays ending with arr[i]
    evensum stores sum of evenlength subarrays of current number.
    update even,odd,evensum accordingly.
    */

    int odd = 1;
    int even = 0;

    vector<int> dp(arr.size(), 0);

    dp[0] = arr[0];
    int evensum = 0;

    for (int i = 1; i < arr.size(); i++)
    {

        dp[i] = arr[i] + (even * arr[i]) + evensum;
        evensum = odd * arr[i] + dp[i - 1];
        int temp = odd;
        odd = even + 1;
        even = temp;
    }

    int sum = 0;

    for (int i = 0; i < dp.size(); i++)
        sum += dp[i];

    return sum;
}

int sumOddLengthSubarrays(vector<int> &arr)
{
    int ans{};
    int n = arr.size();

    for (int i = 0; i < n; i++)
    {
        int start = i;
        int end = n - i;
        int total = (start + 1) * (end);
        int odd = total / 2;

        if (total % 2 != 0)
            odd++;

        ans += arr[i] * odd;
    }

    return ans;
}

int main(void)
{
    vector<int> A = {1, 4, 2, 5, 3};

    int res = 0, n = A.size();

    for (int i = 0; i < n; ++i)
    {
        cout << i + 1 << "\t" << n - i << endl;
        res += ((i + 1) * (n - i) + 1) / 2 * A[i];
        cout << res << endl;
    }
    return res;
}
