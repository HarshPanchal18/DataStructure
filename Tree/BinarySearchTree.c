#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

/*
Binary Search Tree: A binary tree in which for each node, value of all the nodes in left-subtree is lesser or equal
                        and value of all the nodes in right-subtree is greater.
*/

/*Tree Structure

           |  ROOT  |
            /      \
      |<=ROOT|   |>ROOT|
*/

struct Node
{
    int data;
    struct Node *left;
    struct Node *right;
};

struct Node *GetNewNode(int data)
{
    struct Node *newNode = (struct Node *)malloc(sizeof(struct Node));
    newNode->data = data;
    newNode->left = newNode->right = NULL;
    return newNode;
}

// void Insert(struct Node *root, int data)
struct Node *Insert(struct Node *root, int data)
{
    if (root == NULL)
    {
        root = GetNewNode(data);
        return root;
    }
    else if (data <= root->data)
        root->left = Insert(root->left, data);
    else
        root->right = Insert(root->right, data);

    return root;
}

void _FreeTree(struct Node *root)
{
    if (root->left)
    {
        _FreeTree(root->left);
        free(root->left);
    }

    if (root->right)
    {
        _FreeTree(root->right);
        free(root->right);
    }
}

void DelTree(struct Node **root)
{
    if (*root)
    {
        _FreeTree(*root);
        free(*root);
    }
}

void DeleteTree(struct Node *root)
{
    if (root == NULL)
        return;

    DeleteTree(root->left);
    DeleteTree(root->right);

    printf("\nDeleting Node...%d", root->data);
    free(root);

    printf("\n");
}

bool Search(struct Node *root, int data)
{
    if (root == NULL)
        return false;

    else if (root->data == data)
        return true;
    else if (data <= root->data)
        return Search(root->left, data);
    else
        return Search(root->right, data);
}

int min(struct Node *root)
{
    if (root == NULL)
    {
        printf("Tree is empty");
        return -1;
    }

    while (root->left != NULL) // root of the argument
        root = root->left;

    return root->data;
}

int RecMin(struct Node *root)
{
    if (root == NULL)
    {
        printf("Tree is empty");
        return -1;
    }
    else if (root->left == NULL)
        return root->data;

    RecMin(root->left);
}

int max(struct Node *root)
{
    if (root == NULL)
    {
        printf("Tree is empty");
        return -1;
    }

    while (root->right != NULL) // root of the argument
        root = root->right;

    return root->data;
}

int RecMax(struct Node *root)
{
    if (root == NULL)
    {
        printf("Tree is empty");
        return -1;
    }
    else if (root->right == NULL)
        return root->data;

    RecMax(root->right);
}

int Maxx(int x, int y)
{
    return x >= y ? x : y;
}

int FindHeight(struct Node *root)
{
    // Height of tree(root) = Number of edges in longest path from root to leaf-node. (measured from leaf-node)
    if (root == NULL)
        return -1;

    return Maxx(FindHeight(root->left), FindHeight(root->right)) + 1;
}

int FindDepth(struct Node *root)
{
    // Depth of tree(root) = Number of edges in longest path from root to leaf-node. (measured from leaf-node)
    if (root == NULL)
        return -1;

    return Maxx(FindDepth(root->left), FindDepth(root->right)) + 1;
}

struct Node *FindMin(struct Node *root)
{
    if (root == NULL)
        return NULL;
    while (root->left != NULL)
        root = root->left;
    return root;
}

/* Tree Traversal = Process of Visiting/Reading/Processing each node in the tree exactly once in same order.

1. Breadth-First
    -> Level order - Access node level by level

2. Depth-First

    ==> Access orders of the node(s)
    -> PreOrder  = <root> --> <left>  --> <right>
    -> InOrder   = <left> --> <root>  --> <right>
    -> PostOrder = <left> --> <right> --> <root>

*/

void CurrLevel(struct Node *root, int level)
{
    if (!root)
        return;

    if (level == 0)
        printf("%d\t", root->data);

    else
    {
        CurrLevel(root->left, level - 1);
        CurrLevel(root->right, level - 1);
    }
}

void LevelOrder(struct Node *root)
{

    int h = FindHeight(root);

    for (int i = 0; i <= h; i++)
    {
        printf("level %d : ", i + 1);
        CurrLevel(root, i);
        printf("\n");
    }
    printf("\n");
}

void PreOrder(struct Node *root)
{
    if (root == NULL)
        return;

    printf("%2d ", root->data);
    PreOrder(root->left);
    PreOrder(root->right);
}

void InOrder(struct Node *root)
{
    if (root == NULL)
        return;

    InOrder(root->left);
    printf("%2d ", root->data);
    InOrder(root->right);
}

void PostOrder(struct Node *root)
{
    if (root == NULL)
        return;

    PostOrder(root->left);
    PostOrder(root->right);
    printf("%2d ", root->data);
}
