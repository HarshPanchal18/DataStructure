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

string remove2(const string &str)
{
    /*
    Time Complexity: O(n)
    Return: Arg string with no duplicate chars
    */

    int len = str.length();

    if (len < 2)
        return str;

    string result("");
    bool check[256];

    for (auto &ch : str)
    {
        if (!check[(int)ch])
        {
            check[(int)ch] = true;
            result += ch;
        }
    }
    return result;
}

string remove3(string str)
{
    /*
    Time Complexity: O(n)
    Return: Arg string with no duplicate chars
    */

    int len = str.length();
    if (len < 2)
        return str;

    string result("");

    for (int i = 0; i < len; i++)
    {
        if (str[i] != '\0')
        {
            result += str[i];
            for (int j = i + 1; j < len; j++)
                if (str[i] == str[j])
                    str[j] = '\0';
        }
    }
    return str;
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
    
    cout << "\nFor Method 2: " << endl;
    cout << "Original: " << s1 << " Removing: " << remove2(s1) << endl;
    cout << "Original: " << s2 << " Removing: " << remove2(s2) << endl;
    cout << "Original: " << s3 << " Removing: " << remove2(s3) << endl;
    cout << "Original: " << s4 << " Removing: " << remove2(s4) << endl;
    
    cout << "\nFor Method 3: " << endl;
    cout << "Original: " << s1 << " Removing: " << remove3(s1) << endl;
    cout << "Original: " << s2 << " Removing: " << remove3(s2) << endl;
    cout << "Original: " << s3 << " Removing: " << remove3(s3) << endl;
    cout << "Original: " << s4 << " Removing: " << remove3(s4) << endl;

}
