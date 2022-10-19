#include <bits/stdc++.h>
using namespace std;

/*
Given a string s and a dictionary of words dict of length n,
add spaces in s to construct a sentence where each word is a valid dictionary word.
Each dictionary word can be used more than once. Return all such possible sentences.

Example 1:
Input: s = "catsanddog", n = 5
dict = {"cats", "cat", "and", "sand", "dog"}
Output: (cats and dog)(cat sand dog)
Explanation: All the words in the given sentences are present in the dictionary.

Example 2:
Input: s = "catsandog", n = 5
dict = {"cats", "cat", "and", "sand", "dog"}
Output: Empty
Explanation: There is no possible breaking of the string s where all the words are present in dict.
*/

class Solution
{
public:
    void solve(string s, string ans, unordered_set<string> &st, vector<string> &dict)
    {
        if (s.length() <= 0)
        {
            ans.pop_back();
            dict.push_back(ans);
        }
        for (int i = 0; i < s.length(); i++)
        {
            string l = s.substr(0, i + 1);
            if (st.find(l) != st.end())
                solve(s.substr(i + 1), ans + l + " ", st, dict);
        }
    }
    vector<string> wordBreak(int n, vector<string> &dict, string s)
    {
        unordered_set<string> st;
        for (string str : dict)
            st.insert(str);

        dict.clear();
        solve(s, "", st, dict);

        return dict;
    }
};

int main()
{
    int n;
    vector<string> dict;
    string s;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        cin >> s;
        dict.push_back(s);
    }
    cin >> s;

    Solution ob;
    vector<string> ans = ob.wordBreak(n, dict, s);
    if (ans.size() == 0)
        cout << "Empty\n";
    else
    {
        sort(ans.begin(), ans.end());
        for (int i = 0; i < ans.size(); i++)
            cout << "(" << ans[i] << ")";
        cout << endl;
    }
    return 0;
}
