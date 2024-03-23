#include <iostream>

using namespace std;

class Node
{
public:
    int data;
    Node *next;

    Node(int d)
    {
        data = d;
        next = NULL;
    }
};

void insertTail(Node *&head, int data)
{
    if (head == NULL)
    {
        head = new Node(data);
        return;
    }
    Node *tail = head;

    while (tail->next != NULL)
        tail = tail->next;

    tail->next = new Node(data);
    return;
}

void buildList(Node *&head)
{
    int n;
    cout << "\nEnter the number of elements: ";
    cin >> n;

    cout << "\nEnter elements in sorted order ";
    while (n > 0)
    {
        int data;
        cin >> data;
        insertTail(head, data);
        n--;
    }
}

void print(Node *head)
{
    if (head == NULL)
        return;

    cout << head->data << " ";
    print(head->next);
}

Node *merge(Node *n1, Node *n2)
{
    if (n1 == NULL)
        return n2;

    if (n2 == NULL)
        return n1;

    Node *n3;

    if (n1->data <= n2->data)
    {
        n3 = n1;
        n3->next = merge(n1->next, n2);
    }
    else
    {
        n3 = n2;
        n3->next = merge(n1, n2->next);
    }
    return n3;
}

int main(void)
{
    Node *head1 = NULL;
    Node *head2 = NULL;

    buildList(head1);
    buildList(head2);

    Node *newHead = merge(head1, head2);
    cout << "Sorted: ";
    print(newHead);
}
