#include <bits/stdc++.h>
using namespace std;

/*
* Imagine you have a special keyboard with all keys in a single row.
* The layout of characters on a keyboard is denoted by a string S1 of length 26.
* S1 is indexed from 0 to 25. Initially, your finger is at index 0.

* To type a character, you have to move your finger to the index of the desired character.
* The time taken to move your finger from index i to index j is |j-i|, where || denotes absolute value.

S1 = "abcdefghijklmnopqrstuvwxyz"
S2 = "cba"
Output: 4

Initially we are at index 0. To type 'c', it will take a time of abs(0-2) = 2. To,
type 'b' next, it will take abs(2-1) = 1. And, for 'a', it will take abs(1-0) = 1 unit time.
So, total time = 2+1+1 = 4.

* Find the time taken to type the string S2 with the given keyboard layout.
*/

int findTime(string S1, string S2)
{
    int start = 0, v = 0;
    for (int i = 0; i < S2.size(); i++)
    {
        int temp = 0;
        for (int j = 0; j < S1.size(); j++)
            if (S2[i] == S1[j])
            {
                temp = j;
                break;
            }

        v += abs(temp - start);
        start = temp;
    }
    return v;
}

int main()
{
    string S1, S2;
    cin >> S1;
    cin >> S2;

    cout << findTime(S1, S2) << endl;
    return 0;
}
/*
fdshfiesldk
dsf
4
*/
