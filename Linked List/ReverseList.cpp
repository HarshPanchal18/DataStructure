#include <iostream>
using namespace std;

/*
* Reverse Linked List
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]

Input: head = [1,2]
Output: [2,1]

Input: head = []
Output: []
*/

// Definition for singly-linked list.
struct ListNode
{
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

ListNode *reverseList(ListNode *head, ListNode *prev = NULL)
{

    if (head == NULL)
        return head;

    if (!head->next)
    {
        head->next = prev;
        return head;
    }

    ListNode *temp = head->next;
    head->next = prev;

    return reverseList(temp, head);
}

ListNode *reverseList(ListNode *head)
{
    //         While traversing through the list, first we store the next in temp,
    //         second assign  curr->next to prev, third assign prev to curr because this is the case in the next iteration,
    // the forth step assign curr to orginal curr->next(that is temp)
    //         return prev because the last value's next will be null and that is where the while loop stops at the time prev
    // will be the last node of the orginal list now that we reversed it return it(it's the new head now)

    ListNode *prev = head;
    ListNode *curr = head;
    while (prev != NULL)
    {
        ListNode *temp = curr->next;
        curr->next = prev;
        prev = curr;
        curr = temp;
    }
    return prev;
}
