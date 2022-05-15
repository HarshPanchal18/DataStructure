#include <bits/stdc++.h>
using namespace std;

void getSubStr(string s, string out)
{
    if (s.size() == 0)
    {
        cout << out << endl;
        return;
    }
    char c = s[0];
    s.erase(s.begin() + 0);

    getSubStr(s, out);
    getSubStr(s, out + c);
}

int main(void)
{
    string s;
    cin >> s;

    string ans;
    getSubStr(s, ans);
}

/*
super

r
e
er
p
pr
pe
per
u
ur
ue
uer
up
upr
upe
uper
s
sr
se
ser
sp
spr
spe
sper
su
sur
sue
suer
sup
supr
supe
super
*/
