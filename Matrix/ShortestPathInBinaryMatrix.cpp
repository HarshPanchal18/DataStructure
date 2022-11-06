#include <queue>
#include <iostream>
using namespace std;

/*
* Shortest Path in Binary Matrix

Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix.
If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0))
to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected
(i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.

Example 1:
Input: grid = [[0,1],[1,0]]
Output: 2

Example 2:
Input: grid = [[0,0,0],
               [1,1,0],
               [1,1,0]]
Output: 4

Example 3:
Input: grid = [[1,0,0],
               [1,1,0],
               [1,1,0]]
Output: -1
*/

/*
* Use BFS approach to find shortest path like we solve for graph problems.
    You can think of adjacent cells with value = 0 having an undirected edge between them.
* Apply BFS and update counter at everly level.
* To track levels I have used nodesPushed. It is equal to number of nodes in current level.
* If you are able to reach (n-1, n-1) return its level otherwise return -1.
* Little Optimization : Rather than keeping visited vector, after we have pushed a node in the queue make its value
    in grid equal to 1. This would make it unavailable to process next time we encounter it.
    However this method will corrupt the memory.
* DP will not work here because the way we traverse the matrix in DP will not lead to formulation of correct solution
    or rather the optimal path. However, BFS will lead to an optimal path.
* We can also get our answer by DFS but it would be computationaly more expensive and might give TLE on large inputs.
*/

class Solution
{
public:
    bool isValid(int i, int j, int n, vector<vector<int>> &grid, vector<vector<bool>> &vis)
    {
        return (i >= 0 && i < n && j >= 0 && j < n && grid[i][j] == 0 && !vis[i][j]);
    }

    int shortestPathBinaryMatrix(vector<vector<int>> &grid)
    {
        int n = grid.size();
        vector<vector<bool>> vis(n, vector<bool>(n, false));
        queue<pair<int, int>> q;
        int pushedNodes = 0;
        int res = 0;

        if (grid[0][0] == 0)
        {
            q.push({0, 0});
            vis[0][0] = true;
        }

        while (!q.empty())
        {
            res++;
            pushedNodes = q.size();

            for (int count = 0; count < pushedNodes; count++)
            {
                pair<int, int> headNode = q.front();
                q.pop();

                int i = headNode.first;
                int j = headNode.second;

                if (i == n - 1 && j == n - 1)
                    return res;

                for (int k = i - 1; k <= i + 1; k++)
                    for (int l = j - 1; l <= j + 1; l++)
                        if (isValid(k, l, n, grid, vis))
                        {
                            q.push({k, l});
                            vis[k][l] = true;
                        }
            }
        }
        return -1;
    }
};

/*
Intuition
We have to find the shortest distnace from a source node
(i.e. index (0,0)(0,0)(0,0)) to a destination node (i.e. index (n−1,n−1)(n-1, n-1)(n−1,n−1)).
So, here we can use simple Dijkstra Algorithm,
taking 8 direction from current index as child node and taking unit weight
(i.e. Taking Distance to travel another node is 1).

Base Cases :-
node should be inside boundry of matrix.
(i.e, i>=0 and j>=0 and i<n and j<n )
We can only traverse when grid[i][j]==0
if source (0,0) or destination (n−1,n−1) is 1 than as per point no. 2, we can not even move forward,
so we will return -1 at starting only.
After completing Djikstra algorithm if distTo[n−1][n−1] == INT_MAX,
than it will conclude that reaching destination node (n−1,n−1) is not possible, so we will return -1.
if point no. 4 is not true, than we will return (distTo[n-1][n-1] + 1).
(Here  we  add  1  to  final  result,  to  count  source  node  (0,0)  in  the  path)
*/

struct myPair
{
    int dist;
    int i;
    int j;
};

struct myComp
{
    constexpr bool operator()(myPair const &a, myPair const &b) const noexcept
    {
        return a.dist > b.dist;
    }
};
class Solution
{
    bool check(int i, int j, int n, vector<vector<int>> &grid)
    {
        if (i >= 0 and j >= 0 and i < n and j < n and grid[i][j] == 0)
            return true;
        return false;
    }

public:
    int shortestPathBinaryMatrix(vector<vector<int>> &grid)
    {

        int n = grid.size();

        if (grid[0][0] != 0 or grid[n - 1][n - 1] != 0)
            return -1;

        vector<vector<int>> distTo(n, vector<int>(n, INT_MAX));

        priority_queue<myPair, vector<myPair>, myComp> pq;

        vector<int> dirx{1, -1, 1, 1, 0, -1, 0, -1};
        vector<int> diry{1, 1, -1, 0, 1, 0, -1, -1};

        distTo[0][0] = 0;
        pq.push({0, 0, 0});

        while (!pq.empty())
        {
            myPair u = pq.top();
            pq.pop();

            for (int k = 0; k < 8; k++)
                if (check((u.i + dirx[k]), (u.j + diry[k]), n, grid))
                {
                    myPair v = {distTo[(u.i + dirx[k])][(u.j + diry[k])], (u.i + dirx[k]), (u.j + diry[k])};

                    if (v.dist > (distTo[u.i][u.j] + 1))
                    {
                        v.dist = (distTo[u.i][u.j] + 1);
                        distTo[(u.i + dirx[k])][(u.j + diry[k])] = v.dist;
                        pq.push(v);
                    }
                }
        }

        return (distTo[n - 1][n - 1] == INT_MAX) ? -1 : (distTo[n - 1][n - 1] + 1);
    }
};
