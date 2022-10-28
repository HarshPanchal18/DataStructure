#include <vector>
#include <algorithm>
using namespace std;

/*
* Can Make Arithmetic Progression From Sequence

A sequence of numbers is called an arithmetic progression if the difference between any two consecutive elements
is the same.

Given an array of numbers arr, return true if the array can be rearranged to form an arithmetic progression.
Otherwise, return false.

Example 1:
Input: arr = [3,5,1]
Output: true
Explanation: We can reorder the elements as [1,3,5] or [5,3,1] with differences 2 and -2 respectively,
between each consecutive elements.

Example 2:
Input: arr = [1,2,4]
Output: false
Explanation: There is no way to reorder the elements to obtain an arithmetic progression.
*/

class Solution
{
public:
    bool canMakeArithmeticProgression(vector<int> &arr)
    {
        sort(arr.begin(), arr.end());
        int diff{arr[1] - arr[0]};
        for (int i = 1; i < arr.size() - 1; ++i)
        {
            if (arr[i + 1] - arr[i] == diff)
                continue;
            else
                return false;
        }

        return true;
    }

    //
    bool canMakeArithmeticProgression(vector<int> &arr)
    {
        int n = arr.size(), mini = INT_MAX, maxi = INT_MIN;
        for (auto &it : arr)
            mini = min(mini, it), maxi = max(maxi, it);

        if ((maxi - mini) % (n - 1) != 0)
            return false;

        int diff = (maxi - mini) / (n - 1);
        int i = 0;

        while (i < n)
        {
            if (arr[i] == mini + i * diff)
                i++;
            else if ((arr[i] - mini) % diff != 0)
                return false;
            else
            {
                int pos = (arr[i] - mini) / diff;

                if (arr[pos] == arr[i])
                    return false;

                swap(arr[i], arr[pos]);
            }
        }
        return true;
    }
    
    //
    bool canMakeArithmeticProgression(vector<int> &arr)
    {
        sort(arr.begin(), arr.end());

        int dis = arr[1] - arr[0];

        for (int i = 0; i < arr.size() - 1; i++)
            if (arr[i + 1] - arr[i] != dis)
                return false;

        return true;
    }
};
