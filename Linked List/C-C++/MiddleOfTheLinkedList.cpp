#include <iostream>

using namespace std;

/*
* Middle of the Linked List
Given the head of a singly linked list, return the middle node of the linked list.
If there are two middle nodes, return the second middle node.

Example 1
Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.

Example 2
Input: head = [1,2,3,4,5,6]
Output: [4,5,6]
Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
*/

// * Definition for singly-linked list.
struct ListNode
{
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution
{
public:
    ListNode *middleNode(ListNode *head)
    {

        /*
        ListNode* ptr=head;
        ListNode* temp=head;
        ListNode* temp2=head;
        int n=0;
        int k=0;

        if(head->next==NULL)
            return ptr;

        while(temp2->next!=NULL)
        {
            temp2=temp2->next;
            n++;
        }

        int mid=(n/2)-1;

        while(k++<mid)
            ptr=ptr->next;

        while(mid++<n)
        {
            temp=ptr;
            ptr=ptr->next;
        }
        return temp;
        */

        /*
         * Slow & fast tortoise concept
         * Here slow pointer is moving by 1 step where as fast by 2 steps simultaneously.
         * So the fast pointer will reach the null in half time in comparison to slow.
         * So slow pointer will reach at middle when the fast will reach the last node or null
         */

        ListNode *ptr = head;
        ListNode *ptr2 = head;

        // while (ptr->next != NULL && ptr->next->next != NULL) //it works
        while (ptr && ptr->next) // it also works
        {
            ptr = ptr->next->next;
            ptr2 = ptr2->next;
        }
        return ptr2;
    }
};
