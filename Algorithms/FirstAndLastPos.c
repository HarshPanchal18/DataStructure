#include <stdio.h>

/**
 * Note: The returned array must be malloced, assume caller calls free().
 * Find First and Last Position of Element in Sorted Array
Given an array of integers nums sorted in non-decreasing order,
find the starting and ending position of a given target value.
If target is not found in the array, return [-1, -1].
You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

Example 3:
Input: nums = [], target = 0
Output: [-1,-1]
 */

int *searchRange(int *nums, int numsSize, int target, int *returnSize)
{
    int j = 0;
    if (numsSize == 0)
    {
        int *ans = (int *)malloc(sizeof(int) * 2);
        *returnSize = 2;
        ans[0] = -1;
        ans[1] = -1;
        return ans;
    }
    else if (numsSize == 1)
    {
        int *ans = (int *)malloc(sizeof(int) * 2);
        *returnSize = 2;
        if (target == nums[0])
        {
            ans[0] = 0;
            ans[1] = 0;
            return ans;
        }
        else
        {
            ans[0] = -1;
            ans[1] = -1;
            return ans;
        }
    }
    else
    {
        int *ans = (int *)malloc(sizeof(int) * numsSize);
        *returnSize = numsSize;
        for (int i = 0; i < numsSize; i++)
        {
            if (nums[i] == target)
            {
                ans[j] = i;
                j++;
            }
        }

        if (j == 0)
        {
            int *answer = (int *)malloc(sizeof(int) * 2);
            *returnSize = 2;
            answer[0] = -1;
            answer[1] = -1;
            return answer;
        }
        else if (j == 1)
        {
            int *answer = (int *)malloc(sizeof(int) * 2);
            *returnSize = 2;
            answer[0] = ans[0];
            answer[1] = ans[0];
            return answer;
        }

        int *answer = (int *)malloc(sizeof(int) * 2);
        *returnSize = 2;
        answer[0] = ans[0];
        answer[1] = ans[j - 1];
        return answer;
    }
}
