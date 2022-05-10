#include <stdio.h>
#include <stdlib.h>

struct Node
{
    int data;
    struct Node *left;
    struct Node *right;
};

struct Node *NewNode(int data)
{
    struct Node *temp = (struct Node *)malloc(sizeof(struct Node));
    temp->data = data;
    temp->left = NULL;
    temp->right = NULL;

    return temp;
}

struct Node *ArrToBST(int arr[], int start, int end)
{
    if (start > end)
        return NULL;

    int mid = (start + end) / 2;
    struct Node *root = NewNode(arr[mid]);

    root->left = ArrToBST(arr, start, mid - 1);
    root->right = ArrToBST(arr, mid + 1, end);

    return root;
}

void preOrder(struct Node *root)
{
    if (root == NULL)
        return;

    printf("%d\t", root->data);
    preOrder(root->left);
    preOrder(root->right);
}

int main()
{
    int arr[] = {1, 3, 5, 7, 9, 11, 13};

    int n = sizeof(arr) / sizeof(arr[0]);

    struct Node *root = ArrToBST(arr, 0, n - 1);

    printf("\nROOT: %d\n", root->data);

    printf("Result:\n"), preOrder(root);
}

/*
ROOT: 7
Result:
7       3       1       5       11      9       13

            7
        3       11
      1   5   9    13
*/
