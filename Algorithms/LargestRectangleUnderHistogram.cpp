#include <bits/stdc++.h>
using namespace std;

// https://www.geeksforgeeks.org/largest-rectangle-under-histogram/

void largestArea(int height[], int n)
{
    stack<int> s;
    int ans = 0;
    int l = 0, r = 0;

    for (int i = 0; i < n; i++)
    {
        if (s.empty()) // If stack is empty, push the bar
        {
            s.push(i);
            continue;
        }

        if (height[i] > height[s.top()]) // If height[i] is greater then push the bar
            s.push(i);

        else
        {
            // Pop out all bars whose height is greater than height[i],
            // Simulataneously calculate the new area
            while (!s.empty() && height[i] < height[s.top()])
            {
                int toberemoved = s.top();
                s.pop();
                int area;

                if (s.empty())
                    area = height[toberemoved] * i;
                else
                    area = height[toberemoved] * (i - s.top() - 1);

                if (area > ans)
                {
                    ans = area;
                    l = (!s.empty()) ? s.top() : 0;
                    r = i;
                }
                ans = max(ans, area);
            }
            s.push(i);
        }
    } // for loop
    cout << "Largest Area = " << ans << " between bars " << l << " and " << r << " Indexes";
}

int main()
{
    cout << "Enter number of bars: ";
    int n;
    cin >> n;

    cout << "Enter height of each bar: ";
    int height[n];
    for (int i = 0; i < n; i++)
        cin >> height[i];

    largestArea(height, n);
}

/*
Enter number of bars: 7
Enter height of each bar: 6 2 5 4 5 1 6
Largest area = 12 between bars 1 and 5 Indexes

7 |
6 | 6               6
5 |     5     5
4 |        4
3 |
2 |   2
1 |_______________1___
0 |0  1 2  3  4   5 6
        |-----|
*/
