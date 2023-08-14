/*
* Unique Paths II
You are given an m x n integer array grid.
There is a robot initially located at the top-left corner (i.e., grid[0][0]).
The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
The robot can only move either down or right at any point in time.
An obstacle and space are marked as 1 or 0 respectively in grid.
A path that the robot takes cannot include any square that is an obstacle.
Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
The testcases are generated so that the answer will be less than or equal to 2 * 109.

Input: obstacleGrid = [
    [0,0,0],
    [0,1,0],
    [0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right

Input: obstacleGrid = [
    [0,1],
    [0,0]]
Output: 1
*/

fun uniquePathsWithObstacles(obstacleGrid:Array<IntArray>): Int {
    val row = IntArray(obstacleGrid[0].size)
    row[0] = 1 // Initial point
    for(r in obstacleGrid) {
        for(c in r.indices) {
            if(r[c]!=0) row[c] = 0 // sets a row to be 0 if there is no obstacle in that column.
            else if(c > 0) row[c] += row[c-1] // if there is an obstacle, it checks if the current row is equal to the prev. one.
        }
    }
    return row.last()
}