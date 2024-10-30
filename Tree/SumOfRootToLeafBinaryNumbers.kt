/*
* Sum of Root To Leaf Binary Numbers

You are given the root of a binary tree where each node has a value 0 or 1.
Each root-to-leaf path represents a binary number starting with the most significant bit.
For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.
For all leaves in the tree, consider the numbers represented by the path from the root to that leaf. Return the sum of these numbers.
The test cases are generated so that the answer fits in a 32-bits integer.

Example 1:
Input: root = [1,0,1,0,1,0,1]
Output: 22
Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22

Example 2:
Input: root = [0]
Output: 0

Constraints:
The number of nodes in the tree is in the range [1, 1000].
Node.val is 0 or 1.
*/

class TreeNode(val `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun sumRootToLeaf(root: TreeNode?): Int {
    var res = 0

    fun traverse(node: TreeNode?, curSum: Int) {
        if (node == null) return
        val curSum = (curSum shl 1) or node.`val`
        if (node.left == null && node.right == null) res += curSum
        traverse(node.left, curSum)
        traverse(node.right, curSum)
    }

    traverse(root, 0)
    return res
}

class Solution {
    fun sumRootToLeaf(root: TreeNode?): Int {
        var sum = 0
        if (root == null) return sum

        fun paths(root: TreeNode, path: Int) {
            if (root.left == null && root.right == null) { // we have reached a leaf
                sum += path
                return
            }
            root.left?.let {
                val leftPath = (path shl 1) + it.`val` // shift left the current path and add the left node value
                paths(it, leftPath)
            }
            root.right?.let {
                val rightPath = (path shl 1) + it.`val`
                paths(it, rightPath)
            }
        }

        paths(root, root.`val`)
        return sum
    }

}