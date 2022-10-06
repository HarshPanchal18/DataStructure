#include <iostream>
#include <vector>
using namespace std;

/*

Given the root of a binary tree and two integers val and depth, add a row of nodes with value val at the given depth depth.
Note that the root node is at depth 1.

The adding rule is:

Given the integer depth, for each not NULL tree node cur at the depth depth - 1,
create two tree nodes with value val as cur's left subtree root and right subtree root.
cur's original left subtree should be the left subtree of the new left subtree root.
cur's original right subtree should be the right subtree of the new right subtree root.
If depth == 1 that means there is no depth depth - 1 at all,
then create a tree node with value val as the new root of the whole original tree,
and the original tree is the new root's left subtree.


Example 1:
Input: root = [4,2,6,3,1,5], val = 1, depth = 2
Output: [4,1,1,2,NULL,NULL,6,3,1,5]

Example 2:

        4                   4
    2       =>          2
3       1           *1*   *1*
                  3           1

Input: root = [4,2,NULL,3,1], val = 1, depth = 3
Output: [4,2,NULL,1,1,3,NULL,NULL,1]
*/

// * Definition for a binary tree node.
struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class Solution
{
public:
    TreeNode *addOneRow(TreeNode *root, int val, int depth)
    {
        if (!root)
            return NULL;

        if (depth == 1)
        { // if depth = 1, make a new head and its left is old root, return;
            TreeNode *newNode = new TreeNode(val, root, NULL);
            return newNode;
        }
        TreeNode *temp = root;
        add(temp, val, depth - 1);
        return root;
    }
};

void add(TreeNode *root, int value, int depth)
{
    if (depth == 1)
    { // when depth = 1, add Node for val both left and right
        TreeNode *right = new TreeNode(value, root->left, NULL);
        root->left = right;
        TreeNode *left = new TreeNode(value, NULL, root->right);
        root->right = left;
    }
    if (root->left)
        add(root->left, value, depth - 1);

    if (root->right)
        add(root->right, value, depth - 1);
}

class Solution2
{
public:
    void helper(TreeNode *root, int val, int depth, int currDepth)
    {
        if (root == NULL)
            return;
        if (currDepth == depth - 1)
        {
            TreeNode *l = root->left;
            TreeNode *r = root->right;
            root->left = new TreeNode(val);
            root->right = new TreeNode(val);
            root->left->left = l;
            root->right->right = r;
            return;
        }
        helper(root->left, val, depth, currDepth + 1);
        helper(root->right, val, depth, currDepth + 1);
    }

    TreeNode *addOneRow(TreeNode *root, int val, int depth)
    {
        TreeNode *node;
        if (depth == 1)
        {
            node = new TreeNode(val);
            node->left = root;
        }
        else
            helper(root, val, depth, 1);

        return depth == 1 ? node : root;
    }
};

class Solution3
{
public:
    void insert(TreeNode *root, int val, int depth, int i) // taken i pointer to count the current level or depth of the tree
    {
        if (!root)
            return; // base cases
        if (i == depth - 1)
        {
            TreeNode *leftstore = root->left; // remove the left node of root
            root->left = new TreeNode(val);   // create new node of val and add to left of the root
            root->left->left = leftstore;     // add the leftstore node to the left of new  node
            // similar approach for right subtree;
            TreeNode *rightstore = root->right;
            root->right = new TreeNode(val);
            root->right->right = rightstore;
        }
        else
        {
            insert(root->left, val, depth, i + 1);
            insert(root->right, val, depth, i + 1);
        }
        return;
    }
    TreeNode *addOneRow(TreeNode *root, int val, int depth)
    {
        if (depth == 1)
        {
            TreeNode *temp = new TreeNode(val); // make new node of val
            temp->left = root;                  // connect the root to the left of the temp
            return temp;
        }
        insert(root, val, depth, 1);
        return root;
    }
};
