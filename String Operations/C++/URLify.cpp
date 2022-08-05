#include <iostream>
#include <cstring>
using namespace std;

// Replace spaces in a string with '%20'

void urlify(char *str, int len)
{
    int space = 0;
    int i;

    for (i = 0; i < len; i++)
        if (str[i] == ' ')
            space++;

    int extendLen = len + (2 * space);
    i = extendLen - 1;

    for (int j = len - 1; j >= 0; j--)
    {
        if (str[j] != ' ')
            str[i--] = str[j];
        else
        {
            str[i--] = '0';
            str[i--] = '2';
            str[i--] = '%';
        }
    }
}

int main()
{
    char str[] = "My Name is Harsh  ";
    cout << "\nBefore Operation: " << str << endl;
    urlify(str, strlen(str));
    cout << "\nAfter Operation: " << str << endl;
}
