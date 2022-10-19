#include <vector>
#include <iostream>
#include <cstring>
#include <set>
#include <unordered_set>
using namespace std;

/*
* Word Break

Given a string s and a dictionary of strings wordDict,
return true if s can be segmented into a space-separated sequence of one or more dictionary words.
Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
*/

class Solution
{
public:
    int dp[305];
    unordered_set<string> s;
    bool helper(string &str, int i, int n)
    {
        if (i == n) // base case
            return true;

        if (dp[i] != -1) // if already calculated
            return dp[i];

        // partition str at j and if substring is present int set then call for remaining part
        for (int j = i; j < n; j++)
            if (s.count(str.substr(i, j - i + 1)))
                if (helper(str, j + 1, n))
                    return dp[i] = true;

        return dp[i] = false; // store the res and return
    }

    bool wordBreak(string str, vector<string> &wordDict)
    {
        int n = str.size();

        for (auto word : wordDict) // insert all the words into set
            s.insert(word);

        memset(dp, -1, sizeof(dp)); // declare dp with -1

        return helper(str, 0, n);
    }
};

bool wordBreak(string s, vector<string> &wordDict)
{
    set<string> dict(wordDict.begin(), wordDict.end());
    return solve(0, s, dict);
}

bool solve(int idx, string &s, set<string> &dict)
{
    if (idx == s.length())
        return true;

    for (int i = idx; i < s.length(); i++)
    {
        string cur = s.substr(idx, i - idx + 1);
        if (dict.find(cur) != dict.end() && solve(i + 1, s, dict))
            return true;
    }
    return false;
}

// Memoization
bool wordBreak(string s, vector<string> &wordDict)
{
    set<string> dict(wordDict.begin(), wordDict.end());
    vector<int> dp(s.length(), -1);
    return solve2(0, s, dict, dp);
}

bool solve2(int ind, string &s, set<string> &dict, vector<int> &dp)
{
    if (ind == s.length())
        return true;

    if (dp[ind] != -1)
        return dp[ind];

    for (int i = ind; i < s.length(); i++)
    {
        string cur = s.substr(ind, i - ind + 1);
        if (dict.find(cur) != dict.end() && solve2(i + 1, s, dict, dp))
            return dp[ind] = true;
    }
    return dp[ind] = false;
}

// Tabulation
bool wordBreak(string s, vector<string> &wordDict)
{
    set<string> dict(wordDict.begin(), wordDict.end());
    int n = s.length();
    vector<bool> dp(n + 1, false);
    dp[n] = true;

    for (int ind = n - 1; ind >= 0; ind--)
    {
        for (int i = ind; i < s.length(); i++)
        {
            string cur = s.substr(ind, i - ind + 1);
            if (dict.find(cur) != dict.end() && dp[i + 1])
            {
                dp[ind] = true;
                break;
            }
        }
    }
    return dp[0];
}
