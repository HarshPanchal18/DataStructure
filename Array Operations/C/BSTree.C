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
