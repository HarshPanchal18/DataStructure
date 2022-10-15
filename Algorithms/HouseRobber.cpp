#include <vector>
#include <iostream>

using namespace std;

/*
House Robber
You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed, the only constraint
stopping you from robbing each of them is that adjacent houses have security systems connected
and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house,
return the maximum amount of money you can rob tonight without alerting the police.


Example 1:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 2:
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
*/

class Solution
{
public:
    int robb(vector<int> &nums)
    {
        int n = nums.size();
        if (n == 1)
            return nums[0];
        if (n == 2)
            return max(nums[0], nums[1]);

        int houses[n];
        houses[n - 1] = nums[n - 1];
        houses[n - 2] = nums[n - 2];
        houses[n - 3] = nums[n - 3] + houses[n - 1];

        for (int i = n - 4; i >= 0; i--)
            houses[i] = nums[i] + max(houses[i + 2], houses[i + 3]);

        return max(houses[0], houses[1]);
    }

    /*
    Explaination
think of the choice diagram:-
you have the two choices for a particullar index :-
1:- if you include it then you have to jump two index of the array and add the value of the choosen index to it.
2:- if you have not choosen that index then you have left array of size n-1

So, we can write the above choice in the form of c++ as:-
max(nums[n]+recursive(nums,n-2),recursive(n-1));
    */

    //
    int recursive(vector<int> &nums, int n)
    {
        if (n < 0)
            return 0;

        if (n < 1)
            return nums[0];
        else
            return max(nums[n - 1] + recursive(nums, n - 2), recursive(nums, n - 1));
    }
    int rob(vector<int> &nums)
    {
        int n = nums.size();
        return recursive(nums, n);
    }

    //
    int iterative(vector<int> &nums, int n, vector<int> dp)
    {
        if (n < 0)
            return 0;
        if (n == 1)
            return nums[0];
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 2; i < n + 1; i++)
            dp[i] = max(nums[i - 1] + dp[i - 2], dp[i - 1]);
        return dp[n];
    }

    int rob2(vector<int> &nums)
    {
        vector<int> dp(nums.size() + 1, 0);
        return iterative(nums, nums.size(), dp);
    }

    // Memoization
    int solve(int i, int &n, vector<int> &ans, vector<int> &nums)
    {
        if (i >= n)
            return 0;
        if (ans[i] != -1)
            return ans[i];

        return ans[i] = max(solve(i + 1, n, ans, nums), max(ans[i], nums[i] + solve(i + 2, n, ans, nums)));
    }
    int rob3(vector<int> &nums)
    {
        int n = nums.size();
        vector<int> ans(n, -1);
        solve(0, n, ans, nums);
        return *max_element(ans.begin().ans.end());
    }
};

int main(void)
{
    int n = 5;
    vector<int> nums(n);
    for (int i = 0; i < n; i++)
        nums[i] = i;

    Solution obj;
    cout << obj.recursive(nums, n);
    cout << obj.robb(nums);
}
