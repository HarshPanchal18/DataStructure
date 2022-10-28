#include <vector>
#include <algorithm>
using namespace std;

/*
Given an integer array nums, return the largest perimeter of a triangle with a non-zero area,
formed from three of these lengths. If it is impossible to form any triangle of a non-zero area, return 0.

Example 1:
Input: nums = [2,1,2]
Output: 5

Example 2:
Input: nums = [1,2,1]
Output: 0
*/
int largestPerimeter(vector<int> &nums)
{
    sort(nums.begin(), nums.end());

    for (int i = nums.size() - 3; i >= 0; --i)
        if (nums[i] + nums[i + 1] > nums[i + 2])
            return nums[i] + nums[i + 1] + nums[i + 2];

    return 0;
}

int largestPerimeter(vector<int> &nums)
{
    sort(nums.begin(), nums.end()); // sort the vector

    // any tringle sum of smaller two side greater than 3rd side...so we check that condition, a+b>c where a<b<c

    for (int i = nums.size() - 1; i >= 2; i--)
        if (nums[i - 1] + nums[i - 2] > nums[i])          // check the triangle valid condition
            return (nums[i] + nums[i - 1] + nums[i - 2]); // perimeter

    return 0; // return 0
}
