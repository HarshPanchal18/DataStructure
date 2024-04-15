/*
* Sum of Left Leaves
Given the root of a binary tree, return the sum of all left leaves.
A leaf is a node with no children. A left leaf is a leaf that is the left child of another node.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 24
Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.

Example 2:
Input: root = [1]
Output: 0

Constraints:
The number of nodes in the tree is in the range [1, 1000].
-1000 <= Node.val <= 1000
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

fun sumOfLeftLeaves(root: TreeNode?): Int {
	// if (root == null) return 0
	root ?: return 0

	var sum = 0
	if (root.left != null)
		if (root.left?.left == null && root.left?.right == null)
			sum += root.left!!.`val`

	return sum + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right)
}

class Solution {
	var sum = 0
	fun sumOfLeftLeaves(root: TreeNode?): Int {
		if (root == null) return 0

		if (root.left != null && root.left?.left == null && root.left?.right == null)
			sum += root.left!!.`val`

		sumOfLeftLeaves(root.left)
		sumOfLeftLeaves(root.right)

		return sum
	}
}

class Solution {
	fun sumOfLeftLeaves(root: TreeNode?): Int {
		if (root == null) return 0

		var sum = 0

		if (root.left != null && root.left.isLeave()) {
			sum += root.left!!.`val`
		}

		sum += sumOfLeftLeaves(root.left)
		sum += sumOfLeftLeaves(root.right)

		return sum
	}

	fun TreeNode?.isLeave(): Boolean {
		return this?.left == null && this?.right == null
	}
}

class Solution {
	var sum = 0
	fun sumOfLeftLeaves(root: TreeNode?): Int {
		if (root == null) return 0
		dfs(root)
		return sum
	}

	private fun dfs(root: TreeNode?) {
		if (root == null) return
		if (root.left != null && isLeaf(root.left!!)) {
			sum += root.left!!.`val`
		}

		dfs(root.left)
		dfs(root.right)
	}

	private fun isLeaf(node: TreeNode) = node.left == null && node.right == null
}

class Solution {

	fun sumOfLeftLeaves(root: TreeNode?): Int {
		return root?.let { dfs(it, false) } ?: 0
	}

	private fun dfs(node: TreeNode, isLeft: Boolean): Int {
		if (node.left == null && node.right == null)
			return if (isLeft) node.`val` else 0

		var sum = 0
		node.left?.let { sum += dfs(it, true) }
		node.right?.let { sum += dfs(it, false) }
		return sum
	}
}

class Solution {
	fun sumOfLeftLeaves(root: TreeNode?): Int {
		return bst(root, false)
	}

	fun bst(node: TreeNode?, isLeft: Boolean): Int {
		if (node == null) return 0

		if (node.left == null && node.right == null)
			return if (isLeft) node.`val` else 0

		return bst(node.left, true) + bst(node.right, false)
	}
}