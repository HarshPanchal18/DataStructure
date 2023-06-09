
// Application's Memory
/*
Heap - Free Memory
Stack - Store the function calls
Static/Global - Store the Global variables
Code/Text - Store other Statements
*/

// Index:
// Linked list              from Line 20 to 319

#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>

// 18/12/2021 - 22/12/2021
// Singly Linked List

struct Node
{
    int data;
    struct Node *next;
};

struct Node *q; // For accessible to all

int InsertAtBeg(int x)
{
    struct Node *temp = (struct Node *)malloc(sizeof(struct Node));
    temp->data = x;
    temp->next = head;
    // if (head->next != NULL) // If list is not empty
    //     temp->next = head;
    head = temp; // store temp's properties into the head
}

struct Node *InsertionWithArgs(struct Node *head, int x)
{
    struct Node *temp = (struct Node *)malloc(sizeof(struct Node));
    temp->data = x;
    temp->next = head;
    // if (head->next != NULL) // If list is not empty
    //     temp->next = head;
    head = temp; // store temp's properties into the head
    return head;
}

void InsertionWithPointers(struct Node *head, int x)
{
    struct Node *temp = (struct Node *)malloc(sizeof(struct Node));
    temp->data = x;
    temp->next = NULL;
    if ((*head).next != NULL) // If list is not empty
        temp->next = head;
    head = temp;
}

void InsertAtN(int data, int n)
{
    struct Node *temp1 = (struct Node *)malloc(sizeof(struct Node));
    temp1->data = data;
    temp1->next = NULL;
    if (n == 1) // Insert at Head
    {
        temp1->next = head; // link the newly created Node as existing head
        head = temp1;       // adjust head to point to the new node
        return;
    }

    struct Node *temp2 = head;      // Start from the head
    for (int i = 0; i < n - 2; i++) // n-2 = previous node of the entered position
        temp2 = temp2->next;

    temp1->next = temp2->next; // Store the location of the next node in newly created node
    temp2->next = temp1;       // link previous node to the newly created node
}

void print()
{
    struct Node *temp = head;
    if (head == NULL)
    {
        printf("\nList is empty..!!\n");
        return;
    }
    printf("\nList is :\n");
    while (temp != NULL)
    {
        printf("%d ", temp->data);
        temp = temp->next;
    }
    printf("\n");
}

void printWithArgs(struct Node *head)
{
    if (head == NULL)
    {
        printf("\nList is empty..!!\n");
        return;
    }
    printf("\nList is :\n");
    while (head != NULL)
    {
        printf("%d ", head->data);
        head = head->next;
    }
    printf("\n");
}

void PrintRecursively(struct Node *hd)
{
    if (hd == NULL)
    {
        printf("\n");
        return;
    }

    printf("%d ", hd->data);
    PrintRecursively(hd->next);
}

void del(int n)
{
    struct Node *temp = head;
    if (n == 1)
    {
        head = temp->next; // head points to second node of temp
        free(temp);
        return;
    }
    for (int i = 0; i < n - 2; i++)
        temp = temp->next; // points to n-1th node

    struct Node *temp2 = temp->next;
    temp->next = temp2->next; // joints with n+1th node
    free(temp2);
}

void Reverse()
{
    struct Node *currNode, *prevNode, *nextNode;

    if (head->next == NULL) // If list has only one element then what is left
        return;

    currNode = head;
    prevNode = NULL;
    while (currNode != NULL)
    {
        nextNode = currNode->next; // store the current address to the next node
        currNode->next = prevNode; // store the address of the current node to the previous node
        prevNode = currNode;       // move previous node to the current node
        currNode = nextNode;       // move current node to the next node
    }
    // After the completion of the loop, head is the last node
    head = prevNode;
}

void ReversePrintRecurs(struct Node *hd)
{
    if (hd == NULL)
    {
        printf("\n");
        return;
    }
    ReversePrintRecurs(hd->next);
    printf("%d ", hd->data);
}

struct Node *RevWithArg(struct Node *top)
{
    struct Node *currNode, *prevNode, *nextNode;
    currNode = top;
    prevNode = NULL;
    while (currNode != NULL)
    {
        nextNode = currNode->next; // store the address of the next node
        currNode->next = prevNode;
        prevNode = currNode;
        currNode = nextNode;
    }
    // After the completion of the loop, head is the last node
    top = prevNode;
    return top;
}

void Rev(struct Node *hd)
{
    if (hd->next == NULL)
    {
        head = hd;
        return;
    }
    Rev(hd->next);
    struct Node *q = hd->next;
    q->next = hd;
    hd->next = NULL;
}

void count()
{
    struct Node *q;
    int count = 0;
    q = head;
    while (q != NULL)
    {
        q = q->next;
        count++;
    }
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

int main(void)
{
    // struct Node *head = NULL;
    head = NULL; // Create a empty list
    int choice = 0, n, m;

    while (1)
    {
        printf("\n 1. Create list");
        printf("\n 2. Add at beginning");
        printf("\n 3. Add after");
        printf("\n 4. Delete element by position");
        printf("\n 5. Display");
        printf("\n 6. Length of list");
        printf("\n 7. Reverse  ");
        printf("\n 8. Search");
        printf("\n 9. Quit ");
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
                InsertAtN(m, i + 1);
            }
            break;

        case 2:
            printf("\nEnter the element ");
            scanf("%d", &m);
            InsertAtBeg(m);
            break;

        case 3:
            printf("\nEnter the element ");
            scanf("%d", &m);
            int pos;
            printf("Enter the position after which element is inserted ");
            scanf("%d", &pos);
            InsertAtN(m, pos);
            break;

        case 4:
            if (head == NULL)
            {
                printf("\nList is empty  ");
                continue;
            }
            print();
            printf("\nEnter the element position you want to delete ");
            scanf("%d", &m);
            del(m);
            break;

        case 5:
            print();
            break;

        case 6:
            count();
            break;

        case 7:
            Reverse();
            break;

        case 8:
            printf("Enter the element to be search: ");
            scanf("%d", &m);
            search(m);
            break;

        case 9:
            printf("\nYou're Kicked out...\n");
            exit(0);

        default:
            printf("Invalid choice");
        } // switch
    }     // while
}
