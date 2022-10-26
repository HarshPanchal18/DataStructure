#include <iostream>
#include <cstring>
using namespace std;

/*
 * Longest Palindromic Substring

 Given a string s, return the longest palindromic substring in s.
 A string is called a palindrome string if the reverse of that string is the same as the original string.

 Example 1:
 Input: s = "babad"
 Output: "bab"
 Explanation: "aba" is also a valid answer.

 Example 2:
 Input: s = "cbbd"
 Output: "bb"
 */

class Solution
{
public:
    int check(string &s, int L, int R)
    {
        while (L >= 0 and R < s.length() and s[L] == s[R])
        {
            L--;
            R++;
        }
        return R - L - 1;
    }

    string longestPalindrome(string s)
    {
        int ans = 0, st = 0;
        int n = s.length();

        for (int i = 0; i < n; i++)
        {
            int len1 = check(s, i, i);
            int len2 = check(s, i, i + 1);
            int len = max(len1, len2);

            if (len > ans)
            {
                ans = len;
                st = i - (len - 1) / 2;
            }
        }

        return s.substr(st, ans);
    }

    /*
Approach :-
Here the palindrome could have odd or even length.

For odd length, there must be a central element.
For even the elements will be side by side.

So we calculate both cases for all elements:

For odd: we took left & right as same elements(central) l = i, r = i
if elements in both indexes are the same we’ll increase r & decrease l by 1.
when we find both are not same, we’ll calculate the length length = r-l-1
if the length is greater than mx, then we’ll store start and end point of this length.
For even: same approach, just here we’ll take l & r next to each other. l = i, r = i+1

Return substring from start to max length.

~Time complexity: O(n^2).
~Time complexity: O(1).
    */
    string longestPalindrome(string str)
    {
        int n = str.length();
        int mx = 1;
        int start = 0;
        for (int i = 1; i <= n; i++)
        {
            // Check for even string
            int l = i - 1;
            int r = i;
            while (l >= 0 && r < n && str[l] == str[r])
            {
                if (r - l + 1 > mx)
                {
                    mx = r - l + 1;
                    start = l;
                }
                l--;
                r++;
            }
            // Check for odd string
            l = i - 1;
            r = i + 1;
            while (l >= 0 && r < n && str[l] == str[r])
            {
                if (r - l + 1 > mx)
                {
                    mx = r - l + 1;
                    start = l;
                }
                l--;
                r++;
            }
        }
        return str.substr(start, mx);
    }
};

/*
DYNAMIC APPROACH
PALINDROME in DP->If its first and last elements are same and the substring after excluding the first and last character is a palindrome.
Hence we can now reduce a bigger problem to smaller problem whose answer may be previously known.
Use a matrix where dp[ i ] [ j ] represents substring from ith pos to jth pos.
All single characters are palindromes, thus substring with a length 1 are substring by default.
substrings of length 2 are palin if both characters are same.
And for rest of lengths use the sub problem.
Now we need longest so the palindromic substring with longest length is the substring.
*/

// BRUTE FORECE
string longestPalindrome(string str)
{

    int n = str.size();
    int res = 1, start = 0;
    string ans;

    for (int i = 0; i < n; i++)
    {
        int l = i - 1;
        int r = i + 1;

        while (l >= 0 && str[i] == str[l])
            l--;

        while (r < n && str[i] == str[r])
            r++;

        while (l >= 0 && r < n && str[l] == str[r])
        {
            l--;
            r++;
        }

        int length = (r - 1) - (l + 1) + 1;

        if (length > res)
        {
            res = length;
            start = l + 1;
        }
    }
    ans = str.substr(start, res);
    return ans;
}

class Solution
{
public:
    string longestPalindrome(string str)
    {
        int n = str.size();
        int dp[n][n];

        memset(dp, 0, sizeof(dp));
        int end = 1;
        int strt = 0;

        for (int i = 0; i < n; i++)
            dp[i][i] = 1;

        for (int i = 0; i < n - 1; i++)
            if (str[i] == str[i + 1])
            {
                dp[i][i + 1] = 1;
                strt = i;
                end = 2;
            }

        for (int j = 2; j < n; j++)
            for (int i = 0; i < n - j; i++)
            {
                int lft = i;
                int rght = i + j;

                if (dp[lft + 1][rght - 1] == 1 && str[lft] == str[rght])
                {
                    dp[lft][rght] = 1;
                    strt = i;
                    end = j + 1;
                }
            }

        return str.substr(strt, end);
    }
};
