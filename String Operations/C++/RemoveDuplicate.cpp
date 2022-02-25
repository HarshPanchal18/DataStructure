#include <iostream>
#include <cstring>

using std::cin;
using std::cout;
using std::endl;
using std::string;
using std::strlen;

string remove1(const string &str)
{
    /*
    Time Complexity: O(n)
    Return: Arg string with no duplicate chars
    */

    int len = str.length();

    if (len < 2)
        return str;

    int check = 0;
    string result("");

    for (auto &ch : str)
    {
        int c = (int)(ch - 'a');

        if ((check & (1 << c)) == 0)
        {
            result += ch;
            check |= (1 << c);
        }
    }
    return result;
}

int main(void)
{
    string s1("abbec");
    string s2("abca");
    string s3("aa");
    string s4("");

    cout << "\nFor Method 1: " << endl;
    cout << "Original: " << s1 << " Removing: " << remove1(s1) << endl;
    cout << "Original: " << s2 << " Removing: " << remove1(s2) << endl;
    cout << "Original: " << s3 << " Removing: " << remove1(s3) << endl;
    cout << "Original: " << s4 << " Removing: " << remove1(s4) << endl;
}
