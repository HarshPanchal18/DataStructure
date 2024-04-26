/*
* Sum Root to Leaf Numbers
You are given the root of a binary tree containing digits from 0 to 9 only.
Each root-to-leaf path in the tree represents a number.
For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
A leaf node is a node with no children.

Example 1:
Input: root = [1,2,3]
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.

Example 2:
Input: root = [4,9,0,5,1]
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.

Constraints:
The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 9
The depth of the tree will not exceed 10.
 */
/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 **/
class TreeNode(var `val`: Int) {
	var left: TreeNode? = null
	var right: TreeNode? = null
}

class Solution {
	fun sumNumbers(root: TreeNode?) = recSum(root, 0)

	private fun recSum(node: TreeNode?, current: Int): Int {
		node ?: return 0

		val next = node.`val` + (current * 10)

		return if (node.left == null && node.right == null) next
		else recSum(node.left, next) + recSum(node.right, next)
	}
}

class Solution {

	fun sumNumbers(root: TreeNode?): Int {
		sum = 0
		root?.let { sum(it, 0) }
		return sum
	}

	private fun sum(node: TreeNode, number: Int) {
		node.`val` += number * 10
		when {
			node.left == null && node.right == null -> {
				sum += node.`val`
			}

			else -> {
				node.left?.let { sum(it, node.`val`) }
				node.right?.let { sum(it, node.`val`) }
			}
		}
	}

	companion object {
		var sum = 0
	}
}

class Solution {
	fun sumNumbers(root: TreeNode?): Int {
		val rootToLeafList = mutableListOf<Int>()

		traverseTree(root, 0, rootToLeafList)

		return rootToLeafList.reduce { curr, acc -> curr + acc }
	}

	fun traverseTree(node: TreeNode?, number: Int, rootToLeafList: MutableList<Int>) {
		if (node == null) return

		val newNumber = number * 10 + node.`val`

		if (node.left == null && node.right == null) {
			rootToLeafList.add(newNumber)
		} else {
			traverseTree(node.left, newNumber, rootToLeafList)
			traverseTree(node.right, newNumber, rootToLeafList)
		}
	}
}

class Solution {
	fun sumNumbers(root: TreeNode?): Int {
		root ?: return 0
		val list = getList(root)
		return list.sumOf { it.toInt() }

	}

	private fun getList(root: TreeNode?): List<String> {
		root ?: return emptyList()

		if (root.hasNoChild())
			return listOf(root.`val`.toString())

		val left = getList(root.left)
		val right = getList(root.right)

		return listOf(left, right).flatten()
			.map { root.`val`.toString() + it }
	}

	fun TreeNode.hasNoChild(): Boolean {
		return this.left == null || this.right == null
	}
}

class Solution {

	private val numbers = mutableListOf<Int>()

	fun sumNumbers(root: TreeNode?): Int {
		getNumbers(root, "")
		return numbers.sum()
	}

	private fun getNumbers(root: TreeNode?, numberString: String) {
		root ?: return

		if (root.left == null && root.right == null) {
			val number = (numberString + root.`val`).toInt()
			numbers.add(number)
			return
		}

		getNumbers(root.left, numberString + root.`val`)
		getNumbers(root.right, numberString + root.`val`)
	}
}

class Solution {
	fun sumNumbers(root: TreeNode?): Int {
		return if (root == null) 0
		else if (root.left == null && root.right == null) root.`val`
		else {
			val left = root.left?.also { it.`val` += root.`val` * 10 }.let { sumNumbers(it) }
			val right = root.right?.also { it.`val` += root.`val` * 10 }.let { sumNumbers(it) }
			left + right
		}
	}
}