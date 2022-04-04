#include <bits/stdc++.h>

using namespace std;

struct Tree
{
    int data;
    struct Tree *left, *right;
    Tree(int val)
    {
        this->data = val;
    }
};

int ans;

int unival(Tree *node, int data)
{
    if (node == NULL)
        return 1;
    int x = 1;
    x = min(x, unival(node->left, node->data));
    x = min(x, unival(node->right, node->data));

    if (x == 1)
        ans++;

    if (node->data == data)
        return min(x, 1);
    else
        return 0;
}
