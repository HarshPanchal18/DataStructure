#include <bits/stdc++.h>
using namespace std;

/*
* Ball Coloring
https://practice.geeksforgeeks.org/problems/ball-coloring3450/

Given n balls. All of them are initially uncolored.
You have to color the balls with two colors RED and BLUE
such that there can be atmost 2 positions where a RED ball is touching BLUE ball or vice versa.
You have to color all the balls.Find the number of ways in which balls can be colored.

Example 1:
Input: n = 1
Output: 2
Explanation: Possible ways to color are {{R},{B}}.So the answer is 2 .

Example 2:
Input: n = 2
Output: 4
Explanation: Possible ways to color are {{RB},{BR},{RR},{BB}}.So the answer is 4.
*/

class Solution
{
public:
    unsigned long long int noOfWays(unsigned long long int n)
    {
        return (n * (n - 1)) + 2;
    }

    unsigned long long int noOfWays2(unsigned long long int n)
    {
        if (n == 1)
            return 2;
        return (n * n) - (n - 2);
        // we reduce 2, because every ball is not collered with only one colour
    }

    unsigned long long int noOfWays3(unsigned long long int n)
    {
        return (2 + (n - 1) * 2 + ((n - 2) * (n - 1)));
    }

    unsigned long long int noOfWays4(unsigned long long int n)
    {
        return 2 * n + (n - 1) * (n - 2);
    }
};

int main()
{
    unsigned long long int n;
    cin >> n;
    Solution ob;
    cout << ob.noOfWays(n) << endl;
    return 0;
}
