#include <vector>
using namespace std;

/*
N-ary Tree Preorder Traversal

Given the root of an n-ary tree, return the preorder traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal.
Each group of children is separated by the null value (See examples)

Example 1:
Input: root = [1,null,3,2,4,null,5,6]
Output: [1,3,5,6,2,4]

Example 2:
Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [1,2,3,6,7,11,14,4,8,12,5,9,13,10]
*/

class Node
{
public:
    int val;
    vector<Node *> children;

    Node() {}

    Node(int _val)
    {
        val = _val;
    }

    Node(int _val, vector<Node *> _children)
    {
        val = _val;
        children = _children;
    }
};

class Solution
{
public:
    vector<int> preorder(Node *root)
    {

        // return an empty vector in case of null root, or when there is no node
        if (!root)
            return {};

        // Since this is pre-order traversal of the the tree, we're
        // going to push the root into the result vector first

        vector<int> v;

        // then we would proceed to push the other children nodes

        // we basically iterate through the children list and recursively
        // call the function on the child nodes and the returned
        // vector is added to the current vector

        v.push_back(root->val);
        for (int i = 0; i < root->children.size(); i++)
        {
            vector<int> v1 = preorder(root->children[i]);
            v.insert(v.end(), v1.begin(), v1.end());
        }
        return v;
    }
};

class Solution
{
public:
    void solve(Node *root, vector<int> &ans)
    {
        ans.emplace_back(root->val);
        for (int i = 0; i < root->children.size(); i++)
            solve(root->children[i], ans);
    }

    vector<int> preorder(Node *root)
    {
        vector<int> ans;
        if (!root)
            return ans;

        solve(root, ans);
        return ans;
    }
};
