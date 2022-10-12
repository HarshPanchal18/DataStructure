#include <stdio.h>

// * Definition for a Node.
struct Node
{
    int val;
    struct Node *left;
    struct Node *right;
    struct Node *next;
};

// Recursive
struct Node *connect(struct Node *root)
{
    if (!root)
        return NULL; // validate

    /* Find the next parent with a child */
    while (root && !root->left && !root->right)
        root = root->next;
    return root ? (root->left ? root->left : root->right) : NULL;
}
void connect(struct Node *root)
{
    /* Validate node */
    if (!root)
        return;

    /* Point left child to right : Best case */
    if (root->left && root->right)
        root->left->next = root->right;

    /* If find the next node by traversing the siblings of the parent. */
    else if (root->left)
        root->left->next = GetNext(root->next);

    /* If right child is valid, then traverse parent's siblings to
    find the next pointer */
    if (root->right)
        root->right->next = GetNext(root->next);

    /* First going right will populate "next" pointers */
    connect(root->right);
    connect(root->left);
}
