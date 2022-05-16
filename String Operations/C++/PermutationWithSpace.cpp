#include <bits/stdc++.h>

using namespace std;

void getPermutation(string s, string ans)
{
    if (s.size() == 0)
    {
        cout << ans << endl;
        return;
    }

    char temp = s[0];
    s.erase(s.begin() + 0);

    getPermutation(s, ans + temp);

    getPermutation(s, ans + " " + temp);
}

int main(void)
{
    string s;
    cin >> s;

    string ans;
    ans += s[0];
    s.erase(s.begin() + 0);

    getPermutation(s, ans);
}

/*
surat
surat
sura t
sur at
sur a t
su rat
su ra t
su r at
su r a t
s urat
s ura t
s ur at
s ur a t
s u rat
s u ra t
s u r at
s u r a t
*/
