#include <stdio.h>
#include <conio.h>
#include <stdlib.h>

#define MAX 10

struct stack{
    int stack[10];
    int top;
};

typedef struct stack NODE;

void push(NODE *pu) {
    int item;
    if(pu->top == MAX-1) {
        printf("\nStack is Full(Overflow)...");
    }
    else {
        printf("\nEnter the element you want to push: ");
        scanf("%d",&item);
        pu->stack[++pu->top] = item;
    }
}

void pop(NODE *po) {
    int item;
    if(po->top == -1) {
        printf("\nStack is empty(underflow)...");
    }
    else {
        item=po->stack[po->top--];
        printf("\nItem deleted from the stack is %d ",item);
    }
}

void display(NODE *pt) {
    int i;
    if(pt->top==-1) {
        printf("\nStack is empty");
        getch();
    }
    else {
        for(i=pt->top;i>=0;i--)
            printf("%d ",pt->stack[i]);
    }
}

int peep(NODE *pp) {
    int item=0,top1;
    if(pp->top==-1) {
        printf("\nStack is empty");
        getch();
    }
    else {
        top1=pp->top;
        item=pp->stack[top1];
    }
    return item;
}

int main() {
    NODE *ps;
    int choice,item=0;
    char ch;
    ps->top=-1;
    do {
        printf("\n1. Insert");
        printf("\n2. Delete");
        printf("\n3. Display");
        printf("\n4. Show the top element");
        printf("\n5. Quit");
        printf("\nEnter your choice: ");
        scanf("%d", &choice);
        switch (choice) {
            case 1:
            push(ps);
            break;

            case 2:
            pop(ps);
            break;

            case 3:
            display(ps);
            break;

            case 4:
            item=peep(ps);
            printf("\nTop element %d",item);
            break;

            case 5:
            exit(1);

            default:
            printf("\nYour choice is wrong..");
        }
    printf("\nDo you want to continue? (y/n): ");
    fflush(stdin);
    scanf("%c",&ch);
    } while(ch == 'y' || ch == 'Y');

    if(ch!='y'|| ch!='Y'){
        printf("\nYour list was..\n");
        display(ps);
    }

  return 0;
}
