/*
* Palindrome Linked List
Given the head of a singly linked list, return true if it is a palindrome or false otherwise.

Example 1:
Input: head = [1,2,2,1]
Output: true

Example 2:
Input: head = [1,2]
Output: false

Constraints:
The number of nodes in the list is in the range [1, 105].
0 <= Node.val <= 9
Follow up: Could you do it in O(n) time and O(1) space?
*/

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    fun reverseLinkedList(head: ListNode?): ListNode? {
        var prev: ListNode? = null
        var nextNode: ListNode?
        var current = head

        while (current != null) {
            nextNode = current.next
            current.next = prev
            prev = current
            current = nextNode
        }

        return prev
    }

    fun isPalindrome(head: ListNode?): Boolean {
        if (head == null || head?.next == null)
            return true

        var fast = head
        var slow = head

        while (fast != null && fast.next != null) {
            fast = fast.next?.next
            slow = slow?.next
        }

        val reverseHead = reverseLinkedList(slow)
        var temp1 = head
        var temp2 = reverseHead

        while (temp2 != null) {
            if (temp1?.`val` != temp2.`val`)
                return false

            temp1 = temp1.next
            temp2 = temp2.next
        }

        return true
    }
}

class Solution {
    fun isPalindrome(head: ListNode?): Boolean =
        sequence { listSequence(head) }
            .zip(sequence { reverseListSequence(head) })
            .all { (a, b) -> a == b }

    private tailrec suspend fun SequenceScope<Int>.listSequence(node: ListNode?) {
        if (node == null) return
        yield(node.`val`)
        listSequence(node.next)
    }

    private suspend fun SequenceScope<Int>.reverseListSequence(node: ListNode?) {
        if (node == null) return
        reverseListSequence(node.next)
        yield(node.`val`)
    }
}

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    /*
    * Complexity: Time O(N) and Space O(1) where N is the length of head;
    */
    fun isPalindrome(head: ListNode?): Boolean {
        var slow = head
        var fast = head

        while (fast?.next != null && fast.next?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }

        fun reverse(node: ListNode?): ListNode? {
            var currHead = node
            while (node?.next != null) {
                val nextNode = node.next
                node.next = nextNode?.next
                nextNode?.next = currHead
                currHead = nextNode
            }
            return currHead
        }

        fast = head

        var reversed = reverse(slow?.next)
        var isPalindrome = true

        while (isPalindrome && reversed != null) {
            isPalindrome = reversed.`val` == fast?.`val`
            reversed = reversed.next
            fast = fast?.next
        }

        reverse(slow?.next)
        return isPalindrome
    }
}

class Solution {
    fun isPalindrome(head: ListNode?): Boolean {
        val sb = StringBuilder()
        var list = head

        while(list != null) {
            sb.append(list.`val`)
            list = list.next
        }

        return sb.toString() == sb.reverse().toString()
    }
}

class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of head;
     */
    fun isPalindrome(head: ListNode?): Boolean {
        return head.toList().isPalindrome()
    }

    private fun ListNode?.toList(): List<Int> = buildList {
        var nodePtr = this@toList

        while (nodePtr != null) {
            add(nodePtr.`val`)
            nodePtr = nodePtr.next
        }
    }

    private fun List<Int>.isPalindrome(): Boolean {
        var left = 0
        var right = lastIndex

        while (left <= right) {
            if (this[left] != this[right])
                return false

            left += 1
            right -= 1
        }

        return true
    }
}