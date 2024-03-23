#include <iostream>
using namespace std;

/*
* Delete the Middle Node of a Linked List

You are given the head of a linked list.
Delete the middle node, and return the head of the modified linked list.
The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing,
where ⌊x⌋ denotes the largest integer less than or equal to x.
For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.

Example 1:
Input: head = [1,3,4,7,1,2,6]
Output: [1,3,4,1,2,6]
Explanation:
Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
We return the new list after removing this node.

Example 2:
Input: head = [1,2,3,4]
Output: [1,2,4]
Explanation:
For n = 4, node 2 with value 3 is the middle node, which is marked in red.

Example 3:
Input: head = [2,1]
Output: [2]
Explanation:
The above figure represents the given linked list.
For n = 2, node 1 with value 1 is the middle node, which is marked in red.
Node 0 with value 2 is the only node remaining after removing node 1.
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
    ListNode *deleteMiddle(ListNode *head)
    {
        if (!head || !head->next)
            return NULL; // if 0 or 1 node is present, return NULL

        ListNode *prev = NULL, *slow = head, *fast = head; // Maintain 3 Pointers; prev, slow, fast

        while (fast and fast->next)
        {
            prev = slow;             // update prev = slow
            slow = slow->next;       // increment slow once, slow = slow->next;
            fast = fast->next->next; // increment fast twice, fast = fast->next->next;
        }

        prev->next = slow->next; // update connection
        delete slow;             // deleting middle node
        return head;             // return head;
    }

    // Tortoise Method
    ListNode *deleteMiddle(ListNode *head)
    {
        if (!head || !head->next)
            return NULL;

        // Since we do not access the members within null pointers
        ListNode *slow = new ListNode(0);
        ListNode *fast = slow;

        slow->next = head;
        fast->next = head;

        // Find the middle of the linked list : Tortoise Method
        while (fast->next && fast->next->next)
        {
            slow = slow->next;
            fast = fast->next->next;
        }

        // Delete a node in a Linked list
        slow->next = slow->next->next;

        // return the modified Linked list
        return head;
    }
};
