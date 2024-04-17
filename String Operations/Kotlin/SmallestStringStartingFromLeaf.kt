import java.util.*

/*
* Smallest String Starting From Leaf - https://leetcode.com/problems/smallest-string-starting-from-leaf/
You are given the root of a binary tree where each node has a value in the range [0, 25] representing the letters 'a' to 'z'.
Return the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
As a reminder, any shorter prefix of a string is lexicographically smaller.
For example, "ab" is lexicographically smaller than "aba".
A leaf of a node is a node that has no children.

Example 1:
Input: root = [0,1,2,3,4,3,4]
Output: "dba"

Example 2:
Input: root = [25,1,3,1,3,0,2]
Output: "adz"
Example 3:
Input: root = [2,2,1,null,1,0,null,0]
Output: "abc"

Constraints:
The number of nodes in the tree is in the range [1, 8500].
0 <= Node.val <= 25
*/

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * */

class TreeNode(var `val`: Int) {
	var left: TreeNode? = null
	var right: TreeNode? = null
}

fun smallestFromLeaf(root: TreeNode?): String {
	return smallString(root, StringBuilder()).toString()
}

/*
This approach recursively traverses the binary tree from root to leaf, constructing the string representation along the way.
At each step, it compares the strings obtained from the left and right subtrees and returns the smaller one.
Finally, it returns the smallest string formed from a leaf to the root of the tree.
*/

fun smallString(root: TreeNode?, stringBuilder: StringBuilder): StringBuilder? {
	root ?: return stringBuilder

	stringBuilder.append('a' + root.`val`) // Append the current value

	// Check the leaf node
	// Return answer in reversed order to get the string from leaf to root.
	if (root.left == null && root.right == null)
		return stringBuilder.reverse()

	// Traverse left and right children
	val left = if (root.left != null) smallString(root.left, StringBuilder(stringBuilder)) else null
	val right = if (root.right != null) smallString(root.right, StringBuilder(stringBuilder)) else null

	return if (left != null && right != null) {
		// Compare left and right strings and return the smaller one
		if (left.toString() < right.toString()) left
		else right
	} else {
		left ?: right // Return left if itself is not null, otherwise right
	}
}

class Solution {
	// 1. DFS preorder with signature dfs(word, node)
	// 2. base case is when node is leaf (i.e. no left or right)
	// then check that word is smallest and set that to output i.e. min(output, word)
	// 3. otherwise recurse through left and right, i.e. dfs('a'+node.`val`+word, node.left) and right
	var output: String = ""

	fun smallestFromLeaf(root: TreeNode?): String {
		if (root == null) return ""
		dfs(root, output)
		return output
	}

	fun dfs(node: TreeNode, curr: String) {
		val newCurr = ('a' + node.`val`) + curr

		if (node.left == null && node.right == null) {
			output = if (output.isEmpty() || newCurr < output) newCurr else output
			return
		}

		if (node.left != null) dfs(node.left!!, newCurr)
		if (node.right != null) dfs(node.right!!, newCurr)
	}
}

class Solution {

	var smallest = ""

	fun smallestFromLeaf(root: TreeNode?): String {
		if (root == null) {
			return ""
		}
		traverse(root, "")
		return smallest
	}

	fun traverse(node: TreeNode?, curr: String) {
		val str = ('a' + node!!.`val`) + curr

		var visited = false
		if (node.left != null) {
			traverse(node.left, str)
			visited = true
		}
		if (node.right != null) {
			traverse(node.right, str)
			visited = true
		}

		if (!visited) {
			verifySmallestString(str)
		}
	}

	fun verifySmallestString(str: String) {
		if (smallest == "") {
			smallest = str
			return
		}

		var i = 0
		var found = false
		while (i < smallest.length && i < str.length) {
			if (smallest[i] < str[i]) {
				found = true
				break
			} else if (str[i] < smallest[i]) {
				found = true
				smallest = str
			}
			i++
		}

		if (!found && str.length < smallest.length) {
			smallest = str
		}
	}
}

class Solution {
	fun smallestFromLeaf(root: TreeNode?): String {
		fun dfs(n: TreeNode?, s: String): String {
			if (n == null) return s
			val s = ('a'.code + n.`val`).toChar() + s

			if (n.left == null && n.right == null)
				return s

			if (n.left == null || n.right == null)
				return dfs(
					if (n.left == null) n.right
					else n.left, s
				)

			return (dfs(n.left, s) to dfs(n.right, s)).let { (l, r) -> if (l <= r) l else r }
		}
		return dfs(root, "")
	}
}

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
	// 1. DFS preorder with signature dfs(word, node)
	// 2. base case is when node is leaf (i.e. no left or right)
	// then check that word is smallest and set that to output i.e. min(output, word)
	// 3. otherwise recurse through left and right, i.e. dfs('a'+node.`val`+word, node.left) and right
	var output: String = ""

	fun smallestFromLeaf(root: TreeNode?): String {
		if (root == null) return ""
		dfs(root, output)
		return output
	}

	fun dfs(node: TreeNode, curr: String) {
		val newCurr = ('a' + node.`val`) + curr

		if (node.left == null && node.right == null) {
			output = if (output.isEmpty() || newCurr < output) newCurr else output
			return
		}

		if (node.left != null) dfs(node.left!!, newCurr)
		if (node.right != null) dfs(node.right!!, newCurr)
	}
}

class Solution {

	fun smallestFromLeaf(root: TreeNode?): String {

		data class TreeWord(
			val node: TreeNode,
			val word: String
		)

		val q: Queue<TreeWord> = LinkedList()
		q.offer(TreeWord(root!!, ""))

		var result = "#"

		while (q.isNotEmpty()) {

			val (curr, word) = q.poll()
			val newWord = ('a' + curr.`val`) + word

			if (curr.left == null && curr.right == null)
				result = min(result, newWord)
			else {
				curr.left?.let { q.offer(TreeWord(it, newWord)) }
				curr.right?.let { q.offer(TreeWord(it, newWord)) }
			}
		}

		return result
	}

	fun min(str1: String, str2: String) = if (str1 == "#" || str1 > str2) str2 else str1

}