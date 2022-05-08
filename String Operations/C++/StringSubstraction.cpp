#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int isSmaller(string s1, string s2)
{
    int n1 = s1.length(), n2 = s2.length();

    if (n1 < n2)
        return 1;
    if (n2 < n1)
        return 0;

    for (int i = 0; i < n1; i++)
    {
        if (s1[i] < s2[i])
            return 1;
        else if (s1[i] > s2[i])
            return 0;
    }
    return 0;
}

string strSub(string s1, string s2)
{
    if (isSmaller(s1, s2))
        swap(s1, s2); // place bigger to the first

    string str = "";

    int n1 = s1.length(), n2 = s2.length();

    reverse(s1.begin(), s1.end());
    // to start do operation from 0th index (in number's terms from last digit)
    reverse(s2.begin(), s2.end());

    int carry = 0;

    for (int i = 0; i < n2; i++)
    {
        int sub = ((s1[i] - '0') - (s2[i] - '0') - carry);

        if (sub < 0)
        {
            sub += 10; // if sub is negative then make it positive
            carry = 1;
        }
        else
            carry = 0;

        str.push_back(sub + '0'); // push to the end and marked as calculated
    }

    for (int i = n2; i < n1; i++)
    { // In case of length of one is > then another
        int sub = ((s1[i] - '0') - carry);
        carry = 0;
        str.push_back(sub + '0');
    }

    reverse(str.begin(), str.end());

    return str;
}

int main(void)
{
    string s1, s2;
    cout << "Enter two numbers: ";
    cin >> s1 >> s2;
    cout << "Subtraction: " << strSub(s1, s2);
}

/*
Enter two numbers: 87 50
Subtraction: 37
*/
