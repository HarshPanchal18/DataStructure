#include <iostream>
#include <string>

using namespace std;

/*
Enter a string: abbbcbb
abbbcbb can't be compress

Enter a string: hhhrer
hhhrer can't be compress

Enter a string: aaadddddddbbbbc
aaadddddddbbbbc can be compressed to a3d7b4c1

Enter a string: abbbb
abbbb can be compressed to a1b4
*/

string copmress(string str)
{
    int originL = str.length();
    if (originL < 2)
        return str;

    string out("");
    int count = 1;

    for (int i = 1; i < originL; i++)
    {
        if (str[i - 1] == str[i])
            count++;
        else
        {
            out += str[i - 1];
            out += to_string(count);
            count = 1;
        }
        if (out.length() >= originL)
            return str;
    }
    out += str[originL - 1];
    out += to_string(count);

    if (out.length() >= originL)
        return str;

    return out;
}

int main(void)
{
    string str, out;
    cout << "\nEnter a string: ";
    cin >> str;

    out = copmress(str);

    if (str.compare(out))
        cout << str << " can be compressed to " << out << endl;
    else
        cout << str << " can't be compress" << endl;
}