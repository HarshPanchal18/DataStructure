/*

Ravi has been given two strings named string1 and string 2.
He is supposed to find the minimum length substring of the string1 which contains all the characters of string2.
Help him to find the substring.

Input Format
The first line of the input contains string1. String1 can be a string containing spaces also.
The second line of the input contains string2. String2 can be a string containing spaces also.

Output Format
Output the substring in a single line. If no such substring exist then output "No String" without quotes

Sample Input
qwerty asdfgh qazxsw
qas

Sample Output
qazxs

Explanation
The substring "qazxs" is the shortest substring of s1 that contains all the characters of s2.

*/

#include <iostream>
#include <bits/stdc++.h>

using namespace std;

#define ll long long

int main(void)
{
    string s, pat;

    cout << "\nEnter String: ";
    getline(cin, s);
    // cin >> s;

    cout << "\nEnter Pattern: ";
    getline(cin, pat);
    // cin >> pat;

    unordered_map<char, ll> pattern, str;

    ll i, n1, n2;
    n1 = pat.length();

    for (i = 0; i < n1; i++)
        pattern[pat[i]]++;

    n2 = s.length();
    ll c = 0, start = 0;
    ll mLen = INT_MAX, astr, aEnd = -1;

    for (i = 0; i < n2; i++)
    {
        str[s[i]]++;

        if (str[s[i]] <= pattern[s[i]])
            c++;

        if (c == n1)
        {
            while (str[s[start]] > pattern[s[start]])
            {
                str[s[start]]--;
                start++;
            }
            ll len = i - start + 1;

            if (len < mLen)
            {
                mLen = len;
                astr = start;
                aEnd = i;
            }
        }
    }

    if (aEnd == -1)
        cout << "\nNo String";
    else
    {
        for (i = astr; i <= aEnd; i++)
            cout << s[i];
        cout << endl;
    }
}
