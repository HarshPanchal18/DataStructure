import java.util.*

/*
* Add One Row to Tree - https://leetcode.com/problems/add-one-row-to-tree/
Given the root of a binary tree and two integers val and depth, add a row of nodes with value val at the given depth depth.
Note that the root node is at depth 1.

The adding rule is:
Given the integer depth, for each not null tree node cur at the depth depth - 1, create two tree nodes with value val as cur's left subtree root and right subtree root.
cur's original left subtree should be the left subtree of the new left subtree root.
cur's original right subtree should be the right subtree of the new right subtree root.
If depth == 1 that means there is no depth depth - 1 at all, then create a tree node with value val as the new root of the whole original tree, and the original tree is the new root's left subtree.

Example 1:
Input: root = [4,2,6,3,1,5], val = 1, depth = 2
Output: [4,1,1,2,null,null,6,3,1,5]

Example 2:
Input: root = [4,2,null,3,1], val = 1, depth = 3
Output: [4,2,null,1,1,3,null,null,1]

Constraints:
The number of nodes in the tree is in the range [1, 104].
The depth of the tree is in the range [1, 104].
-100 <= Node.val <= 100
-105 <= val <= 105
1 <= depth <= the depth of tree + 1
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

fun addOneRow(root: TreeNode?, target: Int, targetDepth: Int): TreeNode? {
	return if (targetDepth == 1) {
		TreeNode(target).apply { left = root }
	} else {
		addRow(root, 1, targetDepth, target)
		root
	}
}

private fun addRow(node: TreeNode?, curDepth: Int, targetDepth: Int, target: Int) {
	if (node == null) return

	if (curDepth + 1 == targetDepth) {
		node.left = TreeNode(target).apply { left = node.left }
		node.right = TreeNode(target).apply { right = node.right }
	} else {
		addRow(node.left, curDepth + 1, targetDepth, target)
		addRow(node.right, curDepth + 1, targetDepth, target)
	}
}

class Solution {
	fun addOneRow(root: TreeNode?, `val`: Int, depth: Int): TreeNode? {
		if (depth == 1) {
			val newRoot = TreeNode(`val`)
			newRoot.left = root
			return newRoot
		}

		val queue: Queue<TreeNode?> = LinkedList()
		queue.offer(root)
		var currentDepth = 1

		while (currentDepth < depth - 1) {
			val size = queue.size
			for (i in 0 until size) {
				val node = queue.poll()
				node?.left?.let { queue.offer(it) }
				node?.right?.let { queue.offer(it) }
			}
			currentDepth++
		}

		while (queue.isNotEmpty()) {
			val node = queue.poll() // Retrieve and remove head of the queue

			val leftChild = TreeNode(`val`)
			leftChild.left = node?.left
			node?.left = leftChild

			val rightChild = TreeNode(`val`)
			rightChild.right = node?.right
			node?.right = rightChild
		}

		return root
	}
}

class Solution {
	fun addOneRow(root: TreeNode?, `val`: Int, depth: Int): TreeNode? {
		row = `val`
		return if (depth > 2) {
			addFast(root, depth - 1)
			root
		} else {
			addRow(root, depth - 1, true)
		}
	}

	private fun addFast(root: TreeNode?, depth: Int) {
		if (root != null) {
			if (depth > 1) {
				addFast(root.left, depth - 1)
				addFast(root.right, depth - 1)
			} else {
				addRow(root, depth, true)
			}
		}
	}

	private fun addRow(root: TreeNode?, depth: Int, leftLink: Boolean): TreeNode? = when (depth) {
		1 -> {
			root?.left = addRow(root?.left, 0, true)
			root?.right = addRow(root?.right, 0, false)
			root
		}

		0 -> TreeNode(row).apply {
			if (leftLink) left = root
			else right = root
		}

		else -> root
	}

	companion object {
		var row = 0
	}
}

class Solution {

	fun addOneRow(root: TreeNode?, `val`: Int, depth: Int): TreeNode? {
		if (depth == 1)
			return TreeNode(`val`).apply { left = root }

		fun traverse(root: TreeNode?, `val`: Int, depth: Int, currentDepth: Int = 0) {
			if (root == null)
				return
			if (currentDepth == depth - 1) {
				root.left = TreeNode(`val`).apply { left = root.left }
				root.right = TreeNode(`val`).apply { right = root.right }
				return
			}
			traverse(root.left, `val`, depth, currentDepth + 1)
			traverse(root.right, `val`, depth, currentDepth + 1)
		}

		traverse(root, `val`, depth, 1)
		return root
	}

}

class Solution {
	fun addOneRow(root: TreeNode?, v: Int, depth: Int): TreeNode? =
		if (depth < 2)
			TreeNode(v).apply {
				if (depth < 1) right = root
				else left = root
			}
		else root?.apply {
			left = addOneRow(left, v, depth - 1)
			right = addOneRow(right, v, if (depth < 3) 0 else depth - 1)
		}
}

class Solution {
	fun addOneRow(root: TreeNode?, `val`: Int, depth: Int): TreeNode? = when {
		depth < 2 -> {
			val node = TreeNode(`val`)
			if (depth == 0) node.right = root else node.left = root
			node
		}

		root == null -> null

		depth == 2 -> {
			root.left = addOneRow(root.left, `val`, 1)
			root.right = addOneRow(root.right, `val`, 0)
			root
		}

		else -> {
			root.left = addOneRow(root.left, `val`, depth - 1)
			root.right = addOneRow(root.right, `val`, depth - 1)
			root
		}
	}
}

class Solution {
	fun add(root: TreeNode?, `val`: Int, depth: Int, curr: Int): TreeNode? {
		if (root == null)
			return null

		if (curr == depth - 1) {
			val leftTemp = root.left
			val rightTemp = root.right

			root.left = TreeNode(`val`)
			root.right = TreeNode(`val`)
			root.left!!.left = leftTemp
			root.right!!.right = rightTemp

			return root
		}

		root.left = add(root.left, `val`, depth, curr + 1)
		root.right = add(root.right, `val`, depth, curr + 1)

		return root
	}

	fun addOneRow(root: TreeNode?, `val`: Int, depth: Int): TreeNode? {
		if (depth == 1) {
			val newRoot = TreeNode(`val`)
			newRoot.left = root
			return newRoot
		}

		return add(root, `val`, depth, 1)
	}
}