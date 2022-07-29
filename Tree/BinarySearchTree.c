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

struct Node *FindDistUtil(struct Node *root, int no1, int no2, int d1, int d2, int level, int dist)
{
    if (root == NULL)
        return NULL;

    // If either no1 or no2 matches with root's data, report the presence by returning root
    if (root->data == no1)
    {
        d1 = level;
        return root;
    }
    if (root->data == no2)
    {
        d2 = level;
        return root;
    }

    // Look for no1 and no2 in left and right subtree
    struct Node *left = FindDistUtil(root->left, no1, no2, d1, d2, level, dist);
    struct Node *right = FindDistUtil(root->right, no1, no2, d1, d2, level, dist);

    // If both of the above returns the Non-NULL, then one key is present in once subtree and the other is present in other.
    if (left && right)
    {
        dist = d1 + d2 - 2 * level;
        return root;
    }
    return (left != NULL) ? left : right;
}

int FindDist(struct Node *root, int n1, int n2)
{
    int d1 = -1, d2 = -1, dist;
    struct Node *lca = FindDistUtil(root, n1, n2, d1, d2, dist, 1);

    // If both n1 and n2 were present in the tree, return dist
    if (d1 != -1 && d2 != -1)
        return dist;

    // If n1 is Ancestor of n2, consider n1 as root and find level of n2 in subtree rooted with n2
    if (d1 != -1)
    {
        dist = FindLevel(lca, n2, 0);
        return dist;
    }

    // If n2 is Ancestor of n1, consider n2 as root and find level of n1 in subtree rooted with n2
    if (d2 != -1)
    {
        dist = FindLevel(lca, n1, 0);
        return dist;
    }
    return -1;
}

int count(struct Node *root)
{
    int c = 1;
    if (root == NULL)
        return 0;
    else
    {
        c += count(root->left);
        c += count(root->right);
        return c;
    }
}

void PrintLeftTree(struct Node *root)
{
    if (root->left == NULL)
        return;
    else
    {
        root = root->left;
        printf("%2d\n", root->data);
        PrintLeftTree(root);
    }
}

void PrintRightTree(struct Node *root)
{
    if (root->right == NULL)
        return;
    else
    {
        root = root->right;
        printf("%2d\n", root->data);
        PrintRightTree(root);
    }
}

void InOrder2(struct Node *root)
{
    if (root->left)
        InOrder2(root->left);

    printf("%d  ", root->data);

    if (root->right)
        InOrder2(root->right);
}


void CountLeaf(struct Node *root)
{
    if (root == NULL)
        return;

    if (root->left == NULL && root->right == NULL)
        counter++;

    CountLeaf(root->left);
    CountLeaf(root->right);
}

void PrintLeaf(struct Node *root)
{
    if (root != NULL)
    {
        if (root->left == NULL && root->right == NULL)
            printf("%d ", root->data);
        else
        {
            PrintLeaf(root->left);
            PrintLeaf(root->right);
        }
    }
    else
        return;
}

int GetRoot(struct Node *root)
{
    if (root == NULL)
        return 0;
    else
        return root->data;
}

int Diameter(struct Node *root)
{
    if (root == NULL)
        return 0;

    int DLeft = Diameter(root->left);
    int DRight = Diameter(root->right);

    int curr = FindHeight(root->left) + FindHeight(root->right) + 1;

    return Maxx(curr, Maxx(DLeft, DRight)) + 1;
}

int ans = 1;

int Diameter2(struct Node *root)
{
    if (root == NULL)
        return 0;
    int LeftSize = Diameter2(root->left);
    int RightSize = Diameter2(root->right);

    ans = Maxx(ans, 1 + LeftSize + RightSize);

    return 1 + Maxx(LeftSize, RightSize);
}

void PrintLeftBoundary(struct Node *root)
{
    if (root == NULL)
        return;

    struct Node *temp = root;
    while (temp != NULL)
    {
        printf("%d ", temp->data);
        temp = (temp->left) ? (temp->left) : (temp->right);
    }
}

void PrintRightBoundry(struct Node *root)
{
    if (root == NULL || (root->left == NULL && root->right == NULL))
        return;

    PrintRightBoundry(root->right ? root->right : root->left);
    printf("%d ", root->data);
}

void printBorder(struct Node *root)
{
    if (root == NULL)
        return;

    printf("%d ", root->data);

    PrintLeftBoundary(root->left);

    if (root->left == NULL && root->right == NULL)
        PrintLeaf(root);

    PrintRightBoundry(root->right);
}

struct Node *invertTree(struct Node *root)
{
    if (!root)
        return root;

    struct Node *tempR = invertTree(root->right);
    root->right = invertTree(root->left);
    root->left = tempR;
    return root;
}

int sumOfLeftLeaves(struct Node *root)
{
    if (!root)
        return 0;

    int r = root->left && !root->left->right && !root->left->left ? root->left->data : 0;

    return r + sumOfLeftLeaves(root->left) + sumOfLeftLeaves(root->right);
}

int sumOfRightLeaves(struct Node *root)
{
    if (!root)
        return 0;

    int r = root->right && !root->right->right && !root->right->left ? root->right->data : 0;

    return r + sumOfRighLeaves(root->left) + sumOfRightLeaves(root->right);
}

int main(void)
{
    struct Node *root = NULL; // a pointer for the root, not root itself.
    int choice, n, ele;

    while (1)
    {
        printf("\n");
        printf("\n1.  Insert a NewNode");
        printf("\n2.  Enter Multiple Nodes at a time");
        printf("\n3.  Delete a Node");
        printf("\n4.  Display Tree in LevelOrder (ZigZag)");
        printf("\n5.  Display Tree in PreOrder   (root, left,  right)");
        printf("\n6.  Display Tree in InOrder    (left, root,  right)");
        printf("\n7.  Display Tree in PostOrder  (left, right, root)");
        printf("\n8.  Display Tree");
        printf("\n9.  Print Left SubTree");
        printf("\n10. Print Right SubTree");
        printf("\n11. Print Border of the tree");
        printf("\n12. Print Leaf Nodes");
        printf("\n13. Print Root Element");
        printf("\n14. Check is element(Node) is existed in a Tree or not");
        printf("\n15. Count the number of Nodes in the tree");
        printf("\n16. Count the number of Leafs in the tree");
        printf("\n17. Sum of Left Leaves in the tree");
        printf("\n18. Sum of Left Leaves in the tree");
        printf("\n19. Get the Maximum and Minimum from the tree");
        printf("\n20. Get the Height and Depth of the Tree");
        printf("\n21. Get the Largest path from the tree");
        printf("\n22. Check if a tree is a Binary tree or not");
        printf("\n23. Reset the entire Tree");
        printf("\n24. Invert Tree");
        printf("\n25. Exit");

        printf("\n\nEnter your choice: ");
        scanf("%d", &choice);
        printf("\n");

        switch (choice)
        {

        case 1:
            printf("Enter the element: ");
            scanf("%d", &n);
            root = Insert(root, n);

            break;

        case 2:
            printf("Enter the number of elements: ");
            scanf("%d", &n);

            for (int i = 0; i < n; i++)
            {
                printf("\nEnter Node Value: ");
                scanf("%d", &ele);
                root = Insert(root, ele);
            }

            break;

        case 3:
            printf("Enter the element to delete: ");
            scanf("%d", &ele);
            root = Delete(root, ele);

            break;

        case 4:
            printf("Your tree in LevelOrder:\n");
            LevelOrder(root);

            break;

        case 5:
            printf("Your tree in PreOrder:\n");
            PreOrder(root);

            break;

        case 6:
            printf("Your tree in InOrder:\n");
            InOrder(root);

            break;

        case 7:
            printf("Your tree in PostOrder:\n");
            PostOrder(root);

            break;

        case 8:
            printf("\nGiven Tree is: ");
            InOrder2(root);

            break;

        case 9:
            printf("Your Left SubTree:\n");
            PrintLeftTree(root);

            break;

        case 10:
            printf("Your Right SubTree:\n");
            PrintRightTree(root);

            break;

        case 11:
            printBorder(root);

            break;

        case 12:
            PrintLeaf(root);

            break;

        case 13:
            ele = GetRoot(root);
            printf("The root node is: %d", ele);

            break;

        case 14:
            printf("Enter the data to search: ");
            scanf("%d", &ele);

            if (Search(root, ele) == true)
                printf("%d is Existed in a Tree.!!!", ele);
            else
                printf("%d is not Exist in a Tree.!!!", ele);
            printf("\n");

            break;

        case 15:
            ele = count(root);
            printf("The number of Nodes in tree is: %d\n", ele);

            break;

        case 16:
            CountLeaf(root);
            printf("The Number of leaf nodes is: %d\n", counter);

            break;

         case 17:
            ele = sumOfLeftLeaves(root);
            printf("Sum of Left Leaves: %d\n", ele);
            break;

        case 18:
            ele = sumOfRightLeaves(root);
            printf("Sum of Right Leaves: %d\n", ele);
            break;

        case 19:
            ele = RecMin(root);
            printf("Minimum from Tree: %d\n", ele);

            ele = RecMax(root);
            printf("Maximum from Tree: %d\n", ele);

            break;

        case 20:
            ele = FindHeight(root);
            printf("Height of Tree: %d\n", ele);

            ele = FindDepth(root);
            printf("Depth of Tree: %d\n", ele);

            break;

        case 21:
            ele = Diameter(root);
            printf("The Largest path of the tree is: %d\n", ele);

            //  ele = Diameter2(root);
            //  printf("\nThe Largest path of the tree is: %d\n", ele);

            break;

        case 22:
            if (IsBinary(root) == true)
                printf("Given Tree is Binary Search Tree");
            else
                printf("Given Tree is not a Binary Search Tree");

            break;

        case 23:
            // FreeTree(root);

            // DeleteTree(root);

            DeleteTree(root);
            root = NULL;

            if (root == NULL)
                printf("Tree is Cleared successfully...");

            break;

            /*case 23:
                PrintCousin(root, root->left->right);
                PrintCousin(root, root->right->left);
                break;
            */

        case 24:
            root = invertTree(root);
            break;

        case 25:
            printf("You're Kicked out...");
            exit(0);
            
        default:
            printf("\nInvalid choice");
        }
    }
}
