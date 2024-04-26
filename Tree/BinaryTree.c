#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>

struct Node
{
    int key;
    struct Node *left;
    struct Node *right;
};

struct Node *root;

struct Node *getRoot()
{
    return root;
}

int isEmpty()
{
    return root == NULL;
}

void Insert(int key, struct Node *leaf)
{   
    if (leaf == NULL)
    {
        leaf = (struct Node *)malloc(sizeof(struct Node));
        leaf->key = key;
        leaf->left = NULL;
        leaf->right = NULL;
    }
    else if (key < leaf->key)
    {
        Insert(key, leaf->left);
    }
    else if (key >= leaf->key)
    {
        Insert(key, leaf->right);
    }
}

void insert(int key)
{
    if (root == NULL)
    {
        root = (struct Node *)malloc(sizeof(struct Node));
        root->key = key;
        root->left = NULL;
        root->right = NULL;
    }
    else
    {
        Insert(key, root);
    }
}

void display(struct Node *temp)
{
    if (isEmpty())
        printf("\nTree is Empty");

    if (temp != NULL)
    {
        display(temp->left);
        printf("%d", temp->key);
        display(temp->right);
    }
}

int max(int a, int b)
{
    return a > b ? a : b;
}

int depth(struct Node *temp)
{
    int depthLeft, depthRight;

    if (!isEmpty())
    {
        if (temp == NULL)
            return -1;

        depthLeft = depth(temp->left);
        depthRight = depth(temp->right);

        return max(depthLeft, depthRight) + 1;
    }
    else
    {
        printf("\nTree is Empty");
        return 0;
    }
}

void displayLeaf(struct Node *temp)
{
    if (temp != NULL)
    {
        displayLeaf(temp->left);

        if (temp->left == NULL && temp->right == NULL)
            printf("%d", temp->key);

        displayLeaf(temp->right);
    }
    else
        printf("\nTree is Empty");
}

int main(void)
{
    root = NULL;
    int ch;
    int f = 1, n = 0;
    while (f)
    {
        printf("\n1.Insert");
        printf("\n2.Display");
        printf("\n3.Depth");
        printf("\n4.Display Leaf");
        printf("\n5.Exit");

        printf("\nEnter your choice:");
        scanf("%d", &ch);
        switch (ch)
        {
        case 1:
            printf("\nEnter the key to insert: ");
            scanf("%d", &n);
            insert(n);
            break;

        case 2:
            display(getRoot());
            break;

        case 3:
            printf("\nDepth of the given tree: %d", depth(getRoot()));
            break;

        case 4:
            displayLeaf(getRoot());
            break;

        case 5:
            f = 0;
            exit(0);
        }
    }
}
