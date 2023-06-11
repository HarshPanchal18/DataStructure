#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>

struct Node
{
    int data;
    struct Node *next;
    struct Node *prev;
};

struct Node *head;

struct Node *GetNewNode(int x)
{
    struct Node *newNode = (struct Node *)malloc(sizeof(struct Node));
    newNode->data = x;
    newNode->prev = NULL;
    newNode->next = NULL;
    return newNode;
}

void InsertHead(int x)
{
    struct Node *newNode = GetNewNode(x); // return the address of the newly created node
    if (head == NULL)                     // If list is empty  OR  you're going to insert first node
    {
        head = newNode;
        return;
    }
    head->prev = newNode; // set the previous field of existing head node as the address of the newNode
    newNode->next = head; // set the next field of the new node to the head
    head = newNode;
}

void InsertTail(int x) // You can only insert element if list has at least one element
{
    struct Node *q;
    struct Node *temp = (struct Node *)malloc(sizeof(struct Node));
    temp->data = x;
    temp->next = NULL;

    if (head == NULL)
    {
        temp->prev = NULL;
        head->prev = temp;
        head = temp;
    }
    else
    {
        q = head;
        while (q->next != NULL)
            q = q->next;

        q->next = temp;
        temp->prev = q;
    }
}

void InsertAtN(int n, int pos)
{
    struct Node *q = head;
    struct Node *temp = (struct Node *)malloc(sizeof(struct Node));

    for (int i = 0; i < pos - 2; i++)
    {
        q = q->next;
        if (q == NULL)
        {
            printf("\nThere are less than %d nodes\n", pos);
            return;
        }
    }
    temp->data = n;
    q->next->prev = temp;
    temp->next = q->next;
    temp->prev = q;
    q->next = temp;
}

void print()
{
    struct Node *temp = head;
    printf("\nList is : ");
    while (temp != NULL)
    {
        printf("%d  ", temp->data);
        temp = temp->next;
    }
    printf("\n");
}

void ReversePrint()
{
    struct Node *temp = head;
    if (temp == NULL)
        return; // empty list

    while (temp->next != NULL) // going to the last
        temp = temp->next;

    printf("Reverse:  ");
    while (temp != NULL)
    {
        printf("%d  ", temp->data);
        temp = temp->prev;
    }
    printf("\n\n");
}

void del(int n)
{
    struct Node *q, *temp;
    if (head->data == n)
    {
        temp = head;
        head = head->next; // first element deleted
        head->prev = NULL;
        free(temp);
        return;
    }
    q = head;
    while (q->next->next != NULL)
    {
        if (q->next->data == n) // Element deleted in between
        {
            temp = q->next;
            q->next = temp->next;
            temp->next->prev = q;
            free(temp);
            return;
        }
        q = q->next;
    }
    if (q->next->data == n) // last element Deleted
    {
        temp = q->next;
        free(temp);
        q->next = NULL;
        return;
    }
    printf("\nElement %d is not found in list\n", n);
}

void count()
{
    struct Node *q;
    int count = 0;

    // you can use either of the following

    q = head;
    while (q != NULL)
    {
        q = q->next;
        count++;
    }

    for (q = head; q != NULL; q = q->next, count++)
        ;
    printf("Total %d elements in the list...\n", count);
}

void search(int data)
{
    struct Node *q = head;
    int pos = 1;
    while (q != NULL)
    {
        if (q->data == data)
        {
            printf("%d found at position %d  \n", data, pos);
            return;
        }
        q = q->next;
        pos++;
    }
    if (q == NULL)
        printf("%d is not found in list \n", data);
}

int max(struct Node *h)
{
    struct Node *max, *q;
    q = max = h;
    while (q != NULL)
    {
        if (q->data > max->data)
            max = q;
        q = q->next;
    }
    return max->data;
}

int min(struct Node *h)
{
    struct Node *min, *q;
    q = min = h;
    while (q != NULL)
    {
        if (q->data <= min->data)
            min = q;
        q = q->next;
    }
    return min->data;
}

int main(void)
{
    head = NULL;
    int choice = 0, n, m, pos;

    while (1)
    {
        printf("\n 1. Create list");
        printf("\n 2. Add at beginning");
        printf("\n 3. Add at last position");
        printf("\n 4. Insert at position");
        printf("\n 5. Delete element");
        printf("\n 6. Display");
        printf("\n 7. Length of list");
        printf("\n 8. Reverse  ");
        printf("\n 9. Search");
        printf("\n 10. Find Max from the list");
        printf("\n 11. Find Min from the list");
        printf("\n 12. Quit ");
        printf("\nEnter your choice :-  ");
        scanf("%d", &choice);
        switch (choice)
        {
        case 1:
            printf("\nHow many node(s) you want?? ");
            scanf("%d", &n);
            for (int i = 0; i < n; i++)
            {
                printf("Enter %d element :  ", i + 1);
                scanf("%d", &m);
                if (i == 0)
                    InsertHead(m);
                else
                    InsertTail(m);
            }
            break;

        case 2:
            printf("\nEnter the element: ");
            scanf("%d", &m);
            InsertHead(m);
            break;

        case 3:
            printf("\nEnter the element: ");
            scanf("%d", &m);
            InsertTail(m);
            break;

        case 4:
            printf("\nEnter the element: ");
            scanf("%d", &m);
            printf("Enter the element position: ");
            scanf("%d", &pos);
            InsertAtN(m, pos);
            break;

        case 5:
            if (head == NULL)
            {
                printf("\nList is empty  ");
                continue;
            }
            print();
            printf("\nEnter the element you want to delete ");
            scanf("%d", &m);
            del(m);
            break;

        case 6:
            print();
            break;

        case 7:
            count();
            break;

        case 8:
            ReversePrint();
            break;

        case 9:
            printf("Enter the element to be search: ");
            scanf("%d", &m);
            search(m);
            break;

        case 10:
            m = max(head);
            printf("\nBiggest element: %d\n", m);
            break;

        case 11:
            m = min(head);
            printf("\nSmallest element: %d\n", m);
            break;

        case 12:
            printf("\nYou're Kicked out...\n");
            exit(0);

        default:
            printf("Invalid choice");
        } // switch
    } // while
} // main
