/*
A pangram is a string that contains every letter of the alphabet.
Given a sentence determine whether it is a pangram in the English alphabet.
Ignore case.
Return either pangram or not pangram as appropriate.

s = the quick brown fox jumps over the lazy dog
The string contains all letters in the English alphabet, so return pangram.

*/

#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

int main()
{
    bool al, all = true;
    string in;
    cin >> in;

    for (int i = 0; i < 26; i++)
    {
        al = false;
        char s = (char)(i + 65);
        char B = (char)(i + 97);
        for (int j = 0; j < in.length(); j++)
        {
            if (in.at(j) == s || in.at(j) == B)
            {
                al = true;
                break;
            }
        }
        if (!al)
        {
            all = false;
            break;
        }
    }
    all ? cout << "pangram" : cout << "not pangram";
    return 0;
}

/*
We promptly judged antique ivory buckles for the next prize
=> pangram

We promptly judged antique ivory buckles for the prize
=> not pangram
*/
