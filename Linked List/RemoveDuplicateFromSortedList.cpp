#include <iostream>
#include <map>
using namespace std;

/*
Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
leaving only distinct numbers from the original list.
Return the linked list sorted as well.

Example 1:
Input: head = [1,2,3,3,4,4,5]
Output: [1,2,5]

Example 2:
Input: head = [1,1,1,2,3]
Output: [2,3]
*/

// * Definition for singly-linked list.
struct ListNode
{
    int val;
    ListNode *next;
    ListNode() : val(0), next(NULL) {}
    ListNode(int x) : val(x), next(NULL) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution
{
public:
    ListNode *deleteDuplicates(ListNode *head)
    {
        if (!head || !head->next)
            return head;

        if (head->val == head->next->val)
        {
            while (head->next && head->val == head->next->val)
                head = head->next;
            head = deleteDuplicates(head->next);
        }
        else
            head->next = deleteDuplicates(head->next);
        return head;
    }
};

class Solution2
{
public:
    ListNode *deleteDuplicates(ListNode *head)
    {
        if (!head || !head->next)
            return head;

        ListNode *preptr = NULL, *currptr = head, *nextptr = head->next;

        while (nextptr)
        {
            if (currptr->val == nextptr->val)
            {
                while (nextptr && currptr->val == nextptr->val)
                    nextptr = nextptr->next;

                if (preptr == NULL)
                    head = currptr = nextptr;
                else
                {
                    preptr->next = nextptr;
                    currptr = nextptr;
                }
            }
            else
                preptr = currptr, currptr = currptr->next;

            if (nextptr != NULL)
                nextptr = nextptr->next;
        }
        return head;
    }
};

class Solution3
{
public:
    ListNode *deleteDuplicates(ListNode *head)
    {

        if (!head or !head->next)
            return head;

        ListNode *dummy = new ListNode(-1);
        ListNode *curr = dummy;
        ListNode *temp = head;
        map<int, int> mpp;

        while (temp)
        {
            mpp[temp->val]++;
            temp = temp->next;
        }
        temp = head;
        while (temp)
        {
            if (mpp[temp->val] == 1)
            {
                ListNode *node = new ListNode(temp->val);
                curr->next = node;
                curr = curr->next;
            }
            temp = temp->next;
        }
        return dummy->next;
    }
};

ListNode *deleteDuplicates(ListNode *head)
{
    /*
    first make a dummy node and assign its value equals to 0 and its next points to the head of the given linked list.
    also make a prev node and point it to the dummy node.

    Always remember that a prev node is always required wherever we have to delete a node or we have to break links and create new links .
    this thing is very important for beginner coders for developing their intusions in future.
    That's not a rule i have said it on the basis of my coding expirience.

    Then you have to traverse the linked list so run a while loop.
    check that if the two adjacent nodes are equal or not if the two adjacent nodes are equal then traverse the linked list upto when the adjacent nodes are not identical.

    Then directly connect the link from prev->next to the head->next.

    While in other case is the adjacent nodes are not equal then simply traverse the linked list by prev=prev->next.
    */

    ListNode *temp = new ListNode(0, head);
    ListNode *prev = temp;

    while (head)
    {
        if (head->next && head->val == head->next->val)
        {
            while (head->next && head->val == head->next->val)
                head = head->next;

            prev->next = head->next;
        }
        else
            prev = prev->next;

        head = head->next;
    }
    return temp->next;
}
