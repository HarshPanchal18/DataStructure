#include <iostream>
using namespace std;

/*
* Populating Next Right Pointers in Each Node
* https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
* You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.

* The binary tree has the following definition:
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}

* Populate each next pointer to point to its next right node.
* If there is no next right node, the next pointer should be set to NULL.
* Initially, all next pointers are set to NULL.
* Check the given link for more explaination...

* Example 1
Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]

* Example 2:
Input: root = []
Output: []
*/

// Definition for a Node.
class Node
{
public:
    int val;
    Node *left;
    Node *right;
    Node *next;

    Node() : val(0), left(NULL), right(NULL), next(NULL) {}

    Node(int _val) : val(_val), left(NULL), right(NULL), next(NULL) {}

    Node(int _val, Node *_left, Node *_right, Node *_next)
        : val(_val), left(_left), right(_right), next(_next) {}
};

class Solution
{
public:
    Node *connect(Node *root)
    {
        if (!root || !root->left)
            return root;

        root->left->next = root->right;
        root->right->next = (!root->next) ? NULL : root->next->left;

        connect(root->left);
        connect(root->right);

        return root;
    }
};
