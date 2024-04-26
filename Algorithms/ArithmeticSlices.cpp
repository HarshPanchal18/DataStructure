#include <vector>
using namespace std;

/*
* Arithmetic Slices

An integer array is called arithmetic if it consists of at least three elements
and if the difference between any two consecutive elements is the same.

For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.

Given an integer array nums, return the number of arithmetic subarrays of nums.
A subarray is a contiguous subsequence of the array.

Example 1:
Input: nums = [1,2,3,4]
Output: 3
Explanation: We have 3 arithmetic slices in nums: [1, 2, 3], [2, 3, 4] and [1,2,3,4] itself.

Example 2:
Input: nums = [1]
Output: 0
*/

class Solution
{
public:
    int numberOfArithmeticSlices(vector<int> &nums)
    {
        int pairs = 0;
        for (int i = 2, previous = 0; i < nums.size(); i++)
            pairs += (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) ? ++previous : (previous = 0);

        return pairs;
    }
};

class Solution
{
public:
    /*
    The Idea is Simple , the array is called arithmetic if it consists of at least 3 elements.
    So, firstly if arr_size is < 3 , we will return false.
    The second requirement of the question is, that the difference b/w any two consecutive elements should be same.
    so running a loop from i = 0 to nums.size() - 2 as we need at least 2 consecutive elements.
    now we will store the difference of first 2 elements in a diff variable.
    Again, we will traverse a loop to find how many consecutive pairs are with same diff.
    running a inner loop from i + 2 as the outer for loop has calculcated the diff of first 2 elements for us.
    now if the difference of arr[j] - arr[j-1] ,current and previous element is same,
    this means we find a consecutive pair, with same diff, therefore we will increase the count.
    If the diff is not same then we will break the loop, as we need consecutive elements diff to be same.
    Return the Count.
    */
    int numberOfArithmeticSlices(vector<int> &nums)
    {
        // if nums size is less than 3 return false
        if (nums.size() < 3)
            return 0;

        int count = 0, diff;

        for (int i = 0; i < nums.size() - 2; ++i)
        {
            // storing diff of first 2 elements
            diff = nums[i + 1] - nums[i];

            // checking for consecutive elements with same difference.
            for (int j = i + 2; j < nums.size(); ++j)
            {
                // if we find the same diff of next 2 elements
                // this means we find consecutive elements
                // increase the Count
                if (nums[j] - nums[j - 1] == diff)
                    ++count;
                else
                    break; // break as we need to count for consecutive diff elements
            }
        }
        return count;
    }

    // Optimized
    /*
    The Idea is Simple , the array is called arithmetic if it consists of at least 3 elements.

    So, firstly if arr_size is < 3 , we will return false.

    The second requirement of the question is, that the difference b/w any two consecutive elements should be same.
    Storing diff of first 2 elements in diff as prev_diff = nums[1] - nums[0].
    Now iterating a loop from i = 1 to nums.size()-1 and finding the diff of next consecutive elements.
    If the diff is equal to prev_diff, increase the count of idx variable;
    else set idx to zero and update prev_diff with curr diff.

    add idx to count.

    Return count.
    */
    int numberOfArithmeticSlices(vector<int> &nums)
    {
        if (nums.size() < 3)
            return 0;

        int count = 0, diff = 0, idx = 0;
        int prev_diff = nums[1] - nums[0];

        for (int i = 1; i < nums.size() - 1; ++i)
        {
            // curr difference
            int diff = nums[i + 1] - nums[i];

            // if we find same diff of consecutive elements, increase count
            if (diff == prev_diff)
                ++idx;
            else
            {
                // update previous diff with current diff
                // as we don't find consecutive elements with same diff
                prev_diff = diff;
                idx = 0; // make idx to 0
            }
            count += idx; // add cosecutive arithmetic sequence count
        }
        return count;
    }

    // Method 3
    /*
1   2    3    4    5   6  0  1   2    3  4  5
--------                                               |
    ----------                                         |
           ---------                                   |
                ---------                              |_____size 3 , if we get continuous means , then we will get size 4,5.............upto single array of arthemetic
                          ----------                   |                    so if subarray size 3 count  is n, then we will get  n * (n+1)  / 2 arrays
                              ---------                |                   Here there is a break in 6 0 1, so we will make count  = 0, and start again
                                       ---------       |
                                            ---------  |

    */
    int numberOfArithmeticSlices(vector<int> &nums)
    {
        if (nums.size() <= 2)
            return 0;

        int count = 0, ans = 0;
        for (int i = 0; i < nums.size() - 2; i++)
        {
            if (nums[i + 1] - nums[i] == nums[i + 2] - nums[i + 1])
                count++;
            else
            {
                ans += ((count * (count + 1)) / 2);
                count = 0;
            }
        }

        if (count)
            ans += ((count * (count + 1)) / 2);

        return ans;
    }

    // DP
    int numberOfArithmeticSlices(vector<int> &nums)
    {
        int res = 0, n = nums.size();
        if (n <= 2)
            return 0;

        vector<int> dp(n, 0);

        for (int i = 2; i < n; i++)
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2])
            {
                dp[i] = 1 + dp[i - 1];
                res += dp[i];
            }

        return res;
    }

    // Minimalizm
    int numberOfArithmeticSlices(vector<int> &A, int res = 0)
    {
        for (auto i = 2, j = 1; i < A.size(); ++i)
        {
            if (A[i] - A[i - 1] != A[j] - A[j - 1])
                j = i;
            res += i - j;
        }
        return res;
    }

    int getCount(int N)
    {
        if (N < 3)
            return 0;

        return 1 + ((N * N - 3 * N) / 2);
    }

    int numberOfArithmeticSlices(vector<int> &A)
    {
        int N = A.size();
        if (N < 3)
            return 0;

        int diff = A[1] - A[0];
        int count = 2;
        int ans = 0;
        for (int i = 2; i < N; i++)
        {
            if (A[i] - A[i - 1] == diff)
                count++;
            else
            {
                ans += getCount(count);
                diff = A[i] - A[i - 1];
                count = 2;
            }
        }

        ans += getCount(count);
        return ans;
    }

    /*
    Our main motive is that find number of continuous elements with equal mutual difference
    So we will make a vector of mutual difference of given array
    After that we will traverse that diff array and count number of diff same
    ie.diffarr = {2, 2, 2, 2, 2, 4};
    so we will get count = 5. It means there will be 6 elements whose difference count is 5.

    So,
    from these 6 elements we can make total number of subsets are
    subset of size 6 = 1
    subset of size 5 = 2
    subset of size 4 = 3
    subset of size 3 = 4

    So,
    total number of subset = 1 + 2 + 3 + 4 = 10
    From formula let k = 6 - 3 + 1 = 4(minimum size of subset will be 3)
    res = (k * k + 1) / 2 = (4 * 5) / 2 = 10;
    As same we can do for all the unique differences.
    */

    int numberOfArithmeticSlices(vector<int> &A)
    {
        int len = A.size();
        if (len <= 2)
            return 0;

        vector<int> arr(len - 1, 0);
        for (int i = 0; i < len - 1; i++)
            arr[i] = A[i + 1] - A[i];

        int n = 1;
        int res = 0;
        for (int i = 1; i < arr.size(); i++)
        {
            if (arr[i] == arr[i - 1])
                n += 1;

            if (i == arr.size() - 1 || arr[i] != arr[i - 1])
            {
                res += sumHelp(n);
                n = 1;
            }
        }
        return res;
    }

    int sumHelp(int n)
    {
        return n * (n - 1) / 2;
    }
};