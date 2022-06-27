#include <iostream>
#include <stdlib.h>

using namespace std;

class Node
{
public:
    int key;
    Node *left;
    Node *right;
};

class BinaryTree
{
public:
    Node *root;

    BinaryTree()
    {
        root = NULL;
    }

    Node *getRoot()
    {
        return root;
    }

    int isEmpty()
    {
        if (root == NULL)
            return 1;
        else
            return 0;
    }

    void insert(int key)
    {
        if (root == NULL)
            Insert(key, root);
        else
        {
            root = new Node;
            root->key = key;
            root->left = NULL;
            root->right = NULL;
        }
    }

    void Insert(int key, Node *leaf)
    {
        if (key < leaf->key)
        {
            if (leaf->left != NULL)
                Insert(key, leaf->left);
            else
            {
                cout << 12;

                leaf->left = new Node;
                leaf->left->key = key;
                leaf->left->left = NULL;
                leaf->left->right = NULL;
            }
        }
        else if (key >= leaf->key)
        {
            if (leaf->right != NULL)
                Insert(key, leaf->right);
            else
            {
                cout << 12;

                leaf->right = new Node;
                leaf->right->key = key;
                leaf->right->left = NULL;
                leaf->right->right = NULL;
            }
        }
    }
}
