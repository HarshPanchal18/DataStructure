/*
 * Having already mastered cooking, Chef has now decided to learn how to play the guitar.
 * Often while trying to play a song, Chef has to skip several strings to reach the string he has to pluck.
 * Eg. he may have to pluck the 1st string and then the 6th string.
 * This is easy in guitars with only 6 strings; However, Chef is playing a guitar with 10^6 strings.
 * In order to simplify his task, Chef wants you to write a program
 * that will tell him the total number of strings he has to skip while playing his favourite song.
 *
 *
 * Input
6
1 6 11 6 10 11
 * Output
15
 */

#include <bits/stdc++.h>

using namespace std;

int main(void)
{
    int n;
    cin >> n;

    int a[n];
    for (int i = 0; i < n; i++)
        cin >> a[i];

    int sum = 0;
    for (int i = 1; i < n; i++)
    {
        sum += (abs(a[i - 1] - a[i]) - 1);
        //cout << sum << " ";
    }
    cout << endl
         << sum;
}
