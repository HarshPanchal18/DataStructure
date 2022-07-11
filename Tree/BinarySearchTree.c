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

bool IsSubTreeLesser(struct Node *root, int data)
{
    if (root == NULL)
        return true;

    if (root->data <= data && IsSubTreeLesser(root->left, data) && IsSubTreeLesser(root->right, data))
        return true;
    else
        return false;
}

bool IsSubTreeGreater(struct Node *root, int data)
{
    if (root == NULL)
        return true;

    if (root->data > data && IsSubTreeGreater(root->left, data) && IsSubTreeGreater(root->right, data))
        return true;
    else
        return false;
}

bool IsBinary(struct Node *root)
{
    if (root == NULL)
        return true;
    if (IsSubTreeLesser(root->left, root->data) && IsSubTreeGreater(root->right, root->data) && IsBinary(root->left) && IsBinary(root->right))
        return true;
    else
        return false;

    /* You can call only Following function (IsBinarySeacrh()) instead of above lines.
        return IsBinarySearch(root,INT_MAX,INT_MIN);
    */
}

bool IsBinarySearch(struct Node *root, int min, int max)
{

    if (root == NULL)
        return true;

    if (root->data > min && root->data < max && IsBinarySearch(root->left, min, root->data) && IsBinarySearch(root->right, root->data, max))
        return true;
    else
        return false;
}

/*

Deletion of Node:

    Case 1: No leaf-node
    Case 2: One leaf-node
    Case 3: Two leaf-node

    Find Min in Right SubTree -> Copy the value in targetted node -> Delete duplicate from Right SubTree
    OR
    Find Max in Left SubTree  -> Copy the value in targetted node  -> Delete duplicate from Left SubTree

*/

struct Node *Delete(struct Node *root, int data)
{
    if (root == NULL)
        return root;

    else if (data < root->data)
        root->left = Delete(root->left, data);

    else if (data > root->data)
        root->right = Delete(root->right, data);

    else
    {
        // Case 1: No Child
        if (root->left == NULL && root->right == NULL)
        {
            free(root);
            root = NULL;
        }

        // Case 2: One Child
        else if (root->left == NULL)
        {
            struct Node *temp = root; // store the address of the current node to the temp-node you want to delete
            root = root->right;       // move the root to the right
            free(temp);
        }
        else if (root->right == NULL)
        {
            struct Node *temp = root; // store the address of the current node to the temp-node you want to delete
            root = root->left;        // move the root to the left
            free(temp);
        }

        // Case 3: Two Children
        else
        {
            struct Node *temp = RecMin(root->right);
            root->data = temp->data;                       // set the data to the current node
            root->right = Delete(root->right, temp->data); // delete the min. value from the right-tree
        }
    }
    return root;
}

struct Node *Find(struct Node *root, int data)
{
    if (root == NULL)
        return NULL;
    if (root->data == data)
        return root;
    else if (data <= root->data)
        return Find(root->left, data);
    else
        return Find(root->right, data);
}

struct Node *GetSuccessor(struct Node *root, int data)
{
    // Successors  = Leftmost element in a right subtree (Next greater of root->data)
    // Predecessor = Rightmost element in a left subtree (Prev. smaller of root->data)
    // search the node
    struct Node *curr = Find(root, data);
    if (curr == NULL)
        return NULL;

    if (curr->right != NULL)
    {
        /*  Case 1: Node has right subtree
                Go deep the leftmost node in right subtree
                OR
                Find /min. in right subtree     */

        // you can alter this block by applying --->>> return FindMin(root->right);
        struct Node *temp = curr->right;
        while (temp->left != NULL)
            temp = temp->left;
        return temp;
    }
    else
    {
        /*  Case 2: Node has no right subtree
                Go to the nearest ancestor(a node that is at the upper level of the given node) for which given node would be in left subtree*/

        struct Node *Succesor = NULL;
        struct Node *Ancestor = root;

        while (Ancestor != curr)
        {
            if (curr->data < Ancestor->data)
            {
                Succesor = Ancestor; // Deepest node for which current node is in left
                Ancestor = Ancestor->left;
            }
            else
                Ancestor = Ancestor->right;
        }
        return Succesor;
    }
}

// Returns the level of the data in a tree otherwise return -1
int FindLevel(struct Node *root, int data, int level)
{
    if (root == NULL)
        return -1;

    // If key is present at root or in left subtree
    if (root->data == data)
        return level;

    int l = FindLevel(root->left, data, level + 1);
    return (l != -1) ? l : FindLevel(root->right, data, level + 1);
}

int GetLevel(struct Node *root, struct Node *node, int level)
{
    if (root == NULL)
        return 0;

    // If key is present at root or in left subtree
    if (root == node)
        return level;

    int l = GetLevel(root->left, node, level + 1);
    return (l != 0) ? l : GetLevel(root->right, node, level + 1);
}

void PrintGivenLevel(struct Node *root, struct Node *node, int level)
{
    if (root == NULL || level < 2)
        return;

    // If a current node is parent of a node with given level
    if (level == 2)
    {
        if (root->left == node || root->right == node)
            return;
        if (root->left)
            printf("%d ", root->left->data);
        if (root->right)
            printf("%d ", root->right->data);
    }
    else if (level > 2)
    {
        PrintGivenLevel(root->left, node, level - 1);
        PrintGivenLevel(root->right, node, level - 1);
    }
}

void PrintCousin(struct Node *root, struct Node *node)
{
    int level = GetLevel(root, node, 0);
    PrintGivenLevel(root, node, level);
}
