#include <stdio.h>
#define SIZE 5

int queue[SIZE];
int front = -1;
int rear = -1;

int isFull()
{
    return (front == 0 && rear == SIZE - 1) || rear == front - 1;
}

int isEmpty()
{
    return front == -1;
}

void insert(int data)
{
    if (isFull())
    {
        printf("Queue is overflow\n");
        return;
    }

    if (front == -1)
        front = rear = 0;
    else if (rear == SIZE - 1)
        rear = 0;
    else
        rear++;
    queue[rear] = data;
}

int delete()
{
    if (isEmpty())
    {
        printf("Queue is empty");
        return -1;
    }
    return queue[front++];
}

void display()
{
    int fPos = front, rPos = rear;
    if (fPos <= rPos)
    {
        while (fPos <= rPos)
            printf("%d ", queue[fPos++]);
    }
    else
    {
        while (fPos <= SIZE - 1)
            printf("%d ", queue[fPos++]);
        fPos = 0;
        while (fPos <= rPos)
            printf("%d ", queue[fPos++]);
    }
}

int main(int argc, char const *argv[])
{
    insert(5);
    insert(25);
    insert(35);
    insert(45);
    insert(55);
    insert(65);
    insert(75);

    display();

    delete ();
    delete ();
    delete ();

    printf("\n");
    display();

    delete ();
    return 0;
}
