#include <bits/stdc++.h>
using namespace std;

/*
number of digits N as well as the sum S of all the digits of his password.
He know that his password is the largest number of N digits that can be made with given sum S.
As he is busy doing his homework, help him retrieving his password.

Input:
N = 5, S = 12
Output:
93000
Explanation:
Sum of elements is 12.
Largest possible 5 digit number is 93000 with sum 12.
*/

// Function to return the largest possible number of n digits
// with sum equal to given sum.
string largestNumber(int n, int sum)
{
    string ans = "";

    if ((9 * n) < sum)
        return "-1";

    while (n--)
    {
        if (sum > 9)
        {
            ans += "9";
            sum -= 9;
        }
        else if (sum > 0 && sum < 10)
        {
            ans += to_string(sum);
            sum = 0; // reset sum
        }
        else
            ans += "0";
    }
    return ans;
}

int main()
{
    int n, sum;
    cin >> n >> sum;

    cout << largestNumber(n, sum) << endl;
    return 0;
}
