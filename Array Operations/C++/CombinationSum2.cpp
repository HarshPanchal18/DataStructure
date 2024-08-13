#include <iostream>
#include <vector>

using namespace std;

/*
* Combination Sum II
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
Each number in candidates may only be used once in the combination.
Note: The solution set must not contain duplicate combinations.

Example 1:
Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: [ [1,1,6], [1,2,5], [1,7], [2,6] ]

Example 2:
Input: candidates = [2,5,2,1,2], target = 5
Output: [ [1,2,2], [5] ]
Constraints:
1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30
*/

class Solution
{
public:
    vector<vector<int>> ans;

    void backtrack(vector<int> a, vector<int> curr, int t, int i)
    {
        if (t == 0)
            ans.push_back(curr);

        if (t < 0)
            return;

        int prev = -1; // to keep track of prev element

        for (; i < a.size(); i++)
        {
            if (prev == a[i]) // if prev element is same as current element, we continue.
                continue;

            curr.push_back(a[i]); // else we append it to current

            backtrack(a, curr, t - a[i], i + 1); // backtrack for the required element

            curr.pop_back(); // the other backtrack is without using the current element
            prev = a[i];
        }
    }

    vector<vector<int>> combinationSum2(vector<int> &a, int t)
    {
        sort(a.begin(), a.end());
        vector<int> temp;
        temp.clear();
        backtrack(a, temp, t, 0);
        return ans;
    }
};