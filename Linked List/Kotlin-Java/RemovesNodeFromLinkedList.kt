import java.util.Stack

/*
* Remove Nodes From Linked List - https://leetcode.com/problems/remove-nodes-from-linked-list/
You are given the head of a linked list.
Remove every node which has a node with a greater value anywhere to the right side of it.
Return the head of the modified linked list.

Example 1:
Input: head = [5,2,13,3,8]
Output: [13,8]
Explanation: The nodes that should be removed are 5, 2 and 3.
- Node 13 is to the right of node 5.
- Node 13 is to the right of node 2.
- Node 8 is to the right of node 3.

Example 2:
Input: head = [1,1,1,1]
Output: [1,1,1,1]
Explanation: Every node has value 1, so no nodes are removed.

Constraints:
The number of the nodes in the given list is in the range [1, 105].
1 <= Node.val <= 105
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

fun removeNodes(head: ListNode?): ListNode? {
	val stack: Stack<ListNode> = Stack()
	var current: ListNode? = head
	while (current != null) {
		stack.add(current)
		current = current.next
	}

	var next = stack.pop()
	while (stack.isNotEmpty()) {
		val node = stack.pop()
		if (node.`val` >= next.`val`) {
			node.next = next
			next = node
		}
	}
	return next
}

class ListNode(var `val`: Int) {
	var next: ListNode? = null
}

class Solution {
	var head1: ListNode? = null

	fun removeNodes(head: ListNode?): ListNode? {

		val stack = Stack<Int>()
		var curr = head

		while (curr != null) {
			while (stack.isNotEmpty() && curr.`val` > stack.peek())
				stack.pop()

			stack.push(curr.`val`)
			curr = curr.next
		}

		while (stack.isNotEmpty())
			addNode(ListNode(stack.pop()))

		return head1
	}

	fun addNode(node: ListNode?) {
		if (head1 == null) {
			head1 = node
		} else {
			node?.next = head1
			head1 = node
		}
	}
}

class Solution {
	fun removeNodes(head: ListNode?): ListNode {

		val list = mutableListOf<ListNode>()
		var head1 = head
		var count = 0

		while (head1 != null) {
			list.add(head1)
			head1 = head1.next
			count++
		}

		val n1 = list.size
		var pre = list[n1 - 2]
		var current = list[n1 - 1]
		var n = n1 - 1

		while (n > 0) {
			if (pre.`val` >= current.`val`) {
				pre.next = current
				current = pre
			}

			n--

			if (n - 1 >= 0)
				pre = list[n - 1]
		}

		return current
	}

}

class Solution {
	fun removeNodes(head: ListNode?): ListNode? {
		if (head == null)
			return null

		return removeNodesHelper(head)
	}

	private fun removeNodesHelper(current: ListNode?): ListNode? {
		if (current == null)
			return null

		// Recursively check the next nodes
		current.next = removeNodesHelper(current.next)

		// If the current node has a value less than the next node, skip it
		if (current.next != null && current.`val` < current.next!!.`val`)
			return current.next

		return current
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
	fun removeNodes(head: ListNode?, maxValue: Int = 0): ListNode? {
		val seen = mutableListOf<Int>()
		var current = head

		while (current != null) {
			seen.add(current.`val`)
			current = current.next
		}

		var maxSeen = -1
		var k = seen.size - 1
		while (k >= 0) {
			if (maxSeen > seen[k])
				seen[k] = maxSeen
			else
				maxSeen = seen[k]

			k--
		}

		val newHead = ListNode(0)
		newHead.next = head
		current = head
		var previous = newHead

		var y = 0
		while (current != null) {

			if (seen[y] > current.`val`)
				previous.next = current.next
			else
				previous = current

			current = current.next
			y++
		}

		return newHead.next
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

	var stack = Stack<ListNode?>()
	var max = Int.MIN_VALUE
	fun removeNodes(head: ListNode?): ListNode? {

		var result = ListNode(-1)
		val tmp = result

		traverse(head)

		while (stack.isNotEmpty()) {
			result.next = stack.pop()
			result = result.next!!
		}

		return tmp.next
	}

	fun traverse(curr: ListNode?): Int {
		if (curr?.next == null)
			return curr!!.`val`

		var largest = traverse(curr.next)

		if (largest > max) {
			max = largest
			stack.push(curr.next)
		}

		if (curr.`val` == largest)
			stack.push(curr)

		if (curr.`val` > largest)
			largest = curr.`val`

		return largest
	}
}