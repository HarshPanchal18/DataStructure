#include <vector>
#include <unordered_map>
#include <stack>
using namespace std;

/*
* Next Greater Element I

The next greater element of some element x in an array is the first greater element that is to the right of x
in the same array.

You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.

For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j]
    and determine the next greater element of nums2[j] in nums2.
If there is no next greater element, then the answer for this query is -1.

Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
Output: [-1,3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
- 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
- 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.

Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4]
Output: [3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
- 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
*/

class Solution
{
public:
    vector<int> nextGreaterElement(vector<int> &n1, vector<int> &n2)
    {
        unordered_map<int, int> m;
        stack<int> st;
        st.push(-1);

        for (int i = n2.size() - 1; i >= 0; i--)
        {
            while (st.top() != -1 && n2[i] > st.top())
                st.pop();

            m[n2[i]] = st.top();
            st.push(n2[i]);
        }
        for (int i = 0; i < n1.size(); i++)
            n1[i] = m[n1[i]];

        return n1;
    }
};

class Solution
{
public:
    vector<int> nextGreaterElement(vector<int> &n1, vector<int> &n2)
    {
        // store index of num2 element corresponding to n1[i];
        for (int i = 0; i < n1.size(); i++)
            for (int j = 0; j < n2.size(); j++)
                if (n1[i] == n2[j])
                {
                    n1[i] = j;
                    break;
                }

        // store next greater element in nums2
        stack<int> st;
        st.push(-1);

        for (int i = n2.size() - 1; i >= 0; i--)
        {
            while ((st.top() != -1) && (st.top() <= n2[i]))
                st.pop();

            int top = st.top();
            st.push(n2[i]);
            n2[i] = top;
        }

        for (int i = 0; i < n1.size(); i++)
            n1[i] = n2[n1[i]];

        return n1;
    }
};

class Solution
{
public:
    vector<int> nextGreaterElement(vector<int> &n1, vector<int> &n2)
    {
        unordered_map<int, int> m;
        stack<int> st;

        for (int i = n2.size() - 1; i >= 0; i--)
        {
            while (!st.empty() && n2[i] > n2[st.top()])
                st.pop();

            if (st.empty())
                m[n2[i]] = -1;
            else
                m[n2[i]] = n2[st.top()];

            st.push(i);
        }

        for (int i = 0; i < n1.size(); i++)
            n1[i] = m[n1[i]];

        return n1;
    }
};

class Solution
{
public:
    // Brute Force
    vector<int> nextGreaterElement(vector<int> &nums1, vector<int> &nums2)
    {
        vector<int> res;
        for (int i = 0; i < nums1.size(); i++)
            for (int j = 0; j < nums2.size(); j++)
                if (nums1[i] == nums2[j])
                {
                    int max_val = -1;
                    for (int k = j; k < nums2.size(); k++)
                    {
                        if (nums2[k] > nums2[j])
                        {
                            max_val = nums2[k];
                            break;
                        }
                    }
                    res.push_back(max_val);
                }

        return res;
    }

    // Optimized
    vector<int> nextGreaterElement(vector<int> &nums1, vector<int> &nums2)
    {
        int n = nums2.size();
        vector<int> nxtgrt(n, 0);
        nxtgrt[n - 1] = -1;

        // calculating postfix next greator
        for (int i = n - 2; i >= 0; i--)
        {
            if (nums2[i + 1] > nums2[i])
                nxtgrt[i] = nums2[i + 1];
            else
            {
                for (int j = i + 1; j <= nums2.size() - 1; j++)
                {
                    nxtgrt[i] = -1;
                    if (nxtgrt[j] > nums2[i])
                    {
                        nxtgrt[i] = nxtgrt[j];
                        break;
                    }
                }
            }
        }

        // matching and putting value in output vector
        vector<int> out;
        for (int i = 0; i < nums1.size(); i++)
            for (int j = 0; j < nums2.size(); j++)
                if (nums1[i] == nums2[j])
                {
                    out.push_back(nxtgrt[j]);
                    break;
                }

        return out;
    }

    // Optimal
    vector<int> nextGreaterElement(vector<int> &nums1, vector<int> &nums2)
    {

        stack<int> st;
        unordered_map<int, int> mp; // we will be using map as nextgrt array

        for (int i = nums2.size() - 1; i >= 0; i--)
        {
            while (!st.empty() && st.top() <= nums2[i])
                st.pop();

            if (st.empty())
                mp[nums2[i]] = -1;
            else
                mp[nums2[i]] = st.top();

            st.push(nums2[i]);
        }
        // matching and putting value in output vector
        vector<int> out;
        for (int i = 0; i < nums1.size(); i++)
            out.push_back(mp[nums1[i]]);

        return out;
    }
};
