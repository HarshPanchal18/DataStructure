#include <bits/stdc++.h>
using namespace std;

/*

Given a Binary tree and a key in the binary tree, find the node right to the given key. If there is no node on right side, then return a node with value -1.

Example 1:
Input:

       10
     /    \
    2      6
   / \      \
  8   4      5
and key = 2
Output: 6
Explanation: We can see in the above tree that the next right node of 2 is 6.

Example 2:
Input:
      10
    /     \
   2       6
  / \       \
 8   4       5
and key = 5
Output: -1
Explanation: We can see in the above tree that there's No next right node of 5. So, the output is -1.
*/

struct Node
{
    int data;
    struct Node *left;
    struct Node *right;
    struct Node *next;

    Node(int x)
    {
        data = x;
        left = NULL;
        right = NULL;
    }
};

void printInorder(Node *node)
{
    if (node == NULL)
        return;

    printInorder(node->left);

    cout << node->data << " ";

    printInorder(node->right);
}
Node *buildTree(string str)
{
    // Corner Case
    if (str.length() == 0 || str[0] == 'N')
        return NULL;

    // Creating vector of strings from input
    // string after spliting by space
    vector<string> ip;

    istringstream iss(str);
    for (string str; iss >> str;)
        ip.push_back(str);

    // Create the root of the tree
    Node *root = new Node(stoi(ip[0]));

    // Push the root to the queue
    queue<Node *> queue;
    queue.push(root);

    // Starting from the second element
    int i = 1;
    while (!queue.empty() && i < ip.size())
    {

        // Get and remove the front of the queue
        Node *currNode = queue.front();
        queue.pop();

        // Get the current node's value from the string
        string currVal = ip[i];

        // If the left child is not null
        if (currVal != "N")
        {

            // Create the left child for the current Node
            currNode->left = new Node(stoi(currVal));

            // Push it to the queue
            queue.push(currNode->left);
        }

        // For the right child
        i++;
        if (i >= ip.size())
            break;
        currVal = ip[i];

        // If the right child is not null
        if (currVal != "N")
        {

            // Create the right child for the current node
            currNode->right = new Node(stoi(currVal));

            // Push it to the queue
            queue.push(currNode->right);
        }
        i++;
    }

    return root;
}

class Solution
{
public:
    Node *solve(Node *root, int key, int level, int *rlevel, Node *&n)
    {
        if (root == NULL)
            return NULL;

        if (root->data == key)
        {
            *rlevel = level;
            return root;
        }

        if (*rlevel == level)
        {
            n = root;
            *rlevel = -100;
            return root;
        }

        solve(root->left, key, level + 1, rlevel, n);

        solve(root->right, key, level + 1, rlevel, n);
    }

    Node *nextRight(Node *root, int key)
    {
        int l = 0;
        int r = -1;
        Node *p = new Node(-1);
        solve(root, key, l, &r, p);
        return p;
    }
};

int main()
{
    string treeString;
    cin >> treeString;
    Node *root = buildTree(treeString);

    int key;
    cin >> key;

    Solution obj;
    Node *res = obj.nextRight(root, key);

    cout << res->data << endl;
    return 0;
}
