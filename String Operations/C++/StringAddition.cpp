#include <iostream>
#include <string>
#include <cassert>

using namespace std;

string strAdd(string s1, string s2)
{
    int rem = 0;
    string sum;

    assert(s1.length() > 0 && s2.length() > 0);

    if (s2.length() < s1.length())
        s2.insert(s2.begin(), s1.length() - s2.length(), '0');

    else if (s2.length() > s1.length())
        s1.insert(s1.begin(), s2.length() - s1.length(), '0');

    for (int i = s1.length() - 1; i >= 0; i--)
    {
        int a = (int(s1[i] + s2[i]) + rem - 96);
        sum.insert(sum.begin(), char(a % 10 + 48));
        rem = a / 10;
    }

    if (rem != 0)
        sum.insert(sum.begin(), char(rem + 48));

    return sum;
}

int main(void)
{
    string s1, s2;

    cout << "\nEnter Number 1: ";
    cin >> s1;

    cout << "\nEnter Number 2: ";
    cin >> s2;

    cout << "\nAddition: " << strAdd(s1, s2);
}
