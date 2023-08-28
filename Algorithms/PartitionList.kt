/*
* Partition List
Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
You should preserve the original relative order of the nodes in each of the two partitions.

Example 1:
Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]

Example 2:
Input: head = [2,1], x = 2
Output: [1,2]
*/

class ListNode(var value:Int) {
    var next: ListNode?= null
}

fun partition(head:ListNode?, x: Int): ListNode? {
    if(head == null) return null

    val leftHead = ListNode(0) // left most node
    val leftTail = leftHead // keeping track pf the last node
    val rightHead = ListNode(0) // right most node
    val rightTail = rightHead
    var pointer = head

    while(pointer != null) {
        if(pointer.value>=x) {
            rightTail.next = pointer // assign the current pointer
            rightTail = rightTail.next // Update right tail with current
        } else {
            leftTail.next = pointer
            leftTail = leftTail.next // Update left tail with current
        }
        pointer = pointer.next // Moving on next pointer
    }
    leftTail.next = rightHead.next // Joining left and right partition
    rightTail.next = null // Marking end as null

    return leftHead.next // head of the partitioned linked list
}