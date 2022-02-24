#include <iostream>
#include <cstring>
//#include <algorithm>

using namespace std;

void rev1(char *str)
{
    int len = strlen(str);

    for (int i = 0; i < len / 2; i++)
        swap(str[i], str[len - i - 1]);
}

void rev2(char *str)
{
    if (!str)
        return;

    char *p = str;
    char *q = str;

    while (*q)
        ++q;
    --q;

    while (p < q)
        swap(*p++, *q--);
}

int main(void)
{
    char str[] = "abcde";
    cout << str << endl;

    rev1(str);
    cout << "Reverse1: " << str << endl;

    rev2(str);
    cout << "Reverse2: " << str << endl;
}
