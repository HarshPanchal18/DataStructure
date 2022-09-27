#include <vector>
#include <iostream>
using namespace std;

/*
* https://leetcode.com/problems/max-area-of-island/
*
* Max Area of Island
You are given an m x n binary matrix grid.
An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
You may assume all four edges of the grid are surrounded by water.
The area of an island is the number of cells with a value 1 in the island.
Return the maximum area of an island in grid. If there is no island, return 0.

grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],
        [0,0,0,0,0,0,0,1,1,1,0,0,0],
        [0,1,1,0,1,0,0,0,0,0,0,0,0],
        [0,1,0,0,1,1,0,0,1,0,1,0,0],
        [0,1,0,0,1,1,0,0,1,1,1,0,0],
        [0,0,0,0,0,0,0,0,0,0,1,0,0],
        [0,0,0,0,0,0,0,1,1,1,0,0,0],
        [0,0,0,0,0,0,0,1,1,0,0,0,0]]

Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.

Input: grid = [[0,0,0,0,0,0,0,0]]
Output: 0
*/

class Solution
{
public:
    int maximum = 0, current = 0;
    int m = 0, n = 0;

    void maxArea(vector<vector<int>> &isl, int x, int y)
    {
        if (x < 0 || y < 0 || x >= m || y >= n || !isl[x][y])
            return;

        isl[x][y] = 0;
        current++;

        maxArea(isl, x, y + 1);
        maxArea(isl, x, y - 1);
        maxArea(isl, x - 1, y);
        maxArea(isl, x + 1, y);
    }

    int maxAreaOfIsland(vector<vector<int>> &grid)
    {
        m = grid.size();
        n = grid[0].size();

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 1)
                {
                    current = 0;
                    maxArea(grid, i, j);
                    maximum = max(current, maximum);
                }

      return maximum;
    }
};
