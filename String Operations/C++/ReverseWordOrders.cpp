// Given a String S, reverse the string without reversing its individual words. Words are separated by dots.

#include <bits/stdc++.h>
using namespace std;

string reverseWords(string s)
{
    string res = "";
    reverse(s.begin(), s.end());
    int k = 0;

    for (int index = 0; index < s.length(); index++)
    {
        if (s[index] == '.')
        {
            for (int ctr = index - 1; ctr >= k; ctr--)
                res += s[ctr];

            res += '.';
            k = index + 1;
        }
    }
    for (int ctr = s.length() - 1; ctr >= k; ctr--) // store the first word of the original string at the last
        res += s[ctr];

    return res;
}

int main()
{
    string s;
    cin >> s;
    cout << reverseWords(s) << endl;
}

/*
this.file.is.executed
executed.is.file.this
*/
