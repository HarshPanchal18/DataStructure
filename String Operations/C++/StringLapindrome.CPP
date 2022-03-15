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
