#include <iostream>

/*
* Remove Nth Node From End of List
* Given the head of a linked list, remove the nth node from the end of the list and return its head.

* Example 1
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

* Example 2
Input: head = [1], n = 1
Output: []

* Example 3:
Input: head = [1,2], n = 1
Output: [1]
*/

using namespace std;

// Definition for singly-linked list.
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
    ListNode *removeNthFromEnd(ListNode *head, int n)
    {
        ListNode *begin = new ListNode();
        begin->next = head;
        ListNode *ptr = begin;
        ListNode *ptr2 = begin;

        /*
        int totalNodes;
        while(temp->next!=NULL){
            temp=temp->next;
            totalNodes++;
        }
        */

        // for(int i=0;i<totalNodes-n;i++) ptr2=ptr2->next;

        /*
        int k=0;
        while(ptr2->next)
        {
            if(k==totalNodes-n)
                ptr2=ptr2->next->next;
        }
        ListNode* finalptr=ptr2;
        ptr2->next=ptr2->next->next;
        return finalptr;
        */

        for (int i = 1; i <= n; i++)
            ptr2 = ptr2->next;

        while (ptr2->next)
        {
            ptr = ptr->next;
            ptr2 = ptr2->next;
        }
        ptr->next = ptr->next->next;
        return begin->next;
    }
};
