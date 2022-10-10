#include <iostream>
#include <vector>
using namespace std;

/*
* Subtree of Another Tree
https://leetcode.com/problems/subtree-of-another-tree/

Given the roots of two binary trees root and subRoot,
return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants.
The tree tree could also be considered as a subtree of itself.

Example 1:
Input: root = [3,4,5,1,2], subRoot = [4,1,2]
Output: true

Example 2:
Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
Output: false
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
    bool find(TreeNode *root, TreeNode *subroot)
    {
        if (root == NULL) //||!subroot)
            return false;

        if (root->val == subroot->val)
            return similar(root, subroot) || find(root->left, subroot) || find(root->right, subroot);

        return find(root->left, subroot) || find(root->right, subroot);
    }

    bool similar(TreeNode *root, TreeNode *subroot)
    {

        if (root == NULL && subroot == NULL)
            return true;

        if (root == NULL || subroot == NULL)
            return false;

        if (root->val != subroot->val)
            return false;

        return similar(root->left, subroot->left) && similar(root->right, subroot->right);
    }

    bool isSubtree(TreeNode *root, TreeNode *subRoot)
    {
        return find(root, subRoot);
    }

    // -----------------DFS
    bool isSubtree(TreeNode *s, TreeNode *t)
    {
        if (!s)
            return !t;
        return isEqual(s, t) || isSubtree(s->left, t) || isSubtree(s->right, t);
    }

    bool isEqual(TreeNode *p, TreeNode *t)
    {
        if (!p || !t)
            return !p && !t;
        return p->val == t->val && isEqual(p->left, t->left) && isEqual(p->right, t->right);
    }

    // ---------------------Recursion
    /*
    Observation: Tree t is a subtree of tree s if and only if any one of the the following 3 cases hold:
    t and s are identical.
    t is a subtree of s->left.
    t is a subtree of s->right.
    */
    bool isSubtree(TreeNode *s, TreeNode *t)
    {
        return sameTree(s, t) || (s && (isSubtree(s->left, t) || isSubtree(s->right, t)));
    }

    // if tree s and t are identical
    bool sameTree(TreeNode *s, TreeNode *t)
    {
        if (s && t)
            return (s->val == t->val) && sameTree(s->left, t->left) && sameTree(s->right, t->right);
        else
            return !s && !t;
    }

    //------------------
    bool isSameTree(TreeNode *original, TreeNode *check)
    {
        if (!original && check || original && !check)
            return false; // If one node exists and the other doesn't, return false
        if (!original && !check)
            return true; // If both nodes don't exist, and we haven't gotten false yet, return true
        if (original->val != check->val)
            return false; // If there are node but their values dont match, return false
        return isSameTree(original->left, check->left) && isSameTree(original->right, check->right);
    } // Check each side

    bool isSubtree(TreeNode *s, TreeNode *t)
    {
        if (!s)
            return false;               // If there is no tree left to check, return false
        bool result = isSameTree(s, t); // At each node test whether it's true.
        if (result)
            return result;                                      // If the result is true, immediately send it up the call chain
        return isSubtree(s->left, t) || isSubtree(s->right, t); // Check the left and right side to see if its a subtree
    }

    //----------------------DFS2
    void subtreeComparator(TreeNode *s, TreeNode *t, bool &areIdentical)
    {
        if (!s && !t || !areIdentical)
            return;
        if (!s || !t || s->val != t->val)
        {
            areIdentical = false;
            return;
        }

        if (s->val == t->val && areIdentical)
        {
            subtreeComparator(s->left, t->left, areIdentical);
            subtreeComparator(s->right, t->right, areIdentical);
        }
    }
    bool isSubtree(TreeNode *s, TreeNode *t)
    {
        if (s->val == t->val)
        {
            bool areIdentical(true);
            subtreeComparator(s, t, areIdentical);
            if (areIdentical)
                return true;
        }
        return (s->left && isSubtree(s->left, t)) || (s->right && isSubtree(s->right, t));
    }

    // By using depth of the second tree
    vector<TreeNode *> nodes;

    bool isSubtree(TreeNode *s, TreeNode *t)
    {
        if (!s && !t)
            return true;
        if (!s || !t)
            return false;

        int depthOfT(getDepth(t, -1));
        getDepth(s, depthOfT); // adds all nodes at depth depthOfT from s to nodes

        for (TreeNode *node : nodes)
            if (isIdentical(node, t))
                return true;

        return false;
    }

    int getDepth(TreeNode *r, int d)
    {
        if (!r)
            return -1;
        int depth(max(getDepth(r->left, d), getDepth(r->right, d)) + 1);

        if (depth == d)
            nodes.push_back(r);

        return depth;
    }

    bool isIdentical(TreeNode *t1, TreeNode *t2)
    {
        if (!t1 && !t2)
            return true;
        if (!t1 || !t2 || t1->val != t2->val)
            return false;
        return isIdentical(t1->left, t2->left) && isIdentical(t1->right, t2->right);
    }

    // tc: O(mn), sc: O(n)
    bool isSubtree(TreeNode *s, TreeNode *t)
    {
        return !t || s && (same(s, t) || isSubtree(s->left, t) || isSubtree(s->right, t));
    }

    bool same(TreeNode *t1, TreeNode *t2)
    {
        return (!t1 || !t2) ? (t1 == t2) : (t1->val == t2->val) && same(t1->left, t2->left) && same(t1->right, t2->right);
    }

    // One liner
    bool Subtree(TreeNode *s, TreeNode *t)
    {
        // If both nodes exist, and their values are the same, check both their left nodes.
        // If one of their children (left or right), doesnt exist (for instance, s->left exists but t->left doesn't)
        // then the subtree isn't the same. If both nodes don't exist, return true, you''ve
        // hit the end of the tree.

        return s && t         ? s->val == t->val ? Subtree(s->left, t->left) &&
                                               Subtree(s->right, t->right)
                                                 : false
               : (!s) && t    ? false
               : s && (!t)    ? false
               : (!s) && (!t) ? true
                              : false;
    }

    bool isSubtree(TreeNode *s, TreeNode *t)
    {
        // If s exists (the base tree), check whether t is a subtree of s. If it isn't at the base of t
        // then start your search from one of the children of t.
        return s ? Subtree(s, t) || isSubtree(s->left, t) || isSubtree(s->right, t) : false;
    }
};
