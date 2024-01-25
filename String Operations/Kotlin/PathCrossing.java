/*
* Path Crossing
Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing moving one unit north, south, east, or west, respectively.
You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.
Return true if the path crosses itself at any point, that is, if at any time you are on a location you have previously visited. Return false otherwise.

Example 1:
Input: path = "NES"
Output: false
Explanation: Notice that the path doesn't cross any point more than once.

Example 2:
Input: path = "NESWW"
Output: true
Explanation: Notice that the path visits the origin twice.

Constraints:
1 <= path.length <= 104
path[i] is either 'N', 'S', 'E', or 'W'.

*/

class PathCrossing {

    public boolean isPathCrossing(String path) {
        int n = path.length();
        int x = 0;
        int y = 0;

        for (int i = 0; i < n; i++) {
            int tempX = x;
            int tempY = y;

            for (int j = i; j < n; j++) {
                char ch = path.charAt(j);
                if (ch == 'N')
                    y++;
                else if (ch == 'S')
                    y--;
                else if (ch == 'W')
                    x--;
                else
                    x++;

                if (x == tempX && y == tempY)
                    return true;
            }
        }
        return false;
    }

}