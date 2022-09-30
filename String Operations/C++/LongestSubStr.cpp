#include <iostream>
#include <vector>

using namespace std;

/*
* https://leetcode.com/problems/longest-substring-without-repeating-characters/
* Longest Substring Without Repeating Characters

Given a string s, find the length of the longest substring without repeating characters.
Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/

class Solution
{
public:
    int lengthOfLongestSubstring(string s)
    {
        int length = 0;
        vector<int> d(256, -1);
        int start = -1;
        for (int i = 0; i != s.length(); i++)
        {
            if (d[s[i]] > start)
                start = d[s[i]];

            d[s[i]] = i;
            length = max(length, i - start);
        }
        return length;
    }
};
