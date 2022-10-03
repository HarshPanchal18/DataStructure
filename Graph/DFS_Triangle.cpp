#include <iostream>
#include <vector>
using namespace std;

/*
Triangle
https://leetcode.com/problems/triangle/description/?envType=study-plan&id=algorithm-i
Given a triangle array, return the minimum path sum from top to bottom.

For each step, you may move to an adjacent number of the row below.
More formally, if you are on index i on the current row,
you may move to either index i or index i + 1 on the next row.



Example 1:
Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11

Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3

The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11

Example 2:
Input: triangle = [[-10]]
Output: -10
*/

class Solution
{
public:
    int dfs(int i, int j, int n, vector<vector<int>> &tri)
    {
        if (i == n)
            return 0;
        int left = tri[i][j] + dfs(i + 1, j, n, tri);
        int right = tri[i][j] + dfs(i + 1, j + 1, n, tri);
        return min(left, right);
    }
    int minimumTotal(vector<vector<int>> &triangle)
    {
        int n = triangle.size();
        return dfs(0, 0, n, triangle);
    }

    // Method 2
    int minimumTotal2(vector<vector<int>> &triangle)
    {
        vector<vector<int>> dp = triangle;
        for (int i = triangle.size() - 2; i >= 0; i--)
            for (int j = 0; j < triangle[i].size(); j++)
                dp[i][j] += min(dp[i + 1][j], dp[i + 1][j + 1]);

        return dp[0][0];
    }

    // Method 3 - Tabulation
    int minimumTotal3(vector<vector<int>> &triangle)
    {
        int n = triangle.size();

        for (int i = n - 2; i >= 0; --i)
            for (int j = 0; j <= i; ++j)
                triangle[i][j] += min(triangle[i + 1][j], triangle[i + 1][j + 1]);

        return triangle[0][0];
    }

    // Method 4
    int minimumTotal4(vector<vector<int>> triangle)
    {
        int n = triangle.size();
        vector<int> nextRow(triangle[n - 1]);
        vector<int> currRow(n, 0);

        for (int i = n - 2; i >= 0; i--)
        {
            for (int j = 0; j < i + 1; j++)
            {
                int lowLeft = triangle[i][j] + nextRow[j];
                int lowRight = triangle[i][j] + nextRow[j + 1];
                currRow[j] = min(lowLeft, lowRight);
            }
            swap(currRow, nextRow);
        }
        return nextRow[0]; // because we swapped at last iteration
    }

    // Space Optimization
    int minimumTotal(vector<vector<int>> &triangle)
    {
        int n = triangle.size();
        vector<int> next(n);
        for (int i = n - 1; i >= 0; i--)
        {
            vector<int> curr(n);
            for (int j = i; j >= 0; j--)
                if (i == n - 1)
                    curr[j] = triangle[i][j];
                else
                {
                    int up = triangle[i][j] + next[j];
                    int up_left = triangle[i][j] + next[j + 1];
                    curr[j] = min(up, up_left);
                }
            next = curr;
        }
        return next[0];
    }
};
