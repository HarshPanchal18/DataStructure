#include <iostream>
#include <vector>
using namespace std;

/*
Subset
Given an integer array nums of unique elements, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

Example 2:
Input: nums = [0]
Output: [[],[0]]
*/

vector<vector<int>> subs(vector<int> &nums)
{
    /*
    Iterative
    Using [1, 2, 3] as an example, the iterative process is like:
    Initially, one empty subset [[]]
    Adding 1 to []: [[], [1]];
    Adding 2 to [] and [1]: [[], [1], [2], [1, 2]];
    Adding 3 to [], [1], [2] and [1, 2]: [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]].
    */
    vector<vector<int>> res = {{}};
    for (int num : nums)
    {
        int n = res.size();
        for (int i = 0; i < n; i++)
        {
            res.push_back(res[i]);
            res.back().push_back(num);
        }
    }
    return res;
}

/*
Recursive
*/
vector<vector<int>> subsets(vector<int> &nums)
{
    vector<vector<int>> res;
    vector<int> sub;
    subset(nums, 0, sub, res);
    return res;
}

void subset(vector<int> &nums, int i, vector<int> &sub, vector<vector<int>> &res)
{
    res.push_back(sub);
    for (int j = i; j < nums.size(); j++)
    {
        sub.push_back(nums[j]);
        subset(nums, j + 1, sub, res);
        sub.pop_back();
    }
}

//
vector<vector<int>> ans;
void subs(vector<int> n, vector<int> l)
{
    if (n.size() == 0)
        return;

    int s = n[n.size() - 1];
    n.pop_back();
    subs(n, l);

    l.push_back(s);
    subs(n, l);
    ans.push_back(l);
}

vector<vector<int>> subsets(vector<int> &nums)
{
    vector<int> k;
    ans.push_back(k);
    subs(nums, k);
    return ans;
}
