#include <vector>
#include <iostream>

/*
* Find Minimum in Rotated Sorted Array
Suppose an array of length n sorted in ascending order is rotated between 1 and n times.

For example, the array nums = [0,1,2,4,5,6,7] might become:
[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements, return the minimum element of this array.

You must write an algorithm that runs in O(log n) time.

Example 1:
Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.

Example 2:
Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.

Example 3:
Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
*/

using namespace std;
int findMin(vector<int> &nums)
{
    if (nums.size() == 1 || nums[0] < nums.back())
        return nums[0];

    int low = 0, high = nums.size() - 1, res = INT_MAX;
    while (low <= high)
    {
        int mid = (high + low) / 2;
        if (nums[mid] >= nums[0])
            low = mid + 1;
        else
        {
            res = min(res, nums[mid]);
            high = mid - 1;
        }
    }
    return res;
}

int findMin2(vector<int> &nums)
{
    int n = nums.size();
    int s = 0, e = n - 1, mid = 0;

    while (s <= e)
    {
        mid = s + (e - s) / 2;
        // to avoid the out of bound index error when comparing with next and prev element
        int next = (mid + 1) % n, prev = (mid + n - 1) % n;

        // if mid is smallest element then return
        if (nums[mid] <= nums[prev] && nums[mid] <= nums[next])
            return nums[mid];

        // if left half is sorted then search in right half
        else if (nums[0] <= nums[mid])
            s = mid + 1;

        // else right half is sorted then search in left half
        else if (nums[mid] <= nums[n - 1])
            e = mid - 1;

        cout << s << " " << mid << " " << e << endl;
    }
    // when the whole array is sorted then mid wil be pointing to last element so next will be minimum
    return nums[(mid + 1) % n];
}

// Brute force
int findMin3(vector<int> &nums)
{
    int mini = INT_MAX;

    for (int i = 0; i < nums.size(); i++)
        mini = min(nums[i], mini);

    return mini;
}

int findMin4(vector<int> &nums)
{
    int mini = nums[0];

    for (auto v : nums)
        if (v < mini)
            mini = v;

    return mini;
}

int findMin5(vector<int> &nums)
{
    int mid = (0 + nums.size()) / 2;
    int mini = nums[mid];

    if (nums[0] < nums[nums.size() - 1])
        return nums[0];
    if (nums.size() == 2)
        return min(nums[0], nums[1]);

    while (1)
    {
        if (mid == 0 || mid == nums.size() - 1 || nums[mid] < nums[mid - 1] && nums[mid] < nums[mid + 1])
            return nums[mid];
        else if (nums[mid] > nums[0])
            mid++;
        else if (nums[mid] > nums[mid - 1])
            mid--;
    }
}

int findMin6(vector<int> &nums)
{
    int i = 0;
    int j = nums.size() - 1;
    int mid = 0;
    while (i < j)
    {
        mid = i + (j - i) / 2;
        if (nums[i] <= nums[mid])
        {
            if (nums[i] <= nums[mid] && nums[mid] <= nums[j])
                j = mid - 1;
            else
                i = mid + 1;
        }
        else
            i++;
    }
    return nums[i];
}
