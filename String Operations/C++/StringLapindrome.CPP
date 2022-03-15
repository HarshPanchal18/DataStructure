#include <iostream>
#include <algorithm>

using namespace std;

bool IsLapindrome(string str)
{
    int mid = str.length() / 2;
    int delim = (str.length() % 2) == 0 ? mid : mid + 1;

    string FirstHalf = str.substr(0, mid);
    string SecondHalf = str.substr(delim);

    // sorts the elements in ascending order.
    sort(FirstHalf.begin(), FirstHalf.end());
    sort(SecondHalf.begin(), SecondHalf.end());

    return FirstHalf == SecondHalf;
}

int main(void)
{
    string str;
    cout << "Enter the string : ";
    cin >> str;

    if (IsLapindrome(str) == 1)
        cout << str << " is a Lapindrome";
    else
        cout << str << " is not a Lapindrome";
}
#include <iostream>
#include <algorithm>

using namespace std;

bool IsLapindrome(string str)
{
    int mid = str.length() / 2;
    int delim = (str.length() % 2) == 0 ? mid : mid + 1;

    string FirstHalf = str.substr(0, mid);
    string SecondHalf = str.substr(delim);

    // sorts the elements in ascending order.
    sort(FirstHalf.begin(), FirstHalf.end());
    sort(SecondHalf.begin(), SecondHalf.end());

    return FirstHalf == SecondHalf;
}

int main(void)
{
    string str;
    cout << "Enter the string : ";
    cin >> str;

    if (IsLapindrome(str) == 1)
        cout << str << " is a Lapindrome";
    else
        cout << str << " is not a Lapindrome";
}

/*
Lapindrome is defined as a string which when split in the middle, gives two halves having the same characters and same frequency of each character.
If there are odd number of characters in the string, we ignore the middle character and check for lapindrome.

Here, after taking string as an input from the user we divide it into two parts.
If the length is even Part 1 goes from 0 to mid-1 and part goes from mid to end.
Else if the string length is odd, we discard the middle character and Part 1 goes from 0 to mid-1 and part goes from mid+1 to end.

Then we sort both the strings and compare them. If they are same, the given string is lapindrome else it is not.
*/
