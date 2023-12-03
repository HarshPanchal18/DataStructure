#include<stdio.h>
#include<stdlib.h>
#include<malloc.h>

struct node
{
    int data;
    struct node *next;
};

typedef struct node *NODE; // for better readability

NODE last = NULL;

void CreateNodeOf(int x)
{
    NODE q;
    NODE temp = (struct node *)malloc(sizeof(struct node));
    temp->data = x;
    if (last == NULL) // If list has only one element
    {
        last = temp;
        temp->next = last;
    }
    else
    {
        temp->next = last->next;
        last->next = temp;
        last = temp;
    }
}

void AddAtBegin(int no)
{
    NODE temp = (struct node *)malloc(sizeof(struct node));
    temp->data = no;
    temp->next = last->next;
    last->next = temp;
}

void AddAfter(int n, int pos)
{
    NODE q = last->next;
    for (int i = 0; i < pos - 1; i++)
    {
        q = q->next;
        if (q == last->next)
        {
            printf("\nThere are less than %d elements\n", pos);
            return;
        }
    }
    NODE temp = (struct node *)malloc(sizeof(struct node));
    temp->next = q->next;
    temp->data = n;
    q->next = temp;

    if (q == last) // inserted at the end
        last = temp;
}

void del()
{
    if (last == NULL)
    {
        printf("\nList is Empty");
        return;
    }

    int num;
    printf("\nEnter the number to delete: ");
    scanf("%d", &num);

    NODE temp, q;

    if (last->next == last && last->data == num)
    {
        temp = last;
        last = NULL;
        free(temp);
        return;
    }

    q = last->next;
    if (q->data == num)
    {
        temp = q;
        last->next = q->next;
        free(temp);
        return;
    }

    while (q->next != last)
    {
        if (q->next->data == num)
        {
            temp = q->next;
            q->next = temp->next;
            free(temp);
            printf("\n%d is deleted\n", num);
            return;
        }
        q = q->next;
    }

    if (q->next->data == num)
    {
        temp = q->next;
        q->next = last->next;
        free(temp);
        last = q;
        return;
    }
    printf("\n%d not found", num);
}

void display()
{
    if (last == NULL)
    {
        printf("\nList is empty");
        return;
    }

    NODE q = last->next;
    printf("\nList is :\t");
    while (q != last)
    {
        printf("%d\t", q->data);
        q = q->next;
    }
    printf("%d\n", last->data);
}

void count()
{
    if (last == NULL)
    {
        printf("\nList is empty");
        return;
    }

    NODE q;
    int count = 0;
    q = last->next;
    while (q != last)
    {
        q = q->next;
        count++;
    }
    printf("Total %d elements in the list...\n", count + 1);
}

void search()
{
    NODE q;
    int item, i = 0, flag = 1;
    q = last;
    if (q == NULL)
        printf("List is empty\n");

    else
    {
        printf("\nEnter the element you want to search\n");
        scanf("%d", &item);

        if (last->data == item)
        {
            printf("Item found at index %d\n", i);
            flag = 0;
            return;
        }
        else
        {
            while (q->next != last)
            {
                if (q->data == item)
                {
                    printf("\nItem found at index %d\n", i);
                    flag = 0;
                    return;
                }
                else
                    flag = 1;
                i++;
                q = q->next;
            }
        }
        if (flag != 0)
        {
            printf("\nItem  not found\n");
            return;
        }
    }
}

int main(void)
{
    int choice, pos, m, n;
    while (1)
    {
        printf("\n1. Create a list");
        printf("\n2. Add at beginning of list");
        printf("\n3. Add after");
        printf("\n4. Delete");
        printf("\n5. Display");
        printf("\n6. Length of the list");
        printf("\n7. Search element");
        printf("\n8. Exit");

        printf("\nEnter your choice: ");
        scanf("%d", &choice);

        switch (choice)
        {
        case 1:
            printf("\nEnter the number of elements: ");
            scanf("%d", &n);
            for (int i = 0; i < n; i++)
            {
                scanf("%d", &m);
                CreateNodeOf(m);
            }
            break;

        case 2:
            printf("\nEnter the number to add at the beginning: ");
            scanf("%d", &m);

            AddAtBegin(m);
            break;

        case 3:
            printf("\nEnter the number: ");
            scanf("%d", &m);
            printf("\nEnter the after position: ");
            scanf("%d", &pos);

            AddAfter(m, pos);
            break;

        case 4:
            del();
            break;

        case 5:
            display();
            break;

        case 6:
            count();
            break;

        case 7:
            search();
            break;

        case 8:
            printf("\nYou're kicked out...");
            exit(0);

        default:
            printf("Invalid choice");
        }
    }
}
