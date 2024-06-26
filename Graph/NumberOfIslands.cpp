#include <iostream>
#include <vector>
#include <queue>
using namespace std;

/*
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

Example 1:
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
*/

class Solution
{
public:
    //------------------------
    int numIslands(vector<vector<char>> &grid)
    {
        // 1s is land, 0s is water
        int count = 0;
        int n = grid.size();    // this is row
        int m = grid[0].size(); // this is colomn

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)

                if (grid[i][j] == '1')
                {
                    count++;
                    numsIsland(i, j, n, m, grid);
                }

        return count;
    }

    bool validIslands(int i, int j, int n, int m, vector<vector<char>> &grid)
    {
        if (i >= 0 && i < n && j >= 0 && j < m && grid[i][j] == '1')
            return true;

        return false;
    }

    void numsIsland(int i, int j, int n, int m, vector<vector<char>> &grid)
    {
        grid[i][j] = '0';

        // for down
        numsIsland(i + 1, j, n, m, grid);

        // for up
        numsIsland(i - 1, j, n, m, grid);

        // for right
        numsIsland(i, j + 1, n, m, grid);

        // for left
        numsIsland(i, j - 1, n, m, grid);
    }

    //------------------------
    void bfs(int i, int j, vector<vector<int>> &vis, vector<vector<char>> &mat, int m, int n)
    {
        vis[i][j] = 1;
        queue<pair<int, int>> q;
        q.push({i, j});
        vector<pair<int, int>> dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!q.empty())
        {
            pair<int, int> node = q.front();
            q.pop();
            for (auto d : dir)
            {
                int row = node.first + d.first;
                int col = node.second + d.second;
                if (row >= 0 && row < m && col >= 0 && col < n && mat[row][col] == '1' && !vis[row][col])
                {
                    vis[row][col] = 1;
                    q.push({row, col});
                }
            }
        }
    }

    int numIslands(vector<vector<char>> &mat)
    {
        int m = mat.size();
        int n = mat[0].size();
        int count = 0;
        vector<vector<int>> vis(m, vector<int>(n, 0));
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (mat[i][j] == '1' && !vis[i][j])
                {
                    count++;
                    bfs(i, j, vis, mat, m, n);
                }
        return count;
    }

    //-----------------------
    void dfs(vector<vector<char>> &grid, int row, int col)
    {
        if (row < 0 || col < 0 || row == grid.size() || col == grid[0].size())
            return;
        if (grid[row][col] == '0')
            return;

        if (grid[row][col] == '1')
            grid[row][col] = '0';

        // to left
        dfs(grid, row, col - 1);
        // to right
        dfs(grid, row, col + 1);
        // to down
        dfs(grid, row + 1, col);
        // to top
        dfs(grid, row - 1, col);
    }

    int numIslands(vector<vector<char>> &grid)
    {
        // 1s is land, 0s is water
        int count = 0;
        int n = grid.size();    // this is row
        int m = grid[0].size(); // this is colomn
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (grid[i][j] == '1')
                {
                    count++;
                    dfs(grid, i, j);
                }

        return count;
    }
};

class Solution
{
public:
    void solve(vector<vector<char>> &grid, vector<vector<char>> &visited, int row, int col, int delRow[], int delCol[])
    {
        visited[row][col] = 1;
        for (int i = 0; i < 4; i++)
        {
            int newRow = row + delRow[i];
            int newCol = col + delCol[i];
            if (newRow < grid.size() && newCol < grid[0].size() && newRow >= 0 && newCol >= 0 && visited[newRow][newCol] == 0 && grid[newRow][newCol] == '1')
            {
                solve(grid, visited, newRow, newCol, delRow, delCol);
            }
        }
    }
    int numIslands(vector<vector<char>> &grid)
    {
        int m = grid.size();
        int n = grid[0].size();
        int delRow[] = {-1, 0, 1, 0};
        int delCol[] = {0, 1, 0, -1};
        vector<vector<char>> visited(m, vector<char>(n, 0));
        int count = 0;
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (grid[i][j] == '1' && visited[i][j] == 0)
                {
                    solve(grid, visited, i, j, delRow, delCol);
                    count++;
                }
            }
        }
        return count;
    }
};

class Solution
{

public:
    int numIslands(vector<vector<char>> &g)
    {

        int n = g.size();
        int m = g[0].size();
        int num = 0;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (g[i][j] == '1')
                {
                    visit_dfs(g, i, j);
                    num++;
                }
            }
        }
        return num;
    }

    void visit_dfs(vector<vector<char>> &g, int i, int j)
    {
        int n = g.size();
        int m = g[0].size();
        vector<int> dim_row = {0, 1, 0, -1};
        vector<int> dim_col = {1, 0, -1, 0};

        g[i][j] = '0'; // Mark the current cell as visited

        for (int k = 0; k < 4; k++)
        {
            int new_row = i + dim_row[k];
            int new_col = j + dim_col[k];

            if (new_row >= 0 && new_row < n && new_col >= 0 && new_col < m && g[new_row][new_col] == '1')
            {
                visit_dfs(g, new_row, new_col); // Recursively visit the neighboring cell
            }
        }
    }
};