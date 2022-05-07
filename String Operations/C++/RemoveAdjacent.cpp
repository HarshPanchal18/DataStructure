/*
 * Recursively remove all adjacent duplicates
 * Given a string s, remove all its adjacent duplicate characters recursively.
 * Input:
 S = "geeksforgeek"
 * Output: "gksforgk"
 * Explanation:
 * g(ee)ksforg(ee)k -> gksforgk

 * Input:
 S = "abccbccba"
 * Output: ""
 * Explanation:
 * ab(cc)b(cc)ba->abbba->a(bbb)a->aa->(aa)->""(empty string)

 */

#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    string remove(string s)
    {
        int i = 0, n = s.size();
        string temp;
        while (i < n)
        {
            int j = i + 1;

            while (s[i] == s[j] && j < n)
                j++;

            if (j == i + 1)
                temp += s[i];
            i = j;
        }

        if (s.size() == temp.size())
            return temp;
        else
            return remove(temp);
    }

    void removeAdj(string &strIn, int pos = 0)
    {
        int len = strIn.size();

        if (pos == len - 1)
            return;

        if (strIn[pos] == strIn[pos + 1])
            strIn = strIn.substr(0, pos) + strIn.substr(pos + 1, len - pos - 1);

        else
            pos++;
        removeAdj(strIn, pos);
    }
};

int pos = 0;
string removeR(string s)
{
    int length = s.size();
    if (pos == length - 1)
    {
        pos = 0;
        return s;
    }

    if (s[pos] == s[pos + 1])
        s = s.substr(0, pos) + s.substr(pos + 1, length - pos - 1);
    else
        pos++;
    return removeR(s);
}

int main(void)
{
    string s;
    cin >> s;
    Solution ob;
    cout << ob.remove(s) << "\n";
    cout << removeR(s) << "\n";
}
/*
cheeeeessseeee
ch
chese
*/
