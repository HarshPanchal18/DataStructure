#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>

struct Node
{
    int data;
    struct Node *link;
};
struct Node *top = NULL;

void Push(int x)
{
    struct Node *temp = (struct Node *)malloc(sizeof(struct Node));
    temp->data = x;
    temp->link = top;
    top = temp;
}

void Pop()
{
    struct Node *temp;
    if (top == NULL)
        return;

    temp = top;
    top = top->link;
    free(temp);
}

void Display()
{
    struct Node *temp = top;
    if (top == NULL)
        printf("\nStack is empty\n");
    else
    {
        while (temp != NULL)
        {
            printf("%d ", temp->data);
            // top = top->link;
            temp = temp->link;
        }
        printf("\n");
    }
}

int main(void)
{
    top = NULL;
    int choice, ele;

    while (1)
    {
        printf("\n1. Push");
        printf("\n2. Pop");
        printf("\n3. Display");
        printf("\n4. Exit");
        printf("\nEnter your choice: ");
        scanf("%d", &choice);

        switch (choice)
        {
        case 1:
            printf("\nEnter the element to push to the stack: ");
            scanf("%d", &ele);
            Push(ele);
            break;

        case 2:
            Pop();
            break;

        case 3:
            Display();
            break;

        case 4:
            exit(0);

        default:
            printf("\nInvalid choice");
        }
    }
}
