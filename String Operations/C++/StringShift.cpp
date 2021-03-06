#include <iostream>
#include <string.h>

using namespace std;

string shiftLeft(string s, int c)
{
    return s.substr(c) + s.substr(0, c);
}

string shiftRight(string s, int c)
{
    return s.substr(c + 1) + s.substr(0, c + 1);
}

int main(void)
{
    cout << shiftRight("Hello", 2);
    cout << "\n";
    cout << shiftLeft("Hello", 2);
}

/*
loHel
lloHe
*/
