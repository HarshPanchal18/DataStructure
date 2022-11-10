#include <iostream>
#include <vector>
using namespace std;

/*
* Word Search

Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells,
where adjacent cells are horizontally or vertically neighboring.
The same letter cell may not be used more than once.

Example 1:
Input: board = [["A","B","C","E"],
                ["S","F","C","S"],
                ["A","D","E","E"]],
word = "ABCCED"
Output: true

Example 2:
Input: board = [["A","B","C","E"],
                ["S","F","C","S"],
                ["A","D","E","E"]],
word = "SEE"
Output: true

Example 3:
Input: board = [["A","B","C","E"],
                ["S","F","C","S"],
                ["A","D","E","E"]],
word = "ABCB"
Output: false
*/

class Solution
{
public:
    bool search(int i, int j, int n, int m, vector<vector<char>> &board, string word, int k)
    {
        if (k == word.size())
            return true;

        if (i < 0 || j < 0 || i == n || j == m || board[i][j] != word[k])
            return false;

        char ch = board[i][j];
        board[i][j] = '#';

        bool opt1 = search(i + 1, j, n, m, board, word, k + 1);
        bool opt2 = search(i, j + 1, n, m, board, word, k + 1);
        bool opt3 = search(i - 1, j, n, m, board, word, k + 1);
        bool opt4 = search(i, j - 1, n, m, board, word, k + 1);
        board[i][j] = ch;

        return opt1 || opt2 || opt3 || opt4;
    }

    bool exist(vector<vector<char>> &board, string word)
    {
        int n = board.size();
        int m = board[0].size();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (search(i, j, n, m, board, word, 0))
                    return true;

        return false;
    }
};

class Solution2
{
    vector<int> dirx = {0, -1, 0, 1};
    vector<int> diry = {1, 0, -1, 0};

public:
    bool DFS(vector<vector<char>> &board, string word, int i, int j, int n, int m, int index)
    {
        if (index >= word.length())
            return true;
        if (i < 0 || j < 0 || i >= n || j >= m || board[i][j] != word[index])
            return false;

        board[i][j] = '#';

        for (int k = 0; k < 4; k++)
            if (DFS(board, word, i + dirx[k], j + diry[k], n, m, index + 1))
                return true;

        board[i][j] = word[index];
        return false;
    }

    bool exist(vector<vector<char>> &board, string word)
    {
        int n = board.size();
        int m = board[0].size();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (board[i][j] == word[0])
                    if (DFS(board, word, i, j, n, m, 0))
                        return true;

        return false;
    }
};

class Solution3
{
public:
    bool exist(vector<vector<char>> &board, string word)
    {
        for (int i = 0; i < board.size(); i++)
            for (int j = 0; j < board[0].size(); j++)
                if (fun(board, i, j, word, 0))
                    return true;

        return false;
    }

    bool fun(vector<vector<char>> &board, int r, int c, string &word, int count)
    {

        if (word.size() == count)
            return true;

        if (r >= board.size() || r < 0 || c < 0 || c >= board[0].size() || board[r][c] != word[count])
            return false;

        // Visited
        board[r][c] = '@';
        // up                              // down
        bool status = fun(board, r - 1, c, word, count + 1) || fun(board, r + 1, c, word, count + 1) ||
                      // left                          // right
                      fun(board, r, c - 1, word, count + 1) || fun(board, r, c + 1, word, count + 1);

        // Non Visited
        board[r][c] = word[count];

        return status;
    }
};
