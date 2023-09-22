#include <stdio.h>
#include <conio.h>

#define MAX 10

struct stack
{
	int stak[MAX];
	int Top;
};
typedef struct stack NODE;

void push(NODE *pu) {
	int item;
	if (pu->Top == MAX - 1) {
		printf("\nStack is full");
		getch();
	}
	else {
		printf("\nEnter element to be inserted = ");
		scanf("%d", &item);
		pu->stak[++pu->Top] = item;
	}
}

void pop(NODE *po) {
	int item;
	if (po->Top == -1) {
		printf("\nThe stack  is empty ");
	}
	else {
		item = po->stak[po->Top--];
		printf("\nDeleted element is = %d", item);
	}
}

void traverse(NODE *pt) {
	int i;
	if (pt->Top == -1) {
		printf("\nThe stack is empty");
	}
	else {
		printf("\nThe element(s) in the stack are...");
		for (i = pt->Top; i >= 0; i--) {
			printf("\n %d", pt->stak[i]);
		}
	}
}

void main() {
	int choice;
	int i;
	char ch;
	NODE *ps;
	ps->Top = -1;
	for (i = 0; i < MAX; i++) {
		printf("\nEnter the elements 1 by 1 ");
		scanf("%d", ps->stak[i]);
	}
	do
	{
		printf("\n1. PUSH ");
		printf("\n2. POP ");
		printf("\n3. Traverse ");
		printf("\nEnter your choice ");
		scanf("%d", &choice);
		switch (choice) {
		case 1:
			push(ps);
			break;
		case 2:
			pop(ps);
			break;
		case 3:
			traverse(ps);
			break;
		default:
			printf("\nYou entered wrong choice");
			break;
		}
    
		printf("\nPress (Y/y) to continue ");
		fflush(stdin);
		scanf("%c", &ch);
	} while (ch == 'y' || ch == 'Y');

}
