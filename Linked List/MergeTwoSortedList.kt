/* Merge Two Sorted List
You are given the heads of two sorted linked lists list1 and list2.
Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
Return the head of the merged linked list.

Example 1:
Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]

Example 2:
Input: list1 = [], list2 = []
Output: []

Example 3:
Input: list1 = [], list2 = [0]
Output: [0]
*/
class ListNode(var value: Int) {
    var next: ListNode? = null
}

fun MergeTwoList(list1: ListNode?, list2: ListNode?): ListNode? {
    if (list1 == null && list2 == null) return null
    if (list1 == null) return list2
    if (list2 == null) return list1

    if (list1.value < list2.value) {
        list1.next = MergeTwoList(list1.next, list2)
        return list1
    }

    list2.next = MergeTwoList(list2.next, list1)
    return list2
}
