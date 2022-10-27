#include <bits/stdc++.h>
using namespace std;

/*
A flock of sheep is running east along a straight, narrow road.
Each one is running at its own constant speed.
Whenever a sheep catches up to the one in front of it, it has to slow down and follow at the speed of the other sheep.
You are in a mobile crane behind the flock, chasing the sheep towards the barn at the end of the road.
The arm of the crane allows you to pick up any sheep momentarily, let the sheep behind it pass underneath,
 and place the picked-up sheep back down.
This operation takes no time and can only be performed on a pair of sheep that are immediately next to each other,
 even if 3 or more sheep are in a row, one after the other.

Given the initial locations (Xi) at time 0 and natural speeds (Vi) of the sheep, as well as the location of the barn (B),
 what is the minimum number of swaps you need to perform with your crane in order to
 have at least K of the N sheep arrive at the barn no later than time T?

You may think of the sheep as points moving along a line.
Even if 3 or more sheep are at the same location, next to each other,
picking up one of them will only let one of the other two pass through.
Any swap is instantaneous, which means that you may perform multiple swaps at the same time,
 but each one will count as a separate swap.

Example 1:
Input:N=5 k=3 B=10 T=5
X[]={0,2,5 ,6, 7}
V[]={1,1,1,1,4}
Output: 0
Explanation: Sheep 5,4 and 3 will reach the barn before or at 5 secs.

Example 2:
Input:N=5 K=3 B=10 T=5
X[]={0,2,3,4,7}
V[]={2,1,1,1,4}
Output: -1
Explanation: At max 2 sheep can reach the barn within or at 5 secs so the answer is -1.
*/

class Solution
{
public:
    int minimumSwaps(int c[], int v[], int n, int k, int b, int t)
    {
        int slow = 0;
        int nSwaps = 0;
        for (int i = n - 1; i >= 0; i--)
        {
            int pos = (v[i] * t) + c[i];
            if (b <= pos)
            {
                k--;
                nSwaps += slow;
                if (!k)
                    return nSwaps;
            }
            else
                slow++;
        }
        return -1;
    }
};

int main()
{
    int n, k, b, T;
    cin >> n >> k >> b >> T;
    int c[n];
    int v[n];

    for (auto &j : c)
        cin >> j;

    for (auto &j : v)
        cin >> j;

    Solution s;
    int ans = s.minimumSwaps(c, v, n, k, b, T);
    cout << ans << endl;
    return 0;
}
