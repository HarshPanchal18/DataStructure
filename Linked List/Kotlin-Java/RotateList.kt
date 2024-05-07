/*
* Rotate List - https://leetcode.com/problems/rotate-list/
Given the head of a linked list, rotate the list to the right by k places.

Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]

Example 2:
Input: head = [0,1,2], k = 4
Output: [2,0,1]

Constraints:
The number of nodes in the list is in the range [0, 500].
-100 <= Node.val <= 100
0 <= k <= 2 * 109
 */

class ListNode(var `val`: Int) {
	var next: ListNode? = null
}

class Harsh {
	fun runner() {

		val node1 = ListNode(2)
		head?.next = node1

		val node2 = ListNode(3)
		node1.next = node2

		val node3 = ListNode(4)
		node2.next = node3

		val node4 = ListNode(5)
		node3.next = node4

		printListNode(head)

		//moveRightToTail()
		//println(right?.`val`)

		head = rightRotation(head)

		//moveRightToTail()
		//print(right?.`val`)

		printListNode(head)

	}

	var head: ListNode? = ListNode(1)
	var left: ListNode? = head

	var right: ListNode? = null

	fun printListNode(head: ListNode?) {
		var curr = head
		while (curr != null) {
			print("${curr.`val`} ")

			if (curr.next == null)
				break

			curr = curr.next!!
		}
		println()
	}

	fun moveRightToTail() {
		while (left != null) {
			right = left
			left = left!!.next
		}
		left = head
	}

	fun rightRotation(head: ListNode?): ListNode? {
		var curr = head

		while (curr?.next?.next != null) {
			curr = curr.next
		}

		left = curr?.next
		left?.next = head
		curr?.next = null

		return left
	}

}

fun rotateRight(head: ListNode?, k: Int): ListNode? {
	if (head?.next == null)
		return head

	var rotated = head

	for (i in 0 until (k.mod(head.size))) {
		var node = rotated
		var previous: ListNode? = null

		while (node != null) {
			if (node.next == null) {
				node.next = rotated
				rotated = node

				previous?.next = null
				break
			}

			previous = node
			node = node.next
		}
	}

	return rotated
}

val ListNode?.size: Int
	get() {
		var size = 0
		var node = this

		while (node != null) {
			node = node.next
			size++
		}

		return size
	}

class Solution {
	fun rotateRight(head: ListNode?, k: Int): ListNode? {
		if (head == null)
			return null

		val size = getSize(head)
		val realK = k % size

		if (realK == 0)
			return head

		val steps = size - realK
		val tail = getKNode(head, steps - 1)
		val newHead = tail?.next

		tail?.next = null

		var newTail: ListNode? = newHead
		while (newTail?.next != null)
			newTail = newTail.next

		newTail?.next = head

		return newHead
	}

	private fun getKNode(head: ListNode?, k: Int): ListNode? {
		var steps = k
		var node: ListNode? = head
		while (steps > 0) {
			steps--
			node = node?.next
		}
		return node
	}

	private fun getSize(head: ListNode?): Int {
		var size = 0
		var node: ListNode? = head
		while (node != null) {
			size++
			node = node.next
		}
		return size
	}
}

class Solution {
	fun rotateRight(head: ListNode?, k: Int): ListNode? {
		// edge case
		if (k == 0)
			return head

		var listLen = 1
		var curr = head

		while (curr?.next != null) { // reach tail
			listLen++
			curr = curr.next
		}

		// check if need to rotate at all
		if (k % listLen == 0)
			return head

		val tail = curr
		var skip = listLen - (k % listLen) // no of nodes to skip
		curr = head

		while (skip > 1) { // reach prev of new head
			curr = curr?.next
			skip--
		}

		val newHead = curr?.next // store new head
		curr?.next = null // turn prev of new head to a tail
		tail?.next = head // attach original tail to original head

		return newHead
	}
}

class Solution {

	fun length(head: ListNode): Int {
		var len = 0
		var runner: ListNode? = head
		while (runner != null) {
			len++
			runner = runner.next
		}
		return len
	}

	fun rotateRight(head: ListNode?, k: Int): ListNode? {
		if (head == null) return null
		if (head.next == null) return head

		val listLength = length(head)
		val rotateTimes = k % listLength
		if (rotateTimes == 0)
			return head

		var newTail = head

		/*
		* Find the new tail
		*   new tail will be at index = len - 1 - k
		* The new head wil be after the new tail
		* 1- connect the current tail to the head
		* 2- Clear the next of new tail
		*
		*/

		for (i in 1..listLength - 1 - rotateTimes) {
			newTail = newTail?.next
		}

		val newHead = newTail?.next
		var currentTail = newHead

		while (currentTail?.next != null)
			currentTail = currentTail.next

		currentTail?.next = head
		newTail?.next = null
		return newHead
	}
}

class Solution {
	fun rotateRight(head: ListNode?, k: Int): ListNode? {

		if (k == 0) return head

		var slowHead: ListNode? = null
		var fastHead: ListNode? = null

		fun moveToEnd(n: Int) {
			slowHead = head
			fastHead = head
			for (i in 1..n) {

				if (fastHead?.next == null) {
					moveToEnd(n % i)
					return
				} else {
					fastHead = fastHead?.next
				}

			}
		}

		moveToEnd(k)

		while (fastHead?.next != null) {
			slowHead = slowHead?.next
			fastHead = fastHead?.next
		}

		fastHead?.next = head

		val newHead = slowHead?.next
		slowHead?.next = null

		return newHead

	}
}