#include <iostream>

using namespace std;

/*
Z array is of same length as string.
An element Z[i] of Z array stores length of the longest substring starting from str[i] which is also a prefix of str[0..n-1].
The first entry of Z array is meaning less as complete string is always prefix of itself.

Run two nested loops, the outer loop goes to every index and
the inner loop finds length of the longest prefix that matches the substring starting at the current index.
*/

void getZarr(string str, int Z[])
{
    int n = str.length();
    int Left = 0, Right = 0, k;

    for (int i = 1; i < n; i++)
    {
        if (i > Right)
        {
            Left = Right = i;
            // Right-Left = 0 in starting, so it will start checking from 0'th index.
            // For example, for "ababab" and i = 1, the value of Right remains 0 and Z[i] becomes 0.
            // For string "aaaaaa" and i = 1, Z[i] and Right become 5

            while (Right < n && str[Right - Left] == str[Right])
                Right++;

            Z[i] = Right - Left;
            Right--;
        }
        else
        {
            k = i - Left; // k corresponds to number which matches in [Left,Right] interval.

            if (Z[k] < Right - i + 1) // if Z[k] is less than remaining interval then Z[i] will be equal to Z[k].
                Z[i] = Z[k];
            else
            {
                Left = i;
                while (Right < n && str[Right - Left] == str[Right])
                    Right++;

                Z[i] = Right - Left;
                Right--;
            }
        }
    }
}

/*
Concatenate pattern and text, and create a string “P$T” where P is pattern,
$ is a special character should not be present in pattern and text, and T is text.
Build the Z array for concatenated string.
In Z array, if Z value at any point is equal to pattern length, then pattern is present at that point.
*/

void search(string text, string ptrn)
{
    string concat = ptrn + '$' + text; // $ - special character other than string
    int length = concat.length();
    int Z[length];
    int mark;

    getZarr(concat, Z);

    for (int i = 0; i < length; i++)
        if (Z[i] == ptrn.length())
        {
            mark = i - ptrn.length(); // put i - length - 1 if starting from 0
            cout << "\nPattern Found at " << mark << endl;

            // This is added by me (Not a part of algorithm)
            cout << text << endl;
            for (int j = 1; j < mark; j++) // if index is from 0 then put mark + 1
                cout << " ";
            cout << "^";
        }
}

int main(void)
{
    string txt = "Regional Infrastructure Technician";
    string patt = "e";

    string t1 = "ababaa";
    string p1 = "aba";

    search(txt, patt);

    cout << endl;

    search(t1, p1);
}

/*
step1: string tot = pat+$+string => aba$ababaa
step2:
tot = a b a $ a b a b a a
      0 1 2 3 4 5 6 7 8 9

pattern L = 3

Found on 4,6

4 - 3 - 1 = 0
6 - 3 - 1 = 2
=> 0 , 2
*/