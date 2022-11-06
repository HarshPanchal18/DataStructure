#include <stdio.h>
#include <malloc.h>
#include <assert.h>

struct Node
{
    int data;
    struct Node *next;
};

struct Node *insert(struct Node *start, int data)
{
    struct Node *tmp;
    tmp = (struct Node *)malloc(sizeof(struct Node));
    tmp->data = data;

    /*If list is empty*/
    if (start == NULL)
    {
        tmp->next = start;
        start = tmp;
        return start;
    }
    else /*Insert at the end of the list*/
    {
        struct Node *p = start;
        while (p->next != NULL)
            p = p->next;
        tmp->next = p->next;
        p->next = tmp;
    }
    return start;
} /*End of insert()*/

void print(struct Node *head)
{
    if (head == NULL)
    {
        printf("NULL\n");
        return;
    }
    else
    {
        printf("%d ->", head->data);
        print(head->next);
    }
}

struct Node *create(struct Node *head)
{
    int n, data;
    printf("\nEnter the number of nodes : ");
    scanf("%d", &n);

    head = NULL;

    for (int i = 1; i <= n; i++)
    {
        printf("Enter the element to be inserted : ");
        scanf("%d", &data);
        head = insert(head, data);
    }
    return head;
}

struct Node *merge(struct Node *n1, struct Node *n2)
{
    if (n1 == NULL)
        return n2;

    if (n2 == NULL)
        return n1;

    struct Node *n3 = (struct Node *)malloc(sizeof(struct Node));

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
    struct Node *head1 = NULL, *head2 = NULL;

    head1 = create(head1);
    print(head1);

    head2 = create(head2);
    print(head2);

    struct Node *newHead = merge(head1, head2);
    printf("\nSorted: ");
    print(newHead);
}
