#include <iostream>
#include <vector>

/*
Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Example 2:
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
*/

using namespace std;

class Solution
{
public:
    void rotate(vector<int> &nums, int k)
    {
        int n = nums.size();
        vector<int> temp(n);

        for (int i = 0; i < n; i++)
            temp[(i + k) % n] = nums[i];

        nums = temp;
    }
};

int main(void)
{
    int n;
    cout << "Enter the number of elements: ";
    cin >> n;

    vector<int> arr(n);
    for (int i = 0; i < n; i++)
        cin >> arr[i];

    int k;
    cout << "Enter the number of rotations: ";
    cin >> k;
    Solution obj;
    obj.rotate(arr, k);

    for (int i = 0; i < n; i++)
        cout << arr[i] << " ";
}

/*
Enter the number of elements: 5
9 7 4 2 1
Enter the number of rotations: 2
2 1 9 7 4
*/
