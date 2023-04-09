#include <iostream>
#include <vector>

using namespace std;

bool check(vector<int> &nums)
{
    int len = nums.size();

    // deal with the rotated case - the first element is greater than last one
    bool rotate = (nums[0] >= nums[len - 1]);

    for (int i = 0; i < len - 1; i++)
    {
        if (nums[i] <= nums[i + 1])
            continue;

        if (rotate)
        {
            rotate = false;
            continue;
        }
        return false;
    }
    return true;
}

int main(void)
{
    int n;
    cin >> n;

    vector<int> v(n);
    for (int i = 0; i < n; i++)
        cin >> v[i];

    cout << check(v);
}

/*
 * Given an array nums, return true if the array was originally sorted in non-decreasing order, then
 * rotated some number of positions (including zero). Otherwise, return false.

 * There may be duplicates in the original array.

 * Note: An array A rotated by x positions results in an array B of the same length such that A[i] ==
 * B[(i+x) % A.length], where % is the modulo operation.

5
3 4 5 1 2
1
* [1,2,3,4,5] is the original sorted array.
* You can rotate the array by x = 3 positions to begin on the the element of value 3: [3,4,5,1,2].

4
2 1 3 4
0
* There is no sorted array once rotated that can make nums.
*/
