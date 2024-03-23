#include <iostream>
using namespace std;

/*
* Convert Binary Number in a Linked List to Integer

Given head which is a reference node to a singly-linked list.
The value of each node in the linked list is either 0 or 1.
The linked list holds the binary representation of a number.

Return the decimal value of the number in the linked list.

The most significant bit is at the head of the linked list.

Example 1:
Input: head = [1,0,1]
Output: 5
Explanation: (101) in base 2 = (5) in base 10

Example 2:
Input: head = [0]
Output: 0
*/

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
    // Multiplying each index by increasing pow(2) and keep adding the result to form the desired Decimal answer
    int getDecimalValue(ListNode *head)
    {
        string num;
        while (head != NULL)
        {
            num += to_string(head->val);
            head = head->next;
        }
        int res = 0, pv = 1;
        for (int i = num.size() - 1; i >= 0; i--)
        {
            res += pv * (stoi(num.substr(i, 1)));
            pv *= 2;
        }
        return res;
    }
};

class Solution
{
public:
    int getDecimalValue(ListNode *head)
    {
        int res = 0;
        while (head != NULL)
        {
            res = res * 2 + head->val;
            head = head->next;
        }
        return res;
    }

  // With Bitwise
  int getDecimalValue(ListNode *head)
    {
        int res = 0;
        ListNode *iter = head;
        while (iter != NULL)
        {
            res <<= 1;
            res |= iter->val;
            iter = iter->next;
        }
        return res;
    }
    /*
    visualization:
    our sum = 0 And we are gonna do 2 steps
    sum * 2 = sum(left shift <<)
    sum + 1 or 0 = sum
    And will run a loop over these 2 steps until head becomes NULL.....
    For example => Binary number => 10110

    left shift 1 = > (0 * 2) = 0
    After sum = > 0 + 1 = 1
    left shift 1 = > (1 * 2) = 2
    After sum = > 2 + 0 = 2
    left shift 1 = > (2 * 2) = 4
    After sum = > 4 + 1 = 5
    left shift 1 = > (5 * 2) = 10
    After sum = > 10 + 1 = 11
    left shift 1 = > (11 * 2) = 22
    After sum = > 22 + 0 = 22
    */

    // Recursion
    int getDecimalValue(ListNode *head, int res = 0)
    {
        return head ? getDecimalValue(head->next, res * 2 + head->val) : res;
    }
    
    // Through String
    int getDecimalValue(ListNode *head)
    {
        string s = "";
        while (head != NULL)
        {
            s += to_string(head->val);
            head = head->next;
        }

        return stoi(s, 0, 2); // stoi(target_string,starting_index,base)
    }

    int getDecimalValue(ListNode *head)
    {
        int result = head->val;
        while (head->next)
        {
            result = result * 2 + head->next->val;
            head = head->next;
        }
        return result;
    }

    int getDecimalValue(ListNode *head)
    {
        int dec = head->val;
        while (head = head->next)
            dec = dec * 2 + head->val;
        return dec; // Time Complexity = O(N) & Space Complexity = O(1)
    }
};
