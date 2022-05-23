#include <bits/stdc++.h>

using namespace std;

void getPermutation(string s, string out)
{
    if (s.empty())
    {
        cout << out << endl;
        return;
    }

    char c = s[0];
    s.erase(s.begin() + 0);

    getPermutation(s, out + c); // Starting point (from Given String)

    if (c >= 'a' && c <= 'z')
        c -= 'a' - 'A';
    else
        c += 'a' - 'A';

    getPermutation(s, out + c); // End-point (Gives Inverted case of Entered string)
}

int main(void)
{
    string s;
    cout << "\nEnter String: ";
    cin >> s;

    string ans;
    getPermutation(s, ans);
}

/*
Enter String: ride
ride
ridE
riDe
riDE
rIde
rIdE
rIDe
rIDE
Ride
RidE
RiDe
RiDE
RIde
RIdE
RIDe
RIDE
*/
