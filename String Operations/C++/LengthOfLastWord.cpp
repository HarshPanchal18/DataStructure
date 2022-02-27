#include <iostream>

/*
 * Return 0, if no last word exist.
 *
 * Approach:
 * Start from end of the string, and trim the whitespace.
 * When we find the first non-space character, find the next space or till we reach beginning of string.
 *
 */

using namespace std;

int getLastLen(const string &s)
{
    if (s.length() == 0)
        return 0;

    int n = s.length();
    int i = n - 1, j = 0;

    while (i >= 0 && s[i] == ' ') // Trim the whitespace in the end i.e. "Hello               "
        i--;

    if (i < 0) // if we already reached beginning of the string or there is single word.
        return 0;

    for (j = i; j >= 0; j--)
        if (s[j] == ' ')
            break;

    cout << i << j << endl;
    return i - j;
}

int main(void)
{
    string s1 = "Hello";
    string s2 = "Hello         ";
    string s3 = "Hello          World to the C++";

    cout << "String without space of length: " << s1.length() << ":  " << s1 << endl;
    cout << "Length of the last word: " << getLastLen(s1) << endl;

    cout << "String with space in the end of length: " << s2.length() << ":  " << s2 << endl;
    cout << "Length of the last word: " << getLastLen(s2) << endl;

    cout << "String with space at between words of length: " << s3.length() << ":  " << s3 << endl;
    cout << "Length of the last word: " << getLastLen(s3) << endl;
}