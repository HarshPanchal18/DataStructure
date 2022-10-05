#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

/*
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

class Solution
{
public:
    vector<int> searchRange(vector<int> &nums, int target)
    {

        /*if(nums.size()==1&&nums[0]==target){
            res.push_back(0);
            res.push_back(0);
            return res;
        }
        if(nums.size()==1){
            res.push_back(-1);
            res.push_back(-1);
            return res;
        }
        int cnt=0;
        for(int i=0;i<nums.size();i++)
            if(nums[i]==target)
            {
                res.push_back(i);
                cnt++;
                if(cnt==2)
                    return res;
                continue;
            }
            res.push_back(-1);
            res.push_back(-1);*/

        vector<int> res(2);
        for (int i = 0; i < 2; i++)
            res[i] = -1;

        for (int i = 0; i < nums.size(); i++)
            if (nums[i] == target)
                for (int j = 0; j < 2; j++)
                    if (res[j] == -1)
                        res[j] = i;
                    else
                        res[1] = i;
        return res;
    }

    // first occurance
    int firstOccur(int size, vector<int> &nums, int target)
    {
        int res = -1;
        int start = 0;
        int end = size - 1;

        while (start <= end)
        {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target)
            {
                res = mid;
                end = mid - 1;
            }
            else if (target < nums[mid])
                end = mid - 1;
            else
                start = mid + 1;
        }
        return res;
    }

    // last occurance
    int lastOccur(int size, vector<int> &nums, int target)
    {
        int res = -1;
        int start = 0;
        int end = size - 1;

        while (start <= end)
        {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target)
            {
                res = mid;
                start = mid + 1;
            }
            else if (target < nums[mid])
                end = mid - 1;
            else
                start = mid + 1;
        }
        return res;
    }

    vector<int> searchRange2(vector<int> &nums, int target)
    {
        vector<int> ans;
        int size = nums.size();
        int f = firstOccur(size, nums, target);
        int l = lastOccur(size, nums, target);

        ans.push_back(f);
        ans.push_back(l);

        return ans;
    }

    vector<int> searchRange3(vector<int> &nums, int target)
    {
        // approach:
        // 2 binary search for 1st half of mid and 2nd binary search for 2nd half and
        // basically we intialize our 1st mid as some variable and compare the variable
        // in 1st half binary search and 2nd half binary search seprately
        vector<int> ans(2, -1);
        int low = 0;
        int high = nums.size() - 1;

        // 1st half
        while (low <= high)
        {
            int mid = (high + low) / 2;

            if (nums[mid] == target)
            {
                ans[0] = mid;
                high = mid - 1;
            }
            else if (nums[mid] < target)
                low = mid + 1;
            else
                high = mid - 1;
        }

        low = 0;
        high = nums.size() - 1;

        // 2nd half
        while (low <= high)
        {
            int mid = (high + low) / 2;

            if (nums[mid] == target)
            {
                ans[1] = mid;
                low = mid + 1;
            }
            else if (nums[mid] < target)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return ans;
    }

    vector<int> searchRange4(vector<int> &nums, int target)
    {
        vector<int> v;
        for (int i = 0; i < nums.size(); i++)
            if (nums[i] == target)
                v.push_back(i);

        vector<int> v1;
        if (v.size() != 0)
        {
            v1.push_back(v[0]);
            v1.push_back(v[v.size() - 1]);
        }
        else
        {
            v1.push_back(-1);
            v1.push_back(-1);
        }

        return v1;
    }

    vector<int> searchRange5(vector<int> &nums, int target)
    {
        for (size_t start = 0, size = nums.size(); start < size;)
        {
            size_t mid = start + (size - start) / 2;

            int v = nums[mid];
            if (v > target)
                size = mid;
            else if (v < target)
                start = mid + 1;
            else
            {
                auto right = mid;
                while (right + 1 < size && nums[right + 1] == target)
                    ++right;
                while (mid && nums[mid - 1] == target)
                    --mid;

                vector<int> ret(2);
                ret[0] = mid;
                ret[1] = right;
                return ret;
            }
        }
        return {-1, -1};
    }
};
