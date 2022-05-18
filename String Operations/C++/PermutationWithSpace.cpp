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

class method2{
public:

vector<string>ans;
void backTrack(string s,int len)
{
    if(len==0)
    {
        ans.push_back(s);
        return;
    }

    backTrack(s.substr(0,s.size()-len)+" "+s.substr(s.size()-len),len-1);
    backTrack(s,len-1);
}

    vector<string> permutation(string S)
    {
        int len=S.size()-1;
        backTrack(S,len);
        return ans;
    }
};

int main(void)
{
    string s;
    cin >> s;

    string ans;
    ans += s[0];
    s.erase(s.begin() + 0);
    getPermutation(s, ans);
    
    method2 obj1;
    
    string str1;
    cin>>str1;
    
    vector<string> ans;
    
    ans=obj1.permutation(str1);
    
    for(int i=0;i<ans.size();i++)
        cout<<"("<<ans[i]<<")"<<endl;

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
