#include <bits/stdc++.h>
using namespace std;

/*
Consider a special family of Engineers and Doctors with following rules :

Everybody has two children.
First child of an Engineer is an Engineer and second child is a Doctor.
First child of an Doctor is Doctor and second child is an Engineer.
All generations of Doctors and Engineers start with Engineer.
We can represent the situation using below diagram:

1               E
               / \
            _--   -_
           /        \
2          E          D
         /   \       /  \
3       E     D     D    E
      / \   / \   / \   / \
4    E   D D   E  D  E  E  D
Given level and position(pos) of a person in above ancestor tree, find profession of the person.

Example 1:
Input: level = 4, pos = 2
Output: Doctor

Example 2:
Input: level = 3,pos = 4
Output: Engineer
*/

class Solution
{
public:
    char profession(int lvl, int pos)
    {
        if (pos == 1)
            return 'e';

        char c = profession(lvl--, (pos + 1) / 2);

        if (pos % 2 != 0)
            return c;
        else
        {
            if (c == 'e')
                return 'd';
            else
                return 'e';
        }
    }
};

int main()
{
    int level, pos;
    cin >> level >> pos;

    Solution ob;
    if (ob.profession(level, pos) == 'd')
        cout << "Doctor\n";
    else
        cout << "Engineer\n";
    return 0;
}
