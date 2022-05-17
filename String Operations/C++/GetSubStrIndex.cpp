/*
Two String values are passed as input. Find if the second string s2 is a substring of the first string
s1. If it is, print the index of the first occurrence.Else print -1.
Input
make123India
123
Output
4
*/

#include <iostream>
#include <string>

using namespace std;

int isSubStr(string s1, string s2)
{
    int temp;

    for (int i = 0; s1[i] != '\0'; i++)
    {
        int j = 0;
        if (s1[i] == s2[j])
        {
            temp = i + 1;
            while (s1[i] == s2[j])
            {
                i++;
                j++;
            }

            if (s2[j] == '\0')
                return temp - 1;

            else
            {
                i = temp;
                temp = 0;
            }
        }
    }
    return temp;
}

int main(void)
{
    string s1, s2;
    cin >> s1;

    for (int i = 0; i < s1.length(); i++)
        cout << i;
    cout << endl;

    cin >> s2;

    int res = isSubStr(s1, s2);
    if (res == -1)
        cout << "Not found";
    else
        cout << "\nFound at: " << res;
}
