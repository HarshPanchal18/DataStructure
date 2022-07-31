/*
Given a string, remove characters until the string is made up of any two alternating characters.
When you choose a character to remove, all instances of that character must be removed.
Determine the longest string possible that contains just two alternating letters.

s = abaacdabd
Delete a, to leave bcdbd.
Now, remove the character c to leave the valid string bdbd with a length of 4.
Removing either b or d at any point would not result in a valid string.
Return 4.

Given a string s, convert it to the longest possible string t made up only of alternating characters.
Return the length of string t.
If no string t can be formed, return 0.

returns:
int : the length of the longest valid string, or 0 if there are none
*/

#include <bits/stdc++.h>

using namespace std;

int main(void)
{
    int n, mans = 0;
    cin >> n;
    string s;
    cin >> s;

    if (s.size() > 1)
    {
        for (char i = 'a'; i <= 'z'; i++)
        {
            for (char j = 'a'; j <= 'z'; j++)
            {
                int ans = 0;
                char prev = 0;
                for (int k = 0; k < s.size(); k++)
                {
                    if (s[k] == i || s[k] == j)
                    {
                        if (prev == s[k])
                        {
                            ans = 0;
                            break;
                        }
                        else
                        {
                            prev = s[k];
                            ans++;
                        }
                    }
                }
                if (ans > mans)
                    mans = ans;
            }
        }
    }
    cout << mans << endl;
    return 0;
}

/*
input:
10
beabeefeab

output:
5
*/
