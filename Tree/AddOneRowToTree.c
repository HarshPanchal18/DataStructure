#include <stdio.h>

/*
 * 623. Add One Row to Tree
 *
 * Given the root of a binary tree and two integers val and depth, add a row of nodes with value val at the given depth depth.
 *
 * Note that the root node is at depth 1.
 *
 * The adding rule is:
 * Given the integer depth, for each not null tree node cur at the depth depth - 1, create two tree nodes
 * with value val as cur's left subtree root and right subtree root.
 *
 * cur's original left subtree should be the left subtree of the new left subtree root.
 * cur's original right subtree should be the right subtree of the new right subtree root.
 * If depth == 1 that means there is no depth depth - 1 at all, then create a tree node with value val as the
 * new root of the whole original tree, and the original tree is the new root's left subtree.
 *
 */

/*
 * Algorithm:
 *  tree =          1,      depth = 1, value = 1,
 *  =>  tree =
 *                  1
 *              1
 *
 *
 *  tree =          1,      depth = 2, value = 1,
 *              2       3
 *             4 5     6 7
 *
 *  => tree =
 *                  1
 *              1       1
 *          2               3
 *         4 5             6 7
 *
 *  tree =          1,      depth = 4, value = 5
 *              2       3
 *          4
 *
 *  => tree =
 *                  1,
 *              2       3
 *          4
 *
 *  tree =          4,      depth = 3, value = 1
 *              2       6
 *            3   1   5
 *
 *                  4
 *              2       6
 *            1   1    1    x
 *           3 x x 1  5 x
 *
 *  DFS traverse?
 */

// * Definition for a binary tree node.
struct TreeNode
{
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

void addNode(struct TreeNode *root, int val, int depth, int curr)
{
    struct TreeNode *node, *left, *right;

    if (root == NULL)
        return;

    /* Check if current depth matches the target */
    if (curr + 1 == depth)
    {
        /* Store the addresses of original left and right node */
        left = root->left;
        right = root->right;

        /* Insert new left node ans assign the original left node */
        root->left = (struct TreeNode *)malloc(sizeof(struct TreeNode));
        root->left->val = val;
        root->left->left = left;
        root->left->right = NULL;

        /* Insert new right node ans assign the original right node */
        root->right = (struct TreeNode *)malloc(sizeof(struct TreeNode));
        root->right->val = val;
        root->right->left = NULL;
        root->right->right = right;
    }
    /* Continue to visit child node before reaching the target depth */
    else
    {
        addNode(root->left, val, depth, curr + 1);
        addNode(root->right, val, depth, curr + 1);
    }
}

struct TreeNode *addOneRow(struct TreeNode *root, int val, int depth)
{
    /*
     * Input:
     *  root : root of binary tree
     *  val  : value of new node
     *  depth: the depth of new node
     */

    struct TreeNode *node;

    /* Insert new node in root position */
    if (depth == 1)
    {
        node = (struct TreeNode *)malloc(sizeof(struct TreeNode));
        node->val = val;
        node->left = root;
        node->right = NULL;

        return node;
    }
    else
    {
        addNode(root, val, depth, 1);
        return root;
    }

     // * Output: return the binary tree contains new nodes
}
