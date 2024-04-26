/*
* Range Sum of BST https://leetcode.com/problems/range-sum-of-bst/
Given the root node of a binary search tree and two integers low and high,
return the sum of values of all nodes with a value in the inclusive range [low, high].

Example 1:
Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
Output: 32
Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.

Example 2:
Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
Output: 23
Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.

Constraints:
The number of nodes in the tree is in the range [1, 2 * 104].
1 <= Node.val <= 105
1 <= low <= high <= 105
All Node.val are unique.
*/

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

fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
    return root?.let {
        rangeSumBST(it.left, low, high) +
        rangeSumBST(it.right, low, high) +
        if(it.`val` < low || it.`val` > high) 0
        else it.`val`
    } ?: 0
}

fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
    if (root == null) return 0

    val v = root.`val`
    return when {
        v < low -> rangeSumBST(root.right, low, high)
        v > high -> rangeSumBST(root.left, low, high)
        else -> v +
            rangeSumBST(root.left, low, high) +
            rangeSumBST(root.right, low, high)
    }
}

fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
    var sum = 0

    fun dfs(root: TreeNode?) {
        root ?: return

        if (root.`val` in low..high) sum += root.`val`

        dfs(root.left)
        dfs(root.right)
    }
    dfs(root)

    return sum
}


fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
    return walk(root, low, high, 0)
}

private fun walk(node: TreeNode?, low: Int, high: Int, sumSoFar: Int): Int {
    if (node == null) return -1

    val current = node.`val`

    if (current < low) {
        return walk(node.right, low, high, sumSoFar)
    } else if (current > high) {
        return walk(node.left, low, high, sumSoFar)
    } else {
        val left = walk(node.left, low, high, sumSoFar)
        val right = walk(node.right, low, high, sumSoFar)
        var result = sumSoFar
        if (left != -1) {
            result += left
        }
        if (right != -1) {
            result += right
        }
        return result + current
    }
}


fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int = mutableListOf(root!!).let { st ->
        generateSequence(0) { _ ->
                val n = st.removeLast()
                n.left != null && st.add(n.left!!)
                n.right != null && st.add(n.right!!)
                if (n.`val` in low..high) n.`val` else 0
        }.takeWhile { st.isNotEmpty() }.sum()
    }

tailrec fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
    root ?: return 0
    val l = if (root.`val` > low)
        rangeSumBST(root.left, low, high)
    else
        0
    val r = if (root.`val` < high)
        rangeSumBST(root.right, low, high)
    else
        0
    val d = if (root.`val` in low..high)
        root.`val`
    else
        0
    return l + r + d
}

class Solution {
    fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
        if (root == null) return 0

        var result = 0

        queue.add(root)

        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            val value = node.`val`

            if (value >= low && value <= high) result += value
            if (value > low) node.left?.let { queue.addLast(it) }
            if (value < high) node.right?.let { queue.addLast(it) }
        }

        return result
    }

    companion object { val queue = LinkedList<TreeNode>() }
}

fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
    var result = 0

    var index = 0
    array[index++] = root ?: empty

    while (index > 0) {
        val node = array[--index]
        result += if (node.`val` in low..high) node.`val` else 0
        if (node.`val` > low) node.left?.let { array[index++] = it }
        if (node.`val` < high) node.right?.let { array[index++] = it }
    }

    return result
}

companion object {
    val empty = TreeNode(0)
    val array = Array<TreeNode>(500) { empty }
}

class Solution {
    tailrec fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
        root ?: return 0
        val sum = if (root.`val` in low..high) root.`val` else 0
        return sum + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high)
    }
}