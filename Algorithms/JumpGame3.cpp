#include <vector>
#include <queue>
using namespace std;

/*
* Jump Game III

Given an array of non-negative integers arr, you are initially positioned at start index of the array.
When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
Notice that you can not jump outside of the array at any time.

Example 1:
Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation:
All possible ways to reach at index 3 with value 0 are:
index 5 -> index 4 -> index 1 -> index 3
index 5 -> index 6 -> index 4 -> index 1 -> index 3

Example 2:
Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true
Explanation:
One possible way to reach at index 3 with value 0 is: index 0 -> index 4 -> index 1 -> index 3

Example 3:
Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.
*/

class Solution
{
public:
    // DFS Traversal
    /*
We are given a starting index (renamed as current index-cur below) from which we have two choices of either jumping from cur to -
    cur + A[cur] or,
    cur - A[cur]
We can try both these possible jumps to see if we reach any index with value 0 with either of it.
In the scenario where it's possible, we will eventually reach an index with value 0 and return true.
But when it is not possible, we would end up in an infinite recursion
(as we would just keep trying both jumps for every index without ever reaching the target).
To avoid this, we need to ensure that we never visit the same index twice.
This wont affect the answer because if we had already visited an index earlier,
we had already tried out all possibilities and revisiting it again would just lead to endless cycle.

To mark an index as visited, we can either use a boolean array or hashmap and store indices visited in it.
But in the below solution, I am marking an index as visited by making it negative.
Then we can check at the start of each recursion if A[cur] < 0 to check if it is already visited or not.
Marking negative also maintains both possible jumps (cur + A[cur] becomes cur - A[cur] & vice-versa)
    */

    bool canReach(vector<int> &A, int cur)
    {
        if (cur < 0 || cur >= A.size() || A[cur] < 0)
            return false; // out of bounds OR already visited ? return false

        A[cur] *= -1; // mark as visited by making -ve
        return !A[cur] || canReach(A, cur + A[cur]) || canReach(A, cur - A[cur]);
        // return true if A[cur] == 0 or recurse for both possible jumps
    }

    // BFS - Traversal
    /*
Similar to what we did in the above solution, we can do it using BFS as well.
The only difference in these two would be that BFS would be equivalent to exploring all possible paths till now at once
(meaning one move at a time in each path), while DFS is equivalent to exploring one path at a time till we either completely
explore it or reach the target index.
We start by pushing the starting index into the queue and iteratively trying both possible jumps from indices in queue.
If we A[cur] 0, we can return true.
If we reach already visited index (A[cur] < 0), we discard further exploration of this path & continue to next element of queue
If current index is not visited and value is not equal to 0, further explore both possible jumps from this index
by pushing both of it into queue (after proper bounds check).
    */
    bool canReach(vector<int> &A, int cur)
    {
        queue<int> q;
        q.push(cur);

        while (q.size())
        {
            cur = q.front();
            q.pop();

            if (A[cur] == 0)
                return true; // target reached

            if (A[cur] < 0)
                continue; // already visited - stop further exploration of this path

            if (cur + A[cur] < A.size())
                q.push(cur + A[cur]); // try forward jump

            if (cur - A[cur] >= 0)
                q.push(cur - A[cur]); // try backward jump

            A[cur] *= -1; // mark current index as visited by negating
        }
        return false; // explored all array without reaching target
    }

    //
    bool greedy(vector<int> &arr, int start, vector<bool> &visited)
    {

        if (start < 0 || start >= arr.size() || visited[start])
            return false;
        if (arr[start] == 0)
            return true;
        visited[start] = true;

        return greedy(arr, start + arr[start], visited) || greedy(arr, start - arr[start], visited);
    }

    bool canReach(vector<int> &arr, int start)
    {
        vector<bool> visited(arr.size(), false);
        return greedy(arr, start, visited);
    }

    // DFS recursion
    bool ff(vector<int> &arr, vector<bool> &visited, int i)
    {
        if (i < 0 || i >= arr.size() || visited[i])
            return false;
        if (arr[i] == 0)
            return true;
        visited[i] = true;
        return ff(arr, visited, i + arr[i]) || ff(arr, visited, i - arr[i]);
    }

    bool canReach(vector<int> &arr, int start)
    {
        vector<bool> visited(arr.size(), false);
        return ff(arr, visited, start);
    }
};

class Solution
{
public:
    bool reach(vector<int> &arr, int start)
    {
        if (arr[start] == 0)
            return true;

        if (start < 0 || start >= arr.size() || arr[start] == -1)
            return false;

        int jumplength = arr[start];

        arr[start] = -1;
        bool forward = reach(arr, start + jumplength);
        bool backward = reach(arr, start - jumplength);

        return forward || backward;
    }

    bool canReach(vector<int> &arr, int start)
    {
        return reach(arr, start);
    }
};
