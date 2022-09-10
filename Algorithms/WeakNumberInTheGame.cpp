#include <iostream>
#include <vector>
#include <algorithm>

/*
* https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/

You are playing a game that contains multiple characters,
and each of the characters has two main properties: attack and defense.
You are given a 2D integer array properties where properties[i] = [attacki, defensei]
represents the properties of the ith character in the game.

A character is said to be weak if any other character has both attack and defense levels
strictly greater than this character's attack and defense levels.
More formally, a character i is said to be weak if there exists another character j
where attack[j] > attack[i] and defense[j] > defense[i].

Return the number of weak characters.

Example 1:
Input: properties = [[5,5],[6,3],[3,6]]
Output: 0
Explanation: No character has strictly greater attack and defense than the other.

Example 2:
Input: properties = [[2,2],[3,3]]
Output: 1
Explanation: The first character is weak because the second character has a strictly greater attack and defense.

Example 3:
Input: properties = [[1,5],[10,4],[4,3]]
Output: 1
Explanation: The third character is weak because the second character has a strictly greater attack and defense.
*/

using namespace std;
class Solution
{
public:
    static bool cmp(vector<int> &a, vector<int> &b)
    {
        if (a[0] != b[0])
            return a[0] > b[0];
        return a[1] < b[1];
    }

    int numberOfWeakCharacters(vector<vector<int>> &properties)
    {
        sort(properties.begin(), properties.end(), cmp);

        int res = 0;

        int currMax = INT_MIN;

        for (auto &p : properties)
            if (currMax > p[1])
                res++;
            else
                currMax = p[1];

        return res;
    }
};
