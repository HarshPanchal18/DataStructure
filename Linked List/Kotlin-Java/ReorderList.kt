import java.util.Stack

/*
* Reorder List

You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.

Example 1:
Input: head = [1,2,3,4]
Output: [1,4,2,3]

Example 2:
Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]

Constraints:
The number of nodes in the list is in the range [1, 5 * 104].
1 <= Node.val <= 1000
*/

class ListNode(var `val`: Int) {
	var next: ListNode? = null
}

fun main() {
}

fun reorderList(head: ListNode?): Unit {
	var slow = head
	var fast = slow

	// Finding the middle
	while (fast?.next != null) {
		fast = fast.next?.next
		slow = slow?.next
	}

	// Reversing the second part of the list
	fast = null
	while (slow != null) {
		val next = slow.next
		slow.next = fast
		fast = slow
		slow = next
	}

	// Merging two lists
	slow = head
	while (slow != null) {
		val next = slow.next
		slow.next = fast
		slow = fast
		fast = next
	}
}

class Solution {
	fun reorderList(head: ListNode?): Unit {
		val stack = Stack<ListNode>()
		var currentCountNode = head
		while (currentCountNode != null) {
			stack.push(currentCountNode)
			currentCountNode = currentCountNode.next
		}

		var count = stack.size

		val fakeHeadNode = ListNode(-1)
		fakeHeadNode.next = head

		var curNode = fakeHeadNode.next

		while (count > 1) {
			val nextNode = curNode!!.next
			val resortNode = stack.pop()
			curNode.next = resortNode
			resortNode.next = nextNode
			curNode = nextNode!!

			count -= 2
		}

		if (count == 1) {
			curNode!!.next = stack.pop()
			curNode = curNode.next
		}
		curNode?.next = null

		head?.next = fakeHeadNode.next?.next
	}
}

class Solution {

	// O(n)
	// O(n)
	fun reorderList(head: ListNode?): Unit {
		var takeFromLeft: Boolean = true
		var leftHead: ListNode? = head
		var (size: Int, rightHead: ListNode?) = head.reversed()
		var tail: ListNode? = null

		for (i in 0 until size) {
			if (takeFromLeft) {
				val tmp = leftHead!!.next
				leftHead.next = rightHead
				tail = leftHead
				leftHead = tmp
			} else {
				val tmp = rightHead!!.next
				rightHead.next = leftHead
				tail = rightHead
				rightHead = tmp
			}
			takeFromLeft = !takeFromLeft
		}

		tail?.next = null
	}

	private fun ListNode?.reversed(): Pair<Int, ListNode?> {
		var reversedHead: ListNode? = null
		var head: ListNode? = this
		var size = 0
		while (head != null) {
			val copy = ListNode(head.`val`)
			copy.next = reversedHead
			reversedHead = copy

			head = head.next
			size++
		}
		return size to reversedHead
	}
}

class Solution {
	fun reorderList(head: ListNode?): Unit {

		if (head == null) {
			return
		}

		var slowPtr: ListNode = head
		var fastPtr: ListNode? = head
		var counter: Int = 0

		while (fastPtr != null && fastPtr.next != null) {
			fastPtr = fastPtr.next
			slowPtr = if (counter % 2 == 1) slowPtr.next else slowPtr
			counter++
		}

		val midpoint: ListNode = slowPtr

		// println(midpoint.`val`)

		// Split the list into two halves
		val list1: ListNode? = head
		val list2: ListNode? = reverseList(midpoint.next)

		midpoint.next = null        // Null-terminate list1

		printList(list1)
		println("")
		printList(list2)

		var ptr1: ListNode? = list1
		var ptr2: ListNode? = list2

		counter = 0

		while (ptr1 != null && ptr2 != null) {
			if (counter % 2 == 0) {
				val temp: ListNode? = ptr1.next
				ptr1.next = ptr2
				ptr1 = temp
			} else {
				val temp: ListNode? = ptr2.next
				ptr2.next = ptr1
				ptr2 = temp
			}

			counter++
		}
	}

	fun printList(head: ListNode?): Unit {
		var ptr: ListNode? = head
		while (ptr != null) {
			print(ptr.`val`)
			print(" -> ")
			ptr = ptr.next
		}
	}

	fun reverseList(head: ListNode?): ListNode? {
		var ptr1: ListNode? = null
		var ptr2: ListNode? = head

		while (ptr2 != null) {
			val temp: ListNode? = ptr2.next
			ptr2.next = ptr1

			ptr1 = ptr2
			ptr2 = temp
		}

		return ptr1
	}
}

class Solution {
	fun reorderList(head: ListNode?): Unit {
		if (head == null || head.next == null) return
		val mid = findMiddle(head)
		val rev = reverseList(null, mid?.next)
		mid?.next = null
		mergeList(head, rev)
	}

	private fun findMiddle(head: ListNode?): ListNode? {
		if (head == null) return head
		var slow = head
		var fast = head

		while (fast != null && fast.next != null) {
			slow = slow!!.next
			fast = fast.next?.next
		}
		return slow
	}

	private fun mergeList(list1: ListNode?, list2: ListNode?): ListNode? {
		//alternate between list1 and list2
		val pHead = ListNode(Int.MIN_VALUE)
		var cur = pHead
		var l1 = list1
		var l2 = list2
		var pickL1 = true

		while (l1 != null && l2 != null) {
			if (pickL1) {
				cur.next = l1
				l1 = l1.next
			} else {
				cur.next = l2
				l2 = l2.next
			}
			pickL1 = !pickL1
			cur = cur.next!!
		}
		if (l1 != null) cur.next = l1
		if (l2 != null) cur.next = l2
		return pHead.next
	}

	private fun reverseList(prev: ListNode?, head: ListNode?): ListNode? {
		if (head == null) return head
		if (head.next == null) {
			head.next = prev
			return head
		}
		val rHead = reverseList(head, head.next)
		head.next = prev
		return rHead
	}

}

class Solution {
	fun reorderList(head: ListNode?): Unit {
		var slow = head
		var fast = head?.next
		while (fast != null && fast?.next != null) {
			fast = fast.next?.next
			slow = slow?.next
		}
		fast = head
		val second = slow?.next
		slow?.next = null
		slow = reverseList(second)


		while (slow != null) {
			val slowNext = slow.next
			val fastNext = fast?.next
			fast?.next = slow
			slow.next = fastNext
			slow = slowNext
			fast = fastNext
		}

	}

	fun reverseList(head: ListNode?): ListNode? {
		var prev: ListNode? = null
		var cur = head
		var next: ListNode?
		while (cur != null) {
			next = cur.next
			cur.next = prev
			prev = cur
			cur = next
		}
		return prev
	}
}

class Solution {
	fun reorderList(head: ListNode?): Unit {
		//Dive into two part, reverse right part, then merge two list
		if (head?.next == null) {
			return
		}

		var slowNode = head
		var fastNode = head.next

		while (fastNode?.next != null) {
			slowNode = slowNode!!.next
			fastNode = fastNode.next?.next
		}

		val rightHeader = slowNode!!.next
		slowNode.next = null

		val reverseRightHeader = reverseList(rightHeader!!, null)

		var curLeftNode = head
		var curRightNode: ListNode? = reverseRightHeader

		while (curRightNode != null) {
			val leftNext = curLeftNode!!.next
			val rightNext = curRightNode.next

			curLeftNode.next = curRightNode
			curRightNode.next = leftNext

			curLeftNode = leftNext
			curRightNode = rightNext
		}
	}

	private fun reverseList(curNode: ListNode, preNode: ListNode?): ListNode {
		val nextNode = curNode.next
		curNode.next = preNode

		return if (nextNode == null) curNode else reverseList(nextNode, curNode)
	}
}