#include <stdio.h>
#include <stdlib.h>

typedef struct Node
{
    int data;
    struct Node *l, *r;
} N;

N *new (int x)
{
    N *node = (N *)malloc(sizeof(N));
    node->data = x;
    node->l = NULL;
    node->r = NULL;
    return node;
}

N *BST(int arr[], int first, int last)
{
    if (first > last)
        return NULL;

    N *temp = (N *)malloc(sizeof(N));
    int mid = (first + last) / 2;

    temp = new (arr[mid]);
    temp->l = BST(arr, first, mid - 1);
    temp->r = BST(arr, mid + 1, last);
    return temp;
}

void Display(N *temp)
{
    printf("%d ", temp->data);
    if (temp->l != NULL)
        Display(temp->l);
    if (temp->r != NULL)
        Display(temp->r);
}

int main(void)
{
    int arr[] = {10, 20, 30, 40, 50, 60, 70};
    N *root = (N *)malloc(sizeof(struct Node));
    int n = sizeof(arr) / sizeof(arr[0]);

    printf("\nGiven Sorted Array is: ");
    for (int i = 0; i < n; i++)
        printf(" %d", arr[i]);
    printf("\n");

    root = BST(arr, 0, n - 1);
    printf("PreOrder Traversal: ");
    Display(root);
}

/*
Given Sorted Array is:  10 20 30 40 50 60 70
PreOrder Traversal: 40 20 10 30 60 50 70
BALANCED!!!
*/
