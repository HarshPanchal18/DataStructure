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
    void display(Node *temp)
    {
        if (isEmpty())
            cout << "\nTree is Empty";

        if (temp != NULL)
        {
            display(temp->left);
            cout << "  " << temp->key;
            display(temp->right);
        }
    }

    int depth(Node *temp)
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
            cout << "\nTree is Empty";
            return 0;
        }
    }

    void displayLeaf(Node *temp)
    {
        if (temp != NULL)
        {
            displayLeaf(temp->left);

            if (temp->left == NULL && temp->right == NULL)
                cout << " " << temp->key;

            displayLeaf(temp->right);
        }
        else
            cout << "\nTree is Empty";
    }

    Node *copyTree(Node *root)
    {
        Node *temp = NULL;

        if (root != NULL)
        {
            temp = new Node;
            temp->key = root->key;
            temp->left = copyTree(root->left);
            temp->right = copyTree(root->right);
        }
        return temp;
    }
};

int main(void)
{
    BinaryTree tree;
    char ch;
    Node *copy;
    int f = 1, n;
    while (f)
    {
        cout << "\n1.Insert";
        cout << "\n2.Display";
        cout << "\n3.Depth";
        cout << "\n4.Display Leaf";
        cout << "\n5. Create a copy";
        cout << "\n6. Exit";
        cout << "\nEnter your choice:";
        cin >> ch;
        switch (ch)
        {
        case 1:
            cout << "\nEnter the key to insert: ";
            cin >> n;
            tree.insert(n);
            break;

        case 2:
            tree.display(tree.getRoot());
            break;

        case 3:
            cout << "\nDepth of the given tree: " << tree.depth(tree.getRoot()) << endl;
            break;

        case 4:
            tree.displayLeaf(tree.getRoot());
            break;

        case 5:
            copy = tree.copyTree(tree.getRoot());
            cout << "\nTree Copied...";

            cout << "\nOriginal:";
            tree.display(tree.getRoot());

            cout << "\nCopied: ";
            tree.display(tree.getRoot());
            break;

        case 6:
            // exit(0);
            f = 0;
        }
    }
}
